package com.vodacom.challenge.models;

public class SystemUsageStatistic {
    private long maximumWaitingTime;
    private long minimumWaitingTime;
    private int TotalPendingRequests;

    public SystemUsageStatistic(){
        this.maximumWaitingTime = 0;
        this.minimumWaitingTime = 0;
        this.TotalPendingRequests = 0;
    }

    public long getMaximumWaitingTime() {
        return maximumWaitingTime;
    }

    public void setMaximumWaitingTime(long maximumWaitingTime) {
        this.maximumWaitingTime = maximumWaitingTime;
    }

    public long getMinimumWaitingTime() {
        return minimumWaitingTime;
    }

    public void setMinimumWaitingTime(long minimumWaitingTime) {
        this.minimumWaitingTime = minimumWaitingTime;
    }

    public int getTotalPendingRequests() {
        return TotalPendingRequests;
    }

    public void setTotalPendingRequests(int totalPendingRequests) {
        TotalPendingRequests = totalPendingRequests;
    }
}