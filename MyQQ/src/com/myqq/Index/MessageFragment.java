package com.myqq.Index;

import java.util.ArrayList;

import com.example.myqq.R;
import com.myqq.Http.Downloader;
import com.myqq.model.PopupwindowInfo;

import adapter.SetAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class MessageFragment extends Fragment {
	private ListView listView;
	private View view;
	private Downloader downloader;
	
	private TextView setTextView;
	
	private ArrayList<PopupwindowInfo> pupupwindowInfos;
	private PopupWindow pwMyPopWindow;// popupwindow
	private ListView lvPopupList;// popupwindow�е�ListView

	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		pupupwindowInfos=new ArrayList<PopupwindowInfo>();
		 InitPopupwindowInfoData();
		
		
		
	
		
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		  
		   InitPopuwindow();
		
		
		 view=	inflater.inflate(R.layout.tab_item_message, container, false);
		 
		 setTextView=(TextView) view.findViewById(R.id.set);
			listView=(ListView) view.findViewById(R.id.message_listview);
			downloader=new Downloader(listView,getActivity());
			downloader.execute();
			
			setTextView.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if (pwMyPopWindow.isShowing()) {
						pwMyPopWindow.dismiss();
					}
					else {
						pwMyPopWindow.showAsDropDown(setTextView);
					}
					
				}
			});
			
			
		return view;
	}
	private void InitPopupwindowInfoData() {
		PopupwindowInfo pInfo=null;
		pInfo=new PopupwindowInfo();
		pInfo.setName("ɨһɨ");
		pupupwindowInfos.add(pInfo);
		
		pInfo=new PopupwindowInfo();
		pInfo.setName("�Ӻ���");
		pupupwindowInfos.add(pInfo);
		
		pInfo=new PopupwindowInfo();
		pInfo.setName("����������");
		pupupwindowInfos.add(pInfo);
		
		pInfo=new PopupwindowInfo();
		pInfo.setName("���͵�����");
		pupupwindowInfos.add(pInfo);
		
		pInfo=new PopupwindowInfo();
		pInfo.setName("�����촫");
		pupupwindowInfos.add(pInfo);
		
		pInfo=new PopupwindowInfo();
		pInfo.setName("��Ǯ");
		pupupwindowInfos.add(pInfo);
		
	}

    private void InitPopuwindow(){
    	LayoutInflater inflater=LayoutInflater.from(getActivity());
        View view= 	inflater.inflate(R.layout.popupwindow_layout, null);
        lvPopupList=(ListView) view.findViewById(R.id.lv_popup_list);
        
        pwMyPopWindow=new PopupWindow(view);
        pwMyPopWindow.setFocusable(true);
        
        lvPopupList.setAdapter(new SetAdapter(getActivity(), R.layout.popupwindow_item, pupupwindowInfos));
        
     // ����popupwindow�Ŀ�Ⱥ͸߶�����Ӧ
     		lvPopupList.measure(View.MeasureSpec.UNSPECIFIED,
     				View.MeasureSpec.UNSPECIFIED);
     		Log.i("WIDTH", lvPopupList.getMeasuredWidth()+"");
     		pwMyPopWindow.setWidth(500);
     		pwMyPopWindow.setHeight((lvPopupList.getMeasuredHeight() + 20)
     				* pupupwindowInfos.size());
     		
     		// ����popupwindow�����Ļ�����ط���ʧ
    		pwMyPopWindow.setBackgroundDrawable(this.getResources().getDrawable(
    				R.drawable.bg_popupwindow));// ���ñ���ͼƬ�������ڲ��������ã�Ҫͨ������������
    		pwMyPopWindow.setOutsideTouchable(true);// ����popupwindow�ⲿ��popupwindow��ʧ�����Ҫ�����popupwindowҪ�б���ͼƬ�ſ��Գɹ�������
    }
}
