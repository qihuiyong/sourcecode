package org.qhy.tallybook.fragment;

import org.qhy.tallybook.activity.R.layout;

import android.R;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AddRecoredFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// // TODO Auto-generated method stub
		// super.onCreateView(inflater, container, savedInstanceState);
		return inflater.inflate(layout.fragment_addrecord, container, false);
	}


}
