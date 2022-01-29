package com.github.adil.encapsulatedrxjavalibrary.onsubscribe;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;

public interface EncObservableOnSubscribe<@NonNull T> extends ObservableOnSubscribe<@NonNull T> {
}
