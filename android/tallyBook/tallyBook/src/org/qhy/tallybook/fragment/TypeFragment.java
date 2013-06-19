package org.qhy.tallybook.fragment;

import java.util.ArrayList;
import java.util.List;

import org.qhy.tallybook.activity.R;
import org.qhy.tallybook.activity.R.id;
import org.qhy.tallybook.activity.R.layout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class TypeFragment extends Fragment {
	private ListView lv_type;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(layout.fragment_type, container, false);
		lv_type= (ListView)view.findViewById(R.id.lv_type);
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
