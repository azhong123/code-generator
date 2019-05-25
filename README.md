## 平台简介

一直想做一款可以偷懒的项目，看了很多优秀的开源项目发现都挺好，就是没定制的，譬如我既想生成的代码数据操作层 使用 MyBatis 的同时我可能也需要使用JPA 的，所以我就整合并开发的一套自动生成代码项目，可实现通过接口访问可以实现对数据库的增删改查

## 使用说明

1.  项目结构使用 spring boot 2.0x版本 通过Velocity模板引擎 实现代码的生成（java、xml、sql)支持CRUD下载 。
2.  需要生成的数据库必须有完整的备注和库表注释，不然程序无法是识别
3.  项目支持word、excel文件格式下载
4.  项目支持批量生成代码，加快编程效率
5.  项目支持自定义修改 类创建的作者名、包路径、去除表路径。
6.  项目在使用MyBatis 时会自动生成xml，实体类，service、serviceImpl，controller。
7.  项目在使用JPA 时会自动生成 实体类并添加注解，service、serviceImpl，controller。
8.  代码生成：前后端代码的生成（java、html、xml、sql)支持CRUD下载 。


## 演示图

<table>
    <tr>
        <td><img src="https://code-generator.oss-cn-shanghai.aliyuncs.com/1.jpg"/></td>
        <td><img src="https://code-generator.oss-cn-shanghai.aliyuncs.com/2.png"/></td>
    </tr>
    <tr>
        <td><img src="https://code-generator.oss-cn-shanghai.aliyuncs.com/3.jpg"/></td>
        <td><img src="https://code-generator.oss-cn-shanghai.aliyuncs.com/4.png"/></td>
    </tr>
    <tr>
        <td><img src="https://code-generator.oss-cn-shanghai.aliyuncs.com/5.png"/></td>
        <td><img src="https://code-generator.oss-cn-shanghai.aliyuncs.com/6.png"/></td>
    </tr>
</table>