package com.wallet.app.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import com.wallet.app.R;

public class StepChartView extends View {

    private Paint linePaint;
    private Paint dotPaint;
    private Paint dotFillPaint;
    private Path path;
    
    // Data points (normalized 0-1)
    private float[] dataPoints = {0.3f, 0.7f, 0.5f, 0.4f, 0.6f};
    private int highlightIndex = 1; // March is highlighted with $10,500
    
    private float strokeWidth = 2f;
    private float dotRadius = 5f;
    
    private int lineColor;
    private int dotColor;

    public StepChartView(Context context) {
        super(context);
        init();
    }

    public StepChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public StepChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        lineColor = getContext().getColor(R.color.text_dark);
        dotColor = getContext().getColor(R.color.text_dark);
        
        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeWidth(strokeWidth);
        linePaint.setColor(lineColor);
        linePaint.setStrokeCap(Paint.Cap.SQUARE);
        
        dotPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        dotPaint.setStyle(Paint.Style.STROKE);
        dotPaint.setStrokeWidth(strokeWidth);
        dotPaint.setColor(dotColor);
        
        dotFillPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        dotFillPaint.setStyle(Paint.Style.FILL);
        dotFillPaint.setColor(getContext().getColor(R.color.cream));
        
        path = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        
        if (dataPoints == null || dataPoints.length < 2) return;
        
        float width = getWidth();
        float height = getHeight();
        float padding = dotRadius * 2 + strokeWidth;
        
        float chartWidth = width - padding * 2;
        float chartHeight = height - padding * 2;
        
        float stepX = chartWidth / (dataPoints.length - 1);
        
        path.reset();
        
        // Calculate Y positions (inverted because canvas Y increases downward)
        float[] yPositions = new float[dataPoints.length];
        for (int i = 0; i < dataPoints.length; i++) {
            yPositions[i] = padding + chartHeight * (1 - dataPoints[i]);
        }
        
        // Draw step chart
        float startX = padding;
        
        // Start from bottom left
        path.moveTo(startX, height - padding);
        path.lineTo(startX, yPositions[0]);
        
        for (int i = 0; i < dataPoints.length - 1; i++) {
            float x1 = startX + i * stepX;
            float x2 = startX + (i + 1) * stepX;
            float y1 = yPositions[i];
            float y2 = yPositions[i + 1];
            
            // Draw horizontal line
            path.lineTo(x2, y1);
            // Draw vertical line
            path.lineTo(x2, y2);
        }
        
        // End at bottom right
        path.lineTo(width - padding, height - padding);
        
        canvas.drawPath(path, linePaint);
        
        // Draw highlight dot at the selected point
        if (highlightIndex >= 0 && highlightIndex < dataPoints.length) {
            float dotX = startX + highlightIndex * stepX;
            float dotY = yPositions[highlightIndex];
            
            // Draw filled circle with stroke
            canvas.drawCircle(dotX, dotY, dotRadius, dotFillPaint);
            canvas.drawCircle(dotX, dotY, dotRadius, dotPaint);
        }
    }

    public void setDataPoints(float[] dataPoints) {
        this.dataPoints = dataPoints;
        invalidate();
    }

    public void setHighlightIndex(int index) {
        this.highlightIndex = index;
        invalidate();
    }
}
