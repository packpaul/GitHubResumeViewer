/**
 * All code below is exclusively owned by its author - Pavel Perminov (packpaul@mail.ru).
 * Any changes, modifications, borrowing and adaptation are a subject for
 * explicit permission from the owner.
 */

package com.pp.unicsoft.githubresumeviewer.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pp.unicsoft.githubresumeviewer.service.ResumeService;
import com.pp.unicsoft.githubresumeviewer.service.ResumeService.Resume;

/**
 * Main controller that handles session login and logout.
 * 
 * @author Pavel Perminov (packpaul@mail.ru)
 */
@Controller
public class MainController {
    
    @Autowired
    private ResumeService resumeService;
    
    @RequestMapping(value="/")
    public String root() {
        return "redirect:/main";
    }
    
    @RequestMapping(value="/main")
    public String main() {
        return "main";
    }
    
    @RequestMapping(value="/generate", method=RequestMethod.POST)
    public String submitAnswer(Model model, @RequestParam("username") String username) {
        
        username = username.trim();
        
        Resume resume = resumeService.generate(username);
        if (resume == null) {
            try {
                return "redirect:/main?faultUsername=" + URLEncoder.encode(username, "UTF8");
            } catch (UnsupportedEncodingException e) {
                return "redirect:/main";
            }
        }
        
        model.addAttribute("resume", resume);
        
        return "resume";
    }

}
