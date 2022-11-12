package edu.sdau.spring_demo.context.support;

import edu.sdau.spring_demo.beans.BeanDefinition;
import edu.sdau.spring_demo.beans.MutablePropertyValues;
import edu.sdau.spring_demo.beans.PropertyValue;
import edu.sdau.spring_demo.beans.factory.support.BeanDefinitionRegistry;
import edu.sdau.spring_demo.beans.factory.xml.XmlBeanDefinitionReader;
import edu.sdau.spring_demo.utils.MyStringUtils;

import java.lang.reflect.Method;

/**
 * IOC容器的子实现类
 * 加载类路径下的xml配置文件
 * @author ginga
 * @version 1.0
 * @ClassName ClassPathXmlApplicationContext
 * @Date 12/11/2022 下午1:40
 */
public class ClassPathXmlApplicationContext extends AbstractApplicationContext {

    public ClassPathXmlApplicationContext(String configLocation) {
        this.configLocation = configLocation;
        // 1. 创建解析器
        beanDefinitionReader = new XmlBeanDefinitionReader();
        // 2. 刷新容器
        try {
            refresh();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据bean的名称获取bean对象
     * @param name bean的名称
     * @return bean对象
     */
    @Override
    public Object getBean(String name) throws Exception {
        // 1. 判断容器中是否存在该bean
        if (singletonObjects.containsKey(name)) {
            return singletonObjects.get(name);
        }
        // 2. 不存在则创建bean
        final BeanDefinitionRegistry registry = beanDefinitionReader.getRegistry();
        final BeanDefinition beanDefinition = registry.getBeanDefinition(name);
        // 3. 获取bean的className
        final String className = beanDefinition.getClassName();
        // 4. 反射创建对象
        final Class<?> clazz = Class.forName(className);
        final Object bean = clazz.newInstance();
        // 5. 依赖注入 TODO: 2021/12/11 循环依赖问题
        final MutablePropertyValues propertyValues = beanDefinition.getPropertyValues();
        for (PropertyValue propertyValue : propertyValues) {
            // 5.1 获取name, value, ref属性名称
            final String propertyName = propertyValue.getName();
            final String ref = propertyValue.getRef();
            final String value = propertyValue.getValue();
            // 5.2 判断ref是否为空
            if (!MyStringUtils.isEmpty(ref)) {
                // 5.2.1 不为空则通过递归调用getBean方法获取ref对象
                final Object refBean = getBean(ref);
                // 5.2.2 获取set方法
                final String methodName = MyStringUtils.getSetterMethodByFiledName(propertyName);
                // 5.2.3 反射调用set方法
                final Method method = clazz.getMethod(methodName, refBean.getClass().getInterfaces()[0]);
                method.invoke(bean, refBean);
            }

            // 5.3 判断value是否为空
            if (!MyStringUtils.isEmpty(value)) {
                // 5.3.1 不为空则获取set方法
                final String methodName = MyStringUtils.getSetterMethodByFiledName(propertyName);
                // 5.3.2 反射调用set方法
                final Method method = clazz.getMethod(methodName, String.class);
                method.invoke(bean, value);
            }

        }
        // 6. 将bean存入容器
        singletonObjects.put(name, bean);

        return bean;
    }

    @Override
    public <T> T getBean(String name, Class<? extends T> clazz) throws Exception {
        final Object bean = getBean(name);
        return bean == null ? null : clazz.cast(bean);
    }
}
