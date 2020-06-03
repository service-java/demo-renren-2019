# README

- 基于renren-fast改造

# 使用运行

- 账号 admin/admin
- swagger地址   
    - http://localhost:8080/renren-fast/swagger-ui.html#/
- druid地址
    - http://localhost:8080/renren-fast/druid/sql.html
- 默认是不开启redis的
- 部署

```java
mvn clean package wagon:upload-single wagon:sshexec
```

---

# 结构调整

> 在renren-fast的基础上作几个简单调整

- mapping.xml移出resources/mapper, 搬到dao附近

```yml
# mybatis
mybatis-plus:
  mapper-locations: classpath*:com/xyz/modules/**/mapping/*.xml
  typeAliasesPackage: com.xyz.modules.*.entity
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

- 创建lombok.config默认启用链式调用

```properties
lombok.accessors.chain=true
```

- 为了方便, service不写interface -> @lazy
- pojo位置

```
1. controller/query
2. service/dto
3. model/common-pojo(vo+dto+query+...)
```

- 禁用devtools, 用jRebel更新重载

```
// 遇到 xml修改 热更新失败
下载插件 
https://github.com/SweetInk/jrebel-mybatisplus
-Drebel.plugins=d:\jrebel\plugin\jr-mybatisplus.jar
```

- demo+biz目录下的代码是瞎写的!!! --> 用之前一定要删了这两个模块

---

# ChangeLog

- 更新201907-202005期间renren-fast的修改

# TODO

- 上传下载支持本地

```
http://localhost:8080/renren-fast/profile/upload/2019/07/22/f3c65c5a111a6da10bd9acd426d2cd03.jpg


http://localhost:8080/renren-fast/demo/download?fileName=/upload/2019/07/22/f3c65c5a111a6da10bd9acd426d2cd03.jpg&delete=false
http://localhost:8080/renren-fast/demo/download?fileName=/upload/2019/07/23/984174d33a8ef75aba4b855a0c2715e8.xlsx&delete=false
```


- 报表导入导出

```
// 测试地址
http://localhost:8080/renren-fast/demo/excelkit/export/template
http://localhost:8080/renren-fast/demo/excelkit/export/user
http://localhost:8080/renren-fast/demo/excelkit/import/user

http://localhost:8080/renren-fast/demo/easyexcel/export/user
http://localhost:8080/renren-fast/demo/easyexcel/import/user


// 一行代码完成 JAVA 的 EXCEL 读写——EasyExcel 的方法封装
https://juejin.im/post/5ba320546fb9a05d0b14304b
```


# 常见问题 @faq

- easyexcel

```
1. bufferedInputStream包装一下
```

- excelkit @deprecated

```
1. 实体注解貌似不配合lombok，很迷??
2. 仅支持xlsx格式
3. 功能局限(date格式转化??, 多sheet, 单元格合并??)
```

- 管理员创建的角色好像是不能共享??

---

# 参考
