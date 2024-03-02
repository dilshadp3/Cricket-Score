package com.example.cricketscore.adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.cricketscore.fragment.ScoreCardTabOneFragment;
import com.example.cricketscore.fragment.ScoreCardTabTwoFragment;
import com.example.cricketscore.models.FallOfWicketModel;
import com.example.cricketscore.models.TeamModel;
import com.example.cricketscore.models.WicketModel;

public class ViewPageAdapter extends FragmentPagerAdapter {

    private static final int NUM_TABS = 2;
    String teamOne,teamTwo;
    TeamModel teamModelOne;
    TeamModel teamModelTwo;
    FallOfWicketModel wicketModelOne;
    FallOfWicketModel wicketModelTwo;

    public ViewPageAdapter(@NonNull FragmentManager fm, String teamA, String teamB,
                           TeamModel teamModel1, TeamModel teamModel2,
                           FallOfWicketModel wicketModel1,
                           FallOfWicketModel wicketModel2) {
        super(fm);
        teamOne=teamA;
        teamTwo=teamB;
        teamModelOne=teamModel1;
        teamModelTwo=teamModel2;
        wicketModelOne=wicketModel1;
        wicketModelTwo=wicketModel2;

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                ScoreCardTabOneFragment fragment = new ScoreCardTabOneFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("teamModel", teamModelOne);
                bundle.putParcelable("wicketModel", wicketModelOne);
                fragment.setArguments(bundle);
                return fragment;
            case 1:
                ScoreCardTabTwoFragment secondFragment = new ScoreCardTabTwoFragment();
                Bundle bundleTwo = new Bundle();
                bundleTwo.putParcelable("teamModel", teamModelTwo);
                bundleTwo.putParcelable("wicketModel", wicketModelTwo);
                secondFragment.setArguments(bundleTwo);
                return secondFragment;
            default:
                ScoreCardTabOneFragment fragmentOne = new ScoreCardTabOneFragment();
                Bundle bundleOne = new Bundle();
                bundleOne.putParcelable("teamModel", teamModelOne);
                bundleOne.putParcelable("wicketModel", wicketModelOne);
                fragmentOne.setArguments(bundleOne);
                return fragmentOne;

        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return teamOne;
            case 1:
                return teamTwo;

        }
        return null;
    }




    @Override
    public int getCount() {
        return NUM_TABS;
    }
}
