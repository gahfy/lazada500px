package net.gahfy.lazada500px.ui.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * ViewHolder for MVP pattern.
 * @param <P> Theclass of the presenter of the ViewHolder
 * @author Gaëtan HERFRAY
 */
public abstract class MvpViewHolder<P extends BasePresenter> extends RecyclerView.ViewHolder {
    /** The presenter of the MvpViewHolder */
    protected P presenter;

    /**
     * Instantiates a new MvpViewHolder.
     * @param itemView The view of the item
     */
    public MvpViewHolder(View itemView) {
        super(itemView);
    }

    /**
     * Binds the presenter to the MvpViewHolder.
     * @param presenter the presenter to bind to the MvpViewHolder
     */
    @SuppressWarnings("unchecked")
    public void bindPresenter(P presenter) {
        this.presenter = presenter;
        if(presenter != null)
            presenter.bindView(this);
    }

    /**
     * Unbinds the presenter to the MvpViewHolder.
     */
    public void unbindPresenter() {
        presenter = null;
    }
}
