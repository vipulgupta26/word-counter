package com.ms.counter;

import com.ms.translator.ITranslator;
import com.ms.validator.IValidator;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This class lets you add a word and get occurrences of a word
 */
public class WordCounter extends AbstractCounter<String>{
    
    private IValidator<String> iValidator;

    @Autowired
    public WordCounter(ITranslator<String> translator,IValidator<String> iValidator){
        super(translator);
        this.iValidator = iValidator;
    }

    /**
    * Checks if the input is valid
    * @param word input word to validate
    */
    @Override
    public void checkForValidInput(String word) throws Exception {
        iValidator.validateInput(word);
    }

}
