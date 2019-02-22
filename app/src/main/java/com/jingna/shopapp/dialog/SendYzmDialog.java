package com.jingna.shopapp.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.jingna.shopapp.R;

/**
 * Created by Administrator on 2019/2/22.
 */

public class SendYzmDialog extends Dialog {

    private Context context;
    private TextView tvNumber;
    private TextView tvCancel;
    private TextView tvSure;

    private ClickListener listener;
    private String number;

    public SendYzmDialog(@NonNull Context context, String number, ClickListener listener) {
        super(context, R.style.RoundCornerDialog);
        this.context = context;
        this.number = number;
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {

        View view = LayoutInflater.from(context).inflate(R.layout.dialog_send_yzm, null);
        setContentView(view);

        tvNumber = view.findViewById(R.id.tv_number);
        tvCancel = view.findViewById(R.id.tv_cancel);
        tvSure = view.findViewById(R.id.tv_sure);

        tvNumber.setText(number);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onSure();
                dismiss();
            }
        });

    }

    public interface ClickListener{
        void onSure();
    }

}
