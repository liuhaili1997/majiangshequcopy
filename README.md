## 校园社区

## 资料
[spring学习区---文档](https://spring.io/guides)    
[与前端交互的功能实现](https://spring.io/guides/gs/serving-web-content/)  
[对照的网站--es社区](https://elasticsearch.cn/explore)
[BootStrap文档](https://v3.bootcss.com/getting-started/#download)
[OAuth app](https://github.com/settings/applications/1246568)  
[spring MVC](https://docs.spring.io/spring/docs/5.0.3.RELEASE/spring-framework-reference/web.html#spring-web)

## 工具
[jetbrains](https://www.jetbrains.com/idea/download/#section=windows)
[maven](https://maven.apache.org)    
[github](https://github.com/liuhaili1997)     
[git](https://git-scm.com)
[flywaydb](https://flywaydb.org/getstarted/firststeps/maven)

##chrome插件  
===========
* LiveReload
> 自动刷新页面
* One Tab
> 收集当前页面，方便下一次直接打开
* Octotree
> github中的代码可以通过这个直接打开，不用直接clone下来看
* Table of content sidebar
> 获取当前页面的大纲，方便跳转和查询
* post man
> 项目接口的自测

[ctotree](https://www.octotree.io/)   
[Table of content sidebar](https://chrome.google.com/webstore/detail/table-of-contents-sidebar/ohohkfheangmbedkgechjkmbepeikkej)    
[One Tab](https://chrome.google.com/webstore/detail/chphlpgkkbolifaimnlloiipkdnihall)    
[Live Reload](https://chrome.google.com/webstore/detail/livereload/jnihajbhpnppcggbcgedagnkighmdlei/related)  
[Postman](https://chrome.google.com/webstore/detail/coohjcphdfgbiolnekdpbcijmhambjff)

```bash
mvn flyway:migrate
执行mybatis，使用maven方式执行：overwrite覆盖
mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate
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
##Mapper 中的配置信息
```batch
<!-- mapper接口代理实现编写规则：
1.映射文件中namespace要等于接口的全路径
2.通过sql语句实现数据库的操作
3.映射文件中sql语句id要等与于接口的方法名称
4.映射文件中传入参数类型要等于接口方法的传入参数类型
5.映射文件中返回结果集类型要等于接口方法的返回值类型

/* collection属性：对谁进行遍历，在这里是对ids进行遍历
   item属性：遍历的结果放到哪里，这里将ids的遍历结果放到id中。
   open属性：从何处开始进行遍历
   close属性：从何处结束遍历
   separator属性：分隔符是什么，这里的分隔符为‘，’,通过‘，’将id分离开来。
   占位符只需要写#{id}即可，花括号里面的放的为我们将遍历结果放置的地方，也就是id。

 parameterType="com.huida.vo.QueryVo" 接受的是一个实体类，
 resultType="com.huida.po.User"返回值用什么接受
 -->
```
```热部署，不需要重复的启动项目来查看修改的地方
 <!--热部署插件-->
 <dependency>
     <groupId>org.springframework.boot</groupId>
     <artifactId>spring-boot-devtools</artifactId>
     <scope>runtime</scope>
     <optional>true</optional>
 </dependency>
设置：settings->build->compile->Build project automatically  自动编译
ctrl+shift+alt+/->registry  compiler.automake.allow.when.app.running 选中这个 就完成了热部署

```
```html
<!--配置当前页的路径-->
<a th:href="@{'/profile/' + ${section}(page=${questionList.totalPage})}" aria-label="Previous">
    <span aria-hidden="true">&gt;&gt;</span>
</a>
<!--配置当前根目录下面的路径-->
<a th:href="@{/(page=${page})}" th:text="${page}"></a>
```
#拦截器
####对应方法表示的含义：
* preHandle(..) — before the actual handler is executed  
>在实际请求之前进行处理的方法，
>return 一个Boolean的值，true表示程序继续，false 表示当前程序中断了
>
* postHandle(..) — after the handler is executed
>在程序处理结束之前用
* afterCompletion(..) — after the complete request has finished
>请求结束的时候执行


```thymeleaf expressions  可能以后可以用到
#加了拦截器后,配置了http://www.thymeleaf.org的动态界面都需要增加配置信息  跳转静态页面需要经过controller层才能实现跳转 (不经过静态资源报错)
#spring.mvc.static-path-pattern= /static/**
#用于告诉Spring Boot应该在何处查找静态资源文件,查找文件时会依赖于配置的先后顺序依次进行
spring.resources.static-locations=classpath:/static/,classpath:/view/,classpath:/public,classpath:/resources,classpath:/META-INF/resources

#thymeleaf
spring.thymeleaf.prefix = classpath:/static/
#开发阶段，建议关闭thymeleaf的缓存
spring.thymeleaf.cache=false
#使用遗留的html5以去掉对html标签的校验
spring.thymeleaf.mode= HTML5
spring.thymeleaf.check-template = true
spring.thymeleaf.servlet.content-type = text/html
spring.thymeleaf.encoding = UTF-8
spring.thymeleaf.suffix = .html
```



#CSDN 文档学习
[hashMap 的初始化容量 以及hash函数讲解](https://blog.csdn.net/Anenan/article/details/91490390)      
[java8 List转Map的使用技巧](https://blog.csdn.net/Hern_16/article/details/105118006?fps=1&locationNum=2)      