package org.qhy.tallybook.db;

public class TallyBookDatabase {
	/**/
	public static final String DATABASE_NAME = "qhy_tallyBook.db";
	public static final int DATABASE_VERSION = 1;
	public static final String TYPE_TALLYBOOK_TABLE = "tallyType";
	public static final String RECORED_TALLYBOOK_TABLE = "consumeRecord";
	/* create Table sql */
	public static final String CREATE_TABLE_TYPE_SQL = "CREATE TABLE "
			+ TYPE_TALLYBOOK_TABLE + "  ( " + TypeColumns.TID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + TypeColumns.TNAME
			+ " VARCHAR(50), " + TypeColumns.TORDER + "  INTEGER)";

	public static final String CREATE_TABLE_RECORD_SQL = "CREATE TABLE  "
			+ RECORED_TALLYBOOK_TABLE + " (" +
					RecoredColumns.CID+" INTEGER PRIMARY KEY AUTOINCREMENT ," +
					RecoredColumns.CTYPE+" INTEGER REFERENCES "+TYPE_TALLYBOOK_TABLE+" ("+TypeColumns.TID+") ," +
					RecoredColumns.CNAME +" VARCHAR(50) ,"+
					RecoredColumns.CSUM+"  DOUBLE ," +
					RecoredColumns.CDATE+" TIMESTAMP"+
					")";
	/**/
//	private DatabaseHelper databaseHelper;
}
