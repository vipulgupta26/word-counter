package com.ms.controller;

import com.ms.service.WordCounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class WordController {

    @Autowired
    private WordCounterService wordCounterService;

    @GetMapping("add/{word}")
    public ResponseEntity<String> addWord(@PathVariable String word){
        try {
            wordCounterService.addWord(word);
        } catch (Exception e) {
            return new ResponseEntity<>("Could not add word " + word + ", reason : " + e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Added " + word, HttpStatus.OK);
    }

    @GetMapping("get/{word}")
    public ResponseEntity<String> getWordOccurrences(@PathVariable String word){
        try {
            return new ResponseEntity<>("Occurrences for " + word + ": " + wordCounterService.getOccurrencesOfWord(word), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Could not get word " + word + ", reason : " + e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }


}
