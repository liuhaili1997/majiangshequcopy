#[markdown的使用](https://blog.csdn.net/u011630575/article/details/84866673)
----------------
[源文件](https://www.appinn.com/markdown/)
#mybatis 
出现问题：org.apache.ibatis.binding.BindingException: Invalid bound statement (not found)
####解决方法：
1. 检查xml文件所在的package名称是否和interface对应的package名称一一对应   
>接口名和xml写查询语言的文件要用一样的名字
>在mapper下的文件路径要一致，
2. 检查xml文件的namespace是否和xml文件的package名称一一对应 
>namespace="com.haili.project.projectfirst.mapper.UserMapper"
3. 检查函数名称能否对应上  
>方法名也需要一致的对应 <select id="findByIdList",与接口中的定义的方法
>对应的参数也是不能错，特别是集合，实体类可以用paramType接受
4. 去掉xml文件中的中文注释  
> 实在不行就去除中文注释，防止编译时错误，无法编译成文档
5. 匹配文件
>在application.properties或等同的配置文件中配置一下配置  
>mybatis.mapper-locations=classpath:mapper/*.xml
>第二种方法就是在pom文件中加resources，也可以达成目标：  
>
```pom文件中加的，在plugins中加
<resources>
    <!-- mapper.xml文件在java目录下 -->
    <resource>
        <!--指定resources插件处理哪个目录下的资源文件-->
        <directory>src/main/java</directory>
        <includes>
            <include>**/*.xml</include>
        </includes>
        </resource>
        <!-- mapper.xml文件在resources目录下-->
        <!-- <resource>
        <directory>src/main/resources</directory>
    </resource>-->
</resources>
原文档地址：https://www.jianshu.com/p/e9cd54163556
```