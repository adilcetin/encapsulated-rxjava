package com.github.adil.encapsulatedrxjavalibrary;

import com.github.adil.encapsulatedrxjavalibrary.actions.EncAction;
import com.github.adil.encapsulatedrxjavalibrary.actions.EncConsumer;
import com.github.adil.encapsulatedrxjavalibrary.disposable.EncDisposable;
import com.github.adil.encapsulatedrxjavalibrary.disposable.EncDisposableImpl;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.Subject;

public class EncSubject<T> {

    protected Subject<T> subject;

    public void onNext(T t) {
        subject.onNext(t);
    }

    public void onError(Throwable t) {
        subject.onError(t);
    }

    public void onComplete() {
        subject.onComplete();
    }

    public final EncObservable<T> doOnNext(@NonNull Consumer<? super T> onNext) {
        return new EncObservable<>(subject.doOnNext(onNext));
    }

    public EncObservable<T> delayInMillisecond(int time){
        return new EncObservable<>(subject.delay(time, TimeUnit.MILLISECONDS));
    }

    public EncObservable<List<T>> buffer(int timeSpan, int bufferSize){
        return new EncObservable<>(subject.buffer(timeSpan, TimeUnit.MILLISECONDS, bufferSize));
    }

    public EncObservable<T> getMccObservable(){
        return new EncObservable<>(subject);
    }

    /**
     * Subscribe method
     * subscribeOn : IO Thread
     * observeON : IO Thread
     * @param onNext
     * @param onError
     * @param onComplete
     * @return
     */
    public final EncDisposable subscribeOnIOThread(@NonNull EncConsumer<? super T> onNext, @NonNull EncConsumer<? super Throwable> onError, @NonNull EncAction onComplete) {
        Objects.requireNonNull(onNext, "onNext is null");
        Objects.requireNonNull(onError, "onError is null");
        Objects.requireNonNull(onComplete, "onComplete is null");

        return new EncDisposableImpl(subject.subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(onNext, onError, onComplete));
    }

    /**
     * Subscribe method
     * subscribeOn : IO Thread
     * observeON : IO Thread
     * @param onNext
     * @param onError
     * @return
     */
    public final EncDisposable subscribeOnIOThread(@NonNull EncConsumer<? super T> onNext, @NonNull EncConsumer<? super Throwable> onError) {
        return subscribeOnIOThread(onNext, onError, EncAction.EMPTY_ACTION);
    }

    /**
     * Subscribe method
     * subscribeOn : New Thread
     * observeON : New Thread
     * @param onNext
     * @param onError
     * @param onComplete
     * @return
     */
    public final EncDisposable subscribeOnNewThread(@NonNull EncConsumer<? super T> onNext, @NonNull EncConsumer<? super Throwable> onError, @NonNull EncAction onComplete) {
        Objects.requireNonNull(onNext, "onNext is null");
        Objects.requireNonNull(onError, "onError is null");
        Objects.requireNonNull(onComplete, "onComplete is null");

        return new EncDisposableImpl(subject.subscribeOn(Schedulers.newThread()).observeOn(Schedulers.newThread()).subscribe(onNext, onError, onComplete));
    }

    /**
     * Subscribe method
     * subscribeOn : New Thread
     * observeON : New Thread
     * @param onNext
     * @param onError
     * @return
     */
    public final EncDisposable subscribeOnNewThread(@NonNull EncConsumer<? super T> onNext, @NonNull EncConsumer<? super Throwable> onError) {
        return subscribeOnNewThread(onNext, onError, EncAction.EMPTY_ACTION);
    }

    /**
     * Subscribe method
     * subscribeOn : IO Thread
     * observeON : Main Thread
     * @param onNext
     * @param onError
     * @param onComplete
     * @return
     */
    public final EncDisposable subscribeOnIOToMainThread(@NonNull EncConsumer<? super T> onNext, @NonNull EncConsumer<? super Throwable> onError, @NonNull EncAction onComplete) {
        Objects.requireNonNull(onNext, "onNext is null");
        Objects.requireNonNull(onError, "onError is null");
        Objects.requireNonNull(onComplete, "onComplete is null");

        return new EncDisposableImpl(subject.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(onNext, onError, onComplete));
    }

    /**
     * Subscribe method
     * subscribeOn : IO Thread
     * observeON : Main Thread
     * @param onNext
     * @param onError
     * @return
     */
    public final EncDisposable subscribeOnIOToMainThread(@NonNull EncConsumer<? super T> onNext, @NonNull EncConsumer<? super Throwable> onError) {
        return subscribeOnIOToMainThread(onNext, onError, EncAction.EMPTY_ACTION);
    }

    /**
     * Subscribe method
     * subscribeOn : New Thread
     * observeON : Main Thread
     * @param onNext
     * @param onError
     * @param onComplete
     * @return
     */
    public final EncDisposable subscribeOnNewToMainThread(@NonNull EncConsumer<? super T> onNext, @NonNull EncConsumer<? super Throwable> onError, @NonNull EncAction onComplete) {
        Objects.requireNonNull(onNext, "onNext is null");
        Objects.requireNonNull(onError, "onError is null");
        Objects.requireNonNull(onComplete, "onComplete is null");

        return new EncDisposableImpl(subject.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(onNext, onError, onComplete));
    }

    /**
     * Subscribe method
     * subscribeOn : New Thread
     * observeON : Main Thread
     * @param onNext
     * @param onError
     * @return
     */
    public final EncDisposable subscribeOnNewToMainThread(@NonNull EncConsumer<? super T> onNext, @NonNull EncConsumer<? super Throwable> onError) {
        return subscribeOnNewToMainThread(onNext, onError, EncAction.EMPTY_ACTION);
    }

    /**
     * Subscribe method
     * subscribeOn : Main Thread
     * observeON : Main Thread
     * @param onNext
     * @param onError
     * @param onComplete
     * @return
     */
    public final EncDisposable subscribeOnMainThread(@NonNull EncConsumer<? super T> onNext, @NonNull EncConsumer<? super Throwable> onError, @NonNull EncAction onComplete) {
        Objects.requireNonNull(onNext, "onNext is null");
        Objects.requireNonNull(onError, "onError is null");
        Objects.requireNonNull(onComplete, "onComplete is null");

        return new EncDisposableImpl(subject.subscribeOn(AndroidSchedulers.mainThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(onNext, onError, onComplete));
    }

    /**
     * Subscribe method
     * subscribeOn : Main Thread
     * observeON : Main Thread
     * @param onNext
     * @param onError
     * @return
     */
    public final EncDisposable subscribeOnMainThread(@NonNull EncConsumer<? super T> onNext, @NonNull EncConsumer<? super Throwable> onError) {
        return subscribeOnMainThread(onNext, onError, EncAction.EMPTY_ACTION);
    }

    /**
     * Subscribe method
     * subscribeOn : Current Thread
     * observeON : Current Thread
     * @param onNext
     * @param onError
     * @param onComplete
     * @return
     */
    public final EncDisposable subscribe(@NonNull EncConsumer<? super T> onNext, @NonNull EncConsumer<? super Throwable> onError, @NonNull EncAction onComplete) {
        Objects.requireNonNull(onNext, "onNext is null");
        Objects.requireNonNull(onError, "onError is null");
        Objects.requireNonNull(onComplete, "onComplete is null");

        return new EncDisposableImpl(subject.subscribe(onNext, onError, onComplete));
    }

    /**
     * Subscribe method
     * subscribeOn : Current Thread
     * observeON : Current Thread
     * @param onNext
     * @param onError
     * @return
     */
    public final EncDisposable subscribe(@NonNull EncConsumer<? super T> onNext, @NonNull EncConsumer<? super Throwable> onError) {
        Objects.requireNonNull(onNext, "onNext is null");
        Objects.requireNonNull(onError, "onError is null");

        return new EncDisposableImpl(subject.subscribe(onNext, onError, EncAction.EMPTY_ACTION));
    }
}
