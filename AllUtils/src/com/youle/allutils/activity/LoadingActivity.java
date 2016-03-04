package com.youle.allutils.activity;

import java.util.Timer;
import java.util.TimerTask;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.youle.allutils.R;
import com.youle.allutils.util.AppPreferences;
import com.youle.allutils.util.AppPreferences.PreferenceKey;
import com.youle.allutils.util.VersionUtil;

public class LoadingActivity extends BaseActivity {

	@ViewInject(R.id.loading_iv_ad)
	ImageView loading_iv_ad;

	// 透明度补间动画
	private Animation layoutAnimation;

	@Override
	protected void initParams() {
		layoutAnimation = AnimationUtils.loadAnimation(this,
				R.anim.alpha_loading);
		// 绑定动画监听器
		//layoutAnimation.setAnimationListener(new AlphaLayoutListener());

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				SharedPreferences preferences = getSharedPreferences(
						"local_kv", MODE_PRIVATE);
				Editor editor = preferences.edit();
				boolean tag = preferences.getBoolean("isFirst", true);

				if (tag) {
					GuideActivity.startActivity(LoadingActivity.this);

					editor.putBoolean("isFirst", false).commit();

				}

				else {
					MainActivity.startActivity(LoadingActivity.this);
				}
				finish();

				
			}
		}, 3000);
	}

	// 动画监听器
	class AlphaLayoutListener implements AnimationListener {

		@Override
		public void onAnimationStart(Animation animation) {

		}

		@Override
		public void onAnimationEnd(Animation animation) {
			loading_iv_ad.postDelayed(new Runnable() {

				@Override
				public void run() {

					// 得到启动时的版本号
					String start_version = AppPreferences.instance().getString(
							PreferenceKey.START_VERSION);
					// Log.i("###", start_version);
					if (!start_version.equals(VersionUtil.getAppVersionName())) {

						GuideActivity.startActivity(LoadingActivity.this);
						AppPreferences.instance().putString(
								PreferenceKey.START_VERSION,
								VersionUtil.getAppVersionName());

					} else {
						MainActivity.startActivity(LoadingActivity.this);
					}
					finish();
				}
			}, 2000);
		}

		@Override
		public void onAnimationRepeat(Animation animation) {

		}
	}

	@Override
	protected int getLayoutID() {
		// TODO Auto-generated method stub
		return R.layout.loading_main;
	}

}
