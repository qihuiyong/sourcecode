package org.qhy.tallybook.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * 数据库操作帮助类
 * @author qihuiyong
 *
 */
public class DatabaseHelper extends SQLiteOpenHelper {
	private static final String TAG = DatabaseHelper.class.getSimpleName();
	
	public DatabaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		//创建类型表
		db.execSQL(TallyBookDatabase.CREATE_TABLE_TYPE_SQL);
		//创建记录表
		db.execSQL(TallyBookDatabase.CREATE_TABLE_RECORD_SQL);
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

}
