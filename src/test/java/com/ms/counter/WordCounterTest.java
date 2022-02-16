package com.ms.counter;

import com.ms.translator.DummyTranslator;
import com.ms.validator.IValidator;
import com.ms.validator.StringValidator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class WordCounterTest {

    private WordCounter wordCounter;
    private DummyTranslator translator;

    private IValidator<String> validator;

    @Before
    public void setUp(){
        translator = Mockito.mock(DummyTranslator.class);
        validator = new StringValidator();
        when(translator.isNonEnglish("flor")).thenReturn(true);
        when(translator.isNonEnglish("flower")).thenReturn(false);
        when(translator.isNonEnglish("Flower")).thenReturn(false);
        when(translator.translate("flor")).thenReturn("flower");
        wordCounter = new WordCounter(translator, validator );
    }

    @Test
    public void testGetOccurrencesForSingleWord() throws Exception{
        String word = "flower";
        wordCounter.add(word);  
        assertEquals(wordCounter.getOccurrences(word), 1);
    }

    @Test
    public void testGetOccurrencesForMultipleWords() throws Exception{
        String word = "Flower";
        wordCounter.add(word);
        wordCounter.add(word);
        assertEquals(wordCounter.getOccurrences(word), 2);
    }

    @Test(expected=Exception.class)
    public void testGetOccurrencesForNullWord() throws Exception{
        String word = null;
        wordCounter.add(word);
    }

    @Test(expected=Exception.class)
    public void testGetOccurrencesForEmptyWord() throws Exception{
        String word = "";
        wordCounter.add(word);
    }

    @Test(expected=Exception.class)
    public void testGetOccurrencesForWordWithNonAlphabeticCharacter() throws Exception{
        String word = "abc12!";
        wordCounter.add(word);
    }

    @Test
    public void testGetOccurrencesForNoWordAdded() throws Exception{
        String word = "flower";
        assertEquals(wordCounter.getOccurrences(word), -1);
    }

    @Test
    public void testGetOccurrencesForNonEnglishWords() throws Exception{
        String word = "flower";
        wordCounter.add(word);
        word = "flor";
        wordCounter.add(word);
        assertEquals(wordCounter.getOccurrences(word),2);
    }

}
