/**
 * @auther: Raghavendra 
 * @email: rgsingh.iit@gmail.com
 */
package com.diploid.entity;

import java.io.Serializable;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.codehaus.jackson.annotate.JsonTypeInfo;

@PersistenceCapable(detachable = "true", table = "registrationForm")
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class RegistrationForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Persistent(valueStrategy = IdGeneratorStrategy.INCREMENT)
	private long id;

	@Persistent
	@Column(name = "userName", jdbcType = "VARCHAR", length = 400)
	private String userName;
	@Persistent
	@Column(name = "email", jdbcType = "VARCHAR", length = 400)
	private String email;
	@Persistent
	@Column(name = "password", jdbcType = "VARCHAR", length = 400)
	private String password;

	public RegistrationForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RegistrationForm(String userName, String email,
			String password) {
		super();
		this.userName = userName;
		this.email = email;
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "RegistrationForm [id=" + id + ", userName=" + userName
				+ ", email=" + email + ", password=" + password + "]";
	}

}
