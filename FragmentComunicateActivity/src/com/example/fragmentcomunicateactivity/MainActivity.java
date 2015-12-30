package com.example.fragmentcomunicateactivity;

import java.util.ArrayList;

import com.example.fragmentcomunicateactivity.MyFragmentItem.OnNewItemAddListener;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class MainActivity extends Activity implements OnNewItemAddListener {

	//Êý¾ÝÔ´
	private ArrayList<String> data;
	
	//ÊÊÅäÆ÷
	private ArrayAdapter<String> adapter;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data=new ArrayList<String>();
        data.add("Tom");
        data.add("Jack");
     
       
        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
        TodoListFragment fragment=(TodoListFragment) getFragmentManager().findFragmentById(R.id.fragment_bottom);
        fragment.setListAdapter(adapter);
    }

	@Override
	public void newItemAdd(String content) {
		//Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
		data.add(content);
		adapter.notifyDataSetChanged();
		
	}


  
    
}
