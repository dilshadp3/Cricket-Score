package com.example.cricketscore.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cricketscore.R;
import com.example.cricketscore.models.DismissalModel;
import com.example.cricketscore.models.PlayerModel;
import com.example.cricketscore.models.TeamModel;
import com.example.cricketscore.utils.Calculations;

import java.math.BigDecimal;
import java.util.List;

public class BattingAdapter extends RecyclerView.Adapter<BattingAdapter.ViewHolder> {
    private List<PlayerModel> mData;

    public BattingAdapter(List<PlayerModel> data) {
        mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.batting_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DismissalModel dismissalModel=mData.get(position).getDismissal();
        String dismissalType="";
        String dismissalFielder=dismissalModel.getFielder();
        if (dismissalModel!=null&&dismissalModel.getType()!=null&&!dismissalModel.getType().isEmpty()){
            if (dismissalModel.getType().equals("Caught")){
                dismissalType="c";
            }else if (dismissalModel.getType().equals("Bowled")){
                dismissalType="ht wkt";
            }else if (dismissalModel.getType().equals("Run Out")){
                dismissalType="ro";
            }else if (dismissalModel.getType().equals("LBW")){
                dismissalType="lbw";
            }else if (dismissalModel.getType().equals("Stumped")){
                dismissalType="st";
            }
        }else {
            holder.dismissal.setText("not out");
        }
        holder.name.setText(mData.get(position).getName());
        holder.run.setText(mData.get(position).getRuns().toString());
        holder.ball.setText(mData.get(position).getBalls().toString());
        holder.fours.setText(mData.get(position).getFours().toString());
        holder.sixes.setText(mData.get(position).getSixes().toString());
        String strikeRate=Calculations.StrikeRateCalculation(mData.get(position).getRuns(),
                mData.get(position).getBalls());
        holder.strikeRate.setText(strikeRate.toString());

        if (!dismissalType.isEmpty()&&dismissalFielder!=null){
            holder.dismissal.setText(dismissalType+" "+dismissalFielder+" b "+dismissalModel.getBowler());
        }else {
            holder.dismissal.setText(dismissalType+" b "+dismissalModel.getBowler());
        }

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
