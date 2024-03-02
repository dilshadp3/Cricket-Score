package com.example.cricketscore.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class TeamModel implements Parcelable {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("players")
    @Expose
    private List<PlayerModel> players;
    @SerializedName("bowlers")
    @Expose
    private List<BowlerModel> bowlers;

    // Constructor
    public TeamModel() {
    }

    protected TeamModel(Parcel in) {
        name = in.readString();
        players = in.createTypedArrayList(PlayerModel.CREATOR);
        bowlers = in.createTypedArrayList(BowlerModel.CREATOR);
    }

    public static final Creator<TeamModel> CREATOR = new Creator<TeamModel>() {
        @Override
        public TeamModel createFromParcel(Parcel in) {
            return new TeamModel(in);
        }

        @Override
        public TeamModel[] newArray(int size) {
            return new TeamModel[size];
        }
    };

    // Getter and Setter methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PlayerModel> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerModel> players) {
        this.players = players;
    }

    public List<BowlerModel> getBowlers() {
        return bowlers;
    }

    public void setBowlers(List<BowlerModel> bowlers) {
        this.bowlers = bowlers;
    }

    // Parcelable methods
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeTypedList(players);
        dest.writeTypedList(bowlers);
    }
}

