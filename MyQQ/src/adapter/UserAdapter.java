package adapter;

import java.util.List;

import com.example.myqq.R;
import com.myqq.image.SmartImageView;







import com.myqq.model.UserInfo;



import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class UserAdapter extends BaseAdapter{
	
	private List<UserInfo> userLists;
	private Context context;
	public UserAdapter(List<UserInfo> userLists,Context context){
		this.userLists=userLists;
		this.context=context;
	}

	@Override
	public int getCount() {
		
		return userLists.size();
	}

	@Override
	public Object getItem(int position) {
		
		return userLists.get(position);
	}

	@Override
	public long getItemId(int position) {
		
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder=null;
		if (convertView==null) {
			viewHolder=new ViewHolder();
			LayoutInflater minInflater=LayoutInflater.from(context);
			convertView=minInflater.inflate(R.layout.user_listview_item, null);
			viewHolder.head=(SmartImageView) convertView.findViewById(R.id.head);
			viewHolder.name=(TextView) convertView.findViewById(R.id.name);
			viewHolder.desc=(TextView) convertView.findViewById(R.id.desc);
			
			convertView.setTag(viewHolder);
		}
		else {
			viewHolder=(ViewHolder) convertView.getTag();
			
		}
		
		UserInfo userInfo=userLists.get(position);
		Log.i("URL", userInfo.getHead());
		viewHolder.head.setImageUrl(userInfo.getHead(),R.drawable.ic_launcher,R.drawable.ic_launcher);
		viewHolder.name.setText(userInfo.getName());
		viewHolder.desc.setText(userInfo.getDesc());
		
		return convertView;
	}
	class ViewHolder{
		SmartImageView head;
		TextView name;
		TextView desc;
		
	}

}
