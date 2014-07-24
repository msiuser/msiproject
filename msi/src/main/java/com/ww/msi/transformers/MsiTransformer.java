package com.ww.msi.transformers;

import org.mule.api.MuleException;
import org.mule.api.MuleMessage;
import org.mule.api.context.MuleContextAware;
import org.mule.api.lifecycle.Lifecycle;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;

public class MsiTransformer extends AbstractMessageTransformer implements MuleContextAware{

	
	/**
	 * JUST A PLACE HOLDER
	 */
	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding)
			throws TransformerException {
		Lifecycle lifecycle=(Lifecycle) muleContext.getRegistry().lookupFlowConstruct("abc");
		try {
			lifecycle.start();
		} catch (MuleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return null;
	}

	

}
