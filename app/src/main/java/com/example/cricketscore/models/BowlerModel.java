package com.example.cricketscore.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BowlerModel implements Parcelable {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("overs")
    @Expose
    private Integer overs;
    @SerializedName("runsConceded")
    @Expose
    private Integer runsConceded;
    @SerializedName("wickets")
    @Expose
    private Integer wickets;

    public BowlerModel() {
        // Empty constructor
    }

    protected BowlerModel(Parcel in) {
        name = in.readString();
        if (in.readByte() == 0) {
            overs = null;
        } else {
            overs = in.readInt();
        }
        if (in.readByte() == 0) {
            runsConceded = null;
        } else {
            runsConceded = in.readInt();
        }
        if (in.readByte() == 0) {
            wickets = null;
        } else {
            wickets = in.readInt();
        }
    }

    public static final Creator<BowlerModel> CREATOR = new Creator<BowlerModel>() {
        @Override
        public BowlerModel createFromParcel(Parcel in) {
            return new BowlerModel(in);
        }

        @Override
        public BowlerModel[] newArray(int size) {
            return new BowlerModel[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOvers() {
        return overs;
    }

    public void setOvers(Integer overs) {
        this.overs = overs;
    }

    public Integer getRunsConceded() {
        return runsConceded;
    }

    public void setRunsConceded(Integer runsConceded) {
        this.runsConceded = runsConceded;
    }

    public Integer getWickets() {
        return wickets;
    }

    public void setWickets(Integer wickets) {
        this.wickets = wickets;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        if (overs == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(overs);
        }
        if (runsConceded == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(runsConceded);
        }
        if (wickets == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(wickets);
        }
    }
}

