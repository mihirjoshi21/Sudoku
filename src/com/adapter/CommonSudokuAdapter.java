package com.adapter;


import android.content.Context;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.BaseAdapter;
import android.widget.EditText;

import com.sudoku.R;


public class CommonSudokuAdapter extends BaseAdapter {
	private Context 		   mContext;



	// Constructor
	public CommonSudokuAdapter(Context c){

		mContext = c;
	}

	@Override
	public int getCount() {
		return 81;
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return (long)position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		EditText editText;
		if(convertView==null){
			editText = new EditText(mContext);
			convertView=editText;
			convertView.setTag(R.string.edittext, editText);

		}
		else
		{
			editText=(EditText) convertView.getTag(R.string.edittext);
		}		

		InputFilter filter = new InputFilter() {

			@Override
			public CharSequence filter(CharSequence source, int start, int end,
					Spanned dest, int dstart, int dend) {
				for (int i = start; i < end; i++) { 
					if (source.charAt(i)=='0') { 
						return ""; 
					} 
				}
				return null;
			}
		};
		editText.setFilters(new InputFilter[] {new InputFilter.LengthFilter(1),filter});
		editText.setKeyListener(DigitsKeyListener.getInstance("123456789"));
		editText.setInputType(InputType.TYPE_CLASS_NUMBER);
		editText.setSingleLine();
		editText.setImeOptions(EditorInfo.IME_ACTION_NEXT);
		return convertView;
	}

}