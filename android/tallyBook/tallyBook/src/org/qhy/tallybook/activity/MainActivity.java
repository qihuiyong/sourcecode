package org.qhy.tallybook.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.TabWidget;

public class MainActivity extends FragmentActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);
		tabHost.setup();

		tabHost.addTab(tabHost.newTabSpec("33tab1").setIndicator("tab1111")
				.setContent(R.id.tab1));
		tabHost.addTab(tabHost.newTabSpec("33tab2").setIndicator("tab2111")
				.setContent(R.id.tab2));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
