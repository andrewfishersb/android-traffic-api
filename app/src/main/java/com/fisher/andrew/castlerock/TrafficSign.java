package com.fisher.andrew.castlerock;

public class TrafficSign {

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

}
