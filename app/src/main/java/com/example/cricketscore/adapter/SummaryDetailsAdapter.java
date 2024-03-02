package com.example.cricketscore.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cricketscore.R;
import com.example.cricketscore.models.BowlerModel;
import com.example.cricketscore.models.PlayerModel;
import com.example.cricketscore.models.TeamModel;

import java.util.List;

public class SummaryDetailsAdapter extends RecyclerView.Adapter<SummaryDetailsAdapter.ViewHolder> {
    private List<PlayerModel> playerModelList;
    private List<BowlerModel> bowlerModelList;

    public SummaryDetailsAdapter(List<PlayerModel> data,List<BowlerModel> data2) {
        playerModelList = data;
        bowlerModelList=data2;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.summary_item_details_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.playerName.setText(playerModelList.get(position).getName());
        holder.playerRuns.setText(playerModelList.get(position).getRuns().toString()+" ("+
                playerModelList.get(position).getBalls().toString()+")");

        holder.bowlerName.setText(bowlerModelList.get(position).getName());
        holder.bowlerWickets.setText(bowlerModelList.get(position).getWickets().toString()+"/"
                +bowlerModelList.get(position).getRunsConceded().toString()+" ("+
                bowlerModelList.get(position).getOvers().toString()+")");
    }

    @Override
    public int getItemCount() {
        return playerModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView playerName,bowlerName,playerRuns,bowlerWickets;


        public ViewHolder(View itemView) {
            super(itemView);
            playerName = itemView.findViewById(R.id.playerName);
            bowlerName = itemView.findViewById(R.id.bowlerName);
            playerRuns = itemView.findViewById(R.id.playerRuns);
            bowlerWickets = itemView.findViewById(R.id.bowlerWicket);


        }
    }
}
