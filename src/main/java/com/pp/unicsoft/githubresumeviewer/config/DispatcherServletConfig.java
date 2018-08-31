/**
 * All code below is exclusively owned by its author - Pavel Perminov (packpaul@mail.ru).
 * Any changes, modifications, borrowing and adaptation are a subject for
 * explicit permission from the owner.
 */

package com.pp.unicsoft.githubresumeviewer.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Dispatcher servlet configuration.
 * 
 * @author Pavel Perminov (packpaul@mail.ru)
 */
@Configuration
public class DispatcherServletConfig {
    
    private static final Logger LOG = LoggerFactory.getLogger(DispatcherServletConfig.class);
    
    @Bean
    public DispatcherServlet dispatcherServlet() {
        DispatcherServlet result = new DispatcherServlet();
        result.setThrowExceptionIfNoHandlerFound(true);
                
        return result;
    }
    
    @Bean
    public ServletRegistrationBean dispatcherServletRegistration() {
        LOG.info("dispatcherServletRegistration:");
        
        ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet(), "/*");
        registration.setName(DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_REGISTRATION_BEAN_NAME);
        
        return registration;
    }

}
