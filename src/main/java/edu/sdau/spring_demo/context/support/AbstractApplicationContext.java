package edu.sdau.spring_demo.context.support;

import edu.sdau.spring_demo.beans.factory.support.BeanDefinitionReader;
import edu.sdau.spring_demo.beans.factory.support.BeanDefinitionRegistry;
import edu.sdau.spring_demo.context.ApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * ApplicationContext接口的抽象实现, 用于立即加载 bean 定义并初始化所有单例.
 * @author ginga
 * @version 1.0
 * @ClassName AbstractApplicationContext
 * @Date 12/11/2022 下午1:26
 */
public abstract class AbstractApplicationContext implements ApplicationContext {

    // 声明解析器
    protected BeanDefinitionReader beanDefinitionReader;

    /**
     * 用于存储bean的容器
     * TODO: 2021/12/11 不考虑线程安全
     */
    protected Map<String, Object> singletonObjects = new HashMap<>();

    // 声明记录配置文件路径的变量
    protected String configLocation;

    @Override
    public void refresh() throws Exception {
        // 1. 加载配置文件
        beanDefinitionReader.loadBeanDefinitions(configLocation);
        // 2. 初始化bean
        finishBeanInitialization();
    }

    // 初始化bean
    private void finishBeanInitialization() throws Exception {
        // 1. 获取注册表对象
        final BeanDefinitionRegistry registry = beanDefinitionReader.getRegistry();
        // 2. 获取BeanDefinition对象
        final String[] beanNames = registry.getBeanDefinitionNames();
        // 3. 进行所有bean的初始化
        for (String beanName : beanNames) {
            getBean(beanName);
        }
    }
}
