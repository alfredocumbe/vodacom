package com.vodacom.challenge.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.enterprise.inject.Default;
import com.vodacom.challenge.annotation.DelayMe;
import com.vodacom.challenge.business.OnlineThreadPool;
import com.vodacom.challenge.business.RandomNumberGenerator;
import com.vodacom.challenge.models.Random;
import com.vodacom.challenge.models.SystemUsageStatistic;

@Default
public class RandomServiceImpl implements RandomService {
    private static final List<Random> processedList = new ArrayList<>();
    private static final List<Random> pendingList = new ArrayList<>();
    private static final HashMap<String, Future<?>> requestList = new HashMap<String, Future<?>>();

    @Override
    public void random(long maxWait, long generationMinTime){
        try {

        ThreadPoolExecutor executor = OnlineThreadPool.getThreadPoolExecutor();
        Random random = new Random(); 
        random.setRequestID(UUID.randomUUID().toString());
        pendingList.add(random);
   
            final Future handler = executor.submit(
                new Runnable(){   
                    @Override
                    public void run() {
                        try {                            
                            long startTime = System.currentTimeMillis();  
                            System.out.print("gerando o numero... ");                       
                            random.setNumber(RandomNumberGenerator.random(generationMinTime)); 
                            long elapsedTime = System.currentTimeMillis() - startTime;
                            random.setTimeWasTakeToProcess(elapsedTime);
                            random.setPending(false); 
                            random.setTimeRequestSubmitted(new Date(System.currentTimeMillis()));
                            pendingList.remove(random);
                            if(random.getNumber() > 0) processedList.add(random);                                            
                            System.out.print("processado ");                              
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }                               
                }
            }
        );

        if(maxWait < 31000){
            OnlineThreadPool.getTimer().schedule(new Runnable(){
                public void run(){ 
                    handler.cancel(true);
                    pendingList.remove(random);
                    requestList.remove(random.getRequestID()); 
                    System.out.print("cancelar ");
                }      
            }, maxWait, TimeUnit.MILLISECONDS);   
        }

        requestList.put(random.getRequestID(), handler);
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
}

    @Override
    @DelayMe(time = 2000)
    public List<Random> history(){
        return processedList;
    }

    @Override
    public SystemUsageStatistic stats(){
        SystemUsageStatistic stat = new SystemUsageStatistic();
        if(!processedList.isEmpty()){
            stat.setMaximumWaitingTime(processedList.stream().map(x->x.getTimeWasTakeToProcess()).max(Long::compare).get());
            stat.setMinimumWaitingTime(processedList.stream().map(x->x.getTimeWasTakeToProcess()).min(Long::compare).get());     
            stat.setTotalPendingRequests(this.pending().size());
        }
        return stat;
    }

    @Override
    public void cancel(String requestID){
        if(pendingList.removeIf(x->x.getRequestID().equals(requestID))){

            Future<?> future = (Future<?>) requestList.get(requestID);
            if(!(future == null)  ){
               if(!future.isCancelled()) future.cancel(true);
               requestList.remove(requestID);
            } 

            processedList.removeIf(x->x.getRequestID().equals(requestID));
            System.out.println("Remove Request :" + requestID);
        }
    }

    @Override
    @DelayMe(time = 1000)
    public List<Random> pending(){
        return pendingList;
    }

    @Override
    public void threads(int poolSize){
        if(poolSize > 0 && poolSize < 11){
            System.out.println("poolSize: " + poolSize);
            ThreadPoolExecutor pool = OnlineThreadPool.getThreadPoolExecutor();        
            pool.setCorePoolSize(poolSize);
            pool.setMaximumPoolSize(poolSize);
        }
    }
    
}