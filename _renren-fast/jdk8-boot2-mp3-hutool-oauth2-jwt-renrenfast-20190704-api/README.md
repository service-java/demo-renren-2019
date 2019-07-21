# README


- http://localhost:8080/renren-fast/swagger-ui.html#/
- http://localhost:8080/renren-fast/druid/sql.html
- 账号 admin/admin
- 可能需要打开 redis-server

# 调整

在renren-fast的基础上作几个简单调整

1. mapping.xml移出resources/mapper, 搬到dao附近

```yml
# mybatis
mybatis-plus:
  mapper-locations: classpath*:io/renren/modules/**/mapping/*.xml
  typeAliasesPackage: io.renren.modules.*.entity
```

```xml
<build>
    <resources>
        <resource>
            <directory>src/main/resources</directory>
            <filtering>true</filtering>
        </resource>
        <!-- mybatis-xml位置设为资源 -->
        <resource>
            <directory>src/main/java</directory>
            <includes>
                <include>**/*.xml</include>
            </includes>
            <filtering>true</filtering>
        </resource>
    </resources>
</build>
```

2. lombok.config默认启用链式调用

```properties
lombok.accessors.chain=true
```

3. 为了方便, service不写interface -> lazy

4. pojo位置

```
1. controller/query
2. service/dto
3. model/common-pojo(vo+dto+query+...)
```

5. 禁用devtools, 用jRebel更新重载


# TODO

- 上传下载支持本地
- 报表导入导出

# 一些问题

- 管理员创建的角色好像是不能共享??
