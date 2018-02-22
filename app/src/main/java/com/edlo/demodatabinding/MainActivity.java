package com.edlo.demodatabinding;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setActionBar(toolbar);

		initFrag();
	}

	private void initFrag() {
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		String tag = SpotListFrag.TAG;
		Fragment frag = fragmentManager.findFragmentByTag(tag);
		if(frag == null) {
			frag = SpotListFrag.newInstance();
		}
		fragmentTransaction.replace(R.id.content, frag, tag);
		fragmentTransaction.commit();
	}

}
