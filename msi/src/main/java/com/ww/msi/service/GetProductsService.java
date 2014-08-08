package com.ww.msi.service;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
@WebService
public interface GetProductsService {
	@GET
	@Path("/getSFProducts")
	public Object getSFProducts();
	@GET
	@Path("/getMagentoProducts")
	public Object getMagentoProducts();
}
