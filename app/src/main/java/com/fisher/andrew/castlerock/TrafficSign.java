package com.fisher.andrew.castlerock;

public class TrafficSign {

    private boolean mStatus;
    private long mTimestamp;
    private String mName;

    public TrafficSign(String name, boolean status, long timestamp) {
        mName = name;
        mStatus = status;
        mTimestamp = timestamp;
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

}
