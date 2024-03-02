package com.example.cricketscore.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CricketDataModel {

    @SerializedName("matchDetails")
    @Expose
    private MatchDetailsModel matchDetails;

    public MatchDetailsModel getMatchDetails() {
        return matchDetails;
    }

    public void setMatchDetails(MatchDetailsModel matchDetails) {
        this.matchDetails = matchDetails;
    }
}
