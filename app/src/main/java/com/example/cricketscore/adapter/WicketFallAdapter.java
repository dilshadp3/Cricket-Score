package com.example.cricketscore.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cricketscore.R;
import com.example.cricketscore.models.DismissalModel;
import com.example.cricketscore.models.FallOfWicketModel;
import com.example.cricketscore.models.PlayerModel;
import com.example.cricketscore.models.WicketModel;
import com.example.cricketscore.utils.Calculations;

import java.util.List;

public class WicketFallAdapter extends RecyclerView.Adapter<WicketFallAdapter.ViewHolder> {
    private List<WicketModel> mData;

    public WicketFallAdapter(List<WicketModel> data) {
        mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wicket_fall_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.wicket.setText(mData.get(position).getScore()+"/"+(position+1));
        holder.player.setText("("+mData.get(position).getPlayer()+", "+mData.get(position).getOvers()+" ov)");


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView wicket,player;

        public ViewHolder(View itemView) {
            super(itemView);
            wicket = itemView.findViewById(R.id.wicket);
            player = itemView.findViewById(R.id.player);

        }
    }
}
