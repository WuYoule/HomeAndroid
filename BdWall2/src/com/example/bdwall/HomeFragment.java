package com.example.bdwall;

import java.util.ArrayList;

import com.example.bdwall.entity.HomeGrid;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeFragment extends Fragment{

	
	AdScroll myAdScroll;
    ViewPager myvp;
    
    MyGridView mygv;
    
    MyScrollView mysv;
    ArrayList<View> bitmap=new ArrayList<View>();
    

    ArrayList<HomeGrid> gridData=new ArrayList<HomeGrid>();
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		    View view=inflater.inflate(R.layout.home_fragment,container,false);
		    findView(view);
	        return  view;
	}
	
	 @Override
	    public void onActivityCreated(Bundle savedInstanceState) {
	        super.onActivityCreated(savedInstanceState);
	        init();

	    }
	 private void init() {
         initAdData();
        initGridData();
        myvp.setAdapter(new Myadapter());
      
        myAdScroll.setPageFromTime(3000);
        mygv.setAdapter(new GridViewAdapter(getActivity()));
        
        
        //如果不设 进入界面  上面的viewpage 看不到 向下滑动了 这个就是让初始化的时候  不滑动 能看到 上面的viewpager
        mysv.smoothScrollTo(0, 0);

    }
	 private void initGridData() {
		for(int i=0;i<10;i++){
			HomeGrid hg=new HomeGrid();
			hg.setImg(R.drawable.img2);
			hg.setIntroduce("卡通"+i);
			gridData.add(hg);
		}
		
	}

	private void initAdData() {
	        LayoutInflater inflater=getActivity().getLayoutInflater();
	        View view= inflater.inflate(R.layout.ad_scroll_item,null);
	        view.findViewById(R.id.adImg).setBackgroundResource(R.drawable.img1);
	        bitmap.add(view);

	        LayoutInflater inflater2=getActivity().getLayoutInflater();
	        View view2= inflater2.inflate(R.layout.ad_scroll_item,null);
	        view2.findViewById(R.id.adImg).setBackgroundResource(R.drawable.img2);
	        bitmap.add(view2);

	        LayoutInflater inflater3=getActivity().getLayoutInflater();
	        View view3= inflater3.inflate(R.layout.ad_scroll_item,null);
	        view3.findViewById(R.id.adImg).setBackgroundResource(R.drawable.img3);
	        bitmap.add(view3);

	        LayoutInflater inflater4=getActivity().getLayoutInflater();
	        View view4= inflater4.inflate(R.layout.ad_scroll_item,null);
	        view4.findViewById(R.id.adImg).setBackgroundResource(R.drawable.img4);
	        bitmap.add(view4);

	    }
	 
	 //viewpager适配器
	 private  class  Myadapter extends PagerAdapter {
	        @Override
	        public int getCount() {
	          //  Log.i("日志", String.valueOf(bitmap.size()));
	            return  bitmap.size();

	        }

	        @Override
	        public boolean isViewFromObject(View view, Object object) {
	            return view==object;
	        }

	        @Override
	        public void destroyItem(ViewGroup container, int position, Object object) {
	            ((ViewPager)container).removeView(bitmap.get(position));
	        }

	        @Override
	        public Object instantiateItem(ViewGroup container, int position) {

	        	((ViewPager)container).addView(bitmap.get(position));
	            return bitmap.get(position);
	        }
	    }
	//GridView适配器
	 private class GridViewAdapter extends BaseAdapter{
          Context context;
          LayoutInflater inflater;
          public GridViewAdapter(Context context){
        	  
        	  this.context=context;
        	  inflater=(LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        	  
          }
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return gridData.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return gridData.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
		    
			ViewHolder vh;
			if(convertView==null){
		    	convertView=inflater.inflate(R.layout.home_grid_item,null);
		    	vh=new  ViewHolder();
		    	vh.img=(ImageView) convertView.findViewById(R.id.gridimg);
		    	vh.text=(TextView) convertView.findViewById(R.id.gridtext);
		    	
		    	convertView.setTag(vh);
		    }	
			else{
				vh=(ViewHolder) convertView.getTag();
			}
			vh.img.setBackgroundResource(gridData.get(position).getImg());
			vh.text.setText(gridData.get(position).getIntroduce());
		
			return convertView;
		}
		 
		 
		 
	 }
	 
	 
	 static class ViewHolder{
		 ImageView img;
		 TextView text;
	 }
	private void findView(View view) {
	       myAdScroll= (AdScroll) view.findViewById(R.id.myAdScroll);
	        myvp=myAdScroll.getViewPage();
	        mygv=(MyGridView) view.findViewById(R.id.myGridView);
	        mysv=(MyScrollView) view.findViewById(R.id.myScrollView);

	    }
	
	

}
