package edu.sdau.spring_demo.beans.factory.support;

import edu.sdau.spring_demo.beans.BeanDefinition;

/**
 * 注册表对象
 * @author ginga
 * @version 1.0
 * @ClassName BeanDefinitionRegistry
 * @Date 12/11/2022 上午11:41
 */
public interface BeanDefinitionRegistry {

    // 注册BeanDefinition对象到注册表中
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    // 删除指定名称的BeanDefinition对象
    void removeBeanDefinition(String beanName);

    // 获取指定名称的BeanDefinition对象
    BeanDefinition getBeanDefinition(String beanName);

    // 是否包含指定名称的BeanDefinition对象
    boolean containsBeanDefinition(String beanName);

    // 获取BeanDefinition对象的数量
    int getBeanDefinitionCount();

    // 获取所有BeanDefinition对象的名称
    String[] getBeanDefinitionNames();

}
