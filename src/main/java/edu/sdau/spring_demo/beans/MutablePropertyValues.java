package edu.sdau.spring_demo.beans;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 用户存储和管理多个PropertyValue对象
 * @author ginga
 * @version 1.0
 * @ClassName MutablePropertyValues
 * @Date 12/11/2022 上午11:14
 */
public class MutablePropertyValues implements Iterable<PropertyValue>{

    // 定义List集合对象, 用来存储PropertyValue对象
//    private final List<PropertyValue> propertyValueList;
    private final Map<String, PropertyValue> propertyValueMap;

    public MutablePropertyValues() {
        this.propertyValueMap = new HashMap<>();
    }

    // 有参构造方法
    public MutablePropertyValues(Map<String, PropertyValue> propertyValueMap) {
        if (propertyValueMap == null) {
            this.propertyValueMap = new HashMap<>();
        } else {
            this.propertyValueMap = propertyValueMap;
        }
    }

    // 获取所有的PropertyValue对象, 返回数组的形式
    public PropertyValue[] getPropertyValues() {
        return this.propertyValueMap.values().toArray(new PropertyValue[0]);
    }

    // 根据name属性获取PropertyValue对象
    public PropertyValue getPropertyValue(String propertyName) {
        return this.propertyValueMap.get(propertyName);
    }

    // 判断集合是否为空
    public boolean isEmpty() {
        return this.propertyValueMap.isEmpty();
    }

    // 添加PropertyValue对象
    public MutablePropertyValues addPropertyValue(PropertyValue propertyValue) {
        // 判断集合中是否已经存在该对象, 如果重复了, 则覆盖
        if (propertyValue != null) {
            this.propertyValueMap.put(propertyValue.getName(), propertyValue);
        }
        // 链式编程
        return this;
    }

    // 判断是否有指定name属性值的PropertyValue对象
    public boolean contains(String propertyName) {
        return getPropertyValue(propertyName) != null;
    }

    // 获取迭代器对象
    @Override
    public Iterator<PropertyValue> iterator() {
        return propertyValueMap.values().iterator();
    }

}
