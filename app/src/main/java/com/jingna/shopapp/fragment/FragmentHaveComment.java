package com.jingna.shopapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jingna.shopapp.R;
import com.jingna.shopapp.base.BaseFragment;

/**
 * Created by Administrator on 2019/4/2.
 */

public class FragmentHaveComment extends BaseFragment {

    private String id = "";

    public static FragmentToComment newInstance(String id) {
        FragmentToComment newFragment = new FragmentToComment();
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        newFragment.setArguments(bundle);
        return newFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_have_comment, null);

        Bundle args = getArguments();
        if (args != null) {
            id = args.getString("id");
        }

        return view;
    }
}
