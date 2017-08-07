# 下期功能---Swagger2 接口管理

## 项目介绍
- 使用spring、springmvc、spring data jpa
- 持续集成各种常使用的javaweb技术
- mysql数据库[数据库文件](https://github.com/handexing/wish/blob/master/src/main/webapp/doc/wish.sql)

## 系统管理（一）

- 用户管理（添加导出用户资料功能）
- 菜单管理
- 角色管理

## springMVC、spring、spring data jpa框架整合（二）
- 整合spring、springmv、spring data jpa
- 详细项目配置[SSS项目整合](https://handexing.github.io/2017/05/02/wish(%E4%B8%80)/)

## 权限管理实现（三）
- 设计数据库
- 实现界面操作管理权限分配
- 详细说明[权限管理实现](https://handexing.github.io/2017/05/03/wish(%E4%BA%8C)/)

## 文章管理，zxing二维码（四）
- 使用bootstarp markdown插件实现文章管理,[Bootstrap markdown官网](http://www.codingdrama.com/bootstrap-markdown/)
- 使用谷歌开源项目zxing实现二维码在线生成功能[zxing github](https://github.com/zxing/zxing)

## 任务调度（五）
- 整合spring+quartz完成基础定时任务，详情移驾[spring,quartz整合（一）](https://handexing.github.io/2017/05/04/spring+quartz(%E4%B8%80)/)
- 动态任务调度[spring,quartz整合code](https://github.com/handexing/frameworkAggregate)，配置详情：[spring,quartz整合（二）](https://handexing.github.io/2017/05/08/spring+quartz(%E4%BA%8C)/)

## JSOUP爬虫学习（六）
- 移到别的地方了[jsoup项目](https://github.com/handexing/frameworkAggregate)

## Activiti工作流引擎使用（七）
- 流程列表（部署流程、查看流程、删除流程）
- 历史流程列表
- 正在运行的流程列表
- 请假列表（新增请假条）

## 商品比价(八)
- 根据phantomjs爬取数据，jsoup解析数据
- 使用定时任务每天定时爬取数据，使用echarts展示数据变化


> 后台定时检查请假事项，到达请假时间，开始启动请假流程。
 

## 工程配置
- eclipse
- maven
- mysql
- redis
- tomcat

## 效果预览

### 用户管理

![登录界面](https://github.com/handexing/wish/raw/master/img/user.png)

### 菜单管理

![登录界面](https://github.com/handexing/wish/raw/master/img/menu.png)

### 角色管理

![登录界面](https://github.com/handexing/wish/raw/master/img/role.png)

### 文章管理

![登录界面](https://github.com/handexing/wish/raw/master/img/article.png)

###　功能杂项管理

![登录界面](https://github.com/handexing/wish/raw/master/img/code.png)

### 工作流管理

- 流程列表

![登录界面](https://github.com/handexing/wish/raw/master/img/processtask.png)

- 任务管理

![登录界面](https://github.com/handexing/wish/raw/master/img/processlist.png)


### 动态任务管理

![登录界面](https://github.com/handexing/wish/raw/master/img/tasklist.png)

### 商品比价

- 根据phantomjs爬取数据，jsoup解析数据
- 使用定时任务每天定时爬取数据，使用echarts展示数据变化

#### 准备爬取的数据

![资源路径](https://github.com/handexing/wish/raw/master/img/skusrc.png)

#### 已经爬取的数据

![资源路径](https://github.com/handexing/wish/raw/master/img/skuinfo.png)

#### 商品价格趋势图

> 价格升降趋势图

![格力空调](https://github.com/handexing/wish/raw/master/img/geli.png)

![三只松鼠](https://github.com/handexing/wish/raw/master/img/ss.png)

```
**git clone https://github.com/handexing/wish.git**

然后eclipse导入项目：

**import-->existing maven project**
```


## 结语

> 觉得不错的朋友可以点下star,watch,fork也算是对我的鼓励了。