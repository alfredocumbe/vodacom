package com.vodacom.challenge.business;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import com.vodacom.challenge.annotation.DelayMe;

@DelayMe
@Interceptor
public class ResourceInterceptor{
      
        @AroundInvoke
        public Object securePayment(InvocationContext invocationContext) throws Exception {
                System.out.print("Intercepting method "+invocationContext.getMethod().getName()+"...");

               if(invocationContext.getMethod().isAnnotationPresent(DelayMe.class)){
                       System.out.print("is Annoteted...");
                       DelayMe delay = invocationContext.getMethod().getAnnotation(DelayMe.class);
                       System.out.print(invocationContext.getMethod().getName() + " Method Delay Time: " + delay.time());
                       Thread.sleep(delay.time());
               }

               System.out.print("Finish Intercepting method "+invocationContext.getMethod().getName()+"...");
               return invocationContext.proceed();
        }
        
}