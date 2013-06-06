package org.qhy.tallybook.db;

import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
/**
 * 记账本数据库封装
 * @author qihuiyong
 *
 */
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
			+ RECORED_TALLYBOOK_TABLE + " (" + RecoredColumns.CID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + RecoredColumns.CTYPE
			+ " INTEGER REFERENCES " + TYPE_TALLYBOOK_TABLE + " ("
			+ TypeColumns.TID + ") ," + RecoredColumns.CNAME + " VARCHAR(50) ,"
			+ RecoredColumns.CSUM + "  DOUBLE ," + RecoredColumns.CDATE
			+ " TIMESTAMP" + ")";

	/**/
	/**
	 * 获取数据库
	 * @param context
	 * @return
	 */
	public SQLiteDatabase getDatabase(Context context) {
		DatabaseHelper h = new DatabaseHelper(context,DATABASE_NAME,null , DATABASE_VERSION);
		return h.getWritableDatabase();
	}
	
	/*对类型表的操作*/
	/**
	 * 添加一个分类
	 * @param context
	 * @param values
	 * @return
	 */
	public long insertType(Context context,ContentValues values){
		return this.getDatabase(context).insert(TallyBookDatabase.TYPE_TALLYBOOK_TABLE, null, values);
	}
	/**
	 * 更新分类
	 * @param context
	 * @param values
	 * @return
	 */
	public long updateType(Context context,ContentValues values){
		return this.getDatabase(context).update(TallyBookDatabase.TYPE_TALLYBOOK_TABLE, values, TypeColumns.TID+" =  ?", new String[]{values.get(TypeColumns.TID).toString()});
	}
	
}
