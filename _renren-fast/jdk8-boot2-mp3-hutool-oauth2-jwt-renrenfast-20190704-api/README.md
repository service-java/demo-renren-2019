# README


- http://localhost:8080/renren-fast/swagger-ui.html#/
- http://localhost:8080/renren-fast/druid/sql.html
- 账号 admin/admin
- 默认不开启redis


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

```
http://localhost:8080/renren-fast/profile/upload/2019/07/22/f3c65c5a111a6da10bd9acd426d2cd03.jpg

http://localhost:8080/renren-fast/common/download?fileName=/upload/2019/07/22/f3c65c5a111a6da10bd9acd426d2cd03.jpg&delete=false
```


- 报表导入导出

```
// 测试地址
http://localhost:8080/renren-fast/common/excelkit/export/template
http://localhost:8080/renren-fast/common/excelkit/export/user
http://localhost:8080/renren-fast/common/excelkit/import/user

http://localhost:8080/renren-fast/common/easyexcel/export/user
http://localhost:8080/renren-fast/common/easyexcel/import/user


// 一行代码完成 JAVA 的 EXCEL 读写——EasyExcel 的方法封装
https://juejin.im/post/5ba320546fb9a05d0b14304b
```


# 一些问题

- 管理员创建的角色好像是不能共享??
