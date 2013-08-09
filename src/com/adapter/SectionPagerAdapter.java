package com.adapter;

import java.util.Locale;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.sudoku.FragmentRandomSudokuGenerator;
import com.sudoku.FragmentSudokuSolver;
import com.sudoku.R;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionPagerAdapter extends FragmentPagerAdapter {

	private		Context		mContext		= 	null;

	public SectionPagerAdapter(FragmentManager fm,Context context) {
		super(fm);
		mContext = context;
	}

	@Override
	public Fragment getItem(int position) {
		if(position==0)
		{
			Fragment fragment = new FragmentSudokuSolver();
			Bundle args = new Bundle();
			args.putInt(FragmentSudokuSolver.ARG_SECTION_NUMBER, position + 1);
			fragment.setArguments(args);
			return fragment;
		}
		else if(position==1)
			{
			Fragment fragment = new FragmentRandomSudokuGenerator();
			Bundle args = new Bundle();
			args.putInt(FragmentSudokuSolver.ARG_SECTION_NUMBER, position + 1);
			fragment.setArguments(args);
			return fragment;
			}
		return null;

	}

	@Override
	public int getCount() {
		// Show 3 total pages.
		return 2;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		Locale l = Locale.getDefault();
		switch (position) {
		case 0:
			return mContext.getString(R.string.title_section1).toUpperCase(l);
		case 1:
			return mContext.getString(R.string.title_section2).toUpperCase(l);
		case 2:
			return mContext.getString(R.string.title_section3).toUpperCase(l);
		}
		return null;
	}
}