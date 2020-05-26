package edu.ktu.guessthenumber;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private List<GameClass> mData;
    private Button deleteBtn;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    MyRecyclerViewAdapter(Context context, List<GameClass> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView name, dif, result;

        public ViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name_list);
            dif = (TextView) view.findViewById(R.id.difficulty_list);
            result = (TextView) view.findViewById(R.id.result_list);
          //  deleteBtn = (Button) view.findViewById(R.id.delete_btn);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_row, parent, false);

        return new ViewHolder(itemView);
    }


    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GameClass game = mData.get(position);
        holder.name.setText(game.getName());
        holder.dif.setText((game.getDif()));
        holder.result.setText(game.getResult());

    }


    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }




   /* // convenience method for getting data at click position
    String getItem(int id) {
        return mData.get(id);
    }*/

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}