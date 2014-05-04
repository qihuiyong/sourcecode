package org.qhy.test.gencode.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the DAM_APPLICATION_INFO table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="DAM_APPLICATION_INFO"
 */

public abstract class BaseDamApplicationInfo  implements Serializable {

	public static String REF = "DamApplicationInfo";
	public static String PROP_APPLICATION_NAME = "ApplicationName";
	public static String PROP_APPLICATION_TYPE = "ApplicationType";
	public static String PROP_APPLICATION_NO = "ApplicationNo";
	public static String PROP_SERVER_ID = "ServerId";
	public static String PROP_APPLICATION_SCORE = "ApplicationScore";
	public static String PROP_APPLICATION_OWNER = "ApplicationOwner";
	public static String PROP_DESC_TXT = "DescTxt";
	public static String PROP_LOGIC_LAYER = "LogicLayer";
	public static String PROP_MODEL_TYPE = "ModelType";
	public static String PROP_APPLICATION_ID = "ApplicationId";
	public static String PROP_BUSINESS_VALUE = "BusinessValue";
	public static String PROP_DATA_SOURCE = "DataSource";
	public static String PROP_STATUS = "Status";
	public static String PROP_EVALUATE_LEVEL = "EvaluateLevel";
	public static String PROP_CREATE_TIME = "CreateTime";
	public static String PROP_RECOVERY_MODEL = "RecoveryModel";
	public static String PROP_APPLICATION_SITUATION = "ApplicationSituation";
	public static String PROP_RESULT_TYPE = "ResultType";


	// constructors
	public BaseDamApplicationInfo () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseDamApplicationInfo (java.lang.String applicationId) {
		this.setApplicationId(applicationId);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String applicationId;

	// fields
	private java.lang.String applicationName;
	private java.lang.String applicationNo;
	private java.lang.String applicationOwner;
	private java.lang.Short logicLayer;
	private java.lang.String serverId;
	private java.lang.String applicationSituation;
	private java.lang.Short applicationType;
	private java.lang.String resultType;
	private java.lang.Short modelType;
	private java.math.BigDecimal applicationScore;
	private java.lang.Short evaluateLevel;
	private java.lang.String createTime;
	private java.lang.Short recoveryModel;
	private java.lang.String status;
	private java.lang.String descTxt;
	private java.lang.String businessValue;
	private java.lang.String dataSource;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  column="APPLICATION_ID"
     */
	public java.lang.String getApplicationId () {
		return applicationId;
	}

	/**
	 * Set the unique identifier of this class
	 * @param applicationId the new ID
	 */
	public void setApplicationId (java.lang.String applicationId) {
		this.applicationId = applicationId;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: APPLICATION_NAME
	 */
	public java.lang.String getApplicationName () {
		return applicationName;
	}

	/**
	 * Set the value related to the column: APPLICATION_NAME
	 * @param applicationName the APPLICATION_NAME value
	 */
	public void setApplicationName (java.lang.String applicationName) {
		this.applicationName = applicationName;
	}



	/**
	 * Return the value associated with the column: APPLICATION_NO
	 */
	public java.lang.String getApplicationNo () {
		return applicationNo;
	}

	/**
	 * Set the value related to the column: APPLICATION_NO
	 * @param applicationNo the APPLICATION_NO value
	 */
	public void setApplicationNo (java.lang.String applicationNo) {
		this.applicationNo = applicationNo;
	}



	/**
	 * Return the value associated with the column: APPLICATION_OWNER
	 */
	public java.lang.String getApplicationOwner () {
		return applicationOwner;
	}

	/**
	 * Set the value related to the column: APPLICATION_OWNER
	 * @param applicationOwner the APPLICATION_OWNER value
	 */
	public void setApplicationOwner (java.lang.String applicationOwner) {
		this.applicationOwner = applicationOwner;
	}



	/**
	 * Return the value associated with the column: LOGIC_LAYER
	 */
	public java.lang.Short getLogicLayer () {
		return logicLayer;
	}

	/**
	 * Set the value related to the column: LOGIC_LAYER
	 * @param logicLayer the LOGIC_LAYER value
	 */
	public void setLogicLayer (java.lang.Short logicLayer) {
		this.logicLayer = logicLayer;
	}



	/**
	 * Return the value associated with the column: SERVER_ID
	 */
	public java.lang.String getServerId () {
		return serverId;
	}

	/**
	 * Set the value related to the column: SERVER_ID
	 * @param serverId the SERVER_ID value
	 */
	public void setServerId (java.lang.String serverId) {
		this.serverId = serverId;
	}



	/**
	 * Return the value associated with the column: APPLICATION_SITUATION
	 */
	public java.lang.String getApplicationSituation () {
		return applicationSituation;
	}

	/**
	 * Set the value related to the column: APPLICATION_SITUATION
	 * @param applicationSituation the APPLICATION_SITUATION value
	 */
	public void setApplicationSituation (java.lang.String applicationSituation) {
		this.applicationSituation = applicationSituation;
	}



	/**
	 * Return the value associated with the column: APPLICATION_TYPE
	 */
	public java.lang.Short getApplicationType () {
		return applicationType;
	}

	/**
	 * Set the value related to the column: APPLICATION_TYPE
	 * @param applicationType the APPLICATION_TYPE value
	 */
	public void setApplicationType (java.lang.Short applicationType) {
		this.applicationType = applicationType;
	}



	/**
	 * Return the value associated with the column: RESULT_TYPE
	 */
	public java.lang.String getResultType () {
		return resultType;
	}

	/**
	 * Set the value related to the column: RESULT_TYPE
	 * @param resultType the RESULT_TYPE value
	 */
	public void setResultType (java.lang.String resultType) {
		this.resultType = resultType;
	}



	/**
	 * Return the value associated with the column: MODEL_TYPE
	 */
	public java.lang.Short getModelType () {
		return modelType;
	}

	/**
	 * Set the value related to the column: MODEL_TYPE
	 * @param modelType the MODEL_TYPE value
	 */
	public void setModelType (java.lang.Short modelType) {
		this.modelType = modelType;
	}



	/**
	 * Return the value associated with the column: APPLICATION_SCORE
	 */
	public java.math.BigDecimal getApplicationScore () {
		return applicationScore;
	}

	/**
	 * Set the value related to the column: APPLICATION_SCORE
	 * @param applicationScore the APPLICATION_SCORE value
	 */
	public void setApplicationScore (java.math.BigDecimal applicationScore) {
		this.applicationScore = applicationScore;
	}



	/**
	 * Return the value associated with the column: EVALUATE_LEVEL
	 */
	public java.lang.Short getEvaluateLevel () {
		return evaluateLevel;
	}

	/**
	 * Set the value related to the column: EVALUATE_LEVEL
	 * @param evaluateLevel the EVALUATE_LEVEL value
	 */
	public void setEvaluateLevel (java.lang.Short evaluateLevel) {
		this.evaluateLevel = evaluateLevel;
	}



	/**
	 * Return the value associated with the column: CREATE_TIME
	 */
	public java.lang.String getCreateTime () {
		return createTime;
	}

	/**
	 * Set the value related to the column: CREATE_TIME
	 * @param createTime the CREATE_TIME value
	 */
	public void setCreateTime (java.lang.String createTime) {
		this.createTime = createTime;
	}



	/**
	 * Return the value associated with the column: RECOVERY_MODEL
	 */
	public java.lang.Short getRecoveryModel () {
		return recoveryModel;
	}

	/**
	 * Set the value related to the column: RECOVERY_MODEL
	 * @param recoveryModel the RECOVERY_MODEL value
	 */
	public void setRecoveryModel (java.lang.Short recoveryModel) {
		this.recoveryModel = recoveryModel;
	}



	/**
	 * Return the value associated with the column: STATUS
	 */
	public java.lang.String getStatus () {
		return status;
	}

	/**
	 * Set the value related to the column: STATUS
	 * @param status the STATUS value
	 */
	public void setStatus (java.lang.String status) {
		this.status = status;
	}



	/**
	 * Return the value associated with the column: DESC_TXT
	 */
	public java.lang.String getDescTxt () {
		return descTxt;
	}

	/**
	 * Set the value related to the column: DESC_TXT
	 * @param descTxt the DESC_TXT value
	 */
	public void setDescTxt (java.lang.String descTxt) {
		this.descTxt = descTxt;
	}



	/**
	 * Return the value associated with the column: BUSINESS_VALUE
	 */
	public java.lang.String getBusinessValue () {
		return businessValue;
	}

	/**
	 * Set the value related to the column: BUSINESS_VALUE
	 * @param businessValue the BUSINESS_VALUE value
	 */
	public void setBusinessValue (java.lang.String businessValue) {
		this.businessValue = businessValue;
	}



	/**
	 * Return the value associated with the column: DATA_SOURCE
	 */
	public java.lang.String getDataSource () {
		return dataSource;
	}

	/**
	 * Set the value related to the column: DATA_SOURCE
	 * @param dataSource the DATA_SOURCE value
	 */
	public void setDataSource (java.lang.String dataSource) {
		this.dataSource = dataSource;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof org.qhy.test.gencode.DamApplicationInfo)) return false;
		else {
			org.qhy.test.gencode.DamApplicationInfo damApplicationInfo = (org.qhy.test.gencode.DamApplicationInfo) obj;
			if (null == this.getApplicationId() || null == damApplicationInfo.getApplicationId()) return false;
			else return (this.getApplicationId().equals(damApplicationInfo.getApplicationId()));
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getApplicationId()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getApplicationId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}


}