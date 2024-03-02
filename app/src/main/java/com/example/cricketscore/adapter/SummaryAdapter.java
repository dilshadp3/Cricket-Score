package com.example.cricketscore.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cricketscore.R;
import com.example.cricketscore.models.TeamModel;
import com.example.cricketscore.models.WicketModel;

import java.util.List;

public class SummaryAdapter extends RecyclerView.Adapter<SummaryAdapter.ViewHolder> {
    private List<TeamModel> mData;

    Context context;
    public SummaryAdapter(List<TeamModel> data, Context getContext) {
        context=getContext;
        mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.summary_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.team.setText(mData.get(position).getName());
        holder.players.setLayoutManager(new LinearLayoutManager(context));
        SummaryDetailsAdapter adapter = new SummaryDetailsAdapter(mData.get(position).getPlayers(),mData.get(position).getBowlers());
        holder.players.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView team;
        RecyclerView players;

        public ViewHolder(View itemView) {
            super(itemView);
            team = itemView.findViewById(R.id.team);
            players = itemView.findViewById(R.id.players);

        }
    }
}
