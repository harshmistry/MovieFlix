package io.egen.movieflix;

import io.egen.movieflix.constants.DBFinalVariables;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
public class JPAConfig {

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory()
	{
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(getDataSource());
		emf.setPackagesToScan("io.egen.movieflix.entity"); //tell spring where to find all entities
		emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		emf.setJpaProperties(getJPAProperties());
		
		return emf;
	}
	
	@Bean
	public DataSource getDataSource()
	{
		DriverManagerDataSource ds=new DriverManagerDataSource();
		ds.setDriverClassName(DBFinalVariables.DB_DRIVER_CLASSNAME);
		ds.setUrl(DBFinalVariables.DB_URL);
		ds.setUsername(DBFinalVariables.DB_USERNAME);
		ds.setPassword(DBFinalVariables.DB_PASSWORD);
		
		return ds;
	}
	
	@Bean
	public PlatformTransactionManager getTransactionManager(EntityManagerFactory emf)
	{
		JpaTransactionManager tnxManager=new JpaTransactionManager(emf);
		return tnxManager;
	}
	
	public Properties getJPAProperties()
	{
		Properties prop=new Properties();
		prop.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		prop.setProperty("hibernate.hbm2ddl.auto", "create-drop");
		//prop.setProperty("hibernate.hbm2ddl.auto", "validate");
		prop.setProperty("hibernate.show_sql", "true");
		prop.setProperty("hibernate.format_sql", "true");
		
		return prop;
	}
}
