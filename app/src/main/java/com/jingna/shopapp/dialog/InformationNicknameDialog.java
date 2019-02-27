package com.jingna.shopapp.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jingna.shopapp.R;

/**
 * Created by Administrator on 2019/2/27.
 */

public class InformationNicknameDialog extends Dialog {

    private Context context;

    private EditText etName;
    private TextView tvSure;
    private TextView tvCancel;

    private ClickListener listener;

    public InformationNicknameDialog(@NonNull Context context, ClickListener listener) {
        super(context, R.style.RoundCornerDialog);
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {

        View view = LayoutInflater.from(context).inflate(R.layout.dialog_information_nickname, null);
        setContentView(view);

        etName = view.findViewById(R.id.et_nickname);
        tvSure = view.findViewById(R.id.tv_sure);
        tvCancel = view.findViewById(R.id.tv_cancel);

        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onSure(etName.getText().toString());
                dismiss();
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }

    public interface ClickListener{
        void onSure(String name);
    }

}
