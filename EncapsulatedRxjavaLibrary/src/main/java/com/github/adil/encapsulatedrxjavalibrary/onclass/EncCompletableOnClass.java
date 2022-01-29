package com.github.adil.encapsulatedrxjavalibrary.onclass;

import com.github.adil.encapsulatedrxjavalibrary.actions.EncAction;
import com.github.adil.encapsulatedrxjavalibrary.onsubscribe.EncCompletableOnSubscribe;

public abstract class EncCompletableOnClass {

    public abstract EncCompletableOnSubscribe source();

    public abstract void onError(Throwable throwable);

    public abstract EncAction onComplete();
}
