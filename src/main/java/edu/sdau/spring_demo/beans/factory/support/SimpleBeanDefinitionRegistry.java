package edu.sdau.spring_demo.beans.factory.support;

import edu.sdau.spring_demo.beans.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * 注册表接口的子实现类
 * @author ginga
 * @version 1.0
 * @ClassName BeanDefinitionRegistryImpl
 * @Date 12/11/2022 上午11:44
 */
public class SimpleBeanDefinitionRegistry implements BeanDefinitionRegistry {

    // 用来存储BeanDefinition对象的容器
    // TODO: 12/11/2022 为了简化, 不用ConcurrentHashMap(线程安全), 用HashMap
    private final Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    @Override
    public void removeBeanDefinition(String beanName) {
        beanDefinitionMap.remove(beanName);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        return beanDefinitionMap.get(beanName);
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return beanDefinitionMap.containsKey(beanName);
    }

    @Override
    public int getBeanDefinitionCount() {
        return beanDefinitionMap.size();
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return beanDefinitionMap.keySet().toArray(new String[0]);
    }
}
