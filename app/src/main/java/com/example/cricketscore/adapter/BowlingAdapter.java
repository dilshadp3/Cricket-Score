package com.example.cricketscore.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cricketscore.R;
import com.example.cricketscore.models.BowlerModel;
import com.example.cricketscore.models.DismissalModel;
import com.example.cricketscore.models.PlayerModel;
import com.example.cricketscore.utils.Calculations;

import java.util.List;

public class BowlingAdapter extends RecyclerView.Adapter<BowlingAdapter.ViewHolder> {
    private List<BowlerModel> mData;

    public BowlingAdapter(List<BowlerModel> data) {
        mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.batting_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.dismissal.setVisibility(View.GONE);
        holder.name.setText(mData.get(position).getName());
        holder.run.setText(mData.get(position).getOvers().toString());
        holder.ball.setText("0");
        holder.fours.setText(mData.get(position).getRunsConceded().toString());
        holder.sixes.setText(mData.get(position).getWickets().toString());
        String strikeRate= Calculations.EconomyCalculation(mData.get(position).getRunsConceded(),
                mData.get(position).getOvers());
        holder.strikeRate.setText(strikeRate.toString());



    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,run,ball,fours,sixes,strikeRate,dismissal;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            run = itemView.findViewById(R.id.run);
            ball = itemView.findViewById(R.id.ball);
            fours = itemView.findViewById(R.id.fours);
            sixes = itemView.findViewById(R.id.sixes);
            strikeRate = itemView.findViewById(R.id.strikeRate);
            dismissal = itemView.findViewById(R.id.dismissal);
        }
    }
}


