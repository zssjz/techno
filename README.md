# Techno

- 该示例仅为技术验证工程，还存在许多待(b)完(u)善(g)的地方，针对未加入的功能会在后续版本中逐一实现。
- 包结构可通过项目实际需要自行整理。
- 命名规则及其相关问题可参考 `《阿里巴巴Java开发手册》` ，或在IDE中安装 `P3C` 插件自行检查。
- 该项目目的仅在于相关技术交流与个人能力提升。
- 具体版本可查看不同分支。

---

## 项目结构
```
    -src
      -main
      |  -java
      |  |  -com
      |  |    -jason
      |  |      -components   业务组件
      |  |      |  -control    业务控制层
      |  |      |  -dao        业务jpa持久层
      |  |      |  -model      业务数据对象
      |  |      |  -service    业务服务层
      |  |      -config       项目配置
      |  |      -dto          项目数据传输对象
      |  |      -entity       项目实体
      |  |      -repository   项目jpa持久层
      |  |      -schedule     项目定时任务
      |  |      -security     项目security实现
      |  |      -utils        项目工具类
      |  -resources
      |    -static           静态文件（css,js,img等）
      |    -templates        视图文件（html,jsp等）
      -test                  单元测试
```
---
