package com.javarticles.mule;

import java.io.IOException;

import org.mule.api.MuleContext;
import org.mule.api.MuleException;
import org.mule.module.client.MuleClient;
import org.mule.api.context.MuleContextBuilder;
import org.mule.api.context.MuleContextFactory;
import org.mule.config.DefaultMuleConfiguration;
import org.mule.config.spring.SpringXmlConfigurationBuilder;
import org.mule.context.DefaultMuleContextBuilder;
import org.mule.context.DefaultMuleContextFactory;

public class MuleContextExample {
    public static void main(String[] args) throws IOException, MuleException {
        DefaultMuleConfiguration dmc = new DefaultMuleConfiguration();
        dmc.setId("muleexample");
        dmc.setWorkingDirectory("/esb/mule");
        SpringXmlConfigurationBuilder configBuilder = new SpringXmlConfigurationBuilder(
                "muleContext.xml");
        MuleContextBuilder contextBuilder = new DefaultMuleContextBuilder();
        contextBuilder.setMuleConfiguration(dmc);
        MuleContextFactory contextFactory = new DefaultMuleContextFactory();
        MuleContext ctx = contextFactory.createMuleContext(configBuilder,
                contextBuilder);
        ctx.start();
        try {
            MuleClient muleClient = new MuleClient(ctx);
            String payload = "XYZ";
            System.out.println("Sending payload [" + payload + "]");
            muleClient.sendNoReceive("vm://request.msg", payload, null);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
        } finally {
            ctx.dispose();
        }
    }
}
