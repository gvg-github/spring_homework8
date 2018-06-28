package net.zt.funcode.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import net.zt.funcode.config.security.SecurityConfig;

@Configuration
@EnableJpaRepositories("net.zt.funcode.repository")
@EnableTransactionManagement
@ComponentScan("net.zt.funcode.service")
@Import({SecurityConfig.class})
public class AppConfig {
	
	
	@Bean(name="dataSource")
	public DataSource getDataSource(){
		//Создания источника данных
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		//Задание параметров подключения к базе данных
		dataSource.setUrl("jdbc:postgresql://localhost:5432/geekbrains-lesson3");
		dataSource.setUsername("postgres");
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setPassword("123");
		return dataSource;
	}
	
	@Bean(name="entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean getEntityManager(){
	    //Создание класса фабрики реализующей интерфейс FactoryBean<EntityManagerFactory>
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		//Задание источника подключения
		factory.setDataSource(getDataSource());
		//Задание адаптера для конкретной реализации JPA, 
		//указывает, какая именно библиотека будет использоваться в качестве постовщика постоянства
		factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
	    //указание пакетов в котром будут находится классы-сущности
		factory.setPackagesToScan("net.zt.funcode.domain");
		
		//создание свойств для настройки Hibernate
		Properties jpaProperties = new Properties();
		//Указание диалекта конкретной базы данных,необходимо для генерации запросов Hibernate к БД
		jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		//Указание максимальной глубины связи(будет рассмотрено в след. уроке)
		jpaProperties.put("hibernate.max_fetch_depth", 3);
		//Определение максимального количества строк, возвращаемых за один запрос из БД
		jpaProperties.put("hibernate.jdbc.fetch_size", 50);
		//Определение максимального количества запросов при использовании пакетных операций
		jpaProperties.put("hibernate.jdbc.batch_size", 10);
		
		//Включает логгирование 
		jpaProperties.put("hibernate.show_sql", true);
		
		factory.setJpaProperties(jpaProperties);
		
		return factory;

	}
	
	@Bean(name="transactionManager")
	public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){
		
		JpaTransactionManager tm= new JpaTransactionManager();
		tm.setEntityManagerFactory(entityManagerFactory);
		return tm;
		
	}

}
