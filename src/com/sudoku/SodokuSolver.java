package com.sudoku;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;

import com.adapter.SectionPagerAdapter;

public class SodokuSolver extends FragmentActivity {


	SectionPagerAdapter mSectionsPagerAdapter;

	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sodoku_solver);

		Log.d("debug", "debug");
		Log.i("f", "f");
		mSectionsPagerAdapter = new SectionPagerAdapter(
				getSupportFragmentManager(), getApplicationContext());

		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.sodoku_solver, menu);
		return true;
	}

	
}
