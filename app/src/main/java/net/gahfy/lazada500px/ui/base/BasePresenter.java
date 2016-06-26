package net.gahfy.lazada500px.ui.base;

import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;

/**
 * Base presenter. It simply deals with model and views.
 * @param <M> Model class
 * @param <V> View class
 * @author Gaëtan HERFRAY
 */
public abstract class BasePresenter<M, V> {
    /** The model of the base presenter */
    protected M model;
    /** The reference to the view of the base presenter */
    private WeakReference<V> view;

    /**
     * Sets the model of the base presenter.
     * @param model the model of the base presenter to set
     */
    public void setModel(M model) {
        resetState();
        this.model = model;
        if (setupDone()) {
            updateView();
        }
    }

    /**
     * Resets the view, when model is updated.
     * May be overridden.
     */
    protected void resetState(){}

    /**
     * Sets the reference of the view of the presenter.
     * @param view the view to reference in the presenter to set
     */
    public void bindView(@NonNull V view) {
        this.view = new WeakReference<>(view);
        if (setupDone()) {
            updateView();
        }
    }

    /**
     * Unbinds the view from the presenter
     */
    public void unbindView() {
        this.view = null;
    }

    /**
     * Returns the view of the presenter.
     * @return the view of the presenter
     */
    protected V view() {
        if (view == null) {
            return null;
        } else {
            return view.get();
        }
    }

    /**
     * Updates the view
     */
    protected abstract void updateView();

    /**
     * Returnw whether both model and view are set.
     * @return whether both model and view are set (true) or not (false)
     */
    protected boolean setupDone() {
        return view() != null && model != null;
    }
}