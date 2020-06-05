package com.vodacom.challenge.models;

import java.util.Date;

public class Random{

    private String requestID;
    private int number;
    private long timeWasTakeToProcess;
    private Date timeRequestSubmitted;
    private boolean pending;

    public Random(){
        this.pending = true;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public long getTimeWasTakeToProcess() {
        return timeWasTakeToProcess;
    }

    public void setTimeWasTakeToProcess(long timeWasTakeToProcess) {
        this.timeWasTakeToProcess = timeWasTakeToProcess;
    }

    public String getRequestID() {
        return requestID;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    public boolean isPending() {
        return pending;
    }

    public void setPending(boolean pending) {
        this.pending = pending;
    }

    public Date getTimeRequestSubmitted() {
        return timeRequestSubmitted;
    }

    public void setTimeRequestSubmitted(Date timeRequestSubmitted) {
        this.timeRequestSubmitted = timeRequestSubmitted;
    }

}