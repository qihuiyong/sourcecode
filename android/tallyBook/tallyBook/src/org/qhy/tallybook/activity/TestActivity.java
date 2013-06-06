package org.qhy.tallybook.activity;

import org.qhy.tallybook.db.DatabaseHelper;
import org.qhy.tallybook.db.TallyBookDatabase;
import org.qhy.tallybook.db.TypeColumns;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.widget.Button;

public class TestActivity extends Activity {
	private Button createDb;
	private Button addType;
	private Button showData;
	private Button deleteDb;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_test);
		createDb=(Button) this.findViewById(R.id.createDb);
		addType = (Button)this.findViewById(R.id.addType);
		showData = (Button)this.findViewById(R.id.showData);
		deleteDb= (Button)this.findViewById(R.id.deleteDb);
		bindEvent();
	}

	private void bindEvent() {
		
		createDb.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				DatabaseHelper h = new DatabaseHelper(TestActivity.this, "test.db",null , 1);
				h.getWritableDatabase();
			}
		});
		addType.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				DatabaseHelper h = new DatabaseHelper(TestActivity.this, "test.db",null , 1);
				SQLiteDatabase db = h.getWritableDatabase();
				ContentValues values = new ContentValues();
				values.put(TypeColumns.TNAME, "服装3");
				values.put(TypeColumns.TORDER, 3);
				db.insert(TallyBookDatabase.TYPE_TALLYBOOK_TABLE, null, values);
			}
		});
		showData.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				DatabaseHelper h = new DatabaseHelper(TestActivity.this, "test.db",null , 1);
				SQLiteDatabase db = h.getWritableDatabase();
				Cursor  cursor = db.query(TallyBookDatabase.TYPE_TALLYBOOK_TABLE,  new String[]{TypeColumns.TID,TypeColumns.TNAME,TypeColumns.TORDER}, null, null, null, null, null);
				System.out.println("查询记录："+cursor.getCount()+"条");
				int index=0;
				while (cursor.moveToNext()) {
					System.out.print("第"+(index++)+"条"+TypeColumns.TID+"====>"+cursor.getInt(cursor.getColumnIndex(TypeColumns.TID)));
					System.out.print(TypeColumns.TNAME+"====>"+cursor.getString(cursor.getColumnIndex(TypeColumns.TNAME)));
					System.out.print(TypeColumns.TORDER+"====>"+cursor.getInt(cursor.getColumnIndex(TypeColumns.TORDER))+"\n");
				}
				}
		});
		
		deleteDb.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				DatabaseHelper h = new DatabaseHelper(TestActivity.this, "test.db",null , 1);
				SQLiteDatabase db = h.getWritableDatabase();
				db.execSQL("drop table  IF EXISTS "+TallyBookDatabase.TYPE_TALLYBOOK_TABLE);
				db.execSQL("drop table  IF EXISTS "+TallyBookDatabase.TYPE_TALLYBOOK_TABLE);
				TestActivity.this.deleteDatabase("test.db");
			}
		});
	}
}
