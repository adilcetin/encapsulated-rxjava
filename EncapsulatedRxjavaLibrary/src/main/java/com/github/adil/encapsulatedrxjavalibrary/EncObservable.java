package com.github.adil.encapsulatedrxjavalibrary;

import com.github.adil.encapsulatedrxjavalibrary.actions.EncAction;
import com.github.adil.encapsulatedrxjavalibrary.actions.EncConsumer;
import com.github.adil.encapsulatedrxjavalibrary.disposable.EncDisposable;
import com.github.adil.encapsulatedrxjavalibrary.disposable.EncDisposableImpl;
import com.github.adil.encapsulatedrxjavalibrary.onclass.EncObservableOnClass;
import com.github.adil.encapsulatedrxjavalibrary.onsubscribe.EncObservableOnSubscribe;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class EncObservable<@NonNull T>  {

    private Observable<T> observable;

    private EncObservable(@NonNull EncObservableOnSubscribe<T> source) {
        observable = Observable.create(source);
    }

    protected EncObservable(Observable<T> observable) {
        this.observable = observable;
    }

    private Observable<T> getObservable(){
        return observable;
    }

    public EncDisposable subscribe(@NonNull EncConsumer<? super Throwable> onError){
        return new EncDisposableImpl(observable.subscribe(bool -> {}, onError));
    }

    /**
     * Method only creates observable
     * @param source
     * @param <T>
     * @return
     */
    public static <T> EncObservable<T> create(@NonNull EncObservableOnSubscribe<T> source) {
        return new EncObservable<T>(source);
    }

    public EncObservable<List<T>> buffer(int bufferSize){
        // TODO Burası tekrar gözden geçirilecek. Neden tekrar bir observable oluşturuldu.
        return new EncObservable<>(observable.buffer(bufferSize));
    }

    public EncObservable<T> throttleLast(long timeInMilis){
        return new EncObservable<>(observable.throttleLast(timeInMilis, TimeUnit.MILLISECONDS));
    }

    public EncObservable<T> throttleLatest(long timeInMilis){
        return new EncObservable<>(observable.throttleLatest(timeInMilis, TimeUnit.MILLISECONDS));
    }

    public EncObservable<T> sample(long timeInMilis){
        return new EncObservable<>(observable.sample(timeInMilis, TimeUnit.MILLISECONDS));
    }

    public EncObservable<T> skip(long timeInMilis){
        return new EncObservable<>(observable.skip(timeInMilis, TimeUnit.MILLISECONDS));
    }

    /**
     * Method creates observable and subscribes (on IO thread) to given source. It used in trivial cases of the subscribe result.
     * It is recommended to use it when you want to do any operation outside of the main thread.
     * subscribeOn : IO Thread
     * observeON : IO Thread
     * @param source
     * @param onError
     * @param <T>
     */
    public static <T> EncDisposable runOnIOThread(@NonNull EncObservableOnSubscribe<T> source, @NonNull EncConsumer<? super Throwable> onError){
        Objects.requireNonNull(source, "source is null");
        Objects.requireNonNull(onError, "onError is null");
        return new EncDisposableImpl(create(source).getObservable().subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(bool -> {}, onError));
    }

    /**
     * Method creates observable and subscribes (on IO thread) to given source.
     * It is recommended to use it when you want to do any operation outside of the main thread.
     * subscribeOn : IO Thread
     * observeON : IO Thread
     * @param observableOnClass
     * @param <T>
     */
    public static <T> EncDisposable runOnIOThread(@NonNull EncObservableOnClass<T> observableOnClass){
        Objects.requireNonNull(observableOnClass.source(), "source is null");
        return new EncDisposableImpl(create(observableOnClass.source()).getObservable().subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(observableOnClass::onNext, observableOnClass::onError, observableOnClass::onComplete));
    }

    /**
     * Method creates observable and subscribes (on New thread) to given source. It used in trivial cases of the subscribe result.
     * It is recommended to use it when you want to do any operation outside of the main thread.
     * subscribeOn : New Thread
     * observeON : New Thread
     * @param source
     * @param onError
     * @param <T>
     */
    public static <T> EncDisposable runOnNewThread(@NonNull EncObservableOnSubscribe<T> source, @NonNull EncConsumer<? super Throwable> onError){
        Objects.requireNonNull(source, "source is null");
        Objects.requireNonNull(onError, "onError is null");
        return new EncDisposableImpl(create(source).getObservable().subscribeOn(Schedulers.newThread()).observeOn(Schedulers.newThread()).subscribe(bool -> {}, onError));
    }

    /**
     * Method creates observable and subscribes (on New thread) to given source.
     * It is recommended to use it when you want to do any operation outside of the main thread.
     * subscribeOn : New Thread
     * observeON : New Thread
     * @param observableOnClass
     * @param <T>
     */
    public static <T> EncDisposable runOnNewThread(@NonNull EncObservableOnClass<T> observableOnClass){
        Objects.requireNonNull(observableOnClass.source(), "source is null");
        return new EncDisposableImpl(create(observableOnClass.source()).getObservable().subscribeOn(Schedulers.newThread()).observeOn(Schedulers.newThread()).subscribe(observableOnClass::onNext, observableOnClass::onError, observableOnClass::onComplete));
    }

    /**
     * Method creates observable and subscribes (on IO thread) to given source.
     * It is recommended to use it when you want to do any operation outside of the main thread.
     * subscribeOn : IO Thread
     * observeON : Main Thread
     * @param observableOnClass
     * @param <T>
     */
    public static <T> EncDisposable runOnIOToMainThread(@NonNull EncObservableOnClass<T> observableOnClass){
        Objects.requireNonNull(observableOnClass.source(), "source is null");
        return new EncDisposableImpl(create(observableOnClass.source()).getObservable().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observableOnClass::onNext, observableOnClass::onError, observableOnClass::onComplete));
    }

    /**
     * Method creates observable and subscribes (on New thread) to given source.
     * It is recommended to use it when you want to do any operation outside of the main thread.
     * subscribeOn : New Thread
     * observeON : Main Thread
     * @param observableOnClass
     * @param <T>
     */
    public static <T> EncDisposable runOnNewToMainThread(@NonNull EncObservableOnClass<T> observableOnClass){
        Objects.requireNonNull(observableOnClass.source(), "source is null");
        return new EncDisposableImpl(create(observableOnClass.source()).getObservable().subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(observableOnClass::onNext, observableOnClass::onError, observableOnClass::onComplete));
    }


    /**
     * Method creates observable and subscribes (on Main thread) to given source. It used in trivial cases of the subscribe result.
     * It is recommended to use it when you want to do any operation outside of the main thread.
     * subscribeOn : Main Thread
     * observeON : Main Thread
     * @param source
     * @param onError
     * @param <T>
     */
    public static <T> EncDisposable runOnMainThread(@NonNull EncObservableOnSubscribe<T> source, @NonNull EncConsumer<? super Throwable> onError){
        Objects.requireNonNull(source, "source is null");
        Objects.requireNonNull(onError, "onError is null");
        return new EncDisposableImpl(create(source).getObservable().subscribeOn(AndroidSchedulers.mainThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(bool -> {}, onError));
    }

    /**
     * Method creates observable and subscribes (on Main thread) to given source.
     * It is recommended to use it when you want to do any operation outside of the main thread.
     * subscribeOn : Main Thread
     * observeON : Main Thread
     * @param observableOnClass
     * @param <T>
     * @param <T>
     */
    public static <T> EncDisposable runOnMainThread(@NonNull EncObservableOnClass<T> observableOnClass){
        Objects.requireNonNull(observableOnClass.source(), "source is null");
        return new EncDisposableImpl(create(observableOnClass.source()).getObservable().subscribeOn(AndroidSchedulers.mainThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(observableOnClass::onNext, observableOnClass::onError, observableOnClass::onComplete));
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

        return new EncDisposableImpl(getObservable().subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(onNext, onError, onComplete));
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

        return new EncDisposableImpl(getObservable().subscribeOn(Schedulers.newThread()).observeOn(Schedulers.newThread()).subscribe(onNext, onError, onComplete));
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

        return new EncDisposableImpl(getObservable().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(onNext, onError, onComplete));
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

        return new EncDisposableImpl(getObservable().subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(onNext, onError, onComplete));
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

        return new EncDisposableImpl(getObservable().subscribeOn(AndroidSchedulers.mainThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(onNext, onError, onComplete));
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

        return new EncDisposableImpl(getObservable().subscribe(onNext, onError, onComplete));
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
        return subscribe(onNext, onError, EncAction.EMPTY_ACTION);
    }

    /**
     * Method creates interval in minutes and subscribes it
     * SubscribeOn : Single Thread
     * ObserveOn : Single Thread
     * @param period
     * @param onNext
     * @param onError
     * @return
     */
    public static EncDisposable runCycleInMinutes(long period, @NonNull EncConsumer<Long> onNext, @NonNull EncConsumer<? super Throwable> onError){
        Objects.requireNonNull(onNext, "onNext is null");
        Objects.requireNonNull(onError, "onError is null");
        return new EncDisposableImpl(Observable.interval(0, period, TimeUnit.MINUTES, Schedulers.single()).subscribe(onNext, onError));
    }

    /**
     * Method creates interval in seconds and subscribes it
     * SubscribeOn : Single Thread
     * ObserveOn : Single Thread
     * @param period
     * @param onNext
     * @param onError
     * @return
     */
    public static EncDisposable runCycleInSeconds(long period, @NonNull EncConsumer<Long> onNext, @NonNull EncConsumer<? super Throwable> onError){
        Objects.requireNonNull(onNext, "onNext is null");
        Objects.requireNonNull(onError, "onError is null");
        return new EncDisposableImpl(Observable.interval(0, period, TimeUnit.SECONDS, Schedulers.single()).subscribe(onNext, onError));
    }

    /**
     * Method creates interval in milliseconds and subscribes it
     * SubscribeOn : Single Thread
     * @param period
     * @param onNext
     * @param onError
     * @return
     */
    public static EncDisposable runCycleInMilliseconds(long period, @NonNull EncConsumer<Long> onNext, @NonNull EncConsumer<? super Throwable> onError){
        Objects.requireNonNull(onNext, "onNext is null");
        Objects.requireNonNull(onError, "onError is null");
        return new EncDisposableImpl(Observable.interval(0, period, TimeUnit.MILLISECONDS, Schedulers.single()).subscribe(onNext, onError));
    }

    /**
     * Method creates timer in minutes and subscribes it
     * SubscribeOn : IO Thread
     * @param period
     * @param onError
     * @param onComplete
     * @return
     */
    public static EncDisposable runTimerInMinutes(long period, @NonNull EncConsumer<? super Throwable> onError, @NonNull EncAction onComplete){
        Objects.requireNonNull(onComplete, "onComplete is null");
        Objects.requireNonNull(onError, "onError is null");
        return new EncDisposableImpl(Observable.timer(period, TimeUnit.MINUTES).subscribeOn(Schedulers.io()).subscribe(bool -> {}, onError, onComplete));
    }

    /**
     * Method creates timer in seconds and subscribes it
     * SubscribeOn : IO Thread
     * @param period
     * @param onError
     * @param onComplete
     * @return
     */
    public static EncDisposable runTimerInSeconds(long period, @NonNull EncConsumer<? super Throwable> onError, @NonNull EncAction onComplete){
        Objects.requireNonNull(onComplete, "onComplete is null");
        Objects.requireNonNull(onError, "onError is null");
        return new EncDisposableImpl(Observable.timer(period, TimeUnit.SECONDS).subscribeOn(Schedulers.io()).subscribe(bool -> {}, onError, onComplete));
    }

    /**
     * Method creates timer in milliseconds and subscribes it
     * SubscribeOn : IO Thread
     * @param period
     * @param onError
     * @param onComplete
     * @return
     */
    public static EncDisposable runTimerInMilliSeconds(long period, @NonNull EncConsumer<? super Throwable> onError, @NonNull EncAction onComplete){
        Objects.requireNonNull(onComplete, "onComplete is null");
        Objects.requireNonNull(onError, "onError is null");
        return new EncDisposableImpl(Observable.timer(period, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.io()).subscribe(bool -> {}, onError, onComplete));
    }

    /**
     * Method creates observable and just value
     * @param value
     * @param <T>
     * @return
     */
    public static <T> EncObservable<T> just(@NonNull T value) {
       return new EncObservable<T>(Observable.just(value));
    }

    public EncObservable<T> timeout(long timeout, @NonNull TimeUnit unit) {
        return new EncObservable<T>(getObservable().timeout(timeout, unit));
    }
}
