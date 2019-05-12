package com.fisher.andrew.castlerock;

import java.util.ArrayList;
import java.util.List;

public class TrafficSign {

    private boolean mIsDisplayingMessage;
    private long mTimestamp;
    private String mName;
    private List<String> mMessages;

    public TrafficSign(String name, boolean isDisplayingMessage, long timestamp) {
        mName = name;
        mIsDisplayingMessage = isDisplayingMessage;
        mTimestamp = timestamp;
        mMessages = new ArrayList<>();
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

    public List<String> getMessage() {
        return mMessages;
    }

    public void addMessage(String message) {
        mMessages.add(message);
    }

}
