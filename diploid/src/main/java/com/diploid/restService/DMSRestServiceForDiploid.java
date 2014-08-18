/**
 * @auther: Raghavendra 
 * @email: rgsingh.iit@gmail.com
 */
package com.diploid.restService;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.diploid.constant.DMSServiceURLConstants;
import com.diploid.entity.RegistrationForm;
import com.diploid.entity.ResponseForm;

@Path(DMSServiceURLConstants.DMS_SERVICE_FOR_DIPLOID)
public class DMSRestServiceForDiploid {
	private static final Logger logger = LoggerFactory
			.getLogger(DMSRestServiceForDiploid.class);
	static PersistenceManagerFactory pmf = JDOHelper
			.getPersistenceManagerFactory("datanucleus.properties");

	@Path(DMSServiceURLConstants.ADD_FOR_USER_RESPONSE)
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public static Response AddResponseForm(ResponseForm responseForm) {
		PersistenceManager mgr = DMSRestServiceForDiploid.pmf
				.getPersistenceManager();
		Transaction tx = mgr.currentTransaction();

		try {
			tx.begin();
			mgr.makePersistent(responseForm);
			tx.commit();
		} catch (Exception e) {
			logger.error("Exception makePersistent");
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			mgr.close();
		}
		String result = "Track saved : " + responseForm;

		return Response.status(201).entity(result).build();
	}

	@Path(DMSServiceURLConstants.ADD_FOR_USER_REGISTER)
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public static Response AddRegister(RegistrationForm registrationForm) {
		PersistenceManager mgr = DMSRestServiceForDiploid.pmf
				.getPersistenceManager();
		Transaction tx = mgr.currentTransaction();

		try {
			tx.begin();
			registrationForm = mgr.detachCopy(getRegisteredUserByEmailId(mgr,
					registrationForm.getEmail()));
			if (registrationForm == null)
			mgr.makePersistent(registrationForm);
			tx.commit();
		} catch (Exception e) {
			logger.error("Exception makePersistent");
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			mgr.close();
		}
		String result = "Track saved : " + registrationForm;

		return Response.status(201).entity(result).build();
	}

	@Path(DMSServiceURLConstants.GET_REGISTER_USER)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRegisteredUserByEmailId(@QueryParam("email") String email) {
		RegistrationForm registrationForm = null;
		try {
			PersistenceManager pm = DMSRestServiceForDiploid.pmf
					.getPersistenceManager();
			Transaction tx = pm.currentTransaction();

			try {
				tx.begin();
				registrationForm = pm.detachCopy(getRegisteredUserByEmailId(pm,
						email));
				tx.commit();
				// return registrationForm;
			} finally {
				if (tx.isActive())
					tx.rollback();

				pm.close();
			}

		} catch (Exception e) {
			logger.error("Failed to get email : " + email);
			logger.error(e.toString());
		}
		if (registrationForm == null) {
			logger.warn("No email exist");
		}

		// GenericEntity<RegistrationForm> listRegistrationForm = new
		// GenericEntity<RegistrationForm>(
		// registrationForm) {
		// };
		return Response.status(Response.Status.OK).entity(registrationForm)
				.build();
	}

	public static RegistrationForm getRegisteredUserByEmailId(
			PersistenceManager pm, String email) {
		Query query = pm.newQuery(RegistrationForm.class);

		try {
			query.setFilter("this.email == email");
			query.declareParameters("String email");
			query.setUnique(true);
			RegistrationForm registrationForm = (RegistrationForm) query
					.execute(email);
			// if( null == registrationForm )
			// throw new
			// NoSuchObjectException("Email not found with dtnAppsBaId : "+
			// email);
			return registrationForm;
		} finally {
			query.closeAll();
		}
	}

}
