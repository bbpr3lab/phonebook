package phonebook.model;

import java.io.Serializable;



/*
 * Entity object class for contacts
 */
public class Contact implements Serializable {

	private static final long serialVersionUID = -8307512249140185464L;
	
	public static final int NUMBER_OF_PROPERTIES = 7;
	
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
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cellNumber == null) ? 0 : cellNumber.hashCode());
		result = prime * result
				+ ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result
				+ ((homeNumber == null) ? 0 : homeNumber.hashCode());
		result = prime * result
				+ ((lastname == null) ? 0 : lastname.hashCode());
		result = prime * result
				+ ((personalEmail == null) ? 0 : personalEmail.hashCode());
		result = prime * result
				+ ((workEmail == null) ? 0 : workEmail.hashCode());
		result = prime * result
				+ ((workNumber == null) ? 0 : workNumber.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		if (cellNumber == null) {
			if (other.cellNumber != null)
				return false;
		} else if (!cellNumber.equals(other.cellNumber))
			return false;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (homeNumber == null) {
			if (other.homeNumber != null)
				return false;
		} else if (!homeNumber.equals(other.homeNumber))
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;
		if (personalEmail == null) {
			if (other.personalEmail != null)
				return false;
		} else if (!personalEmail.equals(other.personalEmail))
			return false;
		if (workEmail == null) {
			if (other.workEmail != null)
				return false;
		} else if (!workEmail.equals(other.workEmail))
			return false;
		if (workNumber == null) {
			if (other.workNumber != null)
				return false;
		} else if (!workNumber.equals(other.workNumber))
			return false;
		return true;
	}
	
	
	
	
}
