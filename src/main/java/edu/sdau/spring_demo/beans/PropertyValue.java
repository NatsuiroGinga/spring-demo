package edu.sdau.spring_demo.beans;

/**
 * 用来封装bean标签下的property标签的属性
 * name属性
 * ref属性
 * value属性: 给基本数据类型及String类型赋值
 * @author ginga
 * @version 1.0
 * @ClassName PropertyValue
 * @Date 12/11/2022 上午11:09
 */
public class PropertyValue {

    private String name;

    private String ref;

    private String value;

    public PropertyValue() {
    }

    public PropertyValue(String name, String ref, String value) {
        this.name = name;
        this.ref = ref;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
