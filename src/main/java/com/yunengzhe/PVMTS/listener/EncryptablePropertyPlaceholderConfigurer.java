package com.yunengzhe.PVMTS.listener;

import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.yunengzhe.PVMTS.util.DES;



public class EncryptablePropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {  
    private static final String key = "0002000200020002";  
  
    protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props)  
        throws BeansException {  
            try {  
            	DES des = new DES();
                String username = props.getProperty("jdbc.username");  
                if (username != null) {  
                    props.setProperty("jdbc.username", des.decrypt(username, key));  
                }  
                  
                String password = props.getProperty("jdbc.password");  
                if (password != null) {  
                    props.setProperty("jdbc.password", des.decrypt(password, key));  
                }  
                  
                String url = props.getProperty("jdbc.url");  
                if (url != null) {  
                    props.setProperty("jdbc.url", des.decrypt(url, key));  
                }  
                  
                String driverClassName = props.getProperty("jdbc.driverClassName");  
                if(driverClassName != null){  
                    props.setProperty("jdbc.driverClassName", des.decrypt(driverClassName,key));  
                }  
                  
                super.processProperties(beanFactory, props);  
            } catch (Exception e) {  
                e.printStackTrace();  
                throw new BeanInitializationException(e.getMessage());  
            }  
        }  
    
}
