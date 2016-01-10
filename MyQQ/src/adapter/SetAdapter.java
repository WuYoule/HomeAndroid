package adapter;

import java.util.List;

import com.example.myqq.R;
import com.myqq.model.PopupwindowInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SetAdapter extends ArrayAdapter<PopupwindowInfo>{

	private int mResource;
	private List<PopupwindowInfo> infos;
	public SetAdapter(Context context, int resource,
			List<PopupwindowInfo> infos) {
		super(context, resource, infos);
		this.mResource=resource;
		this.infos=infos;
	
	}

	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	    PopupwindowInfo popupwindowInfo=infos.get(position);
	    LayoutInflater inflater=LayoutInflater.from(getContext());
	    View view=inflater.inflate(mResource, null);
	    TextView tv=(TextView) view.findViewById(R.id.popupwindow_tv);
	    ImageView img=(ImageView) view.findViewById(R.id.popupwindow_img);
	    
	    tv.setText(popupwindowInfo.getName());
	    img.setBackgroundResource(R.drawable.ic_launcher);
		
		
		return view;
	}
	

	
	
	
	
	
}
