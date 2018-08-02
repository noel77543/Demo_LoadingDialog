package com.sung.noel.tw.demo_loadingdialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.sung.noel.tw.demo_loadingdialog.util.LoadingDrawableCircleDialog;
import com.sung.noel.tw.demo_loadingdialog.util.LoadingImageCircleDialog;
import com.sung.noel.tw.demo_loadingdialog.util.LoadingProgressCircleDialog;
import com.sung.noel.tw.demo_loadingdialog.util.LoadingProgressDialog;
import com.sung.noel.tw.demo_loadingdialog.util.LoadingTextDialog;
import com.sung.noel.tw.demo_loadingdialog.util.implement.OnUpdateCompleteListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements DialogInterface.OnDismissListener, OnUpdateCompleteListener {

    @BindView(R.id.btn_drawable)
    Button btnDrawable;
    @BindView(R.id.btn_image)
    Button btnImage;
    @BindView(R.id.btn_text)
    Button btnText;
    @BindView(R.id.btn_progress)
    Button btnProgress;
    @BindView(R.id.btn_circle_progress)
    Button btnCircleProgress;

    private LoadingImageCircleDialog loadingImageCircleDialog;
    private LoadingDrawableCircleDialog loadingDrawableCircleDialog;
    private LoadingTextDialog loadingTextDialog;
    private LoadingProgressDialog loadingProgressDialog;
    private LoadingProgressCircleDialog loadingProgressCircleDialog;

    private int progress;
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        loadingImageCircleDialog = new LoadingImageCircleDialog(this);
        loadingDrawableCircleDialog = new LoadingDrawableCircleDialog(this);
        loadingTextDialog = new LoadingTextDialog(this);

        loadingProgressDialog = new LoadingProgressDialog(this, 100);
        loadingProgressDialog.setOnUpdateCompleteListener(this);
        loadingProgressDialog.setOnDismissListener(this);

        loadingProgressCircleDialog = new LoadingProgressCircleDialog(this, 100);
        loadingProgressCircleDialog.setOnUpdateCompleteListener(this);
        loadingProgressCircleDialog.setOnDismissListener(this);
        handler = new Handler();
    }

    @OnClick({R.id.btn_drawable, R.id.btn_image, R.id.btn_text, R.id.btn_progress, R.id.btn_circle_progress})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_drawable:
                loadingDrawableCircleDialog.showLoadingDialog();
                break;
            case R.id.btn_image:
                loadingImageCircleDialog.showLoadingDialog();
                break;
            case R.id.btn_text:
                loadingTextDialog.showLoadingDialog();
                break;
            case R.id.btn_progress:
                loadingProgressDialog.show();
                startUpdate(false);
                break;
            case R.id.btn_circle_progress:
                loadingProgressCircleDialog.show();
                startUpdate(true);
                break;
        }
    }

    //----------

    /***
     * 進行更新
     */
    private void startUpdate(final boolean isCircle) {
        progress = 0;
        runnable = new Runnable() {
            @Override
            public void run() {
                progress++;
                if (isCircle) {
                    loadingProgressCircleDialog.updateProgress(progress);

                } else {
                    loadingProgressDialog.updateProgress(progress);

                }
                handler.postDelayed(this, 100);
            }
        };

        handler.postDelayed(runnable, 0);
    }
    //----------

    /***
     * 當dismiss
     * @param dialog
     */
    @Override
    public void onDismiss(DialogInterface dialog) {
        handler.removeCallbacks(runnable);
        if (dialog instanceof LoadingProgressDialog) {
            Toast.makeText(this, getString(R.string.toast_progress_updated), Toast.LENGTH_SHORT).show();
        } else if (dialog instanceof LoadingProgressCircleDialog) {
            Toast.makeText(this, getString(R.string.toast_circle_progress_updated), Toast.LENGTH_SHORT).show();
        }
    }
    //----------

    /***
     * 當完成更新
     * dismiss
     */
    @Override
    public void onUpdateCompleted() {
        if (loadingProgressDialog.isShowing()) {
            loadingProgressDialog.dismiss();
        } else if (loadingProgressCircleDialog.isShowing()) {
            loadingProgressCircleDialog.dismiss();
        }
    }
}
