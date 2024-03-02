package com.example.cricketscore.fragment;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cricketscore.R;
import com.example.cricketscore.adapter.BattingAdapter;
import com.example.cricketscore.adapter.BowlingAdapter;
import com.example.cricketscore.adapter.WicketFallAdapter;
import com.example.cricketscore.models.BowlerModel;
import com.example.cricketscore.models.FallOfWicketModel;
import com.example.cricketscore.models.PlayerModel;
import com.example.cricketscore.models.TeamModel;
import com.example.cricketscore.models.WicketModel;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import java.util.List;

public class ScoreCardTabTwoFragment extends Fragment {
    private RecyclerView recyclerViewBatting, recyclerViewBowling,recyclerViewWicketFall;
    private TeamModel teamModel;
    private FallOfWicketModel fallOfWicketModel;
    TextView totalRunView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.score_card_detail, viewGroup, false);
        totalRunView = (TextView) view.findViewById(R.id.totlaRun);
        recyclerViewBowling= (RecyclerView)view.findViewById(R.id.bowling);
        recyclerViewBatting= (RecyclerView)view.findViewById(R.id.batting);
        recyclerViewWicketFall= (RecyclerView)view.findViewById(R.id.wicketFall);
        Bundle bundle = getArguments();
        if (bundle != null) {
            teamModel = bundle.getParcelable("teamModel");
            fallOfWicketModel = bundle.getParcelable("wicketModel");
            Log.d(TAG, "onCreateView:model "+teamModel);
            // Use your Parcelable model here
        }
        totalCalculation(teamModel.getPlayers(),teamModel.getBowlers());
        setBattingAdapter(teamModel.getPlayers());
        setBowlingAdapter(teamModel.getBowlers());
        setWicketFallAdapter(fallOfWicketModel.getWickets());
        return view;
    }

    private void setBowlingAdapter(List<BowlerModel> players) {
        recyclerViewBowling.setLayoutManager(new LinearLayoutManager(getActivity()));
        BowlingAdapter adapter = new BowlingAdapter(players);
        recyclerViewBowling.setAdapter(adapter);
    }
    private void setBattingAdapter(List<PlayerModel> players) {
        recyclerViewBatting.setLayoutManager(new LinearLayoutManager(getActivity()));

        BattingAdapter adapter = new BattingAdapter(players);
        recyclerViewBatting.setAdapter(adapter);
    }

    private void totalCalculation(List<PlayerModel> players,List<BowlerModel> bowlerModels) {
        int totalRun=0,totalWkt=0,totalOver=0;
        for (PlayerModel temp:players){
            totalRun=totalRun+temp.getRuns();
            if (temp.getDismissal()!=null){
                totalWkt=totalWkt+1;
            }
        }

        for (BowlerModel temp:bowlerModels){
            totalOver=totalOver+temp.getOvers();

        }
        totalRunView.setText(totalRun+" ("+totalWkt+" wkts, "+totalOver+" ov)");
    }

    private void setWicketFallAdapter(List<WicketModel> wicketModels) {
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(getContext());
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setJustifyContent(JustifyContent.FLEX_START);
        recyclerViewWicketFall.setNestedScrollingEnabled(false);
        recyclerViewWicketFall.setLayoutManager(layoutManager);
        WicketFallAdapter adapter = new WicketFallAdapter(wicketModels);
        recyclerViewWicketFall.setAdapter(adapter);
    }

}
