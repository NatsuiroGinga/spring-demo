package edu.sdau.spring_demo.beans;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 用户存储和管理多个PropertyValue对象
 * @author ginga
 * @version 1.0
 * @ClassName MutablePropertyValues
 * @Date 12/11/2022 上午11:14
 */
public class MutablePropertyValues implements Iterable<PropertyValue>{

    // 定义List集合对象, 用来存储PropertyValue对象
    private final List<PropertyValue> propertyValueList;

    public MutablePropertyValues() {
        this.propertyValueList = new ArrayList<>();
    }

    // 有参构造方法
    public MutablePropertyValues(List<PropertyValue> propertyValueList) {
        if (propertyValueList == null) {
            this.propertyValueList = new ArrayList<>();
        } else {
            this.propertyValueList = propertyValueList;
        }
    }

    // 获取所有的PropertyValue对象, 返回数组的形式
    public PropertyValue[] getPropertyValues() {
        return this.propertyValueList.toArray(new PropertyValue[0]);
    }

    // 根据name属性获取PropertyValue对象
    public PropertyValue getPropertyValue(String propertyName) {
        // 遍历集合对象
        for (PropertyValue propertyValue : this.propertyValueList) {
            if (propertyValue.getName().equals(propertyName)) {
                return propertyValue;
            }
        }
        return null;
    }

    // 判断集合是否为空
    public boolean isEmpty() {
        return this.propertyValueList.isEmpty();
    }

    // 添加PropertyValue对象
    public MutablePropertyValues addPropertyValue(PropertyValue propertyValue) {
        // 判断集合中是否已经存在该对象, 如果重复了, 则覆盖
        for (int i = 0; i < this.propertyValueList.size(); i++) {
            PropertyValue currentPv = this.propertyValueList.get(i);
            if (currentPv.getName().equals(propertyValue.getName())) {
                this.propertyValueList.set(i, propertyValue);
                // 链式编程
                return this;
            }
        }
        this.propertyValueList.add(propertyValue);
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
        return propertyValueList.iterator();
    }

}
