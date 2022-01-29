package com.github.adil.encapsulatedrxjavalibrary.subjects;

import com.github.adil.encapsulatedrxjavalibrary.EncSubject;
import io.reactivex.rxjava3.annotations.CheckReturnValue;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.subjects.PublishSubject;

public final class EncPublishSubject<T> extends EncSubject<T> {

    /**
     * Method creates MccPublishSubject instance
     * @return
     */
    @CheckReturnValue
    @NonNull
    public static <T> EncPublishSubject<T> create() {
        return new EncPublishSubject<>();
    }

    private EncPublishSubject() {
        this.subject = PublishSubject.create();
    }
}