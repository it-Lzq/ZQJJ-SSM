package cn.itlzq.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author 作者:李泽庆
 * @version 创建时间:2020/5/24 23:57
 * @email 邮箱:905866484@qq.com
 * @description 描述：
 */
public class SpringContextUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static ApplicationContext  getApplicationContext(){
        return applicationContext;
    }

    public static Object getBean(String beanName){
        return applicationContext.getBean(beanName);
    }

    public static Object getBean(Class c){
        return applicationContext.getBean(c);
    }
}
