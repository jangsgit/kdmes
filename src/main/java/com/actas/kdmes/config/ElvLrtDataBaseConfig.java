package com.actas.kdmes.config;



import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@MapperScan(value="com.actas.kdmes.mapper.elvlrt", sqlSessionFactoryRef="elvlrtSqlSessionFactory")
@EnableTransactionManagement


public class ElvLrtDataBaseConfig {
    @Bean(name="elvlrtDataSource")
    @ConfigurationProperties(prefix="spring.elvlrt.datasource")
    public DataSource masterDataSource() {
        //application.properties에서 정의한 DB 연결 정보를 빌드
        return DataSourceBuilder.create().build();
    }

    @Bean(name="elvlrtSqlSessionFactory")
    public SqlSessionFactory elvlrtSqlSessionFactory(@Qualifier("elvlrtDataSource") DataSource elvlrtDataSource, ApplicationContext applicationContext) throws Exception{
        //세션 생성 시, 빌드된 DataSource를 세팅하고 SQL문을 관리할 mapper.xml의 경로를 알려준다.
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(elvlrtDataSource);
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:com/actas/kdmes/mapper/elvlrt/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name="elvlrtSqlSessionTemplate")
    public SqlSessionTemplate elvlrtSqlSessionTemplate(SqlSessionFactory elvlrtSqlSessionFactory) throws Exception{
        return new SqlSessionTemplate(elvlrtSqlSessionFactory);
    }

}
