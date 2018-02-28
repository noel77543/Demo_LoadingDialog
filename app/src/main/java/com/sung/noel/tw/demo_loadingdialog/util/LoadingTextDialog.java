package com.sung.noel.tw.demo_loadingdialog.util;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.widget.TextView;

import com.sung.noel.tw.demo_loadingdialog.MainActivity;
import com.sung.noel.tw.demo_loadingdialog.R;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by User on 2018/2/28.
 */

public class LoadingTextDialog extends Dialog implements DialogInterface.OnDismissListener {

    @BindView(R.id.textView)
    TextView textView;

    private MainActivity activity;
    //計時器
    private Timer timer;
    //計時器任務
    private TimerTask timerTask;
    //第幾個文字
    private int textIndex = 0;
    //文字長度
    private int textLengrh = 0;
    //最初的文字大小
    private float textSize = 0;
    //行為重複間隔
    private final int DURATION = 150;
    //放大倍率
    private final float TEXT_SIZE = 1.5f;

    public LoadingTextDialog(Context context) {
        super(context);
        setContentView(R.layout.dialog_text);
        ButterKnife.bind(this);
        if (context instanceof MainActivity) {
            activity = (MainActivity) context;
        }
        init();
    }

    private void init() {
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setOnDismissListener(this);
        textSize = textView.getTextSize();
        textLengrh = textView.getText().toString().length();
    }


    public void showLoadingDialog() {
        setTimerTask();
        show();
    }

    private void setTimerTask() {
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (textIndex < textLengrh) {
                            textView.setText(getSpannedText(textIndex));
                            textIndex++;
                        } else {
                            textIndex = 0;
                        }
                    }
                });
            }
        };
        timer.schedule(timerTask,0, DURATION);
    }
    //-------
    /***
     *  文字設定
     * @param loadingMessage
     */
    public void setLoadingMessage(String loadingMessage) {
        textView.setText(loadingMessage);
        textLengrh = loadingMessage.length();
    }


    private CharSequence getSpannedText(int strIndext) {
        SpannableStringBuilder builder = new SpannableStringBuilder(textView.getText().toString());
        builder.setSpan(new RelativeSizeSpan(TEXT_SIZE), strIndext, strIndext + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return builder;
    }


    @Override
    public void onDismiss(DialogInterface dialog) {
        if (timer != null) {
            timer.cancel();
        }
    }
}
