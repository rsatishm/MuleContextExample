package com.javarticles.mule;

import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.api.MuleMessage;
import org.mule.api.processor.MessageProcessor;

public class MuleCustomProcessor implements MessageProcessor {

    public MuleEvent process(MuleEvent inEvent) throws MuleException {
        MuleMessage message = inEvent.getMessage();
        Object o = message.getPayload();   
        System.out.println(Thread.currentThread().getName() + ":Payload: " + o);
        return inEvent;
    }

}
