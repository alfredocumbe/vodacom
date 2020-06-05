package com.vodacom.challenge;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import com.vodacom.challenge.models.SystemUsageStatistic;
import com.vodacom.challenge.services.RandomServiceImpl;

import org.junit.Before;
import org.junit.Test;

public class RandomServiceTest {

    RandomServiceImpl impl = new RandomServiceImpl();

    @Before
    public void init() {
        try {
            long maxWait = 3200;
            long generationMinTime = 1;
            impl.random(maxWait, generationMinTime);
            Thread.sleep(maxWait + 1000);   
        } catch (Exception e) {
            //TODO: handle exception
        }
    }   

    @Test
    public void history(){
        try {
            assertNotEquals("history - Size", impl.history().size(), 0);
            //assertThat("history - Size", impl.history().size(), 0);
            //assert("history - Checking size of List", 1, impl.history().size());           
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    @Test
    public void random(){       
        try {
            int initialSize = impl.history().size();
            long maxWait = 3200;
            long generationMinTime = 1;
            impl.random(maxWait, generationMinTime);
            Thread.sleep(maxWait + 1000);
            assertEquals("random - Checking size of List", initialSize + 1, impl.history().size());

        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    @Test
    public void random1(){       
        try {
            int initialSize = impl.history().size();
            long maxWait = 1;
            long generationMinTime = 32000;
            impl.random(maxWait, generationMinTime);
            assertEquals("random - Checking size of List", initialSize, impl.history().size());      
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    @Test
    public void stats(){
        SystemUsageStatistic stat = impl.stats();
        assertNotNull(stat);
    }

    @Test
    public void pending(){
        try {
            assertEquals("history - Checking size of List", 0, impl.pending().size());           
        } catch (Exception e) {
            //TODO: handle exception
        }       
    }

}