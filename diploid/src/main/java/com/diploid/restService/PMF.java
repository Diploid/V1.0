/**
 * @auther: Raghavendra 
 * @email: rgsingh.iit@gmail.com
 */
package com.diploid.restService;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

public class PMF {

	private static PersistenceManagerFactory factory;

	protected PMF() {
	}

	public static PersistenceManagerFactory getInstance() {
		if (factory == null)
			factory = JDOHelper
					.getPersistenceManagerFactory(
							"datanucleus.properties");

		return factory;
	}
}