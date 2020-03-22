## 校园社区

## 资料
[spring学习区---文档](https://spring.io/guides)    
[与前端交互的功能实现](https://spring.io/guides/gs/serving-web-content/)  
[对照的网站--es社区](https://elasticsearch.cn/explore)
[BootStrap文档](https://v3.bootcss.com/getting-started/#download)
[OAuth app](https://github.com/settings/applications/1246568)

## 工具
https://www.jetbrains.com/idea/download/#section=windows
https://maven.apache.org    
https://github.com/liuhaili1997     
https://git-scm.com

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
create table USER
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

