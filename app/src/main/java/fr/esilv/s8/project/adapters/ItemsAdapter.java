package fr.esilv.s8.project.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.esilv.s8.project.R;
import fr.esilv.s8.project.interfaces.OnInfosSelectedListener;
import fr.esilv.s8.project.models.Items;
import fr.esilv.s8.project.viewholders.ItemsViewHolder;

/**
 * Created by Harry on 20/03/2017.
 */

public class ItemsAdapter extends RecyclerView.Adapter<ItemsViewHolder> {

    private final Items items;
    private OnInfosSelectedListener onInfosSelectedListener;

    public ItemsAdapter(Items items) {
        this.items = items;
    }

    @Override
    public ItemsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_item, parent, false);
        return new ItemsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemsViewHolder holder, int position) {
        holder.setOnInfosSelectedListener(onInfosSelectedListener);
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setOnInfosSelectedListener(OnInfosSelectedListener onInfosSelectedListener) {
        this.onInfosSelectedListener = onInfosSelectedListener;
    }

}
