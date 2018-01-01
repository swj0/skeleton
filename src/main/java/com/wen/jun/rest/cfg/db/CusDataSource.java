package com.wen.jun.rest.cfg.db;

import java.beans.PropertyVetoException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;


//自定义的数据源
@Configuration
public class CusDataSource {
	
	@Value("${db.mysql.jdbc_url}")
	private String jdbc_url;
	@Value("${db.mysql.jdbc_username}")
	private String jdbc_username;
	@Value("${db.mysql.jdbc_password}")
	private String jdbc_password;



	
	@Bean 
	public  ComboPooledDataSource getDataSource() throws PropertyVetoException{
		
		ComboPooledDataSource ds = new ComboPooledDataSource();
		ds.setUser(jdbc_username);
		ds.setPassword(jdbc_password);
		ds.setJdbcUrl(jdbc_url);
		ds.setDriverClass("com.mysql.jdbc.Driver");
		
		ds.setMaxPoolSize(20);//<!--连接池中保留的最大连接数。默认值: 15 -->   
		ds.setMinPoolSize(2);//<!-- 连接池中保留的最小连接数，默认为：3-->  
		ds.setInitialPoolSize(2);//<!-- 初始化连接池中的连接数，取值应在minPoolSize与maxPoolSize之间，默认为3-->  
		ds.setMaxIdleTime(60);//<!--最大空闲时间，60秒内未使用则连接被丢弃。若为0则永不丢弃。默认值: 0 -->   
		ds.setCheckoutTimeout(3000);//<!-- 当连接池连接耗尽时，客户端调用getConnection()后等待获取新连接的时间，超时后将抛出SQLException，如设为0则无限期等待。单位毫秒。默认: 0 -->   
		ds.setAcquireIncrement(2);//<!--当连接池中的连接耗尽的时候c3p0一次同时获取的连接数。默认值: 3 -->   
		ds.setAcquireRetryAttempts(0);//<!--定义在从数据库获取新连接失败后重复尝试的次数。默认值: 30 ；小于等于0表示无限次-->  
		ds.setAcquireRetryDelay(1000);//<!--重新尝试的时间间隔，默认为：1000毫秒-->   
		ds.setAutoCommitOnClose(false);// <!--关闭连接时，是否提交未提交的事务，默认为false，即关闭连接，回滚未提交的事务 -->   
		//ds.setAutomaticTestTable("Test");//<!--c3p0将建一张名为Test的空表，并使用其自带的查询语句进行测试。如果定义了这个参数那么属性preferredTestQuery将被忽略。你不能在这张Test表上进行任何操作，它将只供c3p0测试使用。默认值: null -->  
		ds.setBreakAfterAcquireFailure(false);// <!--如果为false，则获取连接失败将会引起所有等待连接池来获取连接的线程抛出异常，但是数据源仍有效保留，并在下次调用getConnection()的时候继续尝试获取连接。如果设为true，那么在尝试获取连接失败后该数据源将申明已断开并永久关闭。默认: false-->   
		
		ds.setIdleConnectionTestPeriod(60);//<!--每60秒检查所有连接池中的空闲连接。默认值: 0，不检查 -->   
		
		ds.setMaxStatements(100);//<!--c3p0全局的PreparedStatements缓存的大小。如果maxStatements与maxStatementsPerConnection均为0，则缓存不生效，只要有一个不为0，则语句的缓存就能生效。如果默认值: 0-->  
		ds.setMaxStatementsPerConnection(1);//<!--maxStatementsPerConnection定义了连接池内单个连接所拥有的最大缓存statements数。默认值: 0 -->   
		
		ds.setDataSourceName("db30_dataSource");
		return ds;
	}

	 
}

























