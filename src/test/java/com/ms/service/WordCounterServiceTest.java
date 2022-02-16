package com.ms.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import com.ms.WordCounterApplication;
import com.ms.translator.ITranslator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {WordCounterApplication.class})
public class WordCounterServiceTest {

    @MockBean
    private ITranslator<String> translator;
    
    @Autowired
    private WordCounterService wordCounterService;

    @Test
    public void testAddWord() throws Exception {
        String word = "flower";
        when(translator.isNonEnglish(word)).thenReturn(false);
        wordCounterService.addWord(word);
        assertEquals(1,wordCounterService.getOccurrencesOfWord(word));
    }

    @Test
    public void testAddWordForNonEnglishWor() throws Exception {
        String word = "flo";
        when(translator.isNonEnglish(word)).thenReturn(true);
        when(translator.translate(word)).thenReturn("flower");
        wordCounterService.addWord(word);
        assertEquals(2,wordCounterService.getOccurrencesOfWord(word));
    }

    @Test(expected = Exception.class)
    public void testAddWordForNonAlphabet() throws Exception {
        String word = "1";
        when(translator.isNonEnglish(word)).thenReturn(false);
        wordCounterService.addWord(word);
    }

    @Test
    public void testGetOccurrencesOfWord() throws Exception {
        String word = "flower";
        when(translator.isNonEnglish(word)).thenReturn(false);
        assertEquals(2, wordCounterService.getOccurrencesOfWord(word));

    }

    @Test
    public void testGetOccurrencesOfWordForNonEnglishWord() throws Exception {
        String word = "flo";
        when(translator.isNonEnglish(word)).thenReturn(true);
        when(translator.translate(word)).thenReturn("flower");
        assertEquals(-1, wordCounterService.getOccurrencesOfWord(word));

    }

    @Test(expected = Exception.class)
    public void testGetOccurrencesOfWordForNonAlphabet() throws Exception {
        String word = "1";
        when(translator.isNonEnglish(word)).thenReturn(false);
        wordCounterService.getOccurrencesOfWord(word);
    }
}
