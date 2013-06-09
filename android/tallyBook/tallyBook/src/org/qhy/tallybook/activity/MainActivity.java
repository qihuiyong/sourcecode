package org.qhy.tallybook.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.TabWidget;
/**
 * 应用程序主入口，使用了tab
 * @author qihuiyong
 *
 */
public class MainActivity extends FragmentActivity {
	
	//tabHost
	private TabHost tabHost;
	private TabWidget tabWidget;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.initViewObj();
		
		tabHost.setup();
		
		tabHost.addTab(tabHost.newTabSpec("addrecordTab_tag").setIndicator(this.getResources().getText(R.string.addrecord_tab_lable))
				.setContent(R.id.addrecord_tab));
		
		tabHost.addTab(tabHost.newTabSpec("statisticsTab_tab").setIndicator(this.getResources().getText(R.string.statistics_tab_lable))
				.setContent(R.id.statistics_tab));
		
		tabHost.addTab(tabHost.newTabSpec("typeTab_tab").setIndicator(this.getResources().getText(R.string.type_tab_lable))
				.setContent(R.id.type_tab));
		tabHost.addTab(tabHost.newTabSpec("helpTab_tab").setIndicator(this.getResources().getText(R.string.help_tab_lable)).setContent(R.id.help_tab));
	}
	
	private void initViewObj(){
		tabHost = (TabHost) findViewById(android.R.id.tabhost);
		tabWidget = (TabWidget)findViewById(android.R.id.tabs);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
