package com.ms.counter;

import com.ms.translator.ITranslator;

import java.util.HashMap;
import java.util.Map;

    /**
    *  This is abstract class which implements ICounter
    *  K is generic type
    */
public abstract class AbstractCounter<K> implements ICounter<K>{
    protected final Map<K,Integer> wordOccurrencesMap;
    protected ITranslator<K> translator;

    public AbstractCounter(ITranslator<K> translator){
        this.translator = translator;
        wordOccurrencesMap = new HashMap<>();
    }

    /**
    *  This method lets you add a word
    *  @param k input type to be added
    *  @throws Exception if input is not valid
    */
    @Override
    public void add(K k) throws Exception{
        checkForValidInput(k);
        K k1 = k;

        k1 = transformIfNeeded(k);

        if(wordOccurrencesMap.containsKey(k1)){
            int count = wordOccurrencesMap.get(k1);
            wordOccurrencesMap.put(k1, count + 1);
        }
        else{
            wordOccurrencesMap.put(k1, 1);
        }
    }

    private K transformIfNeeded(K k) {
        K k1 = k;
        if(translator.isNonEnglish(k)){
            k1 = translator.translate(k);
        }
        return k1;
    }

    /**
    *  This method gets occurrences of the input
    * @param k input type to be added
    * @return occurrences of input
    * @throws Exception if input is not valid
    */
    @Override
    public int getOccurrences(K k) throws Exception {
        checkForValidInput(k);
        K k1 = transformIfNeeded(k);
        if(wordOccurrencesMap.containsKey(k1)) {
            return wordOccurrencesMap.get(k1);
        }
        else{
            return -1;
        }
    }

    public abstract void checkForValidInput(K k) throws Exception;

}
