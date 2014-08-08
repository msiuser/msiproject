package com.ww.msi.service.impl;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import org.mule.RequestContext;
import org.mule.api.MuleContext;
import org.mule.api.MuleEvent;
import org.mule.api.annotations.expressions.Lookup;
import org.mule.construct.Flow;

import com.ww.msi.service.GetProductsService;

@WebService(endpointInterface = "com.ww.msi.service.GetProductsService", serviceName = "GetProductsService")
@Path("msi")
public class GetProductsServiceImpl implements
		GetProductsService {

	private static final String GET_SF_PRODUCTS_FLOW_NAME = "GetSFProductSubFlow";
	private static final String GET_MAGENTO_PRODUCTS_FLOW_NAME = "GetMagentoProductsSubFlow";
	@Lookup
	MuleContext muleContext;

	public MuleContext getMuleContext() {
		return muleContext;
	}

	public void setMuleContext(MuleContext muleContext) {
		this.muleContext = muleContext;
	}

	@Override
	@GET
	@Path("/getSFProducts")
	public Object getSFProducts() {
	 MuleEvent event = null;
		try {
            System.out.println("before flow calling @@@@@@@@@@@");
			Flow megentoflow = (Flow) muleContext.getRegistry()
					.lookupFlowConstruct(GET_SF_PRODUCTS_FLOW_NAME);
			MuleEvent muleEvent = RequestContext.getEvent();
		 	event = megentoflow.process(muleEvent);
		 	  System.out.println("after flow called @@@@@@@@@@@");

		} catch (Exception exe) {
			exe.printStackTrace();
		}
     return event.getMessage().getPayload();

	}

	@Override
	@GET
	@Path("/getMagentoProducts")
	public Object getMagentoProducts() {
		MuleEvent event = null;
		try {
		 Flow megentoflow = (Flow) muleContext.getRegistry()
					.lookupFlowConstruct(GET_MAGENTO_PRODUCTS_FLOW_NAME);
			MuleEvent muleEvent = RequestContext.getEvent();
		 	event = megentoflow.process(muleEvent);

		} catch (Exception exe) {
			exe.printStackTrace();
		}
     return event.getMessage().getPayload();
	}
}
