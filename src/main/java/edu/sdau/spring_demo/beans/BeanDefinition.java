package edu.sdau.spring_demo.beans;

/**
 * 用来封装bean的标签数据
 * id:bean的唯一标识
 * className:bean全限定类名
 * property:子标签的数据
 * @author ginga
 * @version 1.0
 * @ClassName BeanDefinition
 * @Date 12/11/2022 上午11:36
 */
public class BeanDefinition {

    private String id;

    private String className;

    private MutablePropertyValues propertyValues;

    public BeanDefinition() {
        propertyValues = new MutablePropertyValues();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public MutablePropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(MutablePropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }
}
