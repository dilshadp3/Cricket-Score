package com.example.cricketscore.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlayerModel implements Parcelable {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("runs")
    @Expose
    private Integer runs;
    @SerializedName("balls")
    @Expose
    private Integer balls;
    @SerializedName("fours")
    @Expose
    private Integer fours;
    @SerializedName("sixes")
    @Expose
    private Integer sixes;
    @SerializedName("dismissal")
    @Expose
    private DismissalModel dismissal;

    public PlayerModel() {
        // Empty constructor
    }

    protected PlayerModel(Parcel in) {
        name = in.readString();
        if (in.readByte() == 0) {
            runs = null;
        } else {
            runs = in.readInt();
        }
        if (in.readByte() == 0) {
            balls = null;
        } else {
            balls = in.readInt();
        }
        if (in.readByte() == 0) {
            fours = null;
        } else {
            fours = in.readInt();
        }
        if (in.readByte() == 0) {
            sixes = null;
        } else {
            sixes = in.readInt();
        }
        dismissal = in.readParcelable(DismissalModel.class.getClassLoader());
    }

    public static final Creator<PlayerModel> CREATOR = new Creator<PlayerModel>() {
        @Override
        public PlayerModel createFromParcel(Parcel in) {
            return new PlayerModel(in);
        }

        @Override
        public PlayerModel[] newArray(int size) {
            return new PlayerModel[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRuns() {
        return runs;
    }

    public void setRuns(Integer runs) {
        this.runs = runs;
    }

    public Integer getBalls() {
        return balls;
    }

    public void setBalls(Integer balls) {
        this.balls = balls;
    }

    public Integer getFours() {
        return fours;
    }

    public void setFours(Integer fours) {
        this.fours = fours;
    }

    public Integer getSixes() {
        return sixes;
    }

    public void setSixes(Integer sixes) {
        this.sixes = sixes;
    }

    public DismissalModel getDismissal() {
        return dismissal;
    }

    public void setDismissal(DismissalModel dismissal) {
        this.dismissal = dismissal;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        if (runs == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(runs);
        }
        if (balls == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(balls);
        }
        if (fours == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(fours);
        }
        if (sixes == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(sixes);
        }
        dest.writeParcelable(dismissal, flags);
    }
}
