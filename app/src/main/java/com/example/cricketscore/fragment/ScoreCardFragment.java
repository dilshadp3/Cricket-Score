package com.example.cricketscore.fragment;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.cricketscore.R;
import com.example.cricketscore.adapter.ViewPageAdapter;
import com.example.cricketscore.models.BowlerModel;
import com.example.cricketscore.models.FallOfWicketModel;
import com.example.cricketscore.models.MatchDetailsModel;
import com.example.cricketscore.models.PlayerModel;
import com.example.cricketscore.models.TeamModel;
import com.example.cricketscore.models.WicketModel;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class ScoreCardFragment extends Fragment {
    private ViewPager viewPager;
    private ViewPageAdapter pagerAdapter;
    private  TabLayout tabLayout;
    MatchDetailsModel matchDetailsModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.score_card_fragment, container, false);
        Bundle bundle = getArguments();
        if (bundle != null) {
            matchDetailsModel = bundle.getParcelable("model");
            Log.d(TAG, "onCreateView:model "+matchDetailsModel);
            // Use your Parcelable model here
        }
        viewPager = rootView.findViewById(R.id.view_pager);
        tabLayout = rootView.findViewById(R.id.tab_layout);

        setData(matchDetailsModel);

        return rootView;
    }

    private void setData(MatchDetailsModel matchDetailsModel) {
        setAdapter(matchDetailsModel.getTeams().get(0).getName(),
                matchDetailsModel.getTeams().get(1).getName(),
                matchDetailsModel.getTeams().get(0), matchDetailsModel.getTeams().get(1),
                matchDetailsModel.getFallOfWickets().get(0),
                matchDetailsModel.getFallOfWickets().get(1));
    }

    private void setAdapter(String teamOne, String teamTwo, TeamModel teamModel1,
                            TeamModel teamModel2, FallOfWicketModel wicketModel1, FallOfWicketModel wicketModel2) {
        pagerAdapter = new ViewPageAdapter(getChildFragmentManager(),
                teamOne,teamTwo,teamModel1,teamModel2,wicketModel1,wicketModel2);
        viewPager.setAdapter(pagerAdapter);

        tabLayout.setupWithViewPager(viewPager);
    }
}
