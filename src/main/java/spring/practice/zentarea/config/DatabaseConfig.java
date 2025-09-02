package spring.practice.zentarea.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    /**
     * Configuration for Redshift database when 'redshift' profile is active
     */
    @Configuration
    @Profile("redshift")
    public static class RedshiftConfig {
        
        @Bean
        @ConfigurationProperties(prefix = "spring.datasource.redshift")
        public DataSource dataSource() {
            return DataSourceBuilder.create().build();
        }
    }
}