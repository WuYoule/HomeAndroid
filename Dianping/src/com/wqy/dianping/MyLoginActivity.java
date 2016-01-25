package com.wqy.dianping;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.wqy.consts.CONSTS;
import com.wqy.dianping.utils.SharedUtils;
import com.wqy.entity.Goods;
import com.wqy.entity.ResponseObject;
import com.wqy.entity.User;
import com.wqy.utils.MyUtils;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MyLoginActivity extends Activity implements OnClickListener {
	@ViewInject(R.id.login_back)
	private ImageView back;

	@ViewInject(R.id.login_register)
	private TextView register;

	@ViewInject(R.id.login_uname)
	private EditText uname;

	@ViewInject(R.id.login_pwd)
	private EditText pwd;

	@ViewInject(R.id.login_check_random)
	private Button login_check_random;

	@ViewInject(R.id.login_num)
	private TextView num;

	@ViewInject(R.id.login_qq)
	private TextView loginqq;

	@ViewInject(R.id.login_weixin)
	private TextView loginweixin;

	@ViewInject(R.id.login)
	private Button login;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_login_act);
		ViewUtils.inject(this);

		login_check_random.setOnClickListener(this);
		login.setOnClickListener(this);
		register.setOnClickListener(this);
		loginqq.setOnClickListener(this);
		loginweixin.setOnClickListener(this);

		// ��ʼ����֤��
		setRandomView(login_check_random);

	}

	// �����֤��
	private void setRandomView(Button login_check_random2) {
		login_check_random2.setText(MyUtils.getRandom(4));

	}

	// qq��������½
	public void loginByQQ() {

	}

	// ΢�ŵ�������½
	public void loginByWeixin() {

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.login_check_random:// ��ȡ��֤��
			setRandomView(login_check_random);
			break;
		case R.id.login:// ��½
			handlerLogin();
			break;
		case R.id.login_register:// ע��
			startActivity(new Intent(MyLoginActivity.this, RegisterActivity.class));
			break;
		case R.id.login_qq:// qq��½
			loginByQQ();
			break;
		case R.id.login_weixin:// ΢�ŵ�½
			loginByWeixin();
			break;
		default:
			break;
		}

	}

	private void handlerLogin() {
		final String name = uname.getText().toString();
		String pass = pwd.getText().toString();
		new HttpUtils().send(HttpMethod.GET, CONSTS.Login_URI + "&uname="
				+ name + "&upwd=" + pass, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				Toast.makeText(getApplication(), "��½ʧ��", Toast.LENGTH_SHORT)
						.show();

			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				Type listType = new TypeToken<ResponseObject<User>>() {
				}.getType();
				ResponseObject<User> object = new GsonBuilder().create()
						.fromJson(arg0.result, listType);
				Log.i("YOULE", object.getState()+"");
				if (object.getState() == "1") {
					// ��½�ɹ�
					SharedUtils.putLoginName(getApplication(), name);
					loginSuccess();
				}
				if (object.getState() == "2") {
					Toast.makeText(getApplication(), "�û������������",
							Toast.LENGTH_SHORT).show();
				}
				if (object.getState() == "0") {
					Toast.makeText(getApplication(), "�û���������Ϊ��Ϊ�գ�",
							Toast.LENGTH_SHORT).show();
				}

			}
		});

	}

	// ��½�ɹ�
	private void loginSuccess() {
		String nameString = uname.getText().toString();

		Intent intent = new Intent(this, MainActivity.class);
		intent.putExtra("login_name", nameString);
		setResult(1, intent);
		finish();

	}

}
