package com.code.generator.service;


import com.code.generator.domain.GenConf;
import com.code.generator.domain.TableInfo;

import java.util.List;

/**
 * 代码生成 服务层
 *
 * @author azhong
 */
public interface IGenService {


    /**
     * 查询数据库表信息
     *
     * @param tableInfo 表信息
     * @return 数据库表列表
     */
    public List<TableInfo> selectTableList(TableInfo tableInfo);

    /**
     * 生成代码
     *
     * @param tableName 表名称
     * @param genConf   配置属性
     * @return 数据
     */
    public byte[] generatorCode(String tableName, GenConf genConf);

    /**
     * 批量生成代码
     *
     * @param tableNames 表数组
     * @param genConf    配置属性
     * @return 数据
     */
    public byte[] generatorCode(String[] tableNames, GenConf genConf);
}
