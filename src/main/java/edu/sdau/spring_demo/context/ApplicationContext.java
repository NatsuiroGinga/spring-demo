package edu.sdau.spring_demo.context;

import edu.sdau.spring_demo.beans.factory.BeanFactory;

/**
 * 定义非延时加载功能
 * @author ginga
 * @version 1.0
 * @ClassName ApplicationContext
 * @Date 12/11/2022 下午1:20
 */
public interface ApplicationContext extends BeanFactory {

    void refresh() throws Exception;

}
