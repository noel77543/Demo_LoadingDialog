package com.sung.noel.tw.demo_loadingdialog.util;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.sung.noel.tw.demo_loadingdialog.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by User on 2018/2/28.
 */

public class LoadingDrawableCircleDialog extends Dialog implements DialogInterface.OnDismissListener {

    @BindView(R.id.imageview)
    ImageView imageview;

    private Animation animation;
    private LinearInterpolator linearInterpolator;
    private final int ANIMATION_DURATION = 800;
    public LoadingDrawableCircleDialog(Context context) {
        super(context);
        setContentView(R.layout.dialog_drawable_circle);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        linearInterpolator = new LinearInterpolator();
        setOnDismissListener(this);
    }


    public void showLoadingDialog() {
        animation = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(ANIMATION_DURATION);
        animation.setRepeatCount(Animation.INFINITE);
        animation.setInterpolator(linearInterpolator);
        imageview.setAnimation(animation);

        animation.startNow();
        show();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        if (animation != null) {
            animation.cancel();
        }
    }
}
