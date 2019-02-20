package com.jingna.shopapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jingna.shopapp.app.MyApplication;
import com.jingna.shopapp.fragment.FragmentFenlei;
import com.jingna.shopapp.fragment.FragmentGouwuche;
import com.jingna.shopapp.fragment.FragmentIndex;
import com.jingna.shopapp.fragment.FragmentWode;
import com.jingna.shopapp.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private Context context = MainActivity.this;

    @BindView(R.id.menu_index)
    ImageButton ibIndex;
    @BindView(R.id.menu_fenlei)
    ImageButton ibFenlei;
    @BindView(R.id.menu_gouwuche)
    ImageButton ibGouwuche;
    @BindView(R.id.menu_wode)
    ImageButton ibWode;
    @BindView(R.id.menu1)
    RelativeLayout rl1;
    @BindView(R.id.menu2)
    RelativeLayout rl2;
    @BindView(R.id.menu3)
    RelativeLayout rl3;
    @BindView(R.id.menu4)
    RelativeLayout rl4;
    @BindView(R.id.tv_index)
    TextView tvIndex;
    @BindView(R.id.tv_fenlei)
    TextView tvFenlei;
    @BindView(R.id.tv_gouwuche)
    TextView tvGouwuche;
    @BindView(R.id.tv_wode)
    TextView tvWode;

    private long exitTime = 0;

    private List<Fragment> fragmentList = new ArrayList<>();
    private MenuOnClickListener listener = new MenuOnClickListener();

    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(MainActivity.this);
        MyApplication.getInstance().addActivity(this);
        init();

    }

    /**
     * 初始化各个组件
     */
    private void init() {

        ibIndex.setOnClickListener(listener);
        ibFenlei.setOnClickListener(listener);
        ibGouwuche.setOnClickListener(listener);
        ibWode.setOnClickListener(listener);

        rl1.setOnClickListener(listener);
        rl2.setOnClickListener(listener);
        rl3.setOnClickListener(listener);
        rl4.setOnClickListener(listener);
        Fragment fragmentIndex = new FragmentIndex();
        Fragment fragmentFenlei = new FragmentFenlei();
        Fragment fragmentGouwuche = new FragmentGouwuche();
        Fragment fragmentWode = new FragmentWode();

        fragmentList.add(fragmentIndex);
        fragmentList.add(fragmentFenlei);
        fragmentList.add(fragmentGouwuche);
        fragmentList.add(fragmentWode);

        fragmentTransaction.add(R.id.fl_container, fragmentIndex);
        fragmentTransaction.add(R.id.fl_container, fragmentFenlei);
        fragmentTransaction.add(R.id.fl_container, fragmentGouwuche);
        fragmentTransaction.add(R.id.fl_container, fragmentWode);

        fragmentTransaction.show(fragmentIndex).hide(fragmentFenlei).hide(fragmentGouwuche).hide(fragmentWode);
        fragmentTransaction.commit();

        selectButton(ibIndex);
        selectText(tvIndex);

    }

    private class MenuOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            switch (v.getId()) {
                case R.id.menu_index:
                    selectButton(ibIndex);
                    selectText(tvIndex);
                    switchFragment(0);
                    break;
                case R.id.menu_fenlei:
                    selectButton(ibFenlei);
                    selectText(tvFenlei);
                    switchFragment(1);
                    break;
                case R.id.menu_gouwuche:
                    selectButton(ibGouwuche);
                    selectText(tvGouwuche);
                    switchFragment(2);
                    break;
                case R.id.menu_wode:
                    selectButton(ibWode);
                    selectText(tvWode);
                    switchFragment(3);
                    break;
                case R.id.menu1:
                    selectButton(ibIndex);
                    selectText(tvIndex);
                    switchFragment(0);
                    break;
                case R.id.menu2:
                    selectButton(ibFenlei);
                    selectText(tvFenlei);
                    switchFragment(1);
                    break;
                case R.id.menu3:
                    selectButton(ibGouwuche);
                    selectText(tvGouwuche);
                    switchFragment(2);
                    break;
                case R.id.menu4:
                    selectButton(ibWode);
                    selectText(tvWode);
                    switchFragment(3);
                    break;
            }

        }
    }

    /**
     * 选择隐藏与显示的Fragment
     *
     * @param index 显示的Frgament的角标
     */
    public void switchFragment(int index) {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        for (int i = 0; i < fragmentList.size(); i++) {
            if (index == i) {
                fragmentTransaction.show(fragmentList.get(index));
            } else {
                fragmentTransaction.hide(fragmentList.get(i));
            }
        }
        fragmentTransaction.commit();
    }

    public void selectText(View v) {
        tvIndex.setSelected(false);
        tvFenlei.setSelected(false);
        tvGouwuche.setSelected(false);
        tvWode.setSelected(false);
        v.setSelected(true);
    }

    /**
     * 控制底部菜单按钮的选中
     *
     * @param v
     */
    public void selectButton(View v) {
        ibIndex.setSelected(false);
        ibFenlei.setSelected(false);
        ibGouwuche.setSelected(false);
        ibWode.setSelected(false);
        v.setSelected(true);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        backtrack();
    }

    /**
     * 退出销毁所有activity
     */
    private void backtrack() {
        if (System.currentTimeMillis() - exitTime > 2000) {
            ToastUtil.showShort(context, "再按一次退出程序");
            exitTime = System.currentTimeMillis();
        } else {
            MyApplication.getInstance().exit();
            exitTime = 0;
        }
    }

}
