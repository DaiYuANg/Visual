package org.visual.model.component.animation;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public abstract class Callback<T, E extends Throwable> {
    private boolean called = false;

    protected abstract void onSucceeded(T value);

    protected abstract void onFailed(E err);

    protected void doFinally() {

    }

    public final boolean isCalled() {
        return called;
    }

    public final void succeeded() {
        succeeded(null);
    }

    public final void succeeded(T value) {
        if (called) {
            return;
        }
        called = true;
        onSucceeded(value);
        doFinally();
    }

    public final void failed(E err) {
        if (called) {
            return;
        }
        called = true;
        onFailed(err);
        doFinally();
    }

    public final void finish(E err) {
        finish(err, null);
    }

    public final void finish(E err, T value) {
        if (err != null) {
            failed(err);
        } else {
            succeeded(value);
        }
    }

    public static <T, E extends Throwable> Callback<T, E> ofFunction(BiConsumer<E, T> cb) {
        return new Callback<T, E>() {
            @Override
            protected void onSucceeded(T value) {
                cb.accept(null, value);
            }

            @Override
            protected void onFailed(E err) {
                cb.accept(err, null);
            }
        };
    }

    public static <T, E extends Throwable> Callback<T, E> ofIgnoreExceptionFunction(Consumer<T> cb) {
        return new Callback<T, E>() {
            @Override
            protected void onSucceeded(T value) {
                cb.accept(value);
            }

            @Override
            protected void onFailed(E err) {
            }
        };
    }
}
