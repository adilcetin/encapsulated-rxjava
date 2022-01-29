package com.github.adil.encapsulatedrxjavalibrary.single;

import com.github.adil.encapsulatedrxjavalibrary.actions.EncConsumer;
import com.github.adil.encapsulatedrxjavalibrary.disposable.EncDisposable;
import com.github.adil.encapsulatedrxjavalibrary.disposable.EncDisposableImpl;
import com.github.adil.encapsulatedrxjavalibrary.onclass.EncSingleOnClass;
import com.github.adil.encapsulatedrxjavalibrary.onsubscribe.EncSingleOnSubscribe;

import java.util.Objects;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class EncSingle<@NonNull T> {

    private final Single<T> single;

    private EncSingle(@NonNull EncSingleOnSubscribe<T> source) {
        this.single = Single.create(source);
    }

    private Single<T> getSingle(){
        return single;
    }

    public EncSingle(Single<T> single) {
        this.single = single;
    }

    /**
     * Method only creates single
     * @param source
     * @param <T>
     * @return
     */
    public static <T> EncSingle<T> create(@NonNull EncSingleOnSubscribe<T> source){
        return new EncSingle<>(source);
    }

    /**
     * Just method
     * @param item
     * @param <T>
     * @return
     */
    public static <@NonNull T> EncSingle<T> just(T item) {
        return new EncSingle<T>(Single.just(item));
    }

    /**
     * Method creates single and subscribes (on IO thread) to given source.
     * subscribeOn : IO Thread
     * observeON : IO Thread
     * @param singleOnClass
     * @param <T>
     */
    public static <T> EncDisposable runOnIOThread(@NonNull EncSingleOnClass<T> singleOnClass){
        Objects.requireNonNull(singleOnClass.source(), "source is null");
        return new EncDisposableImpl(create(singleOnClass.source()).getSingle().subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(singleOnClass::onSuccess, singleOnClass::onError));
    }

    /**
     * Method creates single and subscribes (on New thread) to given source.
     * subscribeOn : New Thread
     * observeON : New Thread
     * @param singleOnClass
     * @param <T>
     */
    public static <T> EncDisposable runOnNewThread(@NonNull EncSingleOnClass<T> singleOnClass){
        Objects.requireNonNull(singleOnClass.source(), "source is null");
        return new EncDisposableImpl(create(singleOnClass.source()).getSingle().subscribeOn(Schedulers.newThread()).observeOn(Schedulers.newThread()).subscribe(singleOnClass::onSuccess, singleOnClass::onError));
    }


    /**
     * Method creates single and subscribes (on IO thread) to given source.
     * subscribeOn : IO Thread
     * observeON : Main Thread
     * @param singleOnClass
     * @param <T>
     */
    public static <T> EncDisposable runOnIOToMainThread(@NonNull EncSingleOnClass<T> singleOnClass){
        Objects.requireNonNull(singleOnClass.source(), "source is null");
        return new EncDisposableImpl(create(singleOnClass.source()).getSingle().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(singleOnClass::onSuccess, singleOnClass::onError));
    }

    /**
     * Method creates single and subscribes (on New thread) to given source.
     * subscribeOn : New Thread
     * observeON : Main Thread
     * @param singleOnClass
     * @param <T>
     */
    public static <T> EncDisposable runOnNewToMainThread(@NonNull EncSingleOnClass<T> singleOnClass){
        Objects.requireNonNull(singleOnClass.source(), "source is null");
        return new EncDisposableImpl(create(singleOnClass.source()).getSingle().subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(singleOnClass::onSuccess, singleOnClass::onError));
    }

    /**
     * Method creates single and subscribes (on Main thread) to given source.
     * subscribeOn : Main Thread
     * observeON : Main Thread
     * @param singleOnClass
     * @param <T>
     */
    public static <T> EncDisposable runOnMainThread(@NonNull EncSingleOnClass<T> singleOnClass){
        Objects.requireNonNull(singleOnClass.source(), "source is null");
        return new EncDisposableImpl(create(singleOnClass.source()).getSingle().subscribeOn(AndroidSchedulers.mainThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(singleOnClass::onSuccess, singleOnClass::onError));
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

        return new EncDisposableImpl(getSingle().subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(onSuccess, onError));
    }

    /**
     * Subscribe method
     * subscribeOn : New Thread
     * observeON : New Thread
     * @param onSuccess
     * @param onError
     * @return
     */
    public final EncDisposable subscribeOnNewThread(@NonNull EncConsumer<? super T> onSuccess, @NonNull EncConsumer<? super Throwable> onError) {
        Objects.requireNonNull(onSuccess, "onSuccess is null");
        Objects.requireNonNull(onError, "onError is null");

        return new EncDisposableImpl(getSingle().subscribeOn(Schedulers.newThread()).observeOn(Schedulers.newThread()).subscribe(onSuccess, onError));
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

        return new EncDisposableImpl(getSingle().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(onSuccess, onError));
    }

    /**
     * Subscribe method
     * subscribeOn : New Thread
     * observeON : Main Thread
     * @param onSuccess
     * @param onError
     * @return
     */
    public final EncDisposable subscribeOnNewToMainThread(@NonNull EncConsumer<? super T> onSuccess, @NonNull EncConsumer<? super Throwable> onError) {
        Objects.requireNonNull(onSuccess, "onSuccess is null");
        Objects.requireNonNull(onError, "onError is null");

        return new EncDisposableImpl(getSingle().subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(onSuccess, onError));
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

        return new EncDisposableImpl(getSingle().subscribeOn(AndroidSchedulers.mainThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(onSuccess, onError));
    }
}
