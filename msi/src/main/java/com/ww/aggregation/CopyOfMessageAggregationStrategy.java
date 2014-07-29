package com.ww.aggregation;
import java.util.Iterator;

import org.mule.DefaultMuleEvent;
import org.mule.DefaultMuleMessage;
import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.api.routing.AggregationContext;
import org.mule.api.store.ObjectStoreException;
import org.mule.api.transformer.TransformerException;
import org.mule.routing.AggregationException;
import org.mule.routing.AggregationStrategy;



 
public class CopyOfMessageAggregationStrategy implements AggregationStrategy {
	 
    @Override
    public MuleEvent aggregate(AggregationContext context) throws MuleException {
        MuleEvent result = null;
        long value = Long.MAX_VALUE;
        StringBuffer buffer = new StringBuffer(128);
        for (MuleEvent event : context.getEvents()) {
           /* Flight flight = (Flight) event.getMessage().getPayload(); 
            if (flight.getCost() < value) {
                result = DefaultMuleEvent.copy(event);
                value = flight.getCost();
            }
*/ 
        	System.out.println("event.getMessage() @@@@@@@@@@@@@@@@@ "+event.getMessage());
        	System.out.println("event.getMessage().getPayload(); @@@@@@@@@@@@@@@@"+ event.getMessage().getPayload());
        	
        	event.getMessage().getPayload(); 
        result = DefaultMuleEvent.copy(event);
        System.out.println("event is @@@@@@@@@@@@@ "+event);
        System.out.println("result is !!!!!!!!!!! "+result);
        
        try
        {
            buffer.append(event.transformMessageToString());
        }
        catch (TransformerException e)
        {
            //throw new Exception(e);
        	e.printStackTrace();
        }
        
        
        
       /* StringBuffer buffer = new StringBuffer(128);
        EventGroup events = context.

        try
        {
            for (Iterator<MuleEvent> iterator = event.iterator(); iterator.hasNext();)
            {
                MuleEvent event = iterator.next();
                try
                {
                    buffer.append(event.transformMessageToString());
                }
                catch (TransformerException e)
                {
                    throw new AggregationException(events, null, e);
                }
            }
        }
        catch (ObjectStoreException e)
        {
            throw new AggregationException(events,null,e);
        }

*/        System.out.println("event payload is: " + buffer.toString());
     //   return new DefaultMuleEvent(new DefaultMuleMessage(buffer.toString(), context), events.getMessageCollectionEvent());

        	
        
        }
         
        if (result != null)  {
            return result;
        }
         
        throw new  RuntimeException("no results obtained ......");
    }
}
