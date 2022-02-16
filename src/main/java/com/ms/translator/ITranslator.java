package com.ms.translator;


public interface ITranslator<K> {
    public K translate(K k);
    public boolean isNonEnglish(K k);
}
