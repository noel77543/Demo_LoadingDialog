package com.sung.noel.tw.demo_loadingdialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.sung.noel.tw.demo_loadingdialog.util.LoadingDrawableCircleDialog;
import com.sung.noel.tw.demo_loadingdialog.util.LoadingImageCircleDialog;
import com.sung.noel.tw.demo_loadingdialog.util.LoadingTextDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_drawable)
    Button btnDrawable;
    @BindView(R.id.btn_image)
    Button btnImage;
    @BindView(R.id.btn_text)
    Button btnText;

    private LoadingImageCircleDialog loadingImageCircleDialog;
    private LoadingDrawableCircleDialog loadingDrawableCircleDialog;
    private LoadingTextDialog loadingTextDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        loadingImageCircleDialog = new LoadingImageCircleDialog(this);
        loadingDrawableCircleDialog = new LoadingDrawableCircleDialog(this);
        loadingTextDialog = new LoadingTextDialog(this);
    }

    @OnClick({R.id.btn_drawable, R.id.btn_image, R.id.btn_text})
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
        }
    }
}
