package com.sudoku;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.adapter.CommonSudokuAdapter;
import com.model.SudokuModel;

public class FragmentSudokuSolver extends Fragment {
	/**
	 * The fragment argument representing the section number for this
	 * fragment.
	 */
	public static final String ARG_SECTION_NUMBER = "section_number";
	private GridView				gridView	=	null;
	private Activity				parent		=   null;
	private	ArrayList<SudokuModel>  list		=   null;

	public FragmentSudokuSolver() {
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		parent = activity;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(
				R.layout.fragment_sodoku_solver, container, false);
		gridView = (GridView)rootView.findViewById(R.id.grid_view);
		gridView.setAdapter(new CommonSudokuAdapter(parent));
		/*TextView dummyTextView = (TextView) rootView
				.findViewById(R.id.section_label);
		dummyTextView.setText(Integer.toString(getArguments().getInt(
				ARG_SECTION_NUMBER)));*/
		return rootView;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.sodoku_solver, menu);
		MenuItem item = (MenuItem) menu.findItem(R.id.action_settings);
		item.setTitle("Solve");
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_settings:
			makeSudoku();			
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	private void makeSudoku() {
		int i=0;
		list = new ArrayList<SudokuModel>();
		while(i<81)
		{
			String data = ((EditText)gridView.getChildAt(i)).getText().toString();
			if(!data.equals(""))
			{
				SudokuModel model = new SudokuModel();
				model.setColumn(i%9);
				model.setRow(i/9);
				model.setData(Integer.parseInt(data));
				list.add(model);
			}
			else
			{
				SudokuModel model = new SudokuModel();
				model.setColumn(i%9);
				model.setRow(i/9);
				model.setData(0);
				list.add(model);
			}
			++i;
		}
		if(fillSudoku(list,0))
		{
			i=0;
			while(i<81)
			{
				EditText editText =(EditText)gridView.getChildAt(i);
				editText.setText(String.valueOf(list.get(i).getData()));
				i++;
			}		
		}
		else
		{
			Toast.makeText(parent, "No solution", Toast.LENGTH_SHORT).show();
		}
	}

	private boolean fillSudoku(ArrayList<SudokuModel> list2,int i) {
		if(i==81)
			return true;
		int row 	= list2.get(i).getRow();
		int column	= list2.get(i).getColumn();

		if (row == 9) {
			i = 0;
			if (++column== 9)
				return true;
		}
		if(list2.get((row*9)+column).getData()!=0)
			return fillSudoku(list2, i+1);
		for (int j = 1; j <= 9; ++j)
		{
			if (isCorrect(i,j,list2)) {
				list2.get((row*9)+column).setData(j);
				if (fillSudoku(list2,i+1))
					return true;
			}
		}
		list2.get((row*9)+column).setData(0); 
		return false;

	}

	private boolean isCorrect(int i, int j, ArrayList<SudokuModel> list2) {

		for (int k = 0; k < 9; ++k)  
			if (j == list2.get((k*9)+(i%9)).getData())
				return false;

		for (int k = 0; k < 9; ++k)
			if (j == list2.get(((i/9)*9)+(k)).getData())
				return false;

		int boxRowOffset = ( list2.get(i).getRow() / 3)*3;
		int boxColOffset = (list2.get(i).getColumn() / 3)*3;
		for (int k = 0; k < 3; ++k) 
			for (int m = 0; m < 3; ++m)
				if (j == list2.get(((boxRowOffset+k)*9)+(boxColOffset+m)).getData())
					return false;
		return true;
	}


}