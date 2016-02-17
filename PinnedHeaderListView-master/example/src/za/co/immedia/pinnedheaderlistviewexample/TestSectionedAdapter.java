package za.co.immedia.pinnedheaderlistviewexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import za.co.immedia.pinnedheaderlistview.SectionedBaseAdapter;

public class TestSectionedAdapter extends SectionedBaseAdapter {

	@Override
	public Object getItem(int section, int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int section, int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSectionCount() {
		return 26;
	}

	@Override
	public int getCountForSection(int section) {
		switch (section) {
		case 0:
			return 2;
		case 1:
			return 5;
		case 2:
			return 10;
		case 3:
			return 15;

		default:
			return 20;

		}

	}

	@Override
	public View getItemView(int section, int position, View convertView,
			ViewGroup parent) {
		LinearLayout layout = null;
		if (convertView == null) {
			LayoutInflater inflator = (LayoutInflater) parent.getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			layout = (LinearLayout) inflator.inflate(R.layout.list_item, null);
		} else {
			layout = (LinearLayout) convertView;
		}
		((TextView) layout.findViewById(R.id.textItem)).setText("Section "
				+ section + " Item " + position);
		return layout;
	}

	@Override
	public View getSectionHeaderView(int section, View convertView,
			ViewGroup parent) {
		LinearLayout layout = null;
		if (convertView == null) {
			LayoutInflater inflator = (LayoutInflater) parent.getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			layout = (LinearLayout) inflator
					.inflate(R.layout.header_item, null);
		} else {
			layout = (LinearLayout) convertView;
		}

		((TextView) layout.findViewById(R.id.textItem))
				.setText(getWord(section));
		return layout;
	}

	public String getWord(int code) {

		String word = "";
		switch (code) {
		case 0:
			word = "A";
			break;
		case 1:
			word = "B";
			break;
		case 2:
			word = "C";
			break;
		case 3:
			word = "D";
			break;
		case 4:
			word = "E";
			break;
		case 5:
			word = "F";
			break;
		case 6:
			word = "G";
			break;
			
		case 7:
			word = "H";
			break;
		case 8:
			word = "I";
			break;
		case 9:
			word = "J";
			break;
		case 10:
			word = "K";
			break;
		case 11:
			word = "L";
			break;
		case 12:
			word = "M";
			break;
		case 13:
			word = "N";
			break;
			
		case 14:
			word = "O";
			break;
		case 15:
			word = "P";
			break;
		case 16:
			word = "Q";
			break;
		case 17:
			word = "R";
			break;
		case 18:
			word = "S";
			break;
		case 19:
			word = "T";
			break;
		case 20:
			word = "U";
			break;
			
		case 21:
			word = "V";
			break;
		case 22:
			word = "W";
			break;
		case 23:
			word = "X";
			break;
		case 24:
			word = "Y";
			break;
		case 25:
			word = "Z";
			break;

		default:
			break;
		}
		return word;

	}

}
