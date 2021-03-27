package com.github.hikarido.projects;

import com.github.hikarido.projects.components.DependencyOwner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@ComponentScan
@PropertySource("application.properties")
public class App {
    public static void main(String[] args) {
        final ConfigurableApplicationContext applicationContext = new AnnotationConfigApplicationContext(App.class);
        DependencyOwner owner = applicationContext.getBean(DependencyOwner.class);
        System.out.println(owner.toString());
        applicationContext.close();
    }

}
