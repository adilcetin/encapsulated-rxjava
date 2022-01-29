package com.github.adil.encapsulatedrxjavalibrary.actions;

import io.reactivex.rxjava3.functions.Action;

public interface EncAction extends Action {

    EncAction EMPTY_ACTION = new EmptyAction();

    final class EmptyAction implements EncAction {
        @Override
        public void run() { }

        @Override
        public String toString() {
            return "EmptyAction";
        }
    }
}
