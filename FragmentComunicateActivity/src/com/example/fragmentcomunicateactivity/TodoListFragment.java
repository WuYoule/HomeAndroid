package com.example.fragmentcomunicateactivity;

import android.app.Fragment;
import android.app.ListFragment;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class TodoListFragment extends ListFragment {

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		Toast.makeText(getActivity(),l.getItemAtPosition(position)+"" , Toast.LENGTH_SHORT).show();
	}
}
