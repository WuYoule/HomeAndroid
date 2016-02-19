package com.youle.allutils.view;

import com.youle.allutils.R;
import com.youle.allutils.R.id;
import com.youle.allutils.adapter.PinnedSectionedAdapter;
import com.youle.allutils.view.photoview.log.LogManager;

import za.co.immedia.pinnedheaderlistview.PinnedHeaderListView;
import za.co.immedia.pinnedheaderlistview.SectionedBaseAdapter;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;

public class SideBar extends View {

	// ��ĸ��
	private char[] alphabet;
	// �б�
	private PinnedHeaderListView listView;
	private SectionIndexer sectionIndexer;
	
	
	
	private ListAdapter lAdapter;
	// ��ʾ�Ի���
	private TextView dialogText;
	
	int idx;
	
	public SideBar(Context context) {
		super(context);
		init();
	}
	public SideBar(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	public SideBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	// ��ʼ����ĸ��
	private void init() {
		
		alphabet = new char[] {
		 '#','A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
        'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
	}
	public void setListView(PinnedHeaderListView listView){
		this.listView=listView;
		 
//	   sectionIndexer=(SectionIndexer) listView.getAdapter();
	   
	}
		public void setTextView(TextView dialogText){
		this.dialogText=dialogText;
	}
	
//	private onTouchingLetterListener onLetterListener;
//	public interface onTouchingLetterListener{
//		public void onTouchingLetter(String str);
//	}
//	public void setOnTouchingLetterListener(onTouchingLetterListener onLetterListener){
//		this.onLetterListener=onLetterListener;
//	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		//�ȵ���ǰ������Yֵ
		int i=(int) event.getY();
		//���㵱ǰ������λ�������ĸ���ĸ
		 idx=i/(getMeasuredHeight()/alphabet.length);
		
		if (idx>=alphabet.length) {
			idx=alphabet.length-1;
		}
		else if(idx<0){
			 idx=0;
		}
		if (event.getAction()==MotionEvent.ACTION_DOWN||event.getAction()==MotionEvent.ACTION_MOVE) {
			//����ʱ������ĸ����
			setBackgroundColor(getResources().getColor(R.color.sidebar_touch));
			//������ʾ��Ϣ
			dialogText.setVisibility(View.VISIBLE);
			dialogText.setText(String.valueOf(idx+"-"+alphabet[idx]));
//			dialogText.setTextSize(TypedValue.COMPLEX_UNIT_SP,34);
			//�б�ѡ�д�����ĸ��Ӧ��
			if (alphabet[idx]=='#') {
				listView.setSelection(0);
				
			}
			else {
//				int position=sectionIndexer.getPositionForSection(alphabet[idx]);
				
				if (idx==-1) {
					return true;
				}
				listView.setSelection(getListViewPosition(alphabet[idx]));
			}
		}
		else {
//			//�ӳ�һ��Ӱ��
			dialogText.postDelayed(new Runnable() {
				@Override
				public void run() {
				   dialogText.setVisibility(View.INVISIBLE);
				}
			}, 1500);
		}
		if (event.getAction()==MotionEvent.ACTION_UP) {
			//�ɿ���������ĸ����
			setBackgroundColor(Color.TRANSPARENT);
		}
		return true;
	}
	
	public int getListViewPosition(char work) {
		int p=0;
		switch (work) {
		case '#':
			p = 0;
			break;
		case 'A':
			p = 0;
			break;
		case 'B':
			p = 3;
			break;
		case 'C':
			p = 6;
			break;
		case 'D':
			p = 9;
			break;
		case 'E':
			p = 12;
			break;
		case 'F':
			p = 15;
			break;
		case 'G':
			p = 18;
			break;
		case 'H':
			p = 21;
			break;
		case 'I':
			p = 24;
			break;
		case 'J':
			p = 27;
			break;
		case 'K':
			p = 30;
			break;
		case 'L':
			p = 33;
			break;
		case 'M':
			p = 36;
			break;
		case 'N':
			p = 39;
			break;

		case 'O':
			p = 42;
			break;
		case 'P':
			p = 45;
			break;
		case 'Q':
			p = 48;
			break;
		case 'R':
			p = 51;
			break;
		case 'S':
			p = 54;
			break;
		case 'T':
			p = 57;
			break;

		case 'U':
			p = 60;
			break;
		case 'V':
			p = 63;
			break;
		case 'W':
			p = 66;
			break;

		case 'X':
			p = 69;
			break;
		case 'Y':
			p = 72;
			break;
		case 'Z':
			p = 75;
			break;

		default:
			break;
		}
		return p;
		
		
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		Paint paint=new Paint();
		//������ɫ
		//paint.setColor(getResources().getColor(R.color.sidebar_selected));
		
		paint.setColor(Color.BLUE);
		//�������ֳߴ�
		paint.setTextSize(50);
		//����λ�þ���
		paint.setTextAlign(Paint.Align.CENTER);
		
		//���Ʒ�񣺴���
		Typeface font=Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD);
		paint.setTypeface(font);
		
		float widthCenter=getMeasuredWidth()/2;
		if (alphabet.length>0) {
			float height=getMeasuredHeight()/alphabet.length;
			for (int i = 0; i < alphabet.length; i++) {
				canvas.drawText(String.valueOf(alphabet[i]), widthCenter, height*(i+1), paint);
			}
		}
		this.invalidate();
		super.onDraw(canvas);
		
	}

}
