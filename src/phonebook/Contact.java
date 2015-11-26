package phonebook;

import java.io.Serializable;



/*
 * Entity object class for contacts
 */
public class Contact implements Serializable {

	private static final long serialVersionUID = -8307512249140185464L;
	
	/* 
	 * name for the contact
	 */
	private String firstname, lastname;
	/*
	 * email addresses
	 */
	private String workEmail, personalEmail;
	/*
	 * phone numbers
	 */
	private String homeNumber, workNumber, cellNumber;
	
	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}
	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}
	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	/**
	 * @return the workEmail
	 */
	public String getWorkEmail() {
		return workEmail;
	}
	/**
	 * @param workEmail the workEmail to set
	 */
	public void setWorkEmail(String workEmail) {
		this.workEmail = workEmail;
	}
	/**
	 * @return the personalEmail
	 */
	public String getPersonalEmail() {
		return personalEmail;
	}
	/**
	 * @param personalEmail the personalEmail to set
	 */
	public void setPersonalEmail(String personalEmail) {
		this.personalEmail = personalEmail;
	}
	/**
	 * @return the homeNumber
	 */
	public String getHomeNumber() {
		return homeNumber;
	}
	/**
	 * @param homeNumber the homeNumber to set
	 */
	public void setHomeNumber(String homeNumber) {
		this.homeNumber = homeNumber;
	}
	/**
	 * @return the workNumber
	 */
	public String getWorkNumber() {
		return workNumber;
	}
	/**
	 * @param workNumber the workNumber to set
	 */
	public void setWorkNumber(String workNumber) {
		this.workNumber = workNumber;
	}
	/**
	 * @return the cellNumber
	 */
	public String getCellNumber() {
		return cellNumber;
	}
	/**
	 * @param cellNumber the cellNumber to set
	 */
	public void setCellNumber(String cellNumber) {
		this.cellNumber = cellNumber;
	}
	
	
	
	
}
