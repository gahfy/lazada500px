package net.gahfy.lazada500px.ui.base;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import java.util.HashMap;
import java.util.Map;

/**
 * RecyclerView's Adapter for MVP Pattern.
 * @param <M> Class of the model
 * @param <P> Class of the presenter
 * @param <VH> Class of the MVP ViewHolder
 * @author Gaëtan HERFRAY
 */
public abstract class MvpRecyclerAdapter<M, P extends BasePresenter, VH extends MvpViewHolder> extends RecyclerView.Adapter<VH> {
    /** The presenters of the Adapter */
    protected final Map<Object, P> presenters;

    /**
     * Instantiates a new MvpRecyclerAdapter.
     */
    public MvpRecyclerAdapter() {
        presenters = new HashMap<>();
    }

    /**
     * Returns the presenter associated to the specified model.
     * @param model the model from which to get the presenter
     * @return the presenter associated to the specified model
     */
    @NonNull protected P getPresenter(@NonNull M model) {
        return presenters.get(getModelId(model));
    }

    /**
     * Returns a presenter for a model.
     * @param model the model for which to return the presenter
     * @return a presenter for the model
     */
    @NonNull protected abstract P createPresenter(@NonNull M model);

    /**
     * Returns the identifier of the specified model.
     * @param model the model from which to return the identifier
     * @return the identifier of the specified model
     */
    @NonNull protected abstract Object getModelId(@NonNull M model);

    @Override
    public void onViewRecycled(VH holder) {
        super.onViewRecycled(holder);

        holder.unbindPresenter();
    }

    @Override
    public boolean onFailedToRecycleView(VH holder) {
        // Sometimes, if animations are running on the itemView's children, the RecyclerView won't
        // be able to recycle the view. We should still unbind the presenter.
        holder.unbindPresenter();

        return super.onFailedToRecycleView(holder);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onBindViewHolder(VH holder, int position) {
        holder.bindPresenter(getPresenter(getItem(position)));
    }

    /**
     * Returns the item at the specified position.
     * @param position the position of the item to return
     * @return the item at the specified position
     */
    protected abstract M getItem(int position);
}
