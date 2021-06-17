package com.example.covid_19application.ui.shop;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid_19application.R;

import java.util.ArrayList;

public class MaskAdapter extends  RecyclerView.Adapter<MaskAdapter.ViewHolder> implements OnMaskItemClickListener {
    ArrayList<Mask> items = new ArrayList<Mask>();
    OnMaskItemClickListener listener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.mask_item, parent, false);

        return new ViewHolder(itemView, this);
    }

    public void setOnItemClickListener(OnMaskItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Mask mask = items.get(position);
        holder.setItem(mask);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(Mask mask) {
        items.add(mask);
        notifyDataSetChanged();
    }

    public void setItems(ArrayList<Mask> items) {
        this.items = items;
    }

    public Mask getItem(int position) {
        return items.get(position);
    }

    public void setItem(int position, Mask mask) {
        items.set(position, mask);
    }

    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {
        if (listener != null) {
            listener.onItemClick(holder, view, position);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvProduct, tvKind, tvPrice;

        public ViewHolder(@NonNull View itemView, final OnMaskItemClickListener listener) {
            super(itemView);
            tvProduct = itemView.findViewById(R.id.tvProduct);
            tvKind = itemView.findViewById(R.id.tvKind);
            tvPrice = itemView.findViewById(R.id.tvPrice);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null) {
                        listener.onItemClick(ViewHolder.this, view, position);
                    }
                }
            });
        }

        public void setItem(Mask item) {
            tvProduct.setText(item.getProduct());
            tvKind.setText(item.getKind());
            tvPrice.setText(item.getPrice());
        }
    }
}