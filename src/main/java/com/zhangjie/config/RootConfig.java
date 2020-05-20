package com.zhangjie.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

// Spring 的根容器不扫描 Controller 组件，是一个父容器
@ComponentScan(value="com.zhangjie",excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class})
})
@EnableTransactionManagement
@PropertySource("classpath:/dbconfig.properties")
@MapperScan("com.zhangjie.dao.*")
public class RootConfig {
    // 控制业务逻辑：数据源、事务控制、aop

    @Value("${jdbc.username}")
    private String userName;
    @Value("${jdbc.password}")
    private String password;
    @Value("${jdbc.driverClass}")
    private String driverClass;
    @Value("${jdbc.jdbcUrl}")
    private String jdbcUrl;
    // 配置数据源
    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(userName);
        dataSource.setPassword(password);
        dataSource.setDriverClass(driverClass);
        dataSource.setJdbcUrl(jdbcUrl);
        return dataSource;
    }
    // 整合mybatis
    // 目的：
    //  1.Spring 管理所有组件、mapper的实现
    //  2.Spring 用来管理事务
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setConfigLocation(new ClassPathResource("classpath*:mybatis-config.xml"));
        factoryBean.setMapperLocations(new ClassPathResource("classpath*:com/zhangjie/**/*.xml"));
        return factoryBean.getObject();
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource)  {
        return new DataSourceTransactionManager(dataSource);
    }

}