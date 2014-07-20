/**
 * @auther: Raghavendra 
 * @email: rgsingh.iit@gmail.com
 */
package com.diploid.restService;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.diploid.entity.ResponseForm;


@Path("/jsonService")
public class DMSRestServiceForUserResponse {
	private static final Logger logger = LoggerFactory
			.getLogger(DMSRestServiceForUserResponse.class);
	 static PersistenceManagerFactory pmf = JDOHelper
			.getPersistenceManagerFactory("datanucleus.properties");
	

	
	@Path("/add")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public static Response Creating(ResponseForm responseForm) {
		PersistenceManager mgr = DMSRestServiceForUserResponse.pmf.getPersistenceManager();
		Transaction tx = mgr.currentTransaction();

		try {
			tx.begin();
			mgr.makePersistent(responseForm);
			tx.commit();
		}
		catch(Exception e)
		{
			logger.error("Exception makePersistent");
		}finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			mgr.close();
		}
		String result = "Track saved : " + responseForm;
		
		return Response.status(201).entity(result).build();
	}
}
