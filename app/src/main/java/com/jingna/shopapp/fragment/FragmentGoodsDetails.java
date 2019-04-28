package com.jingna.shopapp.fragment;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jingna.shopapp.R;
import com.jingna.shopapp.util.HtmlFromUtils;
import com.jingna.shopapp.util.StringUtils;

import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2019/2/20.
 */

public class FragmentGoodsDetails extends Fragment {

    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.ll1)
    LinearLayout ll1;
    @BindView(R.id.rl2)
    RelativeLayout rl2;
    @BindView(R.id.rl3)
    RelativeLayout rl3;
    @BindView(R.id.tv_rl2)
    TextView tvRl2;

    private String id = "";

    public static FragmentGoodsDetails newInstance(String id) {
        FragmentGoodsDetails newFragment = new FragmentGoodsDetails();
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        newFragment.setArguments(bundle);
        return newFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goods_details, null);
        Bundle args = getArguments();
        if (args != null) {
            id = args.getString("id");
        }
        ButterKnife.bind(this, view);

        initData();

        return view;
    }

    private void initData() {

        String html = "<p><img src=\"https://img.alicdn.com/imgextra/i1/2283496633/TB2r_2LgILJ8KJjy0FnXXcFDpXa_!!2283496633.png\"/><img src=\"https://img.alicdn.com/imgextra/i1/2283496633/TB2p7ueXdLO8KJjSZPcXXaV0FXa_!!2283496633.jpg\"/><img src=\"https://img.alicdn.com/imgextra/i1/2283496633/TB2xV1gXXYM8KJjSZFuXXcf7FXa_!!2283496633.jpg\"/><img src=\"https://img.alicdn.com/imgextra/i1/2283496633/TB2YtGgXfjM8KJjSZFyXXXdzVXa_!!2283496633.jpg\"/><img src=\"https://img.alicdn.com/imgextra/i4/2283496633/TB2Q3bmXgLD8KJjSszeXXaGRpXa_!!2283496633.jpg\" class=\"\" width=\"750\" height=\"750\"/><img src=\"https://img.alicdn.com/imgextra/i4/2283496633/TB2dQrhXlTH8KJjy0FiXXcRsXXa_!!2283496633.jpg\" class=\"\" width=\"750\" height=\"750\"/><img src=\"https://img.alicdn.com/imgextra/i1/2283496633/TB2FKCeXdLO8KJjSZFxXXaGEVXa_!!2283496633.jpg\" class=\"\" width=\"750\" height=\"750\"/><img src=\"https://img.alicdn.com/imgextra/i3/2283496633/TB2_yDmXgvD8KJjy0FlXXagBFXa_!!2283496633.jpg\" class=\"\" width=\"750\" height=\"750\"/><img src=\"https://img.alicdn.com/imgextra/i3/2283496633/TB2_imeXdHO8KJjSZFtXXchfXXa_!!2283496633.jpg\" class=\"\" width=\"750\" height=\"750\"/></p><p>&nbsp;</p>";
        String string="\"<p>据多家美媒消息，五年前的今天，也就是2012年12月6日，湖" +
                "人名宿科比-布莱恩特迎来个人生涯一个非常大的里程碑!他成为NBA历史上" +
                "第5位总得分达到30000分的球员，也成了历史最年轻的30000分先生。</p>" +
                "<p><img src=\"http://p4.qhimg.com/t0143c3caa0f210471d.jpg?size=960" +
                "x960\"\" class=\"mCS_img_loaded\"></p><p>在湖人以103-87战胜黄蜂的比赛" +
                "中，科比出战33分钟，17投10中，得到29分6篮板4助攻。其中，第二节比赛他用" +
                "一记抛投让自己的职业生涯总得分达到30000分。</p><p>科比在这一天是34岁104" +
                "天，超越了张伯伦(35岁179天)成为NBA历史上最年轻的30000分先生。</p><p>" +
                "<img src=\"http://p6.qhimg.com/t018adb2d64db452679.jpg?size=1024x576\"" +
                " class=\"mCS_img_loaded\"></p><p>詹姆斯目前32岁，如无意外的话，他在本赛" +
                "季(生涯第15个赛季)就能超越科比，成为NBA历史最年轻的30000分先生。</p><p>" +
                "<img src=\"http://p2.qhimg.com/t0111fe70b44cb3e393.jpg?size=1024x770\"" +
                " class=\"mCS_img_loaded\"></p><p><img src=\"http://p0.qhimg.com/t011f7" +
                "a164e5cd0d6f1.jpg?size=858x572\" class=\"mCS_img_loaded\"></p><p>返回搜" +
                "狐，查看更多</p><p>责任编辑:</p>\"";
        HtmlFromUtils.setTextFromHtml(getActivity(), tvRl2, html);

    }

    private String translation(String content) {
        String replace = content.replace("<", "<");
        String replace1 = replace.replace(">", ">");
        String replace2 = replace1.replace("&", "&");
        String replace3 = replace2.replace("\"", "'\'");
        return replace3.replace("&copy;", "");
    }

    @OnClick({R.id.tv1, R.id.tv2, R.id.tv3})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv1:
                tv1.setTextColor(Color.parseColor("#FF0004"));
                tv2.setTextColor(Color.parseColor("#686868"));
                tv3.setTextColor(Color.parseColor("#686868"));
                ll1.setVisibility(View.VISIBLE);
                rl2.setVisibility(View.GONE);
                rl3.setVisibility(View.GONE);
                break;
            case R.id.tv2:
                tv1.setTextColor(Color.parseColor("#686868"));
                tv2.setTextColor(Color.parseColor("#FF0004"));
                tv3.setTextColor(Color.parseColor("#686868"));
                ll1.setVisibility(View.GONE);
                rl2.setVisibility(View.VISIBLE);
                rl3.setVisibility(View.GONE);
                break;
            case R.id.tv3:
                tv1.setTextColor(Color.parseColor("#686868"));
                tv2.setTextColor(Color.parseColor("#686868"));
                tv3.setTextColor(Color.parseColor("#FF0004"));
                ll1.setVisibility(View.GONE);
                rl2.setVisibility(View.GONE);
                rl3.setVisibility(View.VISIBLE);
                break;
        }
    }

}
