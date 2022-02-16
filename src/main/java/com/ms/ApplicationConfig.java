package com.ms;

import com.ms.counter.ICounter;
import com.ms.counter.WordCounter;
import com.ms.translator.DummyTranslator;
import com.ms.translator.ITranslator;
import com.ms.validator.IValidator;
import com.ms.validator.StringValidator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public ICounter<String> wordCounter(){
        return new WordCounter(translator(), validator());
    }

    @Bean
    public ITranslator<String> translator(){
        return new DummyTranslator();
    }

    @Bean
    public IValidator<String> validator(){
        return new StringValidator();
    }


}
