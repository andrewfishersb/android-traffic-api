package com.fisher.andrew.castlerock;

import java.util.ArrayList;
import java.util.List;

public class TrafficSign {

    private boolean mStatus;
    private long mTimestamp;
    private String mName;
    private List<String> mMessages;

    public TrafficSign(String name, boolean status, long timestamp) {
        mName = name;
        mStatus = status;
        mTimestamp = timestamp;
        mMessages = new ArrayList<>();
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public boolean isStatus() {
        return mStatus;
    }

    public void setStatus(boolean status) {
        mStatus = status;
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
