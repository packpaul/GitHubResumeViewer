/**
 * All code below is exclusively owned by its author - Pavel Perminov (packpaul@mail.ru).
 * Any changes, modifications, borrowing and adaptation are a subject for
 * explicit permission from the owner.
 */

package com.pp.unicsoft.githubresumeviewer.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Web configuration.
 * 
 * @author Pavel Perminov (packpaul@mail.ru)
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.pp.unicsoft.githubresumeviewer.controller" })
public class WebConfig extends WebMvcConfigurerAdapter {
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/main").setViewName("main");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
        registry.addResourceHandler("/img/**").addResourceLocations("classpath:/static/img/");
        registry.addResourceHandler("/images/**").addResourceLocations("classpath:/static/img/");
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
    }

}
