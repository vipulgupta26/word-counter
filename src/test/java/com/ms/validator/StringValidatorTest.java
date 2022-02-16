package com.ms.validator;

import org.junit.Before;
import org.junit.Test;

public class StringValidatorTest {

    private IValidator<String> validator;

    @Before
    public void setUp(){
        validator = new StringValidator();
    }

    @Test
    public void testValidateInputForString() throws Exception{
        String input = "flower";
        validator.validateInput(input);
    }
}
