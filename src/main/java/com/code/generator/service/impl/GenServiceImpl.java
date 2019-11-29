package com.code.generator.service.impl;

import com.code.generator.common.constant.Constants;
import com.code.generator.common.utils.StringUtils;
import com.code.generator.domain.ColumnInfo;
import com.code.generator.domain.GenConf;
import com.code.generator.domain.TableInfo;
import com.code.generator.mapper.GenMapper;
import com.code.generator.service.IGenService;
import com.code.generator.util.GenUtils;
import com.code.generator.util.VelocityInitializer;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 代码生成 服务层处理
 *
 * @author azhong
 */
@Service
public class GenServiceImpl implements IGenService {
    private static final Logger log = LoggerFactory.getLogger(GenServiceImpl.class);

    @Autowired
    private GenMapper genMapper;

    /**
     * 查询数据库表信息
     *
     * @param tableInfo 表信息
     * @return 数据库表列表
     */
    @Override
    public List<TableInfo> selectTableList(TableInfo tableInfo) {
        return genMapper.selectTableList(tableInfo);
    }

    /**
     * 生成代码
     *
     * @param tableName 表名称
     * @param genConf   配置属性
     * @return 数据
     */
    @Override
    public byte[] generatorCode(String tableName, GenConf genConf) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        // 查询表信息
        TableInfo table = genMapper.selectTableByName(tableName);
        // 查询列信息
        List<ColumnInfo> columns = genMapper.selectTableColumnsByName(tableName);
        // 生成代码
        generatorCode(table, columns, zip, genConf);
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }

    /**
     * 批量生成代码
     *
     * @param tableNames 表数组
     * @param genConf    配置属性
     * @return 数据
     */
    @Override
    public byte[] generatorCode(String[] tableNames, GenConf genConf) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        for (String tableName : tableNames) {
            // 查询表信息
            TableInfo table = genMapper.selectTableByName(tableName);
            // 查询列信息
            List<ColumnInfo> columns = genMapper.selectTableColumnsByName(tableName);
            // 生成代码
            generatorCode(table, columns, zip, genConf);
        }
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }

    /**
     * 生成代码
     * 数据库主键 默认第一个
     *
     * @param table   数据表信息
     * @param columns 数据库字段
     * @param zip
     * @param genConf
     */
    public void generatorCode(TableInfo table, List<ColumnInfo> columns, ZipOutputStream zip, GenConf genConf) {
        // 表名转换成Java属性名
        String className = GenUtils.tableToJava(table.getTableName(), genConf);
        table.setClassName(className);
        table.setClassname(StringUtils.uncapitalize(className));
        // 列信息
        table.setColumns(GenUtils.transColums(columns));
        // 设置主键
        table.setPrimaryKey(table.getColumnsLast());
        if (genConf.getDrone().equals("0")) {
            generatorCodeByMyBatis(table, zip, genConf);
        } else {
            generatorCodeByJpa(table, zip, genConf);
        }
    }

    /**
     * 生成代码  持久层使用 mybatis
     *
     * @param table   数据库表信息
     * @param zip
     * @param genConf 自定义配置
     */
    private void generatorCodeByMyBatis(TableInfo table, ZipOutputStream zip, GenConf genConf) {
        VelocityInitializer.initVelocity();

        String packageName = genConf.getPackageName();
        String moduleName = GenUtils.getModuleName(packageName);
        genConf.setPackageName(GenUtils.getBasePackage(packageName));

        VelocityContext context = GenUtils.getVelocityContext(table, genConf);

        // 获取模板列表
        List<String> templates = GenUtils.getTemplatesByMySql();

        // 渲染模板
        renderTemplates(table, templates, moduleName, genConf, context, zip);
    }


    /**
     * 生成代码 持久层 使用 jpa
     *
     * @param table   数据库表信息
     * @param zip
     * @param genConf 自定义配置
     */
    private void generatorCodeByJpa(TableInfo table, ZipOutputStream zip, GenConf genConf) {
        VelocityInitializer.initVelocity();

        String packageName = genConf.getPackageName();
        String moduleName = GenUtils.getModuleName(packageName);

        VelocityContext context = GenUtils.getVelocityContext(table, genConf);
        // 获取模板列表
        List<String> templates = GenUtils.getTemplatesByJpa();

        // 渲染模板
        renderTemplates(table, templates, moduleName, genConf, context, zip);
    }

    /**
     * 渲染模板
     *
     * @param table      表信息
     * @param templates  生成模板信息
     * @param moduleName 模板名
     * @param genConf    自定义配置
     * @param context
     * @param zip
     */
    private void renderTemplates(TableInfo table, List<String> templates, String moduleName, GenConf genConf, VelocityContext context, ZipOutputStream zip) {
        for (String template : templates) {
            // 渲染模板
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, Constants.UTF8);
            tpl.merge(context, sw);
            try {
                // 添加到zip
                zip.putNextEntry(new ZipEntry(GenUtils.getFileName(template, table, genConf, moduleName)));
                IOUtils.write(sw.toString(), zip, Constants.UTF8);
                IOUtils.closeQuietly(sw);
                zip.closeEntry();
            } catch (IOException e) {
                log.error("渲染模板失败，表名：" + table.getTableName(), e);
            }
        }
    }
}
