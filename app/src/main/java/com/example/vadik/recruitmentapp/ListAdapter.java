package com.example.vadik.recruitmentapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

/**
 * Adapter class
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder>{

    private List<RecyclerItem> data;
    private Context context;

    public ListAdapter (List<RecyclerItem> data, Context context){
        this.data = data;
        this.context = context;
    }

    @Override
    public ListAdapter.ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ListViewHolder(LayoutInflater
                                    .from(parent.getContext())
                                    .inflate(R.layout.recycler_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ListAdapter.ListViewHolder holder, int position) {

        int random = (int) (Math.random() * 10);
        holder.getText().setText(data.get(position).getRecyclerItemText());
        Colour colour = data.get(position).getColorOfCircle();
        if(colour == Colour.BLUE){
            holder.getCircle().setBackgroundResource(R.drawable.cicle_blue);
        } else if(colour == Colour.RED){
            holder.getCircle().setBackgroundResource(R.drawable.cicle_red);
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        private TextView text;
        private ImageView circle;
        private View listItem;

        public ListViewHolder(View view){
            super(view);
            text = (TextView) view.findViewById(R.id.list_item_text);
            circle = (ImageView) view.findViewById(R.id.list_item_circle);
            listItem = view;
        }

        public TextView getText(){
            return text;
        }

        public ImageView getCircle(){
            return circle;
        }

        public View getListItem(){
            return listItem;
        }
    }
}