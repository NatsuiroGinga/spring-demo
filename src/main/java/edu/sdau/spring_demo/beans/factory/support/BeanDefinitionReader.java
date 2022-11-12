package edu.sdau.spring_demo.beans.factory.support;

/**
 * 用于解析配置文件的接口, 该接口定义规范
 * @author ginga
 * @version 1.0
 * @ClassName BeanDefinitionReader
 * @Date 12/11/2022 下午12:29
 */
public interface BeanDefinitionReader {

    // 获取注册表对象
    BeanDefinitionRegistry getRegistry();

    // 加载配置文件并在注册表中进行注册
    void loadBeanDefinitions(String location) throws Exception;

}
