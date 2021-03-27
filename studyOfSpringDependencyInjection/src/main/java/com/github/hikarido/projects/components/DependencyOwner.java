package com.github.hikarido.projects.components;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class DependencyOwner implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, InitializingBean, DisposableBean, AutoCloseable{
    private final DependencyForConstructorInjection dependencyForConstructorInjection;
    @Autowired
    private DependencyForFieldInjection dependencyForFieldInjection;


    @Autowired
    public DependencyOwner(DependencyForConstructorInjection dependencyForConstructorInjection) {
        this.dependencyForConstructorInjection = dependencyForConstructorInjection;
        System.out.println("ctor");
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("DependencyOwner");
        final String lineSeparator = System.getProperty("line.separator");
        stringBuilder.append(lineSeparator);
        stringBuilder.append(dependencyForFieldInjection);
        stringBuilder.append(lineSeparator);
        stringBuilder.append(dependencyForConstructorInjection);
        stringBuilder.append(lineSeparator);
        return stringBuilder.toString();
    }

    @PostConstruct
    public void postConstract(){
        System.out.println("postConstract");
    }

    @PreDestroy
    public void preDestroy(){
        System.out.println("preDestroy");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("setBeanName");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("beanFactory");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("app ctx");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("destroy");
    }

    @Override
    public void close() throws Exception {
        System.out.println("Close");
    }
}
