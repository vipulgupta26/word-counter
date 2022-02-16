package com.ms.service;

import com.ms.counter.ICounter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WordCounterService {

    private ICounter<String> wordCounter;

    @Autowired
    public WordCounterService(ICounter<String> wordCounter){
        this.wordCounter = wordCounter;
    }

    public void addWord(String word) throws Exception{
        wordCounter.add(word);
    }

    public int getOccurrencesOfWord(String word) throws Exception{
        return wordCounter.getOccurrences(word);
    }
}
