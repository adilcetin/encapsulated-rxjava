package com.github.adil.encapsulatedrxjavalibrary.onclass;

import com.github.adil.encapsulatedrxjavalibrary.onsubscribe.EncSingleOnSubscribe;

public abstract class EncSingleOnClass<T> {

    public abstract EncSingleOnSubscribe<T> source();

    public abstract void onError(Throwable throwable);

    public abstract void onSuccess(T t);
}
