#------------------------------------springboot2.1.3----------------------------------------
#------------------------------------java1.8.0_171---------------------------------------------------
#-------------------------------------maven3.3.9 ------------------------------------------------------------------------------



#------------------------------------1：配置Log4j2，实现不同环境日志打印-------------------------------------------

#-------------------------------------整合imbok
#-------------------------------------整合jpa
#-------------------------------------异常
整合全局异常,自定义异常，统一返回接口，Validator + BindResult 进行校验，
校验规则和错误提示信息配置完毕后，接下来只需要在接口需要校验的参数上加上 @Valid 注解，并添加 BindResult 参数即可方便完成验证：
通过 Validator + 自动抛出异常来完成了方便的参数校验；
通过全局异常处理 + 自定义异常完成了异常操作的规范；
通过数据统一响应完成了响应数据的规范；
多个方面组装非常优雅的完成了后端接口的协调，让开发人员有更多的经历注重业务逻辑代码，轻松构建后端接口。

#-------------------------------------1:整合 Swagger2 ,构建接口管理界面-----------------------------------------------
整合到Spring Boot中，构建强大RESTful API文档。省去接口文档管理工作，修改代码，自动更新，Swagger2也提供了强大的页面测试功能来调试RESTful API。
Api：修饰整个类，描述Controller的作用
ApiOperation：描述一个类的一个方法，或者说一个接口
ApiParam：单个参数描述
ApiModel：用对象来接收参数
ApiProperty：用对象接收参数时，描述对象的一个字段
ApiResponse：HTTP响应其中1个描述
ApiResponses：HTTP响应整体描述
ApiIgnore：使用该注解忽略这个API
ApiError ：发生错误返回的信息
ApiImplicitParam：一个请求参数
ApiImplicitParams：多个请求参数

#-------------------------------------2：基于SpringBoot框架，管理Excel和PDF文件类型------------------------------------------------
Apache POI是Apache软件基金会的开源类库，POI提供API给Java程序对Microsoft Office格式档案读和写的功能。


#--------------------------------------3：基于SpringBoot框架，管理Xml和CSV文件类型-------------------------------------------------------------------------
Dom4j是基于Java编写的XML文件操作的API包，用来读写XML文件。具有性能优异、功能强大和简单易使用的特点。

#--------------------------------------4：集成 JavaMail ,实现异步发送邮件-----------------------------------------------------------
(1)、Message 类:
javax.mail.Message 类是创建和解析邮件的一个抽象类
子类javax.mail.internet.MimeMessage ：表示一份电子邮件。
发送邮件时，首先创建出封装了邮件数据的 Message 对象， 然后把这个对象传递给邮件发送Transport 类，执行发送。
接收邮件时，把接收到的邮件数据封装在Message 类的实例中，从这个对象中解析收到的邮件数据。

(2)、Transport 类
javax.mail.Transport 类是发送邮件的核心API 类
创建好 Message 对象后， 只需要使用邮件发送API 得到 Transport 对象， 然后把 Message 对象传递给 Transport 对象， 
并调用它的发送方法， 就可以把邮件发送给指定的邮件服务器。

(3)、Store 类
javax.mail.Store 类是接收邮件的核心 API 类
实例对象代表实现了某个邮件接收协议的邮件接收对象，接收邮件时， 只需要得到 Store 对象， 然后调用 Store 对象的接收方法，
就可以从指定的邮件服务器获得邮件数据，并把这些邮件数据封装到表示邮件的 Message 对象中。

(4)、Session 类：
javax.mail.Session 类定义邮件服务器的主机名、端口号、协议等
Session 对象根据这些信息构建用于邮件收发的 Transport 和 Store 对象， 以及为客户端创建 Message 对象时提供信息支持。