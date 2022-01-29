package com.github.adil.encapsulatedrxjavalibrary.subjects;

import com.github.adil.encapsulatedrxjavalibrary.EncSubject;

import io.reactivex.rxjava3.annotations.CheckReturnValue;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.subjects.BehaviorSubject;

public final class EncBehaviorSubject<T> extends EncSubject<T> {

    /**
     * Method creates MccPublishSubject instance
     * @return
     */
    @CheckReturnValue
    @NonNull
    public static <T> EncBehaviorSubject<T> create() {
        return new EncBehaviorSubject<>();
    }

    private EncBehaviorSubject() {
        this.subject = BehaviorSubject.create();
    }
}
