package com.jingna.shopapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jingna.shopapp.R;
import com.jingna.shopapp.adapter.FragmentMyTuijianAdapter;
import com.jingna.shopapp.adapter.IndexAdapter;
import com.jingna.shopapp.bean.FindOrderStatusNumBean;
import com.jingna.shopapp.bean.GetOneBean;
import com.jingna.shopapp.bean.IndexGoodsBean;
import com.jingna.shopapp.pages.AddressActivity;
import com.jingna.shopapp.pages.EditPayActivity;
import com.jingna.shopapp.pages.EditPhoneNum1Activity;
import com.jingna.shopapp.pages.EditPwdActivity;
import com.jingna.shopapp.pages.FocusActivity;
import com.jingna.shopapp.pages.LookHistoryActivity;
import com.jingna.shopapp.pages.MyOrderActivity;
import com.jingna.shopapp.pages.PersonInformationActivity;
import com.jingna.shopapp.pages.SMSLoginActivity;
import com.jingna.shopapp.pages.ToCommentActivity;
import com.jingna.shopapp.receiver.Logger;
import com.jingna.shopapp.util.Const;
import com.jingna.shopapp.util.SpUtils;
import com.jingna.shopapp.util.StatusBarUtils;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import q.rorbin.badgeview.QBadgeView;

/**
 * Created by Administrator on 2019/2/15.
 */

public class FragmentWode extends Fragment {

    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.ll_num)
    LinearLayout llNum;
    @BindView(R.id.ll_login)
    LinearLayout llLogin;
    @BindView(R.id.tv_goods_num)
    TextView tvGoodsNum;
    @BindView(R.id.tv_shop_num)
    TextView tvShopNum;
    @BindView(R.id.tv_browse_num)
    TextView tvBrowseNum;
    @BindView(R.id.tv_daifukuan_num)
    TextView tvDaifukuanNum;
    @BindView(R.id.tv_daishouhuo_num)
    TextView tvDaishouhuoNum;
    @BindView(R.id.tv_daipingjia_num)
    TextView tvDaipingjiaNum;
    @BindView(R.id.tv_tuikuan_num)
    TextView tvTuikuanNum;
    @BindView(R.id.tv_all_order)
    TextView tvAllOrder;

    private FragmentMyTuijianAdapter adapter;
    private List<IndexGoodsBean.DataBean> mList;

    private String userId = "";
    private String memberid="0";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wode, null);

        userId = SpUtils.getUserId(getContext());
        ButterKnife.bind(this, view);
        initData();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Logger.e("123123", userId);
        userId = SpUtils.getUserId(getContext());
        if(userId.equals("0")){
            tvName.setVisibility(View.GONE);
            llNum.setVisibility(View.GONE);
            llLogin.setVisibility(View.VISIBLE);
            Glide.with(getContext()).load(R.mipmap.weidenglu_avatar).into(ivAvatar);
        }else {
            tvName.setVisibility(View.VISIBLE);
            llNum.setVisibility(View.VISIBLE);
            llLogin.setVisibility(View.GONE);
        }
        if(!userId.equals("0")){
            String url = "/MemUser/getOne?id="+userId;
            ViseHttp.GET(url)
                    .request(new ACallback<String>() {
                        @Override
                        public void onSuccess(String data) {
                            try {
                                Logger.e("123123", data);
                                JSONObject jsonObject = new JSONObject(data);
                                if(jsonObject.optString("status").equals("200")){
                                    Gson gson = new Gson();
                                    GetOneBean bean = gson.fromJson(data, GetOneBean.class);
                                    Glide.with(getContext()).load(Const.BASE_URL+bean.getData().getMemberUserInfo().getHeadPhoto()).into(ivAvatar);
                                    tvName.setText(bean.getData().getMemberUserInfo().getMemName());
                                    tvGoodsNum.setText(bean.getData().getGoodsNum()+"");
                                    tvShopNum.setText(bean.getData().getSellerNum()+"");
                                    tvBrowseNum.setText(bean.getData().getBrowseRecord()+"");
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFail(int errCode, String errMsg) {

                        }
                    });

            ViseHttp.GET("/MemUser/findOrderStatusNum")
                    .addParam("memberId", userId)
                    .request(new ACallback<String>() {
                        @Override
                        public void onSuccess(String data) {
                            try {
                                JSONObject jsonObject = new JSONObject(data);
                                if(jsonObject.optString("status").equals("200")){
                                    Gson gson = new Gson();
                                    FindOrderStatusNumBean numBean = gson.fromJson(data, FindOrderStatusNumBean.class);
                                    new QBadgeView(getContext())
                                            .bindTarget(tvDaifukuanNum)
                                            .setShowShadow(false)
                                            .setBadgeGravity(Gravity.START|Gravity.BOTTOM)
                                            .setBadgeTextSize(10, true)
                                            .setBadgeNumber(numBean.getData().getUnpaidNum());
                                    new QBadgeView(getContext())
                                            .bindTarget(tvDaishouhuoNum)
                                            .setShowShadow(false)
                                            .setBadgeGravity(Gravity.START|Gravity.BOTTOM)
                                            .setBadgeTextSize(10, true)
                                            .setBadgeNumber(numBean.getData().getShippedNum());
                                    new QBadgeView(getContext())
                                            .bindTarget(tvDaipingjiaNum)
                                            .setShowShadow(false)
                                            .setBadgeGravity(Gravity.START|Gravity.BOTTOM)
                                            .setBadgeTextSize(10, true)
                                            .setBadgeNumber(numBean.getData().getNotEvaluatedNum());
                                    new QBadgeView(getContext())
                                            .bindTarget(tvTuikuanNum)
                                            .setShowShadow(false)
                                            .setBadgeGravity(Gravity.START|Gravity.BOTTOM)
                                            .setBadgeTextSize(10, true)
                                            .setBadgeNumber(numBean.getData().getCancelledNum());
                                    new QBadgeView(getContext())
                                            .bindTarget(tvAllOrder)
                                            .setShowShadow(false)
                                            .setBadgeGravity(Gravity.START|Gravity.BOTTOM)
                                            .setBadgeTextSize(10, true)
                                            .setBadgeNumber(numBean.getData().getAccomplishedNum());
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

    private void initData() {

        mList = new ArrayList<>();
        if( !SpUtils.getUserId(getContext()).equals("0")){
            memberid = SpUtils.getUserId(getContext());
        }
        ViseHttp.GET("IndexPageApi/queryRecommandStatusGoods")
                .addParam("memberId", memberid)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            Logger.e("12345", data);
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optString("status").equals("200")){
                                Gson gson = new Gson();
                                IndexGoodsBean bean = gson.fromJson(data, IndexGoodsBean.class);
                                mList.clear();
                                mList.addAll(bean.getData());
                                adapter = new FragmentMyTuijianAdapter(mList);
                                GridLayoutManager manager = new GridLayoutManager(getContext(), 2){
                                    @Override
                                    public boolean canScrollVertically() {
                                        return false;
                                    }
                                };
                                recyclerView.setLayoutManager(manager);
                                recyclerView.setAdapter(adapter);
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

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(hidden){

        }else {
            StatusBarUtils.setStatusBarTransparent(getActivity());
        }
    }

    @OnClick({R.id.rl1, R.id.rl2, R.id.rl3, R.id.rl4, R.id.rl5, R.id.iv_avatar, R.id.ll_login, R.id.ll_my_order, R.id.ll_daifukuan,
    R.id.ll_daishouhuo, R.id.ll_comment, R.id.ll_tuikuan, R.id.ll_goods_focus, R.id.ll_shop_focus, R.id.ll_look_history})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl1:
                if(userId.equals("0")){
                    intent.setClass(getContext(), SMSLoginActivity.class);
                    startActivity(intent);
                }else {
                    intent.setClass(getContext(), AddressActivity.class);
                    intent.putExtra("type", "wode");
                    startActivity(intent);
                }
                break;
            case R.id.rl2:
                if(userId.equals("0")){
                    intent.setClass(getContext(), SMSLoginActivity.class);
                    startActivity(intent);
                }else {
                    intent.setClass(getContext(), EditPwdActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.rl3:
                if(userId.equals("0")){
                    intent.setClass(getContext(), SMSLoginActivity.class);
                    startActivity(intent);
                }else {
                    intent.setClass(getContext(), EditPhoneNum1Activity.class);
                    startActivity(intent);
                }
                break;
            case R.id.rl4:
                if(userId.equals("0")){
                    intent.setClass(getContext(), SMSLoginActivity.class);
                    startActivity(intent);
                }else {
                    intent.setClass(getContext(), EditPayActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.rl5:
//                if(userId.equals("0")){
//                    intent.setClass(getContext(), SMSLoginActivity.class);
//                    startActivity(intent);
//                }else {
//                    SpUtils.clear(getContext());
//                    onStart();
//                }
                break;
            case R.id.iv_avatar:
                if(userId.equals("0")){
                    intent.setClass(getContext(), SMSLoginActivity.class);
                    startActivity(intent);
                }else {
                    intent.setClass(getContext(), PersonInformationActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.ll_login:
                intent.setClass(getContext(), SMSLoginActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_my_order:
                if(userId.equals("0")){
                    intent.setClass(getContext(), SMSLoginActivity.class);
                    startActivity(intent);
                }else {
                    intent.setClass(getContext(), MyOrderActivity.class);
                    intent.putExtra("index", 0);
                    startActivity(intent);
                }
                break;
            case R.id.ll_daifukuan:
                if(userId.equals("0")){
                    intent.setClass(getContext(), SMSLoginActivity.class);
                    startActivity(intent);
                }else {
                    intent.setClass(getContext(), MyOrderActivity.class);
                    intent.putExtra("index", 1);
                    startActivity(intent);
                }
                break;
            case R.id.ll_daishouhuo:
                if(userId.equals("0")){
                    intent.setClass(getContext(), SMSLoginActivity.class);
                    startActivity(intent);
                }else {
                    intent.setClass(getContext(), MyOrderActivity.class);
                    intent.putExtra("index", 2);
                    startActivity(intent);
                }
                break;
            case R.id.ll_comment:
                if(userId.equals("0")){
                    intent.setClass(getContext(), SMSLoginActivity.class);
                    startActivity(intent);
                }else {
                    intent.setClass(getContext(), ToCommentActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.ll_tuikuan:
                if(userId.equals("0")){
                    intent.setClass(getContext(), SMSLoginActivity.class);
                    startActivity(intent);
                }else {
                    intent.setClass(getContext(), MyOrderActivity.class);
                    intent.putExtra("index", 5);
                    startActivity(intent);
                }
                break;
            case R.id.ll_goods_focus:
                if(userId.equals("0")){
                    intent.setClass(getContext(), SMSLoginActivity.class);
                    startActivity(intent);
                }else {
                    intent.setClass(getContext(), FocusActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.ll_shop_focus:
                if(userId.equals("0")){
                    intent.setClass(getContext(), SMSLoginActivity.class);
                    startActivity(intent);
                }else {
                    intent.setClass(getContext(), FocusActivity.class);
                    intent.putExtra("index", 1);
                    startActivity(intent);
                }
                break;
            case R.id.ll_look_history:
                if(userId.equals("0")){
                    intent.setClass(getContext(), SMSLoginActivity.class);
                    startActivity(intent);
                }else {
                    intent.setClass(getContext(), LookHistoryActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }

}
