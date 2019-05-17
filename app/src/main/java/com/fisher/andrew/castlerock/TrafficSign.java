package com.fisher.andrew.castlerock;

import android.os.Parcel;
import android.os.Parcelable;

public class TrafficSign implements Parcelable {

    private boolean mIsDisplayingMessage;
    private long mTimestamp;
    private String mName;
    private String mDisplayMessage;

    public TrafficSign(String name, boolean isDisplayingMessage, long timestamp, String displayMessage) {
        mName = name;
        mIsDisplayingMessage = isDisplayingMessage;
        mTimestamp = timestamp;
        mDisplayMessage = displayMessage;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public boolean isDisplayingMessage() {
        return mIsDisplayingMessage;
    }

    public void setDisplayMessageStatus(boolean isDisplayingMessage) {
        mIsDisplayingMessage = isDisplayingMessage;
    }

    public long getTimestamp() {
        return mTimestamp;
    }

    public void setTimestamp(long timestamp) {
        mTimestamp = timestamp;
    }

    public String getMessage() {
        return mDisplayMessage;
    }

    public void setDisplayingMessage(String message) {
        mDisplayMessage = message;
    }

    //Makes the object parcelable
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public TrafficSign createFromParcel(Parcel in) {
            return new TrafficSign(in);
        }

        public TrafficSign[] newArray(int size) {
            return new TrafficSign[size];
        }
    };

    private TrafficSign(Parcel in){
        mIsDisplayingMessage = in.readInt() != 0;
        mName = in.readString();
        mTimestamp = in.readLong();
        mDisplayMessage = in.readString();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeInt((mIsDisplayingMessage) ? 1 : 0);
        dest.writeString(mName);
        dest.writeLong(mTimestamp);
        dest.writeString(mDisplayMessage);
    }
}
