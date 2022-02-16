package com.ms.translator;

public class DummyTranslator implements ITranslator<String>{

    @Override
    public String translate(String o) {
        return "flower";
    }

    @Override
    public boolean isNonEnglish(String o) {
        return true;
    }
}
