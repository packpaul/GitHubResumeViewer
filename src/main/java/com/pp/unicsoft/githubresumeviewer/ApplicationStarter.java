/**
 * All code below is exclusively owned by its author - Pavel Perminov (packpaul@mail.ru).
 * Any changes, modifications, borrowing and adaptation are a subject for
 * explicit permission from the owner.
 */

package com.pp.unicsoft.githubresumeviewer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Main class for web application bootstrapping.
 * 
 * @author Pavel Perminov (packpaul@mail.ru)
 */
@SpringBootApplication
public class ApplicationStarter extends SpringBootServletInitializer {
    
    public static void main(String[] args) throws Exception {
        SpringApplication.run(ApplicationStarter.class, args);
    }

}
