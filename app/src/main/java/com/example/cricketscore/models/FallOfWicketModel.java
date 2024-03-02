package com.example.cricketscore.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class FallOfWicketModel implements Parcelable {

    @SerializedName("team")
    @Expose
    private String team;
    @SerializedName("wickets")
    @Expose
    private List<WicketModel> wickets;

    // Constructor
    public FallOfWicketModel() {
    }

    protected FallOfWicketModel(Parcel in) {
        team = in.readString();
        wickets = in.createTypedArrayList(WicketModel.CREATOR);
    }

    public static final Creator<FallOfWicketModel> CREATOR = new Creator<FallOfWicketModel>() {
        @Override
        public FallOfWicketModel createFromParcel(Parcel in) {
            return new FallOfWicketModel(in);
        }

        @Override
        public FallOfWicketModel[] newArray(int size) {
            return new FallOfWicketModel[size];
        }
    };

    // Getter and Setter methods
    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public List<WicketModel> getWickets() {
        return wickets;
    }

    public void setWickets(List<WicketModel> wickets) {
        this.wickets = wickets;
    }

    // Parcelable methods
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(team);
        dest.writeTypedList(wickets);
    }
}

