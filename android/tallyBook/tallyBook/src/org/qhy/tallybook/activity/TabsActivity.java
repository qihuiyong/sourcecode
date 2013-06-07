package org.qhy.tallybook.activity;


import android.app.Activity;
import android.os.Bundle;
import android.widget.TabHost;

public class TabsActivity extends Activity {
	private TabHost tabHost;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_tabs);
		tabHost = (TabHost) this.findViewById(android.R.id.tabhost);
                 
	}

}
