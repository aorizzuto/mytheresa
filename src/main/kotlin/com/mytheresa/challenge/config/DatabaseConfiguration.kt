//package com.mytheresa.challenge.config
//
//import com.zaxxer.hikari.HikariDataSource
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.core.env.Environment
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
//import java.util.*
//import javax.sql.DataSource
//
//@Configuration
//@EnableJpaRepositories(
//    entityManagerFactoryRef = "entityManagerFactory"
//)
//class DatabaseConfiguration(
//    val env: Environment
//) {
//
//    @Bean
//    fun dataSourceData(): DataSource {
//        return HikariDataSource()
//    }
//
//    @Bean
//    fun entityManagerFactory(): LocalContainerEntityManagerFactoryBean {
//        val factory = LocalContainerEntityManagerFactoryBean()
//        factory.dataSource = dataSourceData()
////        factory.setPackagesToScan()
//        factory.jpaVendorAdapter = HibernateJpaVendorAdapter()
//        val jpaProperties = Properties()
//        jpaProperties["hibernate.hbm2ddl.auto"] = env.getProperty("spring.jpa.hibernate.ddl_auto")
//        env.getProperty("spring.jpa.show-sql")?.let { value ->
//            jpaProperties["hibernate.show-sql"] = value
//        }
//        factory.setJpaProperties(jpaProperties)
//        return factory
//    }
//}