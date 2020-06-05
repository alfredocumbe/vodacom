package com.vodacom.challenge.business;

public class RandomNumberGenerator {
    
    public static int random(long generationMinTime){
        try {
            int min = 3;
            int max = 1000;
            int random_int = (int)(Math.random() * (max - min + 1) + min);
            Thread.sleep(generationMinTime);
            return  random_int;              
        } catch (Exception e) {
            System.out.print(e);
        } 
        return 0;         
    }

}