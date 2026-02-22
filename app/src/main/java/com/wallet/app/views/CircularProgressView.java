package com.wallet.app.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.wallet.app.R;

public class CircularProgressView extends View {

    private Paint strokePaint;
    private Paint dotPaint;
    private RectF rectF;
    
    private float progress = 0.75f; // 75% progress (270 degrees)
    private float strokeWidth = 3f;
    private float dotRadius = 5f;
    private int dotCount = 40;
    
    private int strokeColor;
    private int dotColor;

    public CircularProgressView(Context context) {
        super(context);
        init();
    }

    public CircularProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircularProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        strokeColor = getContext().getColor(R.color.white);
        dotColor = getContext().getColor(R.color.white);
        
        strokePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setStrokeWidth(strokeWidth);
        strokePaint.setColor(strokeColor);
        
        dotPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        dotPaint.setStyle(Paint.Style.FILL);
        dotPaint.setColor(dotColor);
        
        rectF = new RectF();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        float padding = strokeWidth + dotRadius * 2;
        rectF.set(padding, padding, w - padding, h - padding);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        
        float centerX = getWidth() / 2f;
        float centerY = getHeight() / 2f;
        float radius = Math.min(centerX, centerY) - strokeWidth - dotRadius * 2;
        
        // Draw the solid arc (about 270 degrees starting from top)
        float startAngle = -90f; // Start from top
        float sweepAngle = progress * 360f;
        
        // Draw main arc
        canvas.drawArc(rectF, startAngle, sweepAngle * 0.7f, false, strokePaint);
        
        // Draw dotted section at the end
        int dotsToShow = (int) (dotCount * 0.3f);
        float dotStartAngle = startAngle + sweepAngle * 0.7f;
        float angleStep = (sweepAngle * 0.3f) / dotsToShow;
        
        for (int i = 0; i < dotsToShow; i++) {
            float angle = (float) Math.toRadians(dotStartAngle + i * angleStep);
            float dotX = centerX + radius * (float) Math.cos(angle);
            float dotY = centerY + radius * (float) Math.sin(angle);
            canvas.drawCircle(dotX, dotY, dotRadius - 1, dotPaint);
        }
    }

    public void setProgress(float progress) {
        this.progress = Math.max(0f, Math.min(1f, progress));
        invalidate();
    }

    public float getProgress() {
        return progress;
    }
}
