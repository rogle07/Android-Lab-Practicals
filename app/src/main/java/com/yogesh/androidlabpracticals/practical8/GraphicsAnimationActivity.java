package com.yogesh.androidlabpracticals.practical8;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.yogesh.androidlabpracticals.R;

public class GraphicsAnimationActivity extends AppCompatActivity {

    private DrawingView drawingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphics);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Practical 8 – Graphics & Animations");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        drawingView = findViewById(R.id.drawing_view);

        Button btnRotate = findViewById(R.id.btn_rotate);
        Button btnScale = findViewById(R.id.btn_scale);
        Button btnTranslate = findViewById(R.id.btn_translate);
        Button btnFade = findViewById(R.id.btn_fade);
        Button btnReset = findViewById(R.id.btn_reset);

        btnRotate.setOnClickListener(v -> animateRotation());
        btnScale.setOnClickListener(v -> animateScale());
        btnTranslate.setOnClickListener(v -> animateTranslate());
        btnFade.setOnClickListener(v -> animateFade());
        btnReset.setOnClickListener(v -> drawingView.resetAll());
    }

    private void animateRotation() {
        ValueAnimator animator = ValueAnimator.ofFloat(0f, 360f);
        animator.setDuration(1500);
        animator.addUpdateListener(a -> drawingView.setRotationAngle((float) a.getAnimatedValue()));
        animator.start();
    }

    private void animateScale() {
        ValueAnimator animator = ValueAnimator.ofFloat(1f, 2f);
        animator.setDuration(1000);
        animator.setRepeatCount(1);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.addUpdateListener(a -> {
            float s = (float) a.getAnimatedValue();
            drawingView.setScale(s, s);
        });
        animator.start();
    }

    private void animateTranslate() {
        ValueAnimator animator = ValueAnimator.ofFloat(0f, 100f);
        animator.setDuration(1000);
        animator.setRepeatCount(1);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.addUpdateListener(a -> {
            float t = (float) a.getAnimatedValue();
            drawingView.setTranslation(t, t);
        });
        animator.start();
    }

    private void animateFade() {
        ValueAnimator animator = ValueAnimator.ofFloat(1f, 0f);
        animator.setDuration(1000);
        animator.setRepeatCount(1);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.addUpdateListener(a -> drawingView.setAlphaValue((float) a.getAnimatedValue()));
        animator.start();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
