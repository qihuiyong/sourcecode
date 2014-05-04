package org.qhy.test.gencode.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the DAM_APPLICATION_APPROVE table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="DAM_APPLICATION_APPROVE"
 */

public abstract class BaseDamApplicationApprove  implements Serializable {

	public static String REF = "DamApplicationApprove";
	public static String PROP_APPLICANT_DAY = "ApplicantDay";
	public static String PROP_APPROVAL_USER = "ApprovalUser";
	public static String PROP_APPROVAL_RESULT = "ApprovalResult";
	public static String PROP_APPROVAL_TXT = "ApprovalTxt";
	public static String PROP_APPROVAL_DAY = "ApprovalDay";
	public static String PROP_APPLICANT_USER = "ApplicantUser";
	public static String PROP_DESC = "Desc";
	public static String PROP_APPROVAL_TYPE = "ApprovalType";
	public static String PROP_APPLICATION_ID = "ApplicationId";


	// constructors
	public BaseDamApplicationApprove () {
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseDamApplicationApprove (
		java.lang.String applicationId) {

		this.setApplicationId(applicationId);
		initialize();
	}

	protected void initialize () {}



	// fields
	private java.lang.String applicationId;
	private java.lang.Short approvalType;
	private java.lang.String applicantUser;
	private java.util.Date applicantDay;
	private java.lang.String approvalTxt;
	private java.lang.Short approvalResult;
	private java.lang.String approvalUser;
	private java.util.Date approvalDay;
	private java.lang.String desc;






	/**
	 * Return the value associated with the column: APPLICATION_ID
	 */
	public java.lang.String getApplicationId () {
		return applicationId;
	}

	/**
	 * Set the value related to the column: APPLICATION_ID
	 * @param applicationId the APPLICATION_ID value
	 */
	public void setApplicationId (java.lang.String applicationId) {
		this.applicationId = applicationId;
	}



	/**
	 * Return the value associated with the column: APPROVAL_TYPE
	 */
	public java.lang.Short getApprovalType () {
		return approvalType;
	}

	/**
	 * Set the value related to the column: APPROVAL_TYPE
	 * @param approvalType the APPROVAL_TYPE value
	 */
	public void setApprovalType (java.lang.Short approvalType) {
		this.approvalType = approvalType;
	}



	/**
	 * Return the value associated with the column: APPLICANT_USER
	 */
	public java.lang.String getApplicantUser () {
		return applicantUser;
	}

	/**
	 * Set the value related to the column: APPLICANT_USER
	 * @param applicantUser the APPLICANT_USER value
	 */
	public void setApplicantUser (java.lang.String applicantUser) {
		this.applicantUser = applicantUser;
	}



	/**
	 * Return the value associated with the column: APPLICANT_DAY
	 */
	public java.util.Date getApplicantDay () {
		return applicantDay;
	}

	/**
	 * Set the value related to the column: APPLICANT_DAY
	 * @param applicantDay the APPLICANT_DAY value
	 */
	public void setApplicantDay (java.util.Date applicantDay) {
		this.applicantDay = applicantDay;
	}



	/**
	 * Return the value associated with the column: APPROVAL_TXT
	 */
	public java.lang.String getApprovalTxt () {
		return approvalTxt;
	}

	/**
	 * Set the value related to the column: APPROVAL_TXT
	 * @param approvalTxt the APPROVAL_TXT value
	 */
	public void setApprovalTxt (java.lang.String approvalTxt) {
		this.approvalTxt = approvalTxt;
	}



	/**
	 * Return the value associated with the column: APPROVAL_RESULT
	 */
	public java.lang.Short getApprovalResult () {
		return approvalResult;
	}

	/**
	 * Set the value related to the column: APPROVAL_RESULT
	 * @param approvalResult the APPROVAL_RESULT value
	 */
	public void setApprovalResult (java.lang.Short approvalResult) {
		this.approvalResult = approvalResult;
	}



	/**
	 * Return the value associated with the column: APPROVAL_USER
	 */
	public java.lang.String getApprovalUser () {
		return approvalUser;
	}

	/**
	 * Set the value related to the column: APPROVAL_USER
	 * @param approvalUser the APPROVAL_USER value
	 */
	public void setApprovalUser (java.lang.String approvalUser) {
		this.approvalUser = approvalUser;
	}



	/**
	 * Return the value associated with the column: APPROVAL_DAY
	 */
	public java.util.Date getApprovalDay () {
		return approvalDay;
	}

	/**
	 * Set the value related to the column: APPROVAL_DAY
	 * @param approvalDay the APPROVAL_DAY value
	 */
	public void setApprovalDay (java.util.Date approvalDay) {
		this.approvalDay = approvalDay;
	}



	/**
	 * Return the value associated with the column: DESC
	 */
	public java.lang.String getDesc () {
		return desc;
	}

	/**
	 * Set the value related to the column: DESC
	 * @param desc the DESC value
	 */
	public void setDesc (java.lang.String desc) {
		this.desc = desc;
	}







	public String toString () {
		return super.toString();
	}


}