package com.example.cricketscore.models;
import android.os.Parcel;
import android.os.Parcelable;
import java.math.BigDecimal;

public class WicketModel implements Parcelable {
    private String player;
    private Integer score;
    private BigDecimal overs;
    private DismissalModel dismissal;

    public WicketModel() {
        // Empty constructor
    }

    protected WicketModel(Parcel in) {
        player = in.readString();
        if (in.readByte() == 0) {
            score = null;
        } else {
            score = in.readInt();
        }
        overs = new BigDecimal(in.readString());
        dismissal = in.readParcelable(DismissalModel.class.getClassLoader());
    }

    public static final Creator<WicketModel> CREATOR = new Creator<WicketModel>() {
        @Override
        public WicketModel createFromParcel(Parcel in) {
            return new WicketModel(in);
        }

        @Override
        public WicketModel[] newArray(int size) {
            return new WicketModel[size];
        }
    };

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public BigDecimal getOvers() {
        return overs;
    }

    public void setOvers(BigDecimal overs) {
        this.overs = overs;
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
        dest.writeString(player);
        if (score == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(score);
        }
        dest.writeString(overs.toString());
        dest.writeParcelable(dismissal, flags);
    }
}

