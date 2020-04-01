# file-home

一、项目意图
    搭建一个用户云端文件、图片服务器；

二、系统架构
    1.数据层：MySQL集群，主从架构，hikari连接池，redis缓存；
    2.后端服务：spring boot；
    3.后端组件：redis、spring session、mybatis-plus、jjwt、fastjson、lombok；
    4.部署方式：分布式集群部署，nginx代理；
    
三、账户体系
    1.账户分为普通账户和管理员账户；
    2.登录支持session和jwt两种方式验证；
