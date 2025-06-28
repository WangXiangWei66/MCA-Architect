# Spring面试专题

# 1.介绍下你对Spring的理解

&emsp;&emsp;Java程序员其实就是Spring程序员。足见Spring框架对Java程序员的影响力了。

## 1.1 Spring的发展历程

  先介绍Spring是怎么来的，发展中有哪些核心的节点，当前的最新版本是什么等

![image.png](https://fynotefile.oss-cn-zhangjiakou.aliyuncs.com/fynote/fyfile/1462/1660284531085/5006a9345f4f40d0bc2281e868c0cd3f.png)

通过上图可以比较清晰的看到Spring的各个时间版本对应的时间节点了。也就是Spring从之前单纯的xml的配置方式，到现在的完全基于注解的编程方式发展。

## 1.2 Spring的组成

&emsp;&emsp;Spring是一个轻量级的IoC和AOP容器框架。是为Java应用程序提供基础性服务的一套框架，目的是用于简化企业应用程序的开发，它使得开发者只需要关心业务需求。常见的配置方式有三种：基于XML的配置、基于注解的配置、基于Java的配置.

主要由以下几个模块组成：

* Spring Core：核心类库，提供IOC服务；
* Spring Context：提供框架式的Bean访问方式，以及企业级功能（JNDI、定时任务等）；
* Spring AOP：AOP服务；
* Spring DAO：对JDBC的抽象，简化了数据访问异常的处理；
* Spring ORM：对现有的ORM框架的支持；
* Spring Web：提供了基本的面向Web的综合特性，例如多方文件上传；
* Spring MVC：提供面向Web应用的Model-View-Controller实现。

![在这里插入图片描述](https://img-blog.csdnimg.cn/2019011310002937.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM4NTI2NTcz,size_16,color_FFFFFF,t_70)

## 1.3 Spring的好处

| 序号 | 好处              | 说明                                                         |
| ---- | ----------------- | :----------------------------------------------------------- |
| 1    | 轻量              | Spring 是轻量的，基本的版本大约2MB。                         |
| 2    | 控制反转          | Spring通过控制反转实现了松散耦合，对象们给出它们的依赖，`&#x3c;br>`而不是创建或查找依赖的对象们。 |
| 3    | 面向切面编程(AOP) | Spring支持面向切面的编程，并且把应用业务逻辑和系统服务分开。 |
| 4    | 容器              | Spring 包含并管理应用中对象的生命周期和配置。                |
| 5    | MVC框架           | Spring的WEB框架是个精心设计的框架，是Web框架的一个很好的替代品。 |
| 6    | 事务管理          | Spring 提供一个持续的事务管理接口，`&#x3c;br>`可以扩展到上至本地事务下至全局事务（JTA）。 |
| 7    | 异常处理          | Spring 提供方便的API把具体技术相关的异常 `&#x3c;br>`(比如由JDBC，Hibernate or JDO抛出的)转化为一致的unchecked 异常。 |
| 8    | 最重要的          | 用的人多！！！                                               |

# 2.介绍下你对IOC的理解

&emsp;&emsp;IOC（Inversion of Control 即控制反转）将对象交给Spring容器管理.

![image.png](https://fynotefile.oss-cn-zhangjiakou.aliyuncs.com/fynote/fyfile/1462/1660284531085/9f9a8e49dbe74f3ab23e8aa7ab299f8f.png)

和传统使用方式的对比

![image.png](https://fynotefile.oss-cn-zhangjiakou.aliyuncs.com/fynote/fyfile/1462/1660284531085/62eed9974ab54e5294483f3ebe41fcf8.png)

&emsp;&emsp;spring中提供了一种IOC容器，来控制对象的创建，无论是你创建对象，处理对象之间的依赖关系，对象的创建时间还是对象的创建数量，都是spring提供IOC容器上配置对象的信息就可以了。

IoC容器管理对象的好处：

1. 由IOC容器帮对象找相应的依赖思想并注入，并不是由对象主动去找
2. 资源集中管理，实现资源的可配置和易管理
3. 降低了使用资源双方的依赖程度，松耦合
4. 解决了Dao和Service的强耦合。

# 3.介绍下你对AOP的理解

&emsp;&emsp;AOP是Spring中另一个非常重要的核心。AOP【Aspect Oriented Programming】面向切面编程。AOP 是一种编程思想，是面向对象编程（OOP）的一种补充。面向切面编程，实现在不修改源代码的情况下给程序动态统一添加额外功能的一种技术。

![image.png](https://fynotefile.oss-cn-zhangjiakou.aliyuncs.com/fynote/fyfile/1462/1660284531085/6d98ea40051f43509cd97ad603a23326.png)

&emsp;&emsp;AOP的常用应用场景有哪些：

![image.png](https://fynotefile.oss-cn-zhangjiakou.aliyuncs.com/fynote/fyfile/1462/1660284531085/89bef4f44a7c4e82b276be68a93da828.png)

&emsp;&emsp;AOP可以拦截指定的方法，并且对方法增强，比如：事务、日志、权限、性能监测等增强，而且无需侵入到业务代码中，使业务与非业务处理逻辑分离。

&emsp;&emsp;AOP涉及到的术语：

| 术语          | 说明                                                         |
| ------------- | :----------------------------------------------------------- |
| 切面          | 切面泛指交叉业务逻辑。比如事务处理、日志处理就可以理解为切面。常用的切面有通知与顾问。实际就是对主业务逻辑的一种增强 |
| 织入          | 织入是指将切面代码插入到目标对象的过程。                     |
| 连接点        | 连接点指切面可以织入的位置。                                 |
| 切入点        | 切入点指切面具体织入的位置。                                 |
| 通知(Advice)  | 通知是切面的一种实现，可以完成简单织入功能（织入功能就是在这里完成的）。通知定义了增强代码切入到目标代码的时间点，是目标方法执行之前执行，还是之后执行等。通知类型不同，切入时间不同。 |
| 顾问(Advisor) | 顾问是切面的另一种实现，能够将通知以更为复杂的方式织入到目标对象中，是将通知包装为更复杂切面的装配器。 不仅指定了切入时间点,还可以指定具体的切入点 |

&emsp;&emsp;通知的类型：

| 通知类型                       | 说明                                       |
| :----------------------------- | :----------------------------------------- |
| 前置通知(MethodBeforeAdvice)   | 目标方法执行之前调用                       |
| 后置通知(AfterReturningAdvice) | 目标方法执行完成之后调用                   |
| 环绕通知(MethodInterceptor)    | 目标方法执行前后都会调用方法，且能增强结果 |
| 异常处理通知(ThrowsAdvice)     | 目标方法出现异常调用                       |

&emsp;&emsp;实现方式：

1.基于Schema来实现的

![image.png](https://fynotefile.oss-cn-zhangjiakou.aliyuncs.com/fynote/fyfile/1462/1660284531085/d0cfe6447eb24749903ee84c00628842.png)

2.基于aspectJ实现(配置/注解)

&emsp;&emsp;切入点表达式

![image.png](https://fynotefile.oss-cn-zhangjiakou.aliyuncs.com/fynote/fyfile/1462/1660284531085/872bad6e58ec4591a38f2eff18f79f01.png)

![image.png](https://fynotefile.oss-cn-zhangjiakou.aliyuncs.com/fynote/fyfile/1462/1660284531085/2c6c40514c584b23a8b2051ae8dc82a4.png)

AOP的代理方式：

![image.png](https://fynotefile.oss-cn-zhangjiakou.aliyuncs.com/fynote/fyfile/1462/1660284531085/9b290c4387fe464c9711760fcae5d714.png)

&emsp;&emsp;在Spring里可以把一个类型注册成Spring里的一个Bean，这时候Spring就会帮我们把这个Bean初始化，变成一个可用的对象。加入我们需要在上面做一些增强，就是我们所谓的AOP。这时候我们就需要在中间加一层代理类或者增强类。

* JdkProxy：假如说这个对象所在的类上面有接口（基于接口来做的），Spring会默认使用JdkProxy（JDK的动态代理），来生成一个代理，在代理里进一步的把所有对这个类做的增强操作，放到代理执行的代码里面。然后先做了增强的操作，再去调用原本的类的他的方法。
* proxyTargetClass：如果要代理的类有接口但想强制不用默认JDK的动态代理，也是用字节码增强的技术，就可以开启proxyTargetClass选项。同CGlib。
* CGlib：假如说要增强或代理的这个类没有接口，只有一个类的定义，Spring会默认使用CGlib，对他做字节码增强。相当于硬生生的给他生成一个子类。在这个子类里，当我们调用原先这个类的某个方法时，先做增强操作，再去调原本类的方法，最后再把结果返回回来。

# 4.介绍下Spring中的事务处理

&emsp;&emsp;数据库事务(Database Transaction) ，是指作为单个逻辑工作单元执行的一系列操作，要么完全地执行，要么完全地不执行。 事务处理可以确保除非事务性单元内的所有操作都成功完成，否则不会永久更新面向数据的资源。通过将一组相关操作组合为一个要么全部成功要么全部失败的单元，可以简化错误恢复并使应用程序更加可靠。一个逻辑工作单元要成为事务，必须满足所谓的ACID（原子性、一致性、隔离性和持久性）属性。事务是数据库运行中的逻辑工作单位，由DBMS中的事务管理子系统负责事务的处理。

* 原子性（Atomicity）：一个事务是一个不可分割的工作单位，事务中包括的动作要么都做要么都不做。
* 一致性（Consistency）：事务必须保证数据库从一个一致性状态变到另一个一致性状态，一致性和原子性是密切相关的。
* 隔离性（Isolation）：一个事务的执行不能被其它事务干扰，即一个事务内部的操作及使用的数据对并发的其它事务是隔离的，并发执行的各个事务之间不能互相打扰。
* 持久性（Durability）：持久性也称为永久性，指一个事务一旦提交，它对数据库中数据的改变就是永久性的，后面的其它操作和故障都不应该对其有任何影响。

在Spring中事务的实现的两种方式：

1. 基于XML声明式事务
2. 基于注解的使用

![image.png](https://fynotefile.oss-cn-zhangjiakou.aliyuncs.com/fynote/fyfile/1462/1660284531085/624cd7cb064f4d36827902ad3f894eb1.png)

![image.png](https://fynotefile.oss-cn-zhangjiakou.aliyuncs.com/fynote/fyfile/1462/1660284531085/3a1a073a6a3440c69d01704a56dbfeb8.png)

![image.png](https://fynotefile.oss-cn-zhangjiakou.aliyuncs.com/fynote/fyfile/1462/1660284531085/047480c129cf484dbb610329a86616f3.png)

事务的传播行为：

| 事务行为                  | 说明                                                         |
| :------------------------ | :----------------------------------------------------------- |
| PROPAGATION_REQUIRED      | 支持当前事务，假设当前没有事务。就新建一个事务               |
| PROPAGATION_SUPPORTS      | 支持当前事务，假设当前没有事务，就以非事务方式运行           |
| PROPAGATION_MANDATORY     | 支持当前事务，假设当前没有事务，就抛出异常                   |
| PROPAGATION_REQUIRES_NEW  | 新建事务，假设当前存在事务。把当前事务挂起                   |
| PROPAGATION_NOT_SUPPORTED | 以非事务方式运行操作。假设当前存在事务，就把当前事务挂起     |
| PROPAGATION_NEVER         | 以非事务方式运行，假设当前存在事务，则抛出异常               |
| PROPAGATION_NESTED        | 如果当前存在事务，则在嵌套事务内执行。如果当前没有事务，则执行与PROPAGATION_REQUIRED类似的操作。 |

```java
ServiceA {   
     void methodA() {
         ServiceB.methodB();
     }
}

```

```java
ServiceB { 
     void methodB() {
     }  
}

```

有个问题：如果methodA()方法中调用了 `this.b()`这时b方法中的事务会生效吗?

> AopContext.currentProxy();

具体的声明案例

```txt
@Transactional(propagation=Propagation.REQUIRED)
如果有事务, 那么加入事务, 没有的话新建一个(默认情况下)
@Transactional(propagation=Propagation.NOT_SUPPORTED)
容器不为这个方法开启事务
@Transactional(propagation=Propagation.REQUIRES_NEW)
不管是否存在事务,都创建一个新的事务,原来的挂起,新的执行完毕,继续执行老的事务
@Transactional(propagation=Propagation.MANDATORY)
必须在一个已有的事务中执行,否则抛出异常
@Transactional(propagation=Propagation.NEVER)
必须在一个没有的事务中执行,否则抛出异常(与Propagation.MANDATORY相反)
@Transactional(propagation=Propagation.SUPPORTS)
如果其他bean调用这个方法,在其他bean中声明事务,那就用事务.如果其他bean没有声明事务,那就不用事务.
```

**事务的隔离级别**

&emsp;&emsp;事务隔离级别指的是一个事务对数据的修改与另一个并行的事务的隔离程度，当多个事务同时访问相同数据时，如果没有采取必要的隔离机制，就可能发生以下问题：

| 问题       | 描述                                                         |
| ---------- | :----------------------------------------------------------- |
| 脏读       | 一个事务读到另一个事务未提交的更新数据，所谓脏读，就是指事务A读到了事务B还没有提交的数据，比如银行取钱，事务A开启事务，此时切换到事务B，事务B开启事务-->取走100元，此时切换回事务A，事务A读取的肯定是数据库里面的原始数据，因为事务B取走了100块钱，并没有提交，数据库里面的账务余额肯定还是原始余额，这就是脏读 |
| 幻读       | <font color='red'>是指当事务不是独立执行时发生的一种现象</font>，例如第一个事务对一个表中的数据进行了修改，这种修改涉及到表中的全部数据行。 同时，第二个事务也修改这个表中的数据，这种修改是向表中插入一行新数据。那么，以后就会发生操作第一个事务的用户发现表中还有没有修改的数据行，就好象 发生了幻觉一样。 |
| 不可重复读 | <font color='red'>在一个事务里面的操作中发现了未被操作的数据</font> 比方说在同一个事务中先后执行两条一模一样的select语句，期间在此次事务中没有执行过任何DDL语句，但先后得到的结果不一致，这就是不可重复读 |

Spring中支持的隔离级别

| 隔离级别        | 描述                                                         |
| :-------------- | :----------------------------------------------------------- |
| DEFAULT         | 使用数据库本身使用的隔离级别<br> ORACLE（读已提交） MySQL（可重复读） |
| READ_UNCOMITTED | 读未提交（脏读）最低的隔离级别，一切皆有可能。               |
| READ_COMMITED   | 读已提交，ORACLE默认隔离级别，有幻读以及不可重复读风险。     |
| REPEATABLE_READ | 可重复读，解决不可重复读的隔离级别，但还是有幻读风险。       |
| SERLALIZABLE    | 串行化，最高的事务隔离级别，不管多少事务，挨个运行完一个事务的所有子事务之后才可以执行另外一个事务里面的所有子事务，这样就解决了脏读、不可重复读和幻读的问题了 |

![image.png](https://fynotefile.oss-cn-zhangjiakou.aliyuncs.com/fynote/fyfile/1462/1660284531085/86503d5191ca4dd0b71bf71339ae796a.png)

# 5.介绍下Bean的生命周期

&emsp;&emsp;Spring的核心IoC就是管理Bean对象的生命周期，那么Bean的生命周期具体是怎么样的呢？

![image.png](https://fynotefile.oss-cn-zhangjiakou.aliyuncs.com/fynote/fyfile/1462/1660284531085/36ff93289ecb441892693bdc724d4f8f.png)

Bean的完整生命周期经历了各种方法调用，这些方法可以划分为以下几类：

1. Bean自身的方法：这个包括了Bean本身调用的方法和通过配置文件中<bean>的init-method和destroy-method指定的方法
2. Bean级生命周期接口方法：这个包括了BeanNameAware、BeanFactoryAware、InitializingBean和DiposableBean这些接口的方法
3. 容器级生命周期接口方法：这个包括了InstantiationAwareBeanPostProcessor 和 BeanPostProcessor 这两个接口实现，一般称它们的实现类为“后处理器”。
4. 工厂后处理器接口方法：这个包括了AspectJWeavingEnabler,ConfigurationClassPostProcessor,CustomAutowireConfigurer等等非常有用的工厂后处理器接口的方法。工厂后处理器也是容器级的。在应用上下文装配配置文件之后立即调用。

生命周期简单说明：

1. Spring容器 从XML 文件中读取bean的定义，并实例化bean。
2. Spring根据bean的定义填充所有的属性。如果bean实现了BeanNameAware 接口，Spring 传递bean 的ID 到 setBeanName方法。
3. 如果Bean 实现了 BeanFactoryAware 接口， Spring传递beanfactory 给setBeanFactory 方法。
   如果有任何与bean相关联的BeanPostProcessors，Spring会在postProcesserBeforeInitialization()方法内调用它们。
4. 如果bean实现IntializingBean了，调用它的afterPropertySet方法，如果bean声明了初始化方法，调用此初始化方法。
5. 如果有BeanPostProcessors 和bean 关联，这些bean的postProcessAfterInitialization() 方法将被调用。
6. 如果bean实现了 DisposableBean，它将调用destroy()方法。

https://blog.csdn.net/qq_38526573/article/details/88143169

# 6.介绍下Spring用到的设计模式

## 6.1 单例模式

&emsp;&emsp;单例模式应该是大家印象最深的一种设计模式了。在Spring中最明显的使用场景是在配置文件中配置注册bean对象的时候**设置scope的值为singleton** 。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans 
 http://www.springframework.org/schema/beans/spring-beans.xsd">
	<bean class="com.dpb.pojo.User" id="user" scope="singleton">
		<property name="name" value="波波烤鸭"></property>
	</bean>
</beans>

```

## 6.2 原型模式

&emsp;&emsp;原型模式也叫克隆模式，Spring中该模式使用的很明显，和单例一样在bean标签中设置scope的属性prototype即表示该bean以克隆的方式生成

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans 
 http://www.springframework.org/schema/beans/spring-beans.xsd">
	<bean class="com.dpb.pojo.User" id="user" scope="prototype">
		<property name="name" value="波波烤鸭"></property>
	</bean>
</beans>

```

## 6.3 模板模式

&emsp;&emsp;模板模式的核心是父类定义好流程，然后将流程中需要子类实现的方法就抽象话留给子类实现，Spring中的JdbcTemplate就是这样的实现。我们知道jdbc的步骤是固定

* 加载驱动,
* 获取连接通道,
* 构建sql语句.
* 执行sql语句,
* 关闭资源

  在这些步骤中第3步和第四步是不确定的,所以就留给客户实现，而我们实际使用JdbcTemplate的时候也确实是只需要构建SQL就可以了.这就是典型的模板模式。我们以query方法为例来看下JdbcTemplate中的代码.

![image.png](https://fynotefile.oss-cn-zhangjiakou.aliyuncs.com/fynote/1462/1646114654000/a1f1150a38f94b518794e3e5df467092.png)

## 6.4 观察者模式

&emsp;&emsp;观察者模式定义的是对象间的一种一对多的依赖关系，当一个对象的状态发生改变时，所有依赖于它的对象都得到通知并被自动更新。使用比较场景是在监听器中而spring中Observer模式常用的地方也是listener的实现。如ApplicationListener.

![image.png](https://fynotefile.oss-cn-zhangjiakou.aliyuncs.com/fynote/1462/1646114654000/32247ef6524c430dab31ea97294f28ae.png)

## 6.5 工厂模式

**简单工厂模式**：

&emsp;&emsp;简单工厂模式就是通过工厂根据传递进来的参数决定产生哪个对象。Spring中我们通过getBean方法获取对象的时候根据id或者name获取就是简单工厂模式了。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.3.xsd">

	<context:annotation-config/>
	<bean class="com.dpb.pojo.User" id="user"  >
		<property name="name" value="波波烤鸭"></property>
	</bean>
</beans>

```

**工厂方法模式**：

&emsp;&emsp;在Spring中我们一般是将Bean的实例化直接交给容器去管理的，实现了使用和创建的分离，这时容器直接管理对象，还有种情况是，bean的创建过程我们交给一个工厂去实现，而Spring容器管理这个工厂。这个就是我们讲的工厂模式，在Spring中有两种实现一种是静态工厂方法模式，一种是动态工厂方法模式。以静态工厂来演示

```java
/**
 * User 工厂类
 * @author dpb[波波烤鸭]
 *
 */
public class UserFactory {

	/**
	 * 必须是static方法
	 * @return
	 */
	public static UserBean getInstance(){
		return new UserBean();
	}
}

```

application.xml文件中注册

```xml
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans 
	http://www.springframework.org/schema/beans/spring-beans.xsd">
	<!-- 静态工厂方式配置 配置静态工厂及方法 -->
	<bean class="com.dpb.factory.UserFactory" factory-method="getInstance" id="user2"/>
</beans>

```

## 6.6 适配器模式

&emsp;&emsp;将一个类的接口转换成客户希望的另外一个接口。使得原本由于接口不兼容而不能一起工作的那些类可以在一起工作。这就是适配器模式。在Spring中在AOP实现中的Advice和interceptor之间的转换就是通过适配器模式实现的。

```java
class MethodBeforeAdviceAdapter implements AdvisorAdapter, Serializable {

	@Override
	public boolean supportsAdvice(Advice advice) {
		return (advice instanceof MethodBeforeAdvice);
	}

	@Override
	public MethodInterceptor getInterceptor(Advisor advisor) {
		MethodBeforeAdvice advice = (MethodBeforeAdvice) advisor.getAdvice();
		// 通知类型匹配对应的拦截器
		return new MethodBeforeAdviceInterceptor(advice);
	}
}

```

## 6.7 装饰者模式

&emsp;&emsp;装饰者模式又称为包装模式(Wrapper),作用是用来动态的为一个对象增加新的功能。装饰模式是一种用于代替继承的技术，无须通过继承增加子类就能扩展对象的新功能。使用对象的关联关系代替继承关系，更加灵活，同时避免类型体系的快速膨胀。
&emsp;&emsp;spring中用到的包装器模式在类名上有两种表现：一种是类名中含有Wrapper，另一种是类名中含有Decorator。基本上都是动态地给一个对象添加一些额外的职责。
&emsp;&emsp;具体的使用在Spring session框架中的SessionRepositoryRequestWrapper使用包装模式对原生的request的功能进行增强，可以将session中的数据和分布式数据库进行同步，这样即使当前tomcat崩溃，session中的数据也不会丢失。

```xml
<dependency>
	<groupId>org.springframework.session</groupId>
	<artifactId>spring-session</artifactId>
	<version>1.3.1.RELEASE</version>
</dependency>

```

## 6.8 代理模式

&emsp;&emsp;代理模式应该是大家非常熟悉的设计模式了，在Spring中AOP的实现中代理模式使用的很彻底.

## 6.9 策略模式

&emsp;&emsp;策略模式对应于解决某一个问题的一个算法族，允许用户从该算法族中任选一个算法解决某一问题，同时可以方便的更换算法或者增加新的算法。并且由客户端决定调用哪个算法，spring中在实例化对象的时候用到Strategy模式。XmlBeanDefinitionReader,PropertiesBeanDefinitionReader

## 6.10 责任链默认

AOP中的拦截器链

## 6.11 委托者模式

DelegatingFilterProxy，整合Shiro，SpringSecurity的时候都有用到。

.....

# 7.Spring中常用的注解有哪些

举例说明：@RestController @Import  @EnableXXX  @Indexd。。。。