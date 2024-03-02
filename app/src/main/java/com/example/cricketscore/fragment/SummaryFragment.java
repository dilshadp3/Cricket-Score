package com.example.cricketscore.fragment;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cricketscore.R;
import com.example.cricketscore.adapter.BowlingAdapter;
import com.example.cricketscore.adapter.SummaryAdapter;
import com.example.cricketscore.models.BowlerModel;
import com.example.cricketscore.models.MatchDetailsModel;
import com.example.cricketscore.models.PlayerModel;
import com.example.cricketscore.models.TeamModel;

import java.util.ArrayList;
import java.util.List;

public class SummaryFragment extends Fragment {
    MatchDetailsModel matchDetailsModel;
    private RecyclerView recyclerViewSummary;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.summary_fragment, viewGroup, false);
        recyclerViewSummary= (RecyclerView)view.findViewById(R.id.summary);
        Bundle bundle = getArguments();
        if (bundle != null) {
            matchDetailsModel = bundle.getParcelable("model");
            Log.d(TAG, "onCreateView:model "+matchDetailsModel);
        }



        setData(matchDetailsModel);
        return view;
    }

    private void setData(MatchDetailsModel matchDetailsModel) {
        List<TeamModel>teamModelArrayList=new ArrayList<>();

        List<PlayerModel>playerModelListOneMain=matchDetailsModel.getTeams().get(0).getPlayers();
        List<PlayerModel>playerModelListTwoMain=matchDetailsModel.getTeams().get(1).getPlayers();

        List<BowlerModel>bowlerModelListOneMain=matchDetailsModel.getTeams().get(1).getBowlers();
        List<BowlerModel>bowlerModelListTwoMain=matchDetailsModel.getTeams().get(0).getBowlers();

        TeamModel teamModelListOne=new TeamModel();

        teamModelListOne.setName(matchDetailsModel.getTeams().get(0).getName());
        teamModelListOne.setPlayers(playerModelListOneMain);
        teamModelListOne.setBowlers(bowlerModelListOneMain);

        TeamModel teamModelListTwo=new TeamModel();
        teamModelListTwo.setName(matchDetailsModel.getTeams().get(1).getName());
        teamModelListTwo.setPlayers(playerModelListTwoMain);
        teamModelListTwo.setBowlers(bowlerModelListTwoMain);

        teamModelArrayList.add(teamModelListOne);
        teamModelArrayList.add(teamModelListTwo);



        recyclerViewSummary.setLayoutManager(new LinearLayoutManager(getActivity()));
        SummaryAdapter adapter = new SummaryAdapter(teamModelArrayList,getContext());
        recyclerViewSummary.setAdapter(adapter);


//        List<BowlerModel> bowlerModelListOne;
//        List<PlayerModel>playerModelListOne;
//
//        List<TeamModel> teamModelListTwo;
//        List<BowlerModel> bowlerModelListTwo;
//        List<PlayerModel>playerModelListTwo;
//
//        for (TeamModel temp:matchDetailsModel.getTeams()){
//
//        }




    }
}
