package com.ms.validator;

import org.apache.commons.lang3.StringUtils;

public class StringValidator implements IValidator<String> {

    public void validateInput(String word) throws Exception{
        checkForEmpty(word);
        checkForNonAlphabeticCharacter(word);
    }

    private void checkForNonAlphabeticCharacter(String word) throws Exception {
        String pattern = "^[a-zA-Z]*$";
        if(!word.matches(pattern)){
            throw new IllegalArgumentException(word + " contains non alphabetic character");
        }
    }

    private void checkForEmpty(String word) throws Exception {
        if(StringUtils.isEmpty(word)){
            throw new IllegalArgumentException("Word cannot be empty or null");
        }
    }
}
