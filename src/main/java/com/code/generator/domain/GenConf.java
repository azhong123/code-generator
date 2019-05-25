package com.code.generator.domain;

/**
 * 自动化生成代码配置类
 * author: chenxizhong
 * Date:2019/5/2416:34
 */
public class GenConf {

    /**
     * 作者名
     */
    private String author;

    /**
     * 包路径
     */
    private String packageName;

    /**
     * 表前缀是否过滤  0 是, 1 否
     */
    private String autoRemovePre;


    /**
     * 表前缀名
     */
    private String tablePrefix;

    /**
     * 选择持久层框架 0 MyBatis ,1 JPA
     */
    private String drone;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getAutoRemovePre() {
        return autoRemovePre;
    }

    public void setAutoRemovePre(String autoRemovePre) {
        this.autoRemovePre = autoRemovePre;
    }

    public String getTablePrefix() {
        return tablePrefix;
    }

    public void setTablePrefix(String tablePrefix) {
        this.tablePrefix = tablePrefix;
    }

    public String getDrone() {
        return drone;
    }

    public void setDrone(String drone) {
        this.drone = drone;
    }
}
