<p align="center"><img src= "https://cdn.tobebetterjavaer.com/stutymore/pmhub_%E7%AE%80%E4%BB%8B%E7%89%88.png" alt="MaxKB" width="300" /></p>

PmHub 是一套基于 SpringCloud & SpringCloud Alibaba & LLM 的分布式微服务的智能项目管理系统，这个项目旨在让同学们快速掌握微服务/分布式项目的架构设计和开发流程。




## 一、项目简介

PmHub 包括认证、流程、项目管理、系统、网关等服务。包含了 Redis 缓存、RocketMQ 消息队列、Docker 容器化、Jenkins 自动化部署、Spring Security 安全框架、Nacos 服务注册和发现、sentinel 分布式事务、Spring Boot Actuator 服务监控、SkyWalking 链路追踪、OAuth2 统一认证、OpenFeign 服务调用，Vue3 前端框架等互联网开发中需要用到的主流技术栈，可以帮助同学们快速掌握微服务/分布式项目的核心知识点。


## 二、代码结构

```
com.laigeoffer.pmhub     
├── pmhub-ui              // 前端框架 [1024]
├── pmhub-gateway         // 网关模块 [6880]
├── pmhub-auth            // 认证中心 [6800]
├── pmhub-api             // 接口模块
│       └── pmhub-api-system                          // 系统接口
│       └── pmhub-api-workflow                        // 流程接口
├── pmhub-base          // 通用模块
│       └── pmhub-base-core                           // 核心模块组件
│       └── pmhub-base-datasource                     // 多数据源组件
│       └── pmhub-base-seata                          // 分布式事务组件
│       └── pmhub-base-security                       // 安全模块组件
│       └── pmhub-base-swagger                        // 系统接口组件
│       └── pmhub-base-notice                         // 消息组件组件
├── pmhub-modules         // 业务模块
│       └── pmhub-system                              // 系统模块 [6801]
│       └── pmhub-gen                                 // 代码生成 [6802]
│       └── pmhub-job                                 // 定时任务 [6803]
│       └── pmhub-project                             // 项目服务 [6806]
│       └── pmhub-workflow                            // 流程服务 [6808]
├── pmhub-monitor             						  // 监控中心 [6888]                 
├──pom.xml                                            // 公共依赖
```


## 三，基础环境准备
* 1、启动 MySQL（必须）

可以选择本机直接安装 MySQL，也可以通过 Docker 的方式，但需要做好磁盘挂载，推荐本机安装！


* 2、启动 Redis（必须）

①、如果你是 macOS 用户，可以直接在终端输入`redis-server`启动 Redis。

![](https://cdn.tobebetterjavaer.com/images/README/1711692102829.png)

②、如果你是 Windows 用户，可以直接双击 redis-server.exe 启动 Redis。


* 3、启动 Nacos（必须）

[官网](https://nacos.io/download/nacos-server/)下载 Nacos，找到 /conf/application.properties 文件，修改数据库连接信息。可以直接复制 pmhub/docker/nacos/conf/application.properties 内容。

修改下数据库配置信息为你自己的数据库，本地启动可以把鉴权关了。

```
1. 如果数据库名也是pmhub，那么只需要修改用户名和密码即可。
2. 如果用户名也是 root，那么只需要修改密码即可。
3. 如果密码也一样，那么就不需要修改了。
```

![修改nacos配置文件]

①、如果你是 macOS 用户，可以直接在终端输入`sh startup.sh -m standalone`启动 Nacos。

②、如果你是 Windows 用户，可以直接双击 startup.cmd 启动 Nacos。

启动成功后访问 http://localhost:8848/nacos 即可看到 Nacos 控制台。默认用户名密码都是 nacos。

* 4、启动 SkyWalking 分布式链路追踪（非必须）

参考手册：[SkyWalking 启动手册](https://laigeoffer.cn/pmhub/interview/skywalking/)

* 5、启动 Sentinel 分布式熔断和降级（非必须）

参考手册：[Sentinel 启动手册](https://laigeoffer.cn/pmhub/interview/feign-sentinel/)


* 6、启动 Seata 分布式事务（非必须）

参考手册：[Seata 启动手册](https://laigeoffer.cn/pmhub/interview/seata/)

* 7、启动 Rocketmq 消息队列（非必须）

参考手册：[Rocketmq 启动手册](https://laigeoffer.cn/pmhub/interview/rocketmq/)



#### 第五步，启动各个微服务

> 注意：如果遇到服务启动失败，可自行查看 nacos 配置是否做了修改，如数据库连接信息等。

①、启动 pmhub-gateway 网关服务

找到 pmhub-gateway 项目，右键 Run PmHubGatewayApplication.main()。


②、启动 pmhub-auth 认证服务

找到 pmhub-auth 项目，右键 Run PmHubAuthApplication.main()。

③、启动 pmhub-system 系统服务

找到 pmhub-system 项目（在pmhub-modules 下），右键 Run PmHubSystemApplication.main()。
pmhub-system 启动前需要修改 nacos 中的 pmhub-system-dev.yml 配置文件，修改数据库连接信息为你自己的数据库。

④、启动 pmhub-project 项目管理服务

找到 pmhub-project 项目（在pmhub-modules 下），右键 Run PmHubProjectApplication.main()。

启动前需要修改 nacos 中的 pmhub-project-dev.yml 配置文件，修改数据库连接信息为你自己的数据库。

⑤、启动 pmhub-workflow 流程管理服务

找到 pmhub-workflow 项目（在pmhub-modules 下），右键 Run PmHubWorkflowApplication.main()。

启动前需要修改 nacos 中的 pmhub-workflow-dev.yml 配置文件，修改数据库连接信息为你自己的数据库。

⑦、启动 pmhub-job 定时任务调度服务

找到 pmhub-job 项目（在pmhub-modules 下），右键 Run PmHubJobApplication.main()。

启动前需要修改 nacos 中的 pmhub-job-dev.yml 配置文件，修改数据库连接信息为你自己的数据库。

⑧、启动 pmhub-monitor 监控服务

找到 pmhub-monitor 项目，右键 Run PmHubMonitorApplication.main()。

启动前需要修改 nacos 中的 pmhub-monitor-dev.yml 配置文件，修改监控后台的用户名和密码，以及首页展示标题。

启动成功后可访问：http://localhost:6888/wallboard

可以在线实时查案各个服务的状态以及日志：



### 4.3、前端项目启动

请参考 pmhub-ui 项目的 README.md 文档，[前端工程结构说明](pmhub-ui/README.md)


### 4.4、Swagger 地址

http://localhost:1024/dev-api/swagger-ui/index.html


