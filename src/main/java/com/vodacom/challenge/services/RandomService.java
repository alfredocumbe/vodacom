package com.vodacom.challenge.services;

import java.util.List;

import com.vodacom.challenge.models.Random;
import com.vodacom.challenge.models.SystemUsageStatistic;

public interface RandomService {
    
    public void random(long maxWait, long generationMinTime);
    public List<Random> history();
    public SystemUsageStatistic stats();
    public void cancel(String requestID);
    public List<Random> pending();
    public void threads(int poolSize);

}