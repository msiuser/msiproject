package com.ww.aggregation;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.mule.DefaultMuleEvent;
import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.api.routing.AggregationContext;
import org.mule.routing.AggregationStrategy;

import com.ww.msi.vo.CreateProductRequest;
public class MessageAggregationStrategy implements AggregationStrategy {
	 
    @Override
    public MuleEvent aggregate(AggregationContext context) throws MuleException {
    	MuleEvent result = null;
     	List<CreateProductRequest> productList = null;
        for (MuleEvent event : context.getEvents()) {
            result = DefaultMuleEvent.copy(event);
         	if (productList == null) {
				productList = (List<CreateProductRequest>) event.getMessage().getPayload();
			} else {
				productList.addAll((List<CreateProductRequest>) event.getMessage().getPayload());
			}
        }
     	result.getMessage().setPayload(productList);
	     if (result != null)  {
            return result;
        }
         
        throw new  RuntimeException("no results obtained");
    }	
    	
	
}