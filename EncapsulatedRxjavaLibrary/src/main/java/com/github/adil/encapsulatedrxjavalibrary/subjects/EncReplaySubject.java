package com.github.adil.encapsulatedrxjavalibrary.subjects;

import com.github.adil.encapsulatedrxjavalibrary.EncSubject;
import io.reactivex.rxjava3.annotations.CheckReturnValue;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.subjects.ReplaySubject;

public class EncReplaySubject<T> extends EncSubject<T> {
    /**
     * Method creates MccPublishSubject instance
     * @return
     */
    @CheckReturnValue
    @NonNull
    public static <T> EncReplaySubject<T> create() {
        return new EncReplaySubject<>();
    }

    private EncReplaySubject() {
        this.subject = ReplaySubject.create();
    }
}
