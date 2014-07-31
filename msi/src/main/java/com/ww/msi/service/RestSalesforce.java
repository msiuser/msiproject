package com.ww.msi.service;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;


@Path("/users")
public class RestSalesforce {
 
	@GET
	@Path("/query")
	public Response getUsers(
		@QueryParam("SELECT Name, AccountNumber from Account") int accountnumber) {
 
		return Response
		   .status(200)
		   .entity("getUsers is called, from : " + accountnumber).build();
 
	}
 
}