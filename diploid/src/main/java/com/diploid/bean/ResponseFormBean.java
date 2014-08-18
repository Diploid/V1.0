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
import com.diploid.entity.ResponseForm;

@Named("responseFormBean")
@ManagedBean
@SessionScoped
public class ResponseFormBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory
			.getLogger(ResponseFormBean.class);

	private String dropdown1;
	private String dropdown2;
	private String dropdown3;
	private String dropdown4;
	private String dropdown5;
	private String dropdown6;
	private String dropdown7;
	private String dropdown8;
	private String dropdown9;
	private String dropdown10;

	public ResponseFormBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResponseFormBean(String dropdown1, String dropdown2,
			String dropdown3, String dropdown4, String dropdown5,
			String dropdown6, String dropdown7, String dropdown8,
			String dropdown9, String dropdown10) {
		super();
		this.dropdown1 = dropdown1;
		this.dropdown2 = dropdown2;
		this.dropdown3 = dropdown3;
		this.dropdown4 = dropdown4;
		this.dropdown5 = dropdown5;
		this.dropdown6 = dropdown6;
		this.dropdown7 = dropdown7;
		this.dropdown8 = dropdown8;
		this.dropdown9 = dropdown9;
		this.dropdown10 = dropdown10;
	}

	public String getDropdown1() {
		return dropdown1;
	}

	public void setDropdown1(String dropdown1) {
		this.dropdown1 = dropdown1;
	}

	public String getDropdown2() {
		return dropdown2;
	}

	public void setDropdown2(String dropdown2) {
		this.dropdown2 = dropdown2;
	}

	public String getDropdown3() {
		return dropdown3;
	}

	public void setDropdown3(String dropdown3) {
		this.dropdown3 = dropdown3;
	}

	public String getDropdown4() {
		return dropdown4;
	}

	public void setDropdown4(String dropdown4) {
		this.dropdown4 = dropdown4;
	}

	public String getDropdown5() {
		return dropdown5;
	}

	public void setDropdown5(String dropdown5) {
		this.dropdown5 = dropdown5;
	}

	public String getDropdown6() {
		return dropdown6;
	}

	public void setDropdown6(String dropdown6) {
		this.dropdown6 = dropdown6;
	}

	public String getDropdown7() {
		return dropdown7;
	}

	public void setDropdown7(String dropdown7) {
		this.dropdown7 = dropdown7;
	}

	public String getDropdown8() {
		return dropdown8;
	}

	public void setDropdown8(String dropdown8) {
		this.dropdown8 = dropdown8;
	}

	public String getDropdown9() {
		return dropdown9;
	}

	public void setDropdown9(String dropdown9) {
		this.dropdown9 = dropdown9;
	}

	public String getDropdown10() {
		return dropdown10;
	}

	public void setDropdown10(String dropdown10) {
		this.dropdown10 = dropdown10;
	}

	public void addUserResponse() {
		logger.info("addUserResponce Calling");
		String responseFromUser = dropdown1 + ";" + dropdown2 + ";" + dropdown3
				+ ";" + dropdown4 + ";" + dropdown5 + ";" + dropdown6 + ";"
				+ dropdown7 + ";" + dropdown8 + ";" + dropdown9 + ";"
				+ dropdown10;

		ResponseForm responseForm = new ResponseForm(responseFromUser);

		try {

			new DMSServiceClient().AddResponseForm(responseForm);
		} catch (Exception e) {
			logger.error(e.toString());
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage("Failed :",
							"Please Refresh and try again."));
			return;
		}

		FacesMessage msg = new FacesMessage("Successful",
				"Thanks for giving me your valuable response");
		msg.setSeverity(FacesMessage.SEVERITY_INFO);
		FacesContext.getCurrentInstance().addMessage(null, msg);

	}
}
