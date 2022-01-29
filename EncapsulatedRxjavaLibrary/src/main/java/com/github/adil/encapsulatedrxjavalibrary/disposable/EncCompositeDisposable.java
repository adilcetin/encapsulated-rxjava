package com.github.adil.encapsulatedrxjavalibrary.disposable;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class EncCompositeDisposable implements EncDisposable {

    private final CompositeDisposable compositeDisposable;

    public EncCompositeDisposable() { this.compositeDisposable = new CompositeDisposable(); }

    public EncCompositeDisposable(@NonNull EncDisposable... disposables) { this.compositeDisposable = new CompositeDisposable(disposables); }

    public void dispose() { compositeDisposable.dispose(); }

    public boolean isDisposed() {
        return compositeDisposable.isDisposed();
    }

    public boolean add(@NonNull EncDisposable disposable) { return compositeDisposable.add(disposable); }

    public boolean addAll(@NonNull EncDisposable... disposables) { return compositeDisposable.addAll(disposables); }

    public boolean remove(@NonNull EncDisposable disposable) { return compositeDisposable.remove(disposable); }

    public void clear() { compositeDisposable.clear(); }

    public int size() { return compositeDisposable.size(); }

}
