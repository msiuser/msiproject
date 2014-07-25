package com.ww.msi.service.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.codehaus.jackson.map.ObjectMapper;
import org.mule.RequestContext;
import org.mule.api.MuleContext;
import org.mule.api.MuleEvent;
import org.mule.api.annotations.expressions.Lookup;
import org.mule.construct.Flow;

import com.ww.mis.service.MegentoProductService;
import com.ww.mis.util.CreateProductRequest;

@Path("msi")
public class MegentoProductServiceImpl implements MegentoProductService {
	
	private static final String MAGENTO_FLOW_NAME = "MagentoCreateProductSubFlow";
	private static final String JMS_FLOW_NAME ="JmsPublishMessageFlow";
	@Lookup
	MuleContext muleContext;
	
	public MuleContext getMuleContext() {
		return muleContext;
	}

	public void setMuleContext(MuleContext muleContext) {
		this.muleContext = muleContext;
	}

	@Override
	@POST
	@Path("/createProduct")
	@Consumes("application/json")
	public String createProduct(String jsonObject) {
		
		Integer productId = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			CreateProductRequest request = (CreateProductRequest) mapper.readValue(jsonObject, CreateProductRequest.class);

			Flow megentoflow = (Flow) muleContext.getRegistry().lookupFlowConstruct(MAGENTO_FLOW_NAME);
			MuleEvent muleEvent = RequestContext.getEvent();
			muleEvent.getMessage().setPayload(request);
			MuleEvent event = megentoflow.process(muleEvent);

			productId = (Integer) event.getMessage().getPayload();
			request.setProductId(productId.toString());

			Flow jmsflow = (Flow) muleContext.getRegistry().lookupFlowConstruct(JMS_FLOW_NAME);
			muleEvent.getMessage().setPayload(request);
			jmsflow.process(muleEvent);

		} catch (Exception exe) {
			exe.printStackTrace();
		}
		return productId.toString();
	}
}
