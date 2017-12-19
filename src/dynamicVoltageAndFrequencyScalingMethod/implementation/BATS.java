/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dynamicVoltageAndFrequencyScalingMethod.implementation;

/**
 *
 * @author tung-i
 */

import SystemEnvironment.Core;
import SystemEnvironment.Processor;
import WorkLoad.CoreSpeed;
import WorkLoad.Job;
import WorkLoad.Priority;
import WorkLoad.SharedResource;
import WorkLoad.Task;
import WorkLoadSet.CoreSet;
import dynamicVoltageAndFrequencyScalingMethod.DynamicVoltageAndFrequencyScalingMethod;

public class BATS extends DynamicVoltageAndFrequencyScalingMethod {

    double ls,hs;
    double totalu=0.0;
    
    
        public BATS()
    {
        this.setName("Blocking-aware two-speed");
        
        
        
    }
    
    
    
    @Override
    public void definedSpeed(Processor p) {
        
      for(Task t : p.getTaskSet())
        {
            totalu+=t.getUtilization();
           
        }
        System.out.println("        Total_U="+totalu);
        
        for(CoreSet cSet : p.getCoresSets())
        {
            
            for(CoreSpeed cSpeed : cSet.getCoreSpeedSet())
            {
               if(totalu <= (cSpeed.getSpeed()/1000))
               {
                   ls=cSpeed.getSpeed();
                    break;             
               }   
            }
            System.out.println("        Low_Speed="+ls);

        }
        
    }

    @Override
    public void jobArrivesProcessorAction(Job j, Processor p) {
    }

    @Override
    public void jobArrivesCoreAction(Job j, Core c) {
    }

    @Override
    public void coresExecuteAction() {
    }

    @Override
    public void coreExecuteAction(Core c) {
    }

    @Override
    public void jobFirstExecuteAction(Job j) {
        
        
    }

    @Override
    public void jobEveryExecuteAction(Job j) {
                j.getCurrentCore().setCurrentSpeed(ls);

        
    }

    @Override
    public void jobLockAction(Job j, SharedResource r) {
    }

    @Override
    public void jobUnlockAction(Job j, SharedResource r) {
    }

    @Override
    public void jobCompleteAction(Job j) {
    }

    @Override
    public void jobDeadlineAction(Job j) {
    }

    @Override
    public void jobBlockedAction(Job blockedJob, SharedResource blockingRes) {
        blockedJob.getAbsoluteDeadline();
        blockedJob.getCurrentCore().getCurrentTime();
        
        
    }
    
}
