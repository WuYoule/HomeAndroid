package com.youle.allutils.fragment;

import java.util.ArrayList;
import java.util.List;

import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnChildClick;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.youle.allutils.R;
import com.youle.allutils.adapter.FindFilterViewPagerAdapter;

//精准找车
public class FindFilterFragment extends BaseFragment {

	// 已选条件栏
	@ViewInject(R.id.find_filter_hscv_choosed)
	HorizontalScrollView find_filter_hscv_choosed;

	@ViewInject(R.id.find_filter_llyt_choosed)
	LinearLayout find_filter_llyt_choosed;

	// 价格
	@ViewInject(R.id.find_filter_tv_price)
	TextView find_filter_tv_price;

	// 级别
	@ViewInject(R.id.find_filter_tv_level)
	TextView find_filter_tv_level;

	// 排量
	@ViewInject(R.id.find_filter_tv_pai)
	TextView find_filter_tv_pai;

	// 变速箱
	@ViewInject(R.id.find_filter_tv_bian)
	TextView find_filter_tv_bian;

	// 筛选条件的viewpager
	@ViewInject(R.id.fragment_find_filter_vp)
	ViewPager find_filter_vp;

	// 游标
	@ViewInject(R.id.fragment_find_filter_iv_cursor)
	ImageView find_filter_iv_cursor;

	// 价格选项
	private Button fragment_find_price_btn_price1;
	private Button fragment_find_price_btn_price2;
	private Button fragment_find_price_btn_price3;

	// 级别
	private Button fragment_find_price_btn_level1;
	private Button fragment_find_price_btn_level2;
	private Button fragment_find_price_btn_level3;

	// 排量
	private Button fragment_find_price_btn_pai1;
	private Button fragment_find_price_btn_pai2;
	private Button fragment_find_price_btn_pai3;

	// 变速箱
	private Button fragment_find_price_btn_bian1;
	private Button fragment_find_price_btn_bian2;
	private Button fragment_find_price_btn_bian3;

	// viewpager的 数据源
	private List<View> viewList = new ArrayList<>();
	// 当前选中项
	private int currIndex = 0;
	// 图片居中移位
	private int offset;
	// 游标图片宽度
	private int bmpw;

	// 价格是否已经筛选
	private boolean priceAdd = false;
	// 级别是否已经筛选
	private boolean levelAdd = false;
	// 排量是否已经筛选
	private boolean paiAdd = false;
	// 变速器是否已经筛选
	private boolean bianAdd = false;

	@Override
	protected int getLayoutId() {
		return R.layout.fragment_find_filter_main;
	}

	@Override
	protected void initParams() {
		// 游标初始化
		initCursor();
		// 页卡初始化
		initViewPager();

	}

	private void initCursor() {
		// 得到游标图片的宽度
		bmpw = BitmapFactory.decodeResource(getResources(), R.drawable.select)
				.getWidth();

		// 得到屏幕的宽度
		DisplayMetrics dm = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenW = dm.widthPixels;

		// 计算图片居中需要的位移
		offset = (screenW / 4 - bmpw) / 2;
		// 设置初始化位置
		Matrix matrix = new Matrix();
		matrix.postTranslate(offset, 0);
		find_filter_iv_cursor.setImageMatrix(matrix);

	}

	// 视图切换监听器
	public class DefineOnPageChangeListener implements OnPageChangeListener {

		// 游标移动一个单位的长度
		int one = offset * 2 + bmpw;

		// 两个
		int two = one * 2;
		// 三个
		int three = one * 3;

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageSelected(int arg0) {
			Animation animation = null;
			switch (arg0) {
			case 0:// 选中价格

				// 如果位于级别
				if (currIndex == 1) {
					animation = new TranslateAnimation(one, 0, 0, 0);
				}
				// 如果位于排量
				else if (currIndex == 2) {
					animation = new TranslateAnimation(two, 0, 0, 0);
				}
				// 如果位于变速器
				else if (currIndex == 3) {
					animation = new TranslateAnimation(three, 0, 0, 0);
				}
				break;
			case 1:// 选中级别

				// 如果位于价格
				if (currIndex == 0) {
					animation = new TranslateAnimation(0, one, 0, 0);
				}
				// 如果位于排量
				else if (currIndex == 2) {
					animation = new TranslateAnimation(two, one, 0, 0);
				}
				// 如果位于变速器
				else if (currIndex == 3) {
					animation = new TranslateAnimation(three, one, 0, 0);
				}
				break;
			case 2:// 选中排量
					// 如果位于价格
				if (currIndex == 0) {
					animation = new TranslateAnimation(0, two, 0, 0);
				}
				// 如果位于级别
				else if (currIndex == 1) {
					animation = new TranslateAnimation(one, two, 0, 0);
				}
				// 如果位于变速器
				else if (currIndex == 3) {
					animation = new TranslateAnimation(three, two, 0, 0);
				}
				break;
			case 3:// 选中变速器
					// 如果位于价格
				if (currIndex == 0) {
					animation = new TranslateAnimation(0, three, 0, 0);
				}
				// 如果位于级别
				else if (currIndex == 1) {
					animation = new TranslateAnimation(one, three, 0, 0);
				}
				// 如果位于排量
				else if (currIndex == 2) {
					animation = new TranslateAnimation(two, three, 0, 0);
				}
				break;
			default:
				break;
			}
			currIndex = arg0;
			// 切换页卡动画
			animation.setFillAfter(true);
			animation.setDuration(300);
			find_filter_iv_cursor.startAnimation(animation);
		}
	}

	private void initViewPager() {
		// 切换的四个界面的初始化
		LinearLayout price_layout = (LinearLayout) getLayoutInflater(null)
				.inflate(R.layout.fragment_find_filter_price, null);
		LinearLayout level_layout = (LinearLayout) getLayoutInflater(null)
				.inflate(R.layout.fragment_find_filter_level, null);
		LinearLayout pai_layout = (LinearLayout) getLayoutInflater(null)
				.inflate(R.layout.fragment_find_filter_pai, null);
		LinearLayout bian_layout = (LinearLayout) getLayoutInflater(null)
				.inflate(R.layout.fragment_find_filter_bian, null);

		// 初始化价格布局
		initPriceLayout(price_layout);

		// 初始化级别布局
		initLevelLayout(level_layout);

		// 初始化排量布局
		initPaiLayout(pai_layout);
		// 初始化变速箱布局
		initBianLayout(bian_layout);

		// 将四个界面的视图添加viewpager的数据源中
		viewList.add(price_layout);
		viewList.add(level_layout);
		viewList.add(pai_layout);
		viewList.add(bian_layout);

		find_filter_vp.setAdapter(new FindFilterViewPagerAdapter(viewList));
		find_filter_vp.setCurrentItem(currIndex);

		find_filter_vp
				.setOnPageChangeListener(new DefineOnPageChangeListener());

	}

	@OnClick({ R.id.find_filter_tv_price, R.id.find_filter_tv_level,
			R.id.find_filter_tv_bian, R.id.find_filter_tv_pai })
	public void viewOnClick(View view) {
		switch (view.getId()) {
		case R.id.find_filter_tv_price:
			find_filter_vp.setCurrentItem(0);

			break;
		case R.id.find_filter_tv_level:
			find_filter_vp.setCurrentItem(1);
			break;

		case R.id.find_filter_tv_pai:
			find_filter_vp.setCurrentItem(2);
			break;
		case R.id.find_filter_tv_bian:
			find_filter_vp.setCurrentItem(3);
			break;

		default:
			break;
		}
	}

	private void initBianLayout(LinearLayout bian_layout) {
		fragment_find_price_btn_bian1 = (Button) bian_layout
				.findViewById(R.id.fragment_find_bian_btn_bian1);
		fragment_find_price_btn_bian2 = (Button) bian_layout
				.findViewById(R.id.fragment_find_bian_btn_bian2);
		fragment_find_price_btn_bian3 = (Button) bian_layout
				.findViewById(R.id.fragment_find_bian_btn_bian3);

		fragment_find_price_btn_bian1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!bianAdd) {
					bianAdd = true;
					addChoosedItem("bian",
							fragment_find_price_btn_bian1.getText());
				} else {
					changeChoosedItem("bian",
							fragment_find_price_btn_bian1.getText());
				}
				fragment_find_price_btn_bian1.setEnabled(false);
				fragment_find_price_btn_bian2.setEnabled(true);
				fragment_find_price_btn_bian3.setEnabled(true);
				changeButtonBg("bian", 1);

			}
		});

		fragment_find_price_btn_bian2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!bianAdd) {
					bianAdd = true;
					addChoosedItem("bian",
							fragment_find_price_btn_bian2.getText());
				} else {
					changeChoosedItem("bian",
							fragment_find_price_btn_bian2.getText());
				}
				fragment_find_price_btn_bian1.setEnabled(true);
				fragment_find_price_btn_bian2.setEnabled(false);
				fragment_find_price_btn_bian3.setEnabled(true);
				changeButtonBg("bian", 2);
			}
		});

		fragment_find_price_btn_bian3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!bianAdd) {
					bianAdd = true;
					addChoosedItem("bian",
							fragment_find_price_btn_bian3.getText());
				} else {
					changeChoosedItem("bian",
							fragment_find_price_btn_bian3.getText());
				}
				fragment_find_price_btn_bian1.setEnabled(true);
				fragment_find_price_btn_bian2.setEnabled(true);
				fragment_find_price_btn_bian3.setEnabled(false);
				changeButtonBg("bian", 3);
			}
		});

	}

	private void initPaiLayout(LinearLayout pai_layout) {
		fragment_find_price_btn_pai1 = (Button) pai_layout
				.findViewById(R.id.fragment_find_pai_btn_pai1);
		fragment_find_price_btn_pai2 = (Button) pai_layout
				.findViewById(R.id.fragment_find_pai_btn_pai2);
		fragment_find_price_btn_pai3 = (Button) pai_layout
				.findViewById(R.id.fragment_find_pai_btn_pai3);

		fragment_find_price_btn_pai1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!paiAdd) {
					paiAdd = true;
					addChoosedItem("pai",
							fragment_find_price_btn_pai1.getText());
				} else {
					changeChoosedItem("pai",
							fragment_find_price_btn_pai1.getText());
				}
				fragment_find_price_btn_pai1.setEnabled(false);
				fragment_find_price_btn_pai2.setEnabled(true);
				fragment_find_price_btn_pai3.setEnabled(true);
				changeButtonBg("pai", 1);
			}
		});

		fragment_find_price_btn_pai2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!paiAdd) {
					paiAdd = true;
					addChoosedItem("pai",
							fragment_find_price_btn_pai2.getText());
				} else {
					changeChoosedItem("pai",
							fragment_find_price_btn_pai2.getText());
				}
				fragment_find_price_btn_pai1.setEnabled(true);
				fragment_find_price_btn_pai2.setEnabled(false);
				fragment_find_price_btn_pai3.setEnabled(true);
				changeButtonBg("pai", 2);
			}
		});

		fragment_find_price_btn_pai3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!paiAdd) {
					paiAdd = true;
					addChoosedItem("pai",
							fragment_find_price_btn_pai3.getText());
				} else {
					changeChoosedItem("pai",
							fragment_find_price_btn_pai3.getText());
				}
				fragment_find_price_btn_pai1.setEnabled(true);
				fragment_find_price_btn_pai2.setEnabled(true);
				fragment_find_price_btn_pai3.setEnabled(false);
				changeButtonBg("pai", 3);
			}
		});

	}

	private void initLevelLayout(LinearLayout level_layout) {
		fragment_find_price_btn_level1 = (Button) level_layout
				.findViewById(R.id.fragment_find_level_btn_level1);
		fragment_find_price_btn_level2 = (Button) level_layout
				.findViewById(R.id.fragment_find_level_btn_level2);
		fragment_find_price_btn_level3 = (Button) level_layout
				.findViewById(R.id.fragment_find_level_btn_level3);

		fragment_find_price_btn_level1
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						if (!levelAdd) {
							levelAdd = true;
							addChoosedItem("level",
									fragment_find_price_btn_level1.getText());
						} else {
							changeChoosedItem("level",
									fragment_find_price_btn_level1.getText());
						}
						fragment_find_price_btn_level1.setEnabled(false);
						fragment_find_price_btn_level2.setEnabled(true);
						fragment_find_price_btn_level3.setEnabled(true);
						changeButtonBg("level", 1);
					}
				});

		fragment_find_price_btn_level2
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						if (!levelAdd) {
							levelAdd = true;
							addChoosedItem("level",
									fragment_find_price_btn_level2.getText());
						} else {
							changeChoosedItem("level",
									fragment_find_price_btn_level2.getText());
						}
						fragment_find_price_btn_level1.setEnabled(true);
						fragment_find_price_btn_level2.setEnabled(false);
						fragment_find_price_btn_level3.setEnabled(true);
						changeButtonBg("level", 2);
					}
				});

		fragment_find_price_btn_level3
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						if (!levelAdd) {
							levelAdd = true;
							addChoosedItem("level",
									fragment_find_price_btn_level3.getText());
						} else {
							changeChoosedItem("level",
									fragment_find_price_btn_level3.getText());
						}
						fragment_find_price_btn_level1.setEnabled(true);
						fragment_find_price_btn_level2.setEnabled(true);
						fragment_find_price_btn_level3.setEnabled(false);
						changeButtonBg("level", 3);
					}
				});
	}

	private void initPriceLayout(LinearLayout price_layout) {
		fragment_find_price_btn_price1 = (Button) price_layout
				.findViewById(R.id.fragment_find_price_btn_price1);
		fragment_find_price_btn_price2 = (Button) price_layout
				.findViewById(R.id.fragment_find_price_btn_price2);
		fragment_find_price_btn_price3 = (Button) price_layout
				.findViewById(R.id.fragment_find_price_btn_price3);

		fragment_find_price_btn_price1
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						if (!priceAdd) {
							priceAdd = true;
							addChoosedItem("price",
									fragment_find_price_btn_price1.getText());
						} else {
							changeChoosedItem("price",
									fragment_find_price_btn_price1.getText());
						}
						fragment_find_price_btn_price1.setEnabled(false);
						fragment_find_price_btn_price2.setEnabled(true);
						fragment_find_price_btn_price3.setEnabled(true);
						changeButtonBg("price", 1);

					}
				});

		fragment_find_price_btn_price2
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						if (!priceAdd) {
							priceAdd = true;
							addChoosedItem("price",
									fragment_find_price_btn_price2.getText());
						} else {
							changeChoosedItem("price",
									fragment_find_price_btn_price2.getText());
						}
						fragment_find_price_btn_price1.setEnabled(true);
						fragment_find_price_btn_price2.setEnabled(false);
						fragment_find_price_btn_price3.setEnabled(true);
						changeButtonBg("price", 2);
					}
				});

		fragment_find_price_btn_price3
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						if (!priceAdd) {
							priceAdd = true;
							addChoosedItem("price",
									fragment_find_price_btn_price3.getText());
						} else {
							changeChoosedItem("price",
									fragment_find_price_btn_price3.getText());
						}
						fragment_find_price_btn_price1.setEnabled(true);
						fragment_find_price_btn_price2.setEnabled(true);
						fragment_find_price_btn_price3.setEnabled(false);
						changeButtonBg("price", 3);
					}
				});
	}

	// 添加条件
	private void addChoosedItem(final String tag, CharSequence text) {
		final LinearLayout choosedItem = (LinearLayout) getLayoutInflater(null)
				.inflate(R.layout.fragment_find_filter_add_main, null);

		LinearLayout fragment_find_filter_add_llyt = (LinearLayout) choosedItem
				.findViewById(R.id.fragment_find_filter_add_llyt);

		TextView fragment_find_filter_add_txt = (TextView) choosedItem
				.findViewById(R.id.fragment_find_filter_add_txt);

		fragment_find_filter_add_llyt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 清空价格条件
				if (tag.equals("price")) {
					priceAdd = false;
					clearPrice();
				}
				// 清空级别条件
				else if (tag.equals("level")) {
					levelAdd = false;
					clearLevel();
				}
				// 清空排量条件
				else if (tag.equals("pai")) {
					paiAdd = false;
					clearPai();
				}
				// 清空变速器条件
				else if (tag.equals("bian")) {
					bianAdd = false;
					clearBian();
				}
				find_filter_llyt_choosed.removeView(choosedItem);
			}

		});
		fragment_find_filter_add_txt.setText(text);
		choosedItem.setTag(tag);

		find_filter_llyt_choosed.addView(choosedItem);
		// 动态滚动已选条件栏
		find_filter_hscv_choosed.post(new Runnable() {

			@Override
			public void run() {
				find_filter_hscv_choosed.smoothScrollTo(
						find_filter_hscv_choosed.getWidth(), 0);

			}
		});
	}

	protected void clearBian() {

		fragment_find_price_btn_bian1.setEnabled(true);
		fragment_find_price_btn_bian2.setEnabled(true);
		fragment_find_price_btn_bian3.setEnabled(true);
	}

	protected void clearPai() {
		fragment_find_price_btn_pai1.setEnabled(true);
		fragment_find_price_btn_pai2.setEnabled(true);
		fragment_find_price_btn_pai3.setEnabled(true);

	}

	protected void clearLevel() {
		fragment_find_price_btn_level1.setEnabled(true);
		fragment_find_price_btn_level2.setEnabled(true);
		fragment_find_price_btn_level3.setEnabled(true);

	}

	protected void clearPrice() {
		fragment_find_price_btn_price1.setEnabled(true);
		fragment_find_price_btn_price2.setEnabled(true);
		fragment_find_price_btn_price3.setEnabled(true);

	}

	public void changeChoosedItem(String tag, CharSequence text) {
		for (int i = 0; i < find_filter_llyt_choosed.getChildCount(); i++) {
			LinearLayout tmp = (LinearLayout) find_filter_llyt_choosed
					.getChildAt(i);
			String tmpTag = (String) tmp.getTag();
			if (tag.equals(tmpTag)) {
				find_filter_llyt_choosed.removeView(tmp);
				addChoosedItem(tag, text);
				break;
			}
		}

	}

	@Override
	protected void initParams(View view) {
	}

	public void changeButtonBg(String tag, int index) {
		switch (tag) {
		case "price":
			if (index == 1) {
				fragment_find_price_btn_price1
						.setBackgroundResource(R.drawable.button_selected_bg);
				fragment_find_price_btn_price2
						.setBackgroundResource(R.drawable.button_bg);
				fragment_find_price_btn_price3
						.setBackgroundResource(R.drawable.button_bg);

			}
			if (index == 2) {
				fragment_find_price_btn_price1
						.setBackgroundResource(R.drawable.button_bg);
				fragment_find_price_btn_price2
						.setBackgroundResource(R.drawable.button_selected_bg);
				fragment_find_price_btn_price3
						.setBackgroundResource(R.drawable.button_bg);

			}
			if (index == 3) {
				fragment_find_price_btn_price1
						.setBackgroundResource(R.drawable.button_bg);
				fragment_find_price_btn_price2
						.setBackgroundResource(R.drawable.button_bg);
				fragment_find_price_btn_price3
						.setBackgroundResource(R.drawable.button_selected_bg);

			}

			break;
		case "level":
			if (index == 1) {
				fragment_find_price_btn_level1
						.setBackgroundResource(R.drawable.button_selected_bg);
				fragment_find_price_btn_level2
						.setBackgroundResource(R.drawable.button_bg);
				fragment_find_price_btn_level3
						.setBackgroundResource(R.drawable.button_bg);

			}
			if (index == 2) {
				fragment_find_price_btn_level1
						.setBackgroundResource(R.drawable.button_bg);
				fragment_find_price_btn_level2
						.setBackgroundResource(R.drawable.button_selected_bg);
				fragment_find_price_btn_level3
						.setBackgroundResource(R.drawable.button_bg);

			}
			if (index == 3) {
				fragment_find_price_btn_level1
						.setBackgroundResource(R.drawable.button_bg);
				fragment_find_price_btn_level2
						.setBackgroundResource(R.drawable.button_bg);
				fragment_find_price_btn_level3
						.setBackgroundResource(R.drawable.button_selected_bg);

			}
			break;
			
		case "pai":
			if (index == 1) {
				fragment_find_price_btn_pai1
						.setBackgroundResource(R.drawable.button_selected_bg);
				fragment_find_price_btn_pai2
						.setBackgroundResource(R.drawable.button_bg);
				fragment_find_price_btn_pai3
						.setBackgroundResource(R.drawable.button_bg);

			}
			if (index == 2) {
				fragment_find_price_btn_pai1
						.setBackgroundResource(R.drawable.button_bg);
				fragment_find_price_btn_pai2
						.setBackgroundResource(R.drawable.button_selected_bg);
				fragment_find_price_btn_pai3
						.setBackgroundResource(R.drawable.button_bg);

			}
			if (index == 3) {
				fragment_find_price_btn_pai1
						.setBackgroundResource(R.drawable.button_bg);
				fragment_find_price_btn_pai2
						.setBackgroundResource(R.drawable.button_bg);
				fragment_find_price_btn_pai3
						.setBackgroundResource(R.drawable.button_selected_bg);

			}
			break;
		case "bian":
			if (index == 1) {
				fragment_find_price_btn_bian1
						.setBackgroundResource(R.drawable.button_selected_bg);
				fragment_find_price_btn_bian2
						.setBackgroundResource(R.drawable.button_bg);
				fragment_find_price_btn_bian3
						.setBackgroundResource(R.drawable.button_bg);

			}
			if (index == 2) {
				fragment_find_price_btn_bian1
						.setBackgroundResource(R.drawable.button_bg);
				fragment_find_price_btn_bian2
						.setBackgroundResource(R.drawable.button_selected_bg);
				fragment_find_price_btn_bian3
						.setBackgroundResource(R.drawable.button_bg);

			}
			if (index == 3) {
				fragment_find_price_btn_bian1
						.setBackgroundResource(R.drawable.button_bg);
				fragment_find_price_btn_bian2
						.setBackgroundResource(R.drawable.button_bg);
				fragment_find_price_btn_bian3
						.setBackgroundResource(R.drawable.button_selected_bg);

			}
			break;

		default:
			break;
		}

	}

}
