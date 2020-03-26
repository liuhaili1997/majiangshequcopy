## 校园社区

## 资料
[spring学习区---文档](https://spring.io/guides)    
[与前端交互的功能实现](https://spring.io/guides/gs/serving-web-content/)  
[对照的网站--es社区](https://elasticsearch.cn/explore)
[BootStrap文档](https://v3.bootcss.com/getting-started/#download)
[OAuth app](https://github.com/settings/applications/1246568)

## 工具
[jetbrains](https://www.jetbrains.com/idea/download/#section=windows)
[maven](https://maven.apache.org)    
[github](https://github.com/liuhaili1997)     
[git](https://git-scm.com)
[flywaydb](https://flywaydb.org/getstarted/firststeps/maven)

```bash
mvn flyway:migrate
```


##知识：
cookie：就是一张银行卡，可以进入网页
session：对应银行中的账户

UML     
1.泛化关系(generalization)      
is-a的关系：mac is a computer[用带空心的箭头表示] mac指向computer      
2.实现关系(realization)     
理论到实物的这种关系：用实心的箭头表示，实物指向理论。     
3.聚合关系(aggregation)     
个体和群体的关系，用空的菱形箭头表示，群体是由个体组成的。与组合关系的区别是：整体和部分不是强依赖，即使整体不存在了，部分仍然可以存在。        
4.组合关系(composition)     
公司和部分的关系，公司由多个部门组成，公司不存在了，则部门也必须不会存在的。这是整体和部分是强依赖关系，用实心的菱形箭头表示，部分指向整体。      
5.关联关系(association)     
通常用一条直线表示，如果需要标明方向可以添加箭头，描述的是：不同类对象之间的关系，通常不随状态变化而变化，可以看作被关联者属于关联者的一部分,程序中以类变量的方式表示。     
6.依赖关系(dependency)      
用一套带箭头的虚线表示，通常描述一个对象的运行期间会用到另一个对象的关系，程序中通过构造函数，形参体现。        
总结：     
实箭泛化虚实现     
虚线依赖实关联     
空菱聚合实组合     
项目沟通图常见

h2database:log    
刚刚创建的h2数据库：需要重新设置密码和用户名，以及授权才可以使用，授权语句：
create user if not exists 用户名 password ‘密码’;    //设置用户和密码
alter user 用户名 admin true;  //授权

##脚本
```sql
create table if not exists USER
(
    ID           INT auto_increment,
    ACCOUNT_ID   VARCHAR(128),
    NAME         VARCHAR(50),
    TOKEN        CHAR(36),
    GMT_CREATE   BIGINT,
    GMT_MODIFIED BIGINT,
    constraint USER_PK
        primary key (ID)
);
comment on table USER is '用户登录信息表';

```
###sql拓展
```html
-- 创建表
    create [temporary] table[ if not exists] [库名.]表名 ( 表的结构定义 )[ 表选项]
        每个字段必须有数据类型
        最后一个字段后不能有逗号
        temporary 临时表，会话结束时表自动消失
        对于字段的定义：
            字段名 数据类型 [NOT NULL | NULL] [DEFAULT default_value] [AUTO_INCREMENT] [UNIQUE [KEY] | [PRIMARY] KEY] [COMMENT 'string']
            DEFAULT:默认的值  AUTO_INCREMENT:加上表示这个字段自增   COMMENT:注解，对这个字段做解释
-- 表选项
    -- 字符集
        CHARSET = charset_name
        如果表没有设定，则使用数据库字符集
    -- 存储引擎
        ENGINE = engine_name    
        表在管理数据时采用的不同的数据结构，结构不同会导致处理方式、提供的特性操作等不同
        常见的引擎：InnoDB MyISAM Memory/Heap BDB Merge Example CSV MaxDB Archive
        不同的引擎在保存表的结构和数据时采用不同的方式
        MyISAM表文件含义：.frm表定义，.MYD表数据，.MYI表索引
        InnoDB表文件含义：.frm表定义，表空间数据和日志文件
        SHOW ENGINES -- 显示存储引擎的状态信息
        SHOW ENGINE 引擎名 {LOGS|STATUS} -- 显示存储引擎的日志或状态信息
    -- 数据文件目录
        DATA DIRECTORY = '目录'
    -- 索引文件目录
        INDEX DIRECTORY = '目录'
    -- 表注释
        COMMENT = 'string'
    -- 分区选项
        PARTITION BY ... (详细见手册)

-- 修改表
    -- 修改表本身的选项
        ALTER TABLE 表名 表的选项
        EG:    ALTER TABLE 表名 ENGINE=MYISAM;
    -- 对表进行重命名
        RENAME TABLE 原表名 TO 新表名
        RENAME TABLE 原表名 TO 库名.表名    （可将表移动到另一个数据库）
        -- RENAME可以交换两个表名

-- 删除表
    DROP TABLE[ IF EXISTS] 表名 ...
-- 清空表数据
    TRUNCATE [TABLE] 表名
-- 复制表结构
    CREATE TABLE 表名 LIKE 要复制的表名
-- 复制表结构和数据
    CREATE TABLE 表名 [AS] SELECT * FROM 要复制的表名
-- 检查表是否有错误
    CHECK TABLE tbl_name [, tbl_name] ... [option] ...
-- 优化表
    OPTIMIZE [LOCAL | NO_WRITE_TO_BINLOG] TABLE tbl_name [, tbl_name] ...
-- 修复表
    REPAIR [LOCAL | NO_WRITE_TO_BINLOG] TABLE tbl_name [, tbl_name] ... [QUICK] [EXTENDED] [USE_FRM]
-- 分析表
    ANALYZE [LOCAL | NO_WRITE_TO_BINLOG] TABLE tbl_name [, tbl_name] ...
```
###添加一行：
```sql
ALTER TABLE USER ADD bio VARCHAR(256) NULL;
```

###对表字段修改的拓展
```$xslt
-- 修改表的字段机构
        ALTER TABLE 表名 操作名
        -- 操作名
            ADD[COLUMN] 字段名        -- 增加字段
                AFTER 字段名            -- 表示增加在该字段名后面
                FIRST                -- 表示增加在第一个
            ADD PRIMARY KEY(字段名)    -- 创建主键
            ADD UNIQUE [索引名] (字段名)-- 创建唯一索引
            ADD INDEX [索引名] (字段名)    -- 创建普通索引
            ADD 
            DROP[COLUMN] 字段名        -- 删除字段
            MODIFY[COLUMN] 字段名 字段属性        -- 支持对字段属性进行修改，不能修改字段名(所有原有属性也需写上)
            CHANGE[COLUMN] 原字段名 新字段名 字段属性        -- 支持对字段名修改
            DROP PRIMARY KEY    -- 删除主键(删除主键前需删除其AUTO_INCREMENT属性)
            DROP INDEX 索引名    -- 删除索引
            DROP FOREIGN KEY 外键    -- 删除外键
```

