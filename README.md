# Techno

- 该示例仅为技术验证工程，还存在许多待(b)完(u)善(g)的地方，针对未加入的功能会在后续版本中逐一实现。
- 该示例工程使用 `Spring Boot 1.5.12.RELEASE` 版本，与2.0版本存在差异，不可直接修改版本号升级。
- 包结构可通过项目实际需要自行整理。
- 命名规则及其相关问题可参考 `《阿里巴巴Java开发手册》` ，或在IDE中安装 `P3C` 插件自行检查。
- 如有疑问，请查阅[官方文档](https://docs.spring.io/spring-boot/docs/1.5.12.RELEASE/reference/htmlsingle/)。

---

## 启动

运行 [TechnoApplication](https://github.com/zssjz/techno/blob/master/src/main/java/com/jason/TechnoApplication.java) 中的 _main_ 方法。

---

## 配置文件

该示例使用 `.yml` 格式的配置文件，与 `.properties` 格式配置文件效果相同，使用时请严格遵守配置文件书写格式；针对其他配置，请查阅[官方文档](https://docs.spring.io/spring-boot/docs/1.5.12.RELEASE/reference/htmlsingle/#common-application-properties)。

---

## 其他说明

- druid监控：http://localhost:8090/druid/index.html
- swagger：http://localhsot:8090/swagger-ui.html
- actator：http://localhost:8090/manage/*

**注**：本示例访问以上非用户功能时需要通过 `Spring Security` 验证,示例表结构如下；
```
CREATE TABLE `user` (
     `id` int(10) NOT NULL AUTO_INCREMENT,
     `username` varchar(255) NOT NULL COMMENT '账号(邮箱)',
     `password` varchar(255) NOT NULL COMMENT '密码',
     `is_account_non_expired` int(2) NOT NULL COMMENT '1:账号未过期',
     `is_account_non_locked` int(2) NOT NULL COMMENT '1:账号未锁定',
     `is_credentials_non_expired` int(2) NOT NULL COMMENT '1:密码未过期',
     `is_enabled` int(2) NOT NULL COMMENT '1:账号可用',
     `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
     PRIMARY KEY (`id`),
     UNIQUE KEY `IDX_USER_USERNAME` (`username`)
   ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='用户表';
```
**注**：本示例中已使用 _org.springframework.security.crypto.password.PasswordEncoder_ 对密码进行加密，请勿直接向表中手动添加数据，可由 [单元测试](https://github.com/zssjz/techno/blob/master/src/test/java/com/jason/config/dao/UserRepositoryTest.java) 类中添加数据；

**注**：本示例中 [UserDO](https://github.com/zssjz/techno/blob/master/src/main/java/com/jason/entity/UserDO.java) 实体已经默认为用户分配"ADMIN"权限，可根据实际项目需要自行更改权限分配方式；

**注**：actator结尾的 `*` 为通配符，具体使用方式请搜索相关文档。