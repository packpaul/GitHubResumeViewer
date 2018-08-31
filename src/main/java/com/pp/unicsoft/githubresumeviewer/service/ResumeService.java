/**
 * All code below is exclusively owned by its author - Pavel Perminov (packpaul@mail.ru).
 * Any changes, modifications, borrowing and adaptation are a subject for
 * explicit permission from the owner.
 */

package com.pp.unicsoft.githubresumeviewer.service;

import java.util.List;

/**
 * Resume generation service
 * 
 * @author Pavel Perminov (packpaul@mail.ru)
 */
public interface ResumeService {

    /**
     * Generates resume for user by his/her username.
     * 
     * @param username  user to generate resume for
     * @return  resume object
     */
    Resume generate(String username);
    
    public static class Resume {
        public String login;
        public String blog;
        public List<ResumeRepo> repos;
        public List<ReposLanguage> reposLanguages;
    }
    
    public static class ResumeRepo {
        public String id;
        public String name;
        public String url;
        public String description;
        public String language;
    }
    
    public static class ReposLanguage {
        public String name; // "Java"
        public String percentage; // 66.6
    }
}