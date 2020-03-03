package a.b.c;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

@Configuration
@PropertySource("classpath:application.yml")
public class AppConfiguration {

    @Value("${t1}")
    private String s;



    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigurer(){
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setLocation(new ClassPathResource("/application.yml"));
        return configurer;
    }
    @Bean
    static YamlPropertiesFactoryBean yamlPropertiesFactoryBean(){
        YamlPropertiesFactoryBean factoryBean = new YamlPropertiesFactoryBean();
        return factoryBean;
    }

    @Bean
    @ConfigurationProperties(prefix = "t1")
    public TestBean testBean(){
        System.out.println(s);
        return new TestBean();
    }
}

