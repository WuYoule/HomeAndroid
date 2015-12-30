package com.example.fragmentcomunicateactivity;

import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

public class MyFragmentItem extends Fragment {
	
	public interface OnNewItemAddListener{
		
		public void newItemAdd(String content);
	}
	public OnNewItemAddListener o;
	
    
	EditText editText;
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		 try {
	            o = (OnNewItemAddListener) activity;
	        } catch (ClassCastException e) {
	            throw new ClassCastException(activity.toString() + " must implement OnArticleSelectedListener");
	        }
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view= inflater.inflate(R.layout.fragment_top_content, container,false);
	   editText=	(EditText) view.findViewById(R.id.inputcontent);
	   editText.setOnKeyListener(new OnKeyListener() {
		
		@Override
		public boolean onKey(View v, int keyCode, KeyEvent event) {
			if (event.getAction()==KeyEvent.ACTION_DOWN) {
				if (keyCode==KeyEvent.KEYCODE_ENTER) {
					String content=editText.getText().toString();
					//Toast.makeText(getActivity(), content, Toast.LENGTH_SHORT).show();
					o.newItemAdd(content);
					return true;
				}
				
			}
			return false;
		}
	});
		return view;
	}

}
