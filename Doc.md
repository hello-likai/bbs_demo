# 实现的功能
    1，User，Post，Comment的基本增删改查
    2，定User查询此User发表的所有Comment，返回需包含Comment所在的Post的必要信息
    3，给定User查询此User发表的所有Post
    4，Post列表页、Post详情页
    5，创建时间、更新时间
    6，给定User查询都有哪些Comment回复了这个用户，按时间倒序
    7，Swagger UI文档
    8，Liquibase来进行schema升级，实现了部分

# 未实现功能
    1，spring security，尝试了很多遍，还是未能成功登录获取token
    2，JPA的关联映射在增加评论的时候，无法添加user_id,post_id
    3，未实现带有层级的Comment
    4，单元测试
    5，权限控制
    
# 总结
    1，以前接触过一点JPA，印象中和mybatis-plus差不多，这次的经历告诉我，差距非常大，很多东西都是现学的，比如关联映射，
        以及自定义方法查询；
    2，spring security属于完全没概念。之前学过一点shiro的原理，也只能懂个大概，现在才明白那句话，spring security非常重，
        对于我来说，不是短期突击能学会的。
    3，之前的工作都是在现有的框架内写代码，算是专注于业务，自己完全从零开始搭建一遍，发现对于很多经常用到的东西并没有去深入了解，
        比如统一异常处理，统一返回资源处理等等。
    4，短期突击学习新知识是我不擅长的，后面还是要把一些基础掌握牢固。