package com.github.adil.encapsulatedrxjavalibrary.actions;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.functions.Consumer;

public interface EncConsumer<@NonNull T> extends Consumer<@NonNull T> {

}
