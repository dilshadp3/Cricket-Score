package com.example.cricketscore.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DismissalModel implements Parcelable {
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("fielder")
    @Expose
    private String fielder;
    @SerializedName("bowler")
    @Expose
    private String bowler;

    public DismissalModel() {
        // Empty constructor
    }

    protected DismissalModel(Parcel in) {
        type = in.readString();
        fielder = in.readString();
        bowler = in.readString();
    }

    public static final Creator<DismissalModel> CREATOR = new Creator<DismissalModel>() {
        @Override
        public DismissalModel createFromParcel(Parcel in) {
            return new DismissalModel(in);
        }

        @Override
        public DismissalModel[] newArray(int size) {
            return new DismissalModel[size];
        }
    };

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFielder() {
        return fielder;
    }

    public void setFielder(String fielder) {
        this.fielder = fielder;
    }

    public String getBowler() {
        return bowler;
    }

    public void setBowler(String bowler) {
        this.bowler = bowler;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
        dest.writeString(fielder);
        dest.writeString(bowler);
    }
}
