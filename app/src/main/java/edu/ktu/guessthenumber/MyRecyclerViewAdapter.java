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
    private LayoutInflater mInflater;


    // data is passed into the constructor
    MyRecyclerViewAdapter(Context context, List<GameClass> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name, dif, result;

        public ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name_list);
            dif = view.findViewById(R.id.difficulty_list);
            result = view.findViewById(R.id.result_list);

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


}