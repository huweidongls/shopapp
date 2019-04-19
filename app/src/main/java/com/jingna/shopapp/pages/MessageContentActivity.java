package com.jingna.shopapp.pages;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jingna.shopapp.R;
import com.jingna.shopapp.base.BaseActivity;
import com.jingna.shopapp.bean.MessageContentBean;
import com.jingna.shopapp.bean.ShopIndexBean;
import com.jingna.shopapp.util.Const;
import com.jingna.shopapp.util.SpUtils;
import com.jingna.shopapp.util.StatusBarUtils;
import com.jingna.shopapp.util.ToastUtil;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessageContentActivity extends BaseActivity {
    private Context context = MessageContentActivity.this;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.msg_title)
    TextView msg_title;
    @BindView(R.id.mag_time)
    TextView mag_time;
    @BindView(R.id.msg_content)
    TextView msg_content;
    @BindView(R.id.mag_img)
    ImageView mag_img;
    private int typeid=0;//消息ID
    private String TypeName ="";//类型昵称
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        typeid = getIntent().getIntExtra("id",0);//类型ID
        TypeName = getIntent().getStringExtra("TypeName");//类型昵称
        setContentView(R.layout.activity_message_content);
        StatusBarUtils.setStatusBar(MessageContentActivity.this, getResources().getColor(R.color.statusbar_color));
        ButterKnife.bind(MessageContentActivity.this);
        initData();
    }
    private void initData() {
        tv_title.setText(TypeName);
        ViseHttp.GET("MemberPushMsg/getOne")
                .addParam("memberId", SpUtils.getUserId(context))
                .addParam("pushMsgId",typeid+"")
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optString("status").equals("200")){
                                Gson gson = new Gson();
                                MessageContentBean MessageContentBean = gson.fromJson(data,MessageContentBean.class);
                                msg_title.setText(MessageContentBean.getData().getMsgTitle());
                                mag_time.setText(MessageContentBean.getData().getCreateTime());
                                msg_content.setText(MessageContentBean.getData().getMsgContent());
                                Glide.with(context).load(Const.BASE_URL+MessageContentBean.getData().getMsgPic()).into(mag_img);//更换图片
                            }else{
                                ToastUtil.showShort(context, "信息解析错误!");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onFail(int errCode, String errMsg) {

                    }
                });
    }
}
