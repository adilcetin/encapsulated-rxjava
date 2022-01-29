package com.github.adil.encapsulatedrxjavalibrary.disposable;

import io.reactivex.rxjava3.disposables.Disposable;

public class EncDisposableImpl implements EncDisposable {

    private final Disposable disposable;

    public EncDisposableImpl(Disposable disposable) { this.disposable = disposable; }

    @Override
    public void dispose() { disposable.dispose(); }

    @Override
    public boolean isDisposed() { return disposable.isDisposed(); }
}
