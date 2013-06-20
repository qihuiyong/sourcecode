package org.qhy.tallybook.fragment;

import java.util.ArrayList;
import java.util.List;

import org.qhy.tallybook.activity.R;
import org.qhy.tallybook.activity.R.id;
import org.qhy.tallybook.activity.R.layout;
import org.qhy.tallybook.db.TallyBookDatabase;
import org.qhy.tallybook.db.TypeColumns;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

public class TypeFragment extends Fragment {
	private ListView lv_type;
	private Button btn_addtype;
	private LayoutInflater myinflater;
	private TallyBookDatabase db;
	//弹出窗口的控件
	private PopupWindow addTypeWin ;
	private Button btn_cancel;
	private Button btn_savetype;
	private EditText text_typename;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		myinflater=inflater;
		View view = inflater.inflate(layout.fragment_type, container, false);
		lv_type= (ListView)view.findViewById(R.id.lv_type);
		btn_addtype = (Button)view.findViewById(R.id.btn_addtype);
		db = TallyBookDatabase.newInstance(getActivity());
		
		 
		btn_addtype.setOnClickListener( new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				View  addtype_view = myinflater.inflate(layout.add_type, null);
				btn_savetype  = (Button)addtype_view.findViewById(R.id.btn_savetype);
				btn_cancel = (Button)addtype_view.findViewById(R.id.btn_cancel);
				text_typename =(EditText)addtype_view.findViewById(R.id.text_typename);
				
				addTypeWin = new PopupWindow(addtype_view, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
				addTypeWin.setFocusable(true);
				addTypeWin.showAtLocation(v,Gravity.CENTER , 0, 0);
				
				btn_savetype.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						String typename = text_typename.getText().toString().trim();
						if(typename ==null || "".equals(typename) ){
							Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.msg_tynameisnull), Toast.LENGTH_LONG).show();
							return;
						}
						ContentValues values = new ContentValues();
						values.put(TypeColumns.TNAME, typename);
						values.put(TypeColumns.TORDER, 1);
						long returnid = db.insertType( values);
						if(returnid != -1){
							Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.msg_addSuccess), Toast.LENGTH_SHORT).show();
						}else{
							Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.msg_addFailed), Toast.LENGTH_LONG).show();
						}
						 
					}
				});
				
				btn_cancel.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						addTypeWin.dismiss();
					}
				});
			}
		});
		
		List<String> list = new ArrayList<String>();
		list.add("1111");
		list.add("2222");
		list.add("3333");
		list.add("4444");
		ListAdapter adapter = new ArrayAdapter<String>(this.getActivity().getApplicationContext(), layout.lv_type, list);
		lv_type.setAdapter(adapter);
		return view;
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		System.out.println("sdsd");
		super.onCreate(savedInstanceState);
	}
	
}
