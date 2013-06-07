package org.qhy.tallybook.fragment;

import java.io.FileDescriptor;
import java.io.PrintWriter;

import android.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Tab1Fragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// // TODO Auto-generated method stub
		// super.onCreateView(inflater, container, savedInstanceState);
		return inflater.inflate(R.layout.list_content, container, false);
	}

}
