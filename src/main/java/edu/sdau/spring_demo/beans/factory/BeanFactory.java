package edu.sdau.spring_demo.beans.factory;

/**
 * IOC父容器接口
 * @author ginga
 * @version 1.0
 * @ClassName BeanFactory
 * @Date 12/11/2022 下午1:14
 */
public interface BeanFactory {

    /**
     * 根据bean的id获取bean对象
     * @param name bean的名称
     * @return bean对象
     */
    Object getBean(String name) throws Exception;

    /**
     * 根据bean的id获取bean对象
     * @param name bean的名称
     * @param clazz bean的类型
     * @param <T> bean的类型
     * @return bean对象
     */
    <T> T getBean(String name, Class<? extends T> clazz) throws Exception;
}
