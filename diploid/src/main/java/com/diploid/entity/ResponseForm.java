/**
 * @auther: Raghavendra 
 * @email: rgsingh.iit@gmail.com
 */
package com.diploid.entity;

import java.io.Serializable;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.codehaus.jackson.annotate.JsonTypeInfo;

@PersistenceCapable(detachable = "true", table = "responseForm")
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class ResponseForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Persistent
	@Column(name = "userResponse", jdbcType = "VARCHAR", length = 400)
	private long id;

	@Persistent
	@Column(name = "userResponse", jdbcType = "VARCHAR", length = 400)
	private String userResponse;

	public ResponseForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResponseForm(long id, String userResponse) {
		super();
		this.id = id;
		this.userResponse = userResponse;
	}

	public ResponseForm(String userResponse) {
		super();
		this.userResponse = userResponse;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserResponse() {
		return userResponse;
	}

	public void setUserResponse(String userResponse) {
		this.userResponse = userResponse;
	}

	@Override
	public String toString() {
		return "ResponseForm [id=" + id + ", userResponse=" + userResponse
				+ "]";
	}

}
