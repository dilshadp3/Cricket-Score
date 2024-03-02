package com.example.cricketscore.models;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.cricketscore.models.FallOfWicketModel;
import com.example.cricketscore.models.TeamModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class MatchDetailsModel implements Parcelable {

    @SerializedName("format")
    @Expose
    private String format;
    @SerializedName("toss")
    @Expose
    private String toss;
    @SerializedName("toss_decision")
    @Expose
    private String tossDecision;
    @SerializedName("teams")
    @Expose
    private List<TeamModel> teams;
    @SerializedName("fallOfWickets")
    @Expose
    private List<FallOfWicketModel> fallOfWickets;

    // Constructor
    public MatchDetailsModel() {
    }

    protected MatchDetailsModel(Parcel in) {
        format = in.readString();
        toss = in.readString();
        tossDecision = in.readString();
        teams = in.createTypedArrayList(TeamModel.CREATOR);
        fallOfWickets = in.createTypedArrayList(FallOfWicketModel.CREATOR);
    }

    public static final Creator<MatchDetailsModel> CREATOR = new Creator<MatchDetailsModel>() {
        @Override
        public MatchDetailsModel createFromParcel(Parcel in) {
            return new MatchDetailsModel(in);
        }

        @Override
        public MatchDetailsModel[] newArray(int size) {
            return new MatchDetailsModel[size];
        }
    };

    // Getter and Setter methods
    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getToss() {
        return toss;
    }

    public void setToss(String toss) {
        this.toss = toss;
    }

    public String getTossDecision() {
        return tossDecision;
    }

    public void setTossDecision(String tossDecision) {
        this.tossDecision = tossDecision;
    }

    public List<TeamModel> getTeams() {
        return teams;
    }

    public void setTeams(List<TeamModel> teams) {
        this.teams = teams;
    }

    public List<FallOfWicketModel> getFallOfWickets() {
        return fallOfWickets;
    }

    public void setFallOfWickets(List<FallOfWicketModel> fallOfWickets) {
        this.fallOfWickets = fallOfWickets;
    }

    // Parcelable methods
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(format);
        dest.writeString(toss);
        dest.writeString(tossDecision);
        dest.writeTypedList(teams);
        dest.writeTypedList(fallOfWickets);
    }
}
