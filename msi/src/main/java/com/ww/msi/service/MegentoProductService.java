package com.ww.msi.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

public interface MegentoProductService {

	@POST
	@Path("/createProduct")
	@Consumes("application/json")
	public String createProduct(String jsonObject);

}
