package com.github.adil.encapsulatedrxjavalibrary;

import com.github.adil.encapsulatedrxjavalibrary.actions.EncAction;
import com.github.adil.encapsulatedrxjavalibrary.actions.EncConsumer;
import com.github.adil.encapsulatedrxjavalibrary.disposable.EncDisposable;
import com.github.adil.encapsulatedrxjavalibrary.disposable.EncDisposableImpl;
import com.github.adil.encapsulatedrxjavalibrary.onclass.EncCompletableOnClass;
import com.github.adil.encapsulatedrxjavalibrary.onsubscribe.EncCompletableOnSubscribe;

import java.util.Objects;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class EncCompletable {

    private final Completable completable;

    private EncCompletable(@NonNull EncCompletableOnSubscribe source) {
        this.completable = Completable.create(source);
    }

    private Completable getCompletable(){
        return completable;
    }

    /**
     * Method only creates completable.
     * @param source
     * @return
     */
    public static EncCompletable create(@NonNull EncCompletableOnSubscribe source){
        return new EncCompletable(source);
    }

    /**
     * Method creates completable and subscribes (on IO thread) to given source.
     * subscribeOn : IO Thread
     * observeON : IO Thread
     * @param completableOnClass
     * @param <T>
     */
    public static <T> EncDisposable runOnIOThread(@NonNull EncCompletableOnClass completableOnClass){
        Objects.requireNonNull(completableOnClass.source(), "source is null");
        return new EncDisposableImpl(create(completableOnClass.source()).getCompletable().subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(completableOnClass::onComplete, completableOnClass::onError));
    }

    /**
     * Method creates completable and subscribes (on New thread) to given source.
     * subscribeOn : New Thread
     * observeON : New Thread
     * @param completableOnClass
     * @param <T>
     */
    public static <T> EncDisposable runOnNewThread(@NonNull EncCompletableOnClass completableOnClass){
        Objects.requireNonNull(completableOnClass.source(), "source is null");
        return new EncDisposableImpl(create(completableOnClass.source()).getCompletable().subscribeOn(Schedulers.newThread()).observeOn(Schedulers.newThread()).subscribe(completableOnClass::onComplete, completableOnClass::onError));
    }

    /**
     * Method creates completable and subscribes (on IO thread) to given source.
     * subscribeOn : IO Thread
     * observeON : Main Thread
     * @param completableOnClass
     * @param <T>
     */
    public static <T> EncDisposable runOnIOToMainThread(@NonNull EncCompletableOnClass completableOnClass){
        Objects.requireNonNull(completableOnClass.source(), "source is null");
        return new EncDisposableImpl(create(completableOnClass.source()).getCompletable().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(completableOnClass::onComplete, completableOnClass::onError));
    }

    /**
     * Method creates completable and subscribes (on New thread) to given source.
     * subscribeOn : New Thread
     * observeON : Main Thread
     * @param completableOnClass
     * @param <T>
     */
    public static <T> EncDisposable runOnNewToMainThread(@NonNull EncCompletableOnClass completableOnClass){
        Objects.requireNonNull(completableOnClass.source(), "source is null");
        return new EncDisposableImpl(create(completableOnClass.source()).getCompletable().subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(completableOnClass::onComplete, completableOnClass::onError));
    }

    /**
     * Method creates completable and subscribes (on Main thread) to given source. It used in trivial cases of the subscribe result.
     * subscribeOn : Main Thread
     * observeON : Main Thread
     * @param completableOnClass
     * @param <T>
     */
    public static <T> EncDisposable runOnMainThread(@NonNull EncCompletableOnClass completableOnClass){
        Objects.requireNonNull(completableOnClass.source(), "source is null");
        return new EncDisposableImpl(create(completableOnClass.source()).getCompletable().subscribeOn(AndroidSchedulers.mainThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(completableOnClass::onComplete, completableOnClass::onError));
    }

    /**
     * Subscribe method
     * subscribeOn : IO Thread
     * observeON : IO Thread
     * @param onComplete
     * @param onError
     * @return
     */
    public final EncDisposable subscribeOnIOThread(@NonNull EncAction onComplete, @NonNull EncConsumer<? super Throwable> onError) {
        Objects.requireNonNull(onComplete, "onComplete is null");
        Objects.requireNonNull(onError, "onError is null");

        return new EncDisposableImpl(getCompletable().subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(onComplete, onError));
    }

    /**
     * Subscribe method
     * subscribeOn : IO Thread
     * observeON : IO Thread
     * onComplete : EMPTY ACTION
     * @param onError
     * @return
     */
    public final EncDisposable subscribeOnIOThread(@NonNull EncConsumer<? super Throwable> onError) {
        return subscribeOnIOThread(EncAction.EMPTY_ACTION, onError);
    }

    /**
     * Subscribe method
     * subscribeOn : New Thread
     * observeON : New Thread
     * @param onComplete
     * @param onError
     * @return
     */
    public final EncDisposable subscribeOnNewThread(@NonNull EncAction onComplete, @NonNull EncConsumer<? super Throwable> onError) {
        Objects.requireNonNull(onComplete, "onComplete is null");
        Objects.requireNonNull(onError, "onError is null");

        return new EncDisposableImpl(getCompletable().subscribeOn(Schedulers.newThread()).observeOn(Schedulers.newThread()).subscribe(onComplete, onError));
    }

    /**
     * Subscribe method
     * subscribeOn : New Thread
     * observeON : New Thread
     * onComplete : EMPTY ACTION
     * @param onError
     * @return
     */
    public final EncDisposable subscribeOnNewThread(@NonNull EncConsumer<? super Throwable> onError) {
        return subscribeOnNewThread(EncAction.EMPTY_ACTION, onError);
    }

    /**
     * Subscribe method
     * subscribeOn : IO Thread
     * observeON : Main Thread
     * @param onComplete
     * @param onError
     * @return
     */
    public final EncDisposable subscribeOnIOToMainThread(@NonNull EncAction onComplete, @NonNull EncConsumer<? super Throwable> onError) {
        Objects.requireNonNull(onComplete, "onComplete is null");
        Objects.requireNonNull(onError, "onError is null");

        return new EncDisposableImpl(getCompletable().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(onComplete, onError));
    }

    /**
     * Subscribe method
     * subscribeOn : IO Thread
     * observeON : Main Thread
     * onComplete : EMPTY ACTION
     * @param onError
     * @return
     */
    public final EncDisposable subscribeOnIOToMainThread(@NonNull EncConsumer<? super Throwable> onError) {
        return subscribeOnIOToMainThread(EncAction.EMPTY_ACTION, onError);
    }

    /**
     * Subscribe method
     * subscribeOn : New Thread
     * observeON : Main Thread
     * @param onComplete
     * @param onError
     * @return
     */
    public final EncDisposable subscribeOnNewToMainThread(@NonNull EncAction onComplete, @NonNull EncConsumer<? super Throwable> onError) {
        Objects.requireNonNull(onComplete, "onComplete is null");
        Objects.requireNonNull(onError, "onError is null");

        return new EncDisposableImpl(getCompletable().subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(onComplete, onError));
    }

    /**
     * Subscribe method
     * subscribeOn : New Thread
     * observeON : Main Thread
     * onComplete : EMPTY ACTION
     * @param onError
     * @return
     */
    public final EncDisposable subscribeOnNewToMainThread(@NonNull EncConsumer<? super Throwable> onError) {
        return subscribeOnNewToMainThread(EncAction.EMPTY_ACTION, onError);
    }

    /**
     * Subscribe method
     * subscribeOn : Main Thread
     * observeON : Main Thread
     * @param onComplete
     * @param onError
     * @return
     */
    public final EncDisposable subscribeOnMainThread(@NonNull EncAction onComplete, @NonNull EncConsumer<? super Throwable> onError) {
        Objects.requireNonNull(onComplete, "onSuccess is null");
        Objects.requireNonNull(onError, "onError is null");

        return new EncDisposableImpl(getCompletable().subscribeOn(AndroidSchedulers.mainThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(onComplete, onError));
    }

    /**
     * Subscribe method
     * subscribeOn : Main Thread
     * observeON : Main Thread
     * onComplete : EMPTY ACTION
     * @param onError
     * @return
     */
    public final EncDisposable subscribeOnMainThread(@NonNull EncConsumer<? super Throwable> onError) {
        return subscribeOnMainThread(EncAction.EMPTY_ACTION, onError);
    }

    /**
     * Subscribe method
     * subscribeOn : Current Thread
     * observeON : Current Thread
     * @param onComplete
     * @param onError
     * @return
     */
    public final EncDisposable subscribe(@NonNull EncAction onComplete, @NonNull EncConsumer<? super Throwable> onError) {
        Objects.requireNonNull(onComplete, "onSuccess is null");
        Objects.requireNonNull(onError, "onError is null");

        return new EncDisposableImpl(getCompletable().subscribe(onComplete, onError));
    }

    /**
     * Subscribe method
     * subscribeOn : Current Thread
     * observeON : Current Thread
     * onComplete : EMPTY ACTION
     * @param onError
     * @return
     */
    public final EncDisposable subscribe(@NonNull EncConsumer<? super Throwable> onError) {
        return subscribe(EncAction.EMPTY_ACTION, onError);
    }
}
