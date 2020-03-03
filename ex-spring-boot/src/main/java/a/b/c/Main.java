package a.b.c;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.io.IOException;

public class Main {


    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("a");
        TestBean bean = context.getBean(TestBean.class);
        System.out.println(context.getBean("propertyConfigurer", PropertySourcesPlaceholderConfigurer.class).getAppliedPropertySources());
        System.out.println(bean.str);
    }
}
