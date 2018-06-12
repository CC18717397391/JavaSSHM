Spring将事务管理分成了两类:
	* 1.编程式事务管理
		* 手动编写代码进行事务管理.(很少使用)
	* 2.声明式事务管理:【基于AOP的编程思想，在一个事务（即原子操作）前后添加切面】
		* 2.基于TransactionProxyFactoryBean的方式.(很少使用)
			* 需要为每个进行事务管理的类,配置一个TransactionProxyFactoryBean进行增强.
		* 3.基于AspectJ的XML方式.(经常使用)
			* 一旦配置好之后,类上不需要添加任何东西
		* 4.基于注解方式.(经常使用)
			* 配置简单,需要在业务层上添加一个@Transactional的注解.
			
JAR:
连接池	com.springsource.com.mchange.v2.c3p0-0.9.1.2
基本开发包    com.springsource.org.apache.commons.logging-1.1.1
		com.springsource.org.apache.log4j-1.2.15
		spring-beans-3.2.0.RELEASE
		spring-context-3.2.0.RELEASE
		spring-core-3.2.0.RELEASE
		spring-expression-3.2.0.RELEASE
数据库操作	spring-jdbc-3.2.0.RELEASE
		spring-test-3.2.0.RELEASE
		spring-tx-3.2.0.RELEASE
声明式事务管理
		spring-aop-3.2.0.RELEASE
		com.springsource.org.aopalliance-1.0.0	【AOP联盟组织包】


		
配置文件：
日志记录	log4j
核心配置	applicationContext1/2/3/4		顶部的beans约束就是spring开发最全的约束文档了
