package com.yogesh.androidlabpracticals.practical8;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class DrawingView extends View {

    private Paint circlePaint, rectPaint, linePaint, triPaint;
    private float scaleX = 1f, scaleY = 1f;
    private float translateX = 0f, translateY = 0f;
    private float rotation = 0f;
    private float alpha = 1f;

    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setColor(Color.parseColor("#E53935"));
        circlePaint.setStyle(Paint.Style.FILL);

        rectPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        rectPaint.setColor(Color.parseColor("#1E88E5"));
        rectPaint.setStyle(Paint.Style.FILL);

        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setColor(Color.parseColor("#43A047"));
        linePaint.setStrokeWidth(8f);

        triPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        triPaint.setColor(Color.parseColor("#FF6F00"));
        triPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.parseColor("#F4F6F9"));

        int w = getWidth();
        int h = getHeight();

        // Circle
        circlePaint.setAlpha((int)(alpha * 255));
        canvas.save();
        canvas.translate(w * 0.25f + translateX, h * 0.25f + translateY);
        canvas.rotate(rotation);
        canvas.scale(scaleX, scaleY);
        canvas.drawCircle(0, 0, 70, circlePaint);
        canvas.restore();

        // Rectangle
        rectPaint.setAlpha((int)(alpha * 255));
        canvas.save();
        canvas.translate(w * 0.75f + translateX, h * 0.25f + translateY);
        canvas.rotate(rotation);
        canvas.scale(scaleX, scaleY);
        canvas.drawRect(-70, -50, 70, 50, rectPaint);
        canvas.restore();

        // Line
        linePaint.setAlpha((int)(alpha * 255));
        canvas.save();
        canvas.translate(w * 0.5f + translateX, h * 0.5f + translateY);
        canvas.rotate(rotation);
        canvas.scale(scaleX, scaleY);
        canvas.drawLine(-80, 0, 80, 0, linePaint);
        canvas.restore();

        // Triangle
        triPaint.setAlpha((int)(alpha * 255));
        canvas.save();
        canvas.translate(w * 0.5f + translateX, h * 0.75f + translateY);
        canvas.rotate(rotation);
        canvas.scale(scaleX, scaleY);
        Path triangle = new Path();
        triangle.moveTo(0, -80);
        triangle.lineTo(70, 60);
        triangle.lineTo(-70, 60);
        triangle.close();
        canvas.drawPath(triangle, triPaint);
        canvas.restore();
    }

    public void setScale(float sx, float sy) { this.scaleX = sx; this.scaleY = sy; invalidate(); }
    public void setTranslation(float tx, float ty) { this.translateX = tx; this.translateY = ty; invalidate(); }
    public void setRotationAngle(float angle) { this.rotation = angle; invalidate(); }
    public void setAlphaValue(float a) { this.alpha = a; invalidate(); }
    public void resetAll() { scaleX = 1; scaleY = 1; translateX = 0; translateY = 0; rotation = 0; alpha = 1; invalidate(); }
}
