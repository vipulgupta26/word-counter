package com.ms.validator;

public interface IValidator<K> {
    public void validateInput(K k) throws Exception;
}
