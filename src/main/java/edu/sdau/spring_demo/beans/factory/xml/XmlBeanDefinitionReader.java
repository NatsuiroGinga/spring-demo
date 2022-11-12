package edu.sdau.spring_demo.beans.factory.xml;

import edu.sdau.spring_demo.beans.BeanDefinition;
import edu.sdau.spring_demo.beans.MutablePropertyValues;
import edu.sdau.spring_demo.beans.PropertyValue;
import edu.sdau.spring_demo.beans.factory.support.BeanDefinitionReader;
import edu.sdau.spring_demo.beans.factory.support.BeanDefinitionRegistry;
import edu.sdau.spring_demo.beans.factory.support.SimpleBeanDefinitionRegistry;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

/**
 * 针对xml配置文件的解析器
 * @author ginga
 * @version 1.0
 * @ClassName XmlBeanDefinitionReader
 * @Date 12/11/2022 下午12:35
 */
public class XmlBeanDefinitionReader implements BeanDefinitionReader {

    private final BeanDefinitionRegistry registry;

    public XmlBeanDefinitionReader() {
        registry = new SimpleBeanDefinitionRegistry();
    }

    @Override
    public BeanDefinitionRegistry getRegistry() {
        return registry;
    }

    /**
     * 读取xml配置文件，解析成BeanDefinition对象
     * 注册到BeanDefinitionRegistry中
     * @param location spring配置文件的路径 例如：classpath:applicationContext.xml
     */
    @Override
    public void loadBeanDefinitions(String location) throws Exception {
        // 使用dom4j解析xml文件
        final SAXReader reader = new SAXReader();
        // 获取类路径下的配置文件
        final InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(location);
        final Document document = reader.read(inputStream);
        // 根据document对象获取根标签对象 <beans/>
        final Element rootElement = document.getRootElement();
        // 获取根标签下的所有子标签 <bean/>
        final List<Element> beanElements = rootElement.elements("bean");
        // 遍历bean标签
        for (Element beanElement : beanElements) {
            // 获取bean标签的id属性
            final String id = beanElement.attributeValue("id");
            // 获取bean标签的class属性
            final String className = beanElement.attributeValue("class");
            // 将id和class属性封装到BeanDefinition对象中
            final BeanDefinition beanDefinition = new BeanDefinition();
            beanDefinition.setId(id);
            beanDefinition.setClassName(className);

            // 创建MutablePropertyValues对象
            final MutablePropertyValues mutablePropertyValues = new MutablePropertyValues();

            // 获取bean标签下的所有子标签 property
            final List<Element> propertyElements = beanElement.elements("property");
            // 遍历property标签
            for (Element propertyElement : propertyElements) {
                // 获取property标签的name, value, ref属性
                final String name = propertyElement.attributeValue("name");
                final String ref = propertyElement.attributeValue("ref");
                final String value = propertyElement.attributeValue("value");
                // 将name, value和ref属性封装到PropertyValue对象中
                final PropertyValue propertyValue = new PropertyValue(name, ref, value);
                // 将PropertyValue对象添加到MutablePropertyValues对象中
                mutablePropertyValues.addPropertyValue(propertyValue);
            }

            // 将mutablePropertyValues对象添加到BeanDefinition对象中
            beanDefinition.setPropertyValues(mutablePropertyValues);
            // 将BeanDefinition对象添加到BeanDefinitionRegistry对象中
            registry.registerBeanDefinition(id, beanDefinition);

        }

    }
}
