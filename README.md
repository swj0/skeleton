

简单的springboot项目骨架，这里的demo配置了mybatis，mongo等持久化，实际生产中会有更多更复杂的项目配置<br>  
1、提供rest配置<br>  
2、提供auth接口调用安全验证特性(通常会做成一个starter，但这里为了方便阅读，与业务代码放在同一项目下)<br>  
3、可以打包成jar，也可以打包成war包，可以根据不同的profile打包成不同环境的包 命令 mvn clean package -Dmaven.test.skip=true -P prod<br>  
<br>
4、使用swagger对接口进行管理,打包运行后可以查看   localhost:8085/swagger-ui.html
