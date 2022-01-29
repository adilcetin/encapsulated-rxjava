package com.github.adil.encapsulatedrxjavalibrary.onclass;

import com.github.adil.encapsulatedrxjavalibrary.actions.EncAction;
import com.github.adil.encapsulatedrxjavalibrary.onsubscribe.EncObservableOnSubscribe;

public abstract class EncObservableOnClass<T> {

    public abstract EncObservableOnSubscribe<T> source();

    public abstract void onNext(T t);

    public abstract void onError(Throwable throwable);

    public EncAction onComplete(){
        return EncAction.EMPTY_ACTION;
    }
}
