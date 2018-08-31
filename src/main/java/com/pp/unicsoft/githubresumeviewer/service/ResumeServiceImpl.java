/**
 * All code below is exclusively owned by its author - Pavel Perminov (packpaul@mail.ru).
 * Any changes, modifications, borrowing and adaptation are a subject for
 * explicit permission from the owner.
 */

package com.pp.unicsoft.githubresumeviewer.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Resume generation service test
 * 
 * @author Pavel Perminov (packpaul@mail.ru)
 */
@Service
class ResumeServiceImpl implements ResumeService {
    
    private RestTemplate restTemplate = new RestTemplate();

    public Resume generate(String username) {
        JsonNode root;
        try {
            root = restTemplate.getForObject("https://api.github.com/users/" + username, JsonNode.class);
        } catch(RestClientException ex) {
            return null;
        }
        
        JsonNode login = root.get("login");
        if (login == null) {
            return null;
        }
        
        Resume resume = new Resume();
        
        resume.login = login.textValue();
        resume.blog = StringUtils.defaultIfEmpty(root.get("blog").textValue(), null);
        
        root = restTemplate.getForObject(
                "https://api.github.com/users/" + username + "/repos", JsonNode.class);
        
        List<ResumeRepo> repos = buildRepositories(root); 
        resume.repos = repos;
        
        resume.reposLanguages = buildReposLanguages(repos);
                
        return resume;
    }

    private List<ResumeRepo> buildRepositories(JsonNode repos) {
        
        List<ResumeRepo> results = new ArrayList<>();
        
        for (Iterator<JsonNode> ri = repos.elements(); ri.hasNext(); ) {
            JsonNode repo = ri.next();
            
            ResumeRepo result = new ResumeRepo();
            result.id = repo.get("id").asText();
            result.name = repo.get("name").textValue();
            result.url = repo.get("html_url").textValue();
            result.description = repo.get("description").textValue();
            result.language = StringUtils.defaultString(repo.get("language").textValue(), "others");
            
            results.add(result);
        }

        return results;
    }
    
    List<ReposLanguage> buildReposLanguages(List<ResumeRepo> repos) {
        final int reposCount = repos.size();
        
        Map<String, Integer> langs = new TreeMap<>(String::compareToIgnoreCase);
        for (ResumeRepo repo : repos) {
            Integer count = langs.getOrDefault(repo.language, 0);
            langs.put(repo.language, ++count);
        }
        
        DecimalFormat df = new DecimalFormat("##.##");
        
        List<ReposLanguage> results = new ArrayList<>(langs.size());
        for (Map.Entry<String, Integer> rle : langs.entrySet()) {
            ReposLanguage result = new ReposLanguage();
            result.name = rle.getKey();
            result.percentage = df.format(100.0 * rle.getValue() / reposCount);
            results.add(result);
        }
        
        return results;
    }

}