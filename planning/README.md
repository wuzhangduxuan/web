# Funiture

## 项目需要
* JDK 1.7及以上
* Maven 管理jar包
* Mysql 数据库存储
* Tomcat 运行用服务器
* Redis 非必须, 缓存用, 可在配置中打开
* Rabbit 非必须, 队列用, 可在配置打开
* Zookeeper 非必须, 可在配置中打开
* Lombok, 需要开发环境(IDEA或eclipse)支持

## 前台 - 用户页面 + 管理界面
* 一个主要供展示家具产品的网站, 项目名称因此而来, 但是前台是扒下来的, 没啥可看的
* 做个后台，管理前台展示数据及权限相关
* [jquery](http://jquery.com/) 框架
* [bootstrap](http://v3.bootcss.com/) 主要样式
* [mustache](https://github.com/janl/mustache.js) 引擎
* [ace](http://responsiweb.com/themes/preview/ace/1.4/index.html) 模板渲染
* [zTree](http://www.ztree.me/v3/main.php) jQuery树插件
* [duallistbox](https://github.com/istvan-ujjmeszaros/bootstrap-duallistbox) 多选插件

## 后台 - 各种技术演练
* 通用的 spring 框架搭建
* 系统全局配置维护, 能实时刷新内存中最新配置
* 通用的权限管理系统 - 通过角色维护用户和权限之间的关系
  * 正常的部门、用户、角色、权限点、权限模块维护
  * 部门树、权限模块树、用户权限树、角色权限树展示及维护
  * 操作记录查询、还原操作
  * 根据配置记录动态渲染后台菜单
  * 根据配置记录拦截请求的url
* 系统监控, 内存、CPU、线程状态、GC情况等
* 系统服务降级, 临时禁止某些url请求及切流量放行
* 实时对系统url做QPS控制
* 系统定时任务调度(Quartz)管理, 动态开启、关闭、调整调度及执行开始结束状态监控
* 执行系统shell命令
* 验证码生成、校验
* redis等缓存使用
* logback, email通知异常
* mybatis, sql监控(sql中异常, 返回行过多等)
* 线程池使用, 异步回调, 抛弃请求监控等
* json(jackson)序列化与反序列化
* 通用邮件配置及发送
* 文件上传与管理，md5 计算
* httpClient 封装, 按需指定各项参数
* cookie 管理
* excel XSSFWorkbook(大数据量)使用, 相关报表导出
* hibernate validator校验
* threadLocal 管理进程信息, 按需使用
* Junit 测试
* RabbitMQ 队列, 生产-消费, 控制台管理
* zookeeper client 封装
* 支持多个数据源(aop切面里确定连接串), 根据需要切换数据库
* 短链接服务, 生成、跳转及过期处理
* 支持请求使用代理, 及动态选择代理
* 添加druid监控,使用/acl/druid/index.html访问

## 其他[必看]
* 强烈建议使用Intellij IDEA作为开发工具，eclipse需要手动做些设置才能正常使用, 项目启动要加载Resources包括：resources和resource.dev(或prod,区分环境选择）
* 项目中log基本都使用@Slf4j提供，需要开发工具支持Lombok插件，相关注解包括：@Getter，@Setter，@ToString，@AllArgsConstuctor, @NoArgsConstructor,@Builder
* 前台页面是从别的网站扒下来的，没任何价值，建议直接访问 /admin/page.do 进入后台, 用户的密码只是普通做了md5加密，登录细节参考LoginServlet.java里实现
* sql重新给了一下,直接导出本地的数据库表及数据, 可使用kanwangzjm@gmail.com/123456登录
* 个别功能只适合单机，比如动态修改quartz调度、qps控制等
* 如有问题，可加微信沟通，个人微信号：kanwangzjm，添加时请注明来自github

## 近期计划
* 在之前切库的基础上, 做动态切库(分库)操作
* mybatis层的分表操作, 借助org.shardbatis包的com.google.code.shardbatis.plugin.ShardPlugin
* 完成主备操作, 借助org.apache.curator包选择zk leader
