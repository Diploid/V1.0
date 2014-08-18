/**
 * @auther: Raghavendra 
 * @email: rgsingh.iit@gmail.com
 */
package com.diploid.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.diploid.common.DMSServiceClient;
import com.diploid.entity.RegistrationForm;

@Named("registrationBean")
@ManagedBean
@SessionScoped
public class RegistrationBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory
			.getLogger(RegistrationBean.class);

	private String userName;
	private String email;
	private String password;
	private String repeatPassword;
	private boolean termsAndCondition;

	public RegistrationBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RegistrationBean(String userName, String email, String password,
			String repeatPassword, boolean termsAndCondition) {
		super();
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.repeatPassword = repeatPassword;
		this.termsAndCondition = termsAndCondition;
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

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}

	public boolean isTermsAndCondition() {
		return termsAndCondition;
	}

	public void setTermsAndCondition(boolean termsAndCondition) {
		this.termsAndCondition = termsAndCondition;
	}

	public void addRegister() {
		logger.info("addRegister Calling");
		RegistrationForm registrationForm = null;
		if (!password.equals(repeatPassword)) {
			facesMessage("Password and RepeatPassword should be same");
		} else if (!termsAndCondition) {

			facesMessage("Please read terms and condition ");
		} else {
			registrationForm = new RegistrationForm(userName, email, password);
			try {
				new DMSServiceClient().AddRegister(registrationForm);
			} catch (Exception e) {
				logger.error(e.toString());
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage("Failed :",
								"Please Refresh and try again."));
				return;
			}
		}

	}

	private void facesMessage(String message) {
		FacesMessage msg = new FacesMessage("Error", message);
		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void getLogin() {
		RegistrationForm registrationForm = DMSServiceClient
				.getRegisteredUserByEmailId(email);
		if (registrationForm != null) {
			if(registrationForm.getPassword().equals(password))
			{
				//go to main 
			}
			else
			{
				//password is incorrect
			}

		}
	}

}
