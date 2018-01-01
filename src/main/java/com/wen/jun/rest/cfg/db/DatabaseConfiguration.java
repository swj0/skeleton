package com.wen.jun.rest.cfg.db;

import java.io.IOException;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.wen.jun.rest.constants.ViewEnum;


@Configuration
@EnableTransactionManagement
@MapperScan(value="com.wen.jun.rest.dao")
public class DatabaseConfiguration  {
	
	private static final Logger log = LoggerFactory.getLogger(DatabaseConfiguration.class);

	public DatabaseConfiguration (){
	}
	
	@Autowired
	com.mchange.v2.c3p0.ComboPooledDataSource dataSource;
	
	@Autowired
    private ResourceLoader resourceLoader;
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception{
		ResourcePatternResolver rr = new PathMatchingResourcePatternResolver(resourceLoader);
		
		MyCustSqlSessionFactoryBean sessionFactory = new MyCustSqlSessionFactoryBean(){
			@Override
            protected void registerJavaTypeMaptypeHandlers(TypeHandlerRegistry typeHandlerRegistry) {

                //应用spring的那种扫描包的方式来做...
                try {
                    Resource[] resources = rr.getResources("classpath*:com/rsc/data20/cms/business/constant/**/*.class");
                    MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(rr);
                    for (Resource resource : resources) {
                        MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(resource);
                        Class<?> cls = Class.forName(metadataReader.getClassMetadata().getClassName());
                        if( ViewEnum.class.isAssignableFrom(cls) && !cls.equals(ViewEnum.class)) {
                            log.info("注册 ViewEnum = {} 枚举类到 mybatis的typehandler中", cls.getName());
                            typeHandlerRegistry.register(cls, new ViewEnumTypeHandler(cls));
                        }
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
		};
		
		//1:数据源
		sessionFactory.setDataSource(dataSource);
		//2:configuration配置
		sessionFactory.setConfigLocation(resourceLoader.getResource("classpath:config/mybatis-config.xml"));
		
		
		//3:Mapper配置
        try {
        	Resource[] resources1 = rr.getResources("classpath:com/wen/jun/auth/mapping/**/*.xml");
            Resource[] resources2 = rr.getResources("classpath:customMybatisXml/**/*.xml");
            	
            sessionFactory.setMapperLocations(ArrayUtils.addAll(resources2, resources1));
            
            
            
        } catch (Exception e) {
			e.printStackTrace();
		}
        
		return sessionFactory.getObject();
	}

	@Bean(destroyMethod = "clearCache",name="template20")
    public SqlSessionTemplate sqlSessionTemplate() throws Exception{
        return new SqlSessionTemplate(sqlSessionFactory());
    }
}














