package com.github.adil.encapsulatedrxjavalibrary.single;

import com.github.adil.encapsulatedrxjavalibrary.actions.EncConsumer;
import com.github.adil.encapsulatedrxjavalibrary.disposable.EncDisposable;
import com.github.adil.encapsulatedrxjavalibrary.disposable.EncDisposableImpl;
import java.util.Objects;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.SingleSubject;

public class EncSingleSubject<T>  {

    protected SingleSubject<T> subject;

    private EncSingleSubject() {
        this.subject = SingleSubject.create();
    }

    public void onSuccess(T t) {
        subject.onSuccess(t);
    }

    public void onError(Throwable t) {
        subject.onError(t);
    }

    public <U> EncSingle<U> cast(@NonNull Class<? extends U> clazz){
        return new <U>EncSingle<U>(subject.cast(clazz));
    }

    /**
     * Method only creates single subject
     * @param
     * @param <T>
     * @return
     */
    public static <T> EncSingleSubject<T> create(){
        return new EncSingleSubject<>();
    }

    /**
     * Subscribe method
     * subscribeOn : IO Thread
     * observeON : IO Thread
     * @param onSuccess
     * @param onError
     * @return
     */
    public final EncDisposable subscribeOnIOThread(@NonNull EncConsumer<? super T> onSuccess, @NonNull EncConsumer<? super Throwable> onError) {
        Objects.requireNonNull(onSuccess, "onSuccess is null");
        Objects.requireNonNull(onError, "onError is null");

        return new EncDisposableImpl(subject.subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(onSuccess, onError));
    }

    /**
     * Subscribe method
     * subscribeOn : IO Thread
     * observeON : Main Thread
     * @param onSuccess
     * @param onError
     * @return
     */
    public final EncDisposable subscribeOnIOToMainThread(@NonNull EncConsumer<? super T> onSuccess, @NonNull EncConsumer<? super Throwable> onError) {
        Objects.requireNonNull(onSuccess, "onSuccess is null");
        Objects.requireNonNull(onError, "onError is null");

        return new EncDisposableImpl(subject.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(onSuccess, onError));
    }

    /**
     * Subscribe method
     * subscribeOn : Main Thread
     * observeON : Main Thread
     * @param onSuccess
     * @param onError
     * @return
     */
    public final EncDisposable subscribeOnMainThread(@NonNull EncConsumer<? super T> onSuccess, @NonNull EncConsumer<? super Throwable> onError) {
        Objects.requireNonNull(onSuccess, "onSuccess is null");
        Objects.requireNonNull(onError, "onError is null");

        return new EncDisposableImpl(subject.subscribeOn(AndroidSchedulers.mainThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(onSuccess, onError));
    }


}
