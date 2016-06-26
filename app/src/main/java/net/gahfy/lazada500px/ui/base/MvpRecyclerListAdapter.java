package net.gahfy.lazada500px.ui.base;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * MVP RecyclerView's Adapter for a list of models.
 * @param <M> The class of the model
 * @param <P> The class of the presenter
 * @param <VH> The class of the MVP ViewHolder
 * @author Gaëtan HERFRAY
 */
public abstract class MvpRecyclerListAdapter<M, P extends BasePresenter, VH extends MvpViewHolder> extends MvpRecyclerAdapter<M, P, VH> {
    /** The list of models of the MVP ViewHolder*/
    private final List<M> models;

    /**
     * Instantiates a new MvpRecyclerListAdapter
     */
    public MvpRecyclerListAdapter() {
        models = new ArrayList<>();
    }

    /**
     * Removes all the models of the list and put new ones.
     * @param data the list of models to replace with
     */
    public void clearAndAddAll(Collection<M> data) {
        models.clear();
        presenters.clear();

        for (M item : data) {
            addInternal(item);
        }

        notifyDataSetChanged();
    }

    /**
     * Adds all the models of the list.
     * @param data the list of models to add
     */
    public void addAll(Collection<M> data) {
        for (M item : data) {
            addInternal(item);
        }

        int addedSize = data.size();
        int oldSize = models.size() - addedSize;
        notifyItemRangeInserted(oldSize, addedSize);
    }

    /**
     * Adds the specified item to the Adapter.
     * @param item the item to add to the Adapter
     */
    private void addInternal(M item) {
        models.add(item);
        presenters.put(getModelId(item), createPresenter(item));
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    @Override
    protected M getItem(int position) {
        return models.get(position);
    }
}
