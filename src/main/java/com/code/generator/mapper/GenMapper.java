package com.code.generator.mapper;


import com.code.generator.domain.ColumnInfo;
import com.code.generator.domain.TableInfo;

import java.util.List;

/**
 * 代码生成 数据层
 *
 * @author azhong
 */
public interface GenMapper {
    /**
     * 查询数据库表信息
     *
     * @param tableInfo 表信息
     * @return 数据库表列表
     */
    public List<TableInfo> selectTableList(TableInfo tableInfo);

    /**
     * 根据表名称查询信息
     *
     * @param tableName 表名称
     * @return 表信息
     */
    public TableInfo selectTableByName(String tableName);

    /**
     * 根据表名称查询列信息
     *
     * @param tableName 表名称
     * @return 列信息
     */
    public List<ColumnInfo> selectTableColumnsByName(String tableName);
}
