/**
 * All code below is exclusively owned by its author - Pavel Perminov (packpaul@mail.ru).
 * Any changes, modifications, borrowing and adaptation are a subject for
 * explicit permission from the owner.
 */

package com.pp.unicsoft.githubresumeviewer.service;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.pp.unicsoft.githubresumeviewer.service.ResumeService.ReposLanguage;
import com.pp.unicsoft.githubresumeviewer.service.ResumeService.ResumeRepo;

/**
 * Unit tests for resume generation
 * 
 * @author Pavel Perminov (packpaul@mail.ru)
 */
public class ResumeServiceImplTest {
    
    private ResumeServiceImpl service = new ResumeServiceImpl();

    @Test
    public void testBuildReposLanguagesAlphabetically() {
        
        ResumeRepo r1 = new ResumeRepo();
        r1.id = "1";
        r1.language = "Java";
        
        ResumeRepo r2 = new ResumeRepo();
        r2.id = "2";
        r2.language = "Kotlin";

        ResumeRepo r3 = new ResumeRepo();
        r3.id = "3";
        r3.language = "C";

        ResumeRepo r4 = new ResumeRepo();
        r4.id = "4";
        r4.language = "others";
        
        ResumeRepo r5 = new ResumeRepo();
        r5.id = "5";
        r5.language = "Delphi";
        
        List<ReposLanguage> langs = service.buildReposLanguages(Arrays.asList(r1, r2, r3, r4, r5));
        
        assertEquals("C", langs.get(0).name);
        assertEquals("Delphi", langs.get(1).name);
        assertEquals("Java", langs.get(2).name);
        assertEquals("Kotlin", langs.get(3).name);
        assertEquals("others", langs.get(4).name);
    }
    
}