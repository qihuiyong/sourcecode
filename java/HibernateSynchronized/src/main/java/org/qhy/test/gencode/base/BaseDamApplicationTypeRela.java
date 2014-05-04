package org.qhy.test.gencode.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the DAM_APPLICATION_TYPE_RELA table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="DAM_APPLICATION_TYPE_RELA"
 */

public abstract class BaseDamApplicationTypeRela  implements Serializable {

	public static String REF = "DamApplicationTypeRela";
	public static String PROP_CHILD_ID = "ChildId";
	public static String PROP_PARENT_ID = "ParentId";
	public static String PROP_TYPE_ID = "TypeId";
	public static String PROP_TYPE_NAME = "TypeName";


	// constructors
	public BaseDamApplicationTypeRela () {
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseDamApplicationTypeRela (
		java.lang.String typeId,
		java.lang.String parentId,
		java.lang.String childId) {

		this.setTypeId(typeId);
		this.setParentId(parentId);
		this.setChildId(childId);
		initialize();
	}

	protected void initialize () {}



	// fields
	private java.lang.String typeId;
	private java.lang.String parentId;
	private java.lang.String childId;
	private java.lang.String typeName;






	/**
	 * Return the value associated with the column: TYPE_ID
	 */
	public java.lang.String getTypeId () {
		return typeId;
	}

	/**
	 * Set the value related to the column: TYPE_ID
	 * @param typeId the TYPE_ID value
	 */
	public void setTypeId (java.lang.String typeId) {
		this.typeId = typeId;
	}



	/**
	 * Return the value associated with the column: PARENT_ID
	 */
	public java.lang.String getParentId () {
		return parentId;
	}

	/**
	 * Set the value related to the column: PARENT_ID
	 * @param parentId the PARENT_ID value
	 */
	public void setParentId (java.lang.String parentId) {
		this.parentId = parentId;
	}



	/**
	 * Return the value associated with the column: CHILD_ID
	 */
	public java.lang.String getChildId () {
		return childId;
	}

	/**
	 * Set the value related to the column: CHILD_ID
	 * @param childId the CHILD_ID value
	 */
	public void setChildId (java.lang.String childId) {
		this.childId = childId;
	}



	/**
	 * Return the value associated with the column: TYPE_NAME
	 */
	public java.lang.String getTypeName () {
		return typeName;
	}

	/**
	 * Set the value related to the column: TYPE_NAME
	 * @param typeName the TYPE_NAME value
	 */
	public void setTypeName (java.lang.String typeName) {
		this.typeName = typeName;
	}







	public String toString () {
		return super.toString();
	}


}