package com.duoker.watch.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.duoker.watch.R;

/**
 * Created by chengang on 4/24/2017.
 * 圆形进度条
 * https://github.com/gyw520gyw/ArcProgress/tree/master/app/src/main/java/com/gyw/arcprogressdemo
 */

public class ArcProgress extends View
{
    private float arcAngle;
    private int contentTextColor;
    private float contentTextSize;
    private float cx;
    private float cy;
    private final float default_arc_angle = 360.0F;
    private final float default_bottom_text_size;
    private final int default_finished_color = -1;
    private final int default_max = 100;
    private final float default_stroke_width;
    private final float default_suffix_padding;
    private final String default_suffix_text = "步";
    private final float default_suffix_text_size;
    private final int default_text_color = Color.rgb(66, 145, 241);
    private float default_text_size;
    private final int default_unfinished_color = Color.rgb(72, 106, 176);
    private int finishedStrokeColor;
    private float innerRadius;
    private boolean isStopStep = false;
    private int max=1;
    private final int min_size;
    private String nameText;
    private float nameTextSize;
    private int otherTextColor;
    private Paint paint;
    private int progress = 0;
    private RectF rectF = new RectF();
    private float strokeWidth;
    private String suffixText = "步";
    private float suffixTextPadding;
    private float suffixTextSize;
    private String targetText;
    private float targetTextSize;
    protected Paint textPaint;
    private int unfinishedStrokeColor;

    public ArcProgress(Context paramContext)
    {
        this(paramContext, null);
    }

    public ArcProgress(Context paramContext, AttributeSet paramAttributeSet)
    {
        this(paramContext, paramAttributeSet, 0);
    }

    public ArcProgress(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);

        default_text_size = DisplayUtil.sp2px(context, 18);
        min_size = (int) DisplayUtil.dip2px(context, 100);
        default_text_size = DisplayUtil.sp2px(context, 40);
        default_suffix_text_size = DisplayUtil.sp2px(context, 15);
        default_suffix_padding = DisplayUtil.dip2px(context, 4);
        default_bottom_text_size = DisplayUtil.sp2px(context, 10);
        default_stroke_width = DisplayUtil.dip2px(context, 4);

        TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ArcProgress, defStyleAttr, 0);
        initByAttributes(attributes);
        attributes.recycle();
        initPainters();
    }

    public int dip2px(Context paramContext, float paramFloat)
    {
        return (int)(0.5F + paramFloat * paramContext.getResources().getDisplayMetrics().density);
    }

    public float getArcAngle()
    {
        return this.arcAngle;
    }

    public int getContentTextColor()
    {
        return this.contentTextColor;
    }

    public float getContentTextSize()
    {
        return this.contentTextSize;
    }

    public int getFinishedStrokeColor()
    {
        return this.finishedStrokeColor;
    }

    public int getMax()
    {
        return this.max;
    }

    public String getNameText()
    {
        return this.nameText;
    }

    public float getNameTextSize()
    {
        return this.nameTextSize;
    }

    public int getProgress()
    {
        return this.progress;
    }

    public float getStrokeWidth()
    {
        return this.strokeWidth;
    }

    public String getSuffixText()
    {
        return this.suffixText;
    }

    public float getSuffixTextPadding()
    {
        return this.suffixTextPadding;
    }

    public float getSuffixTextSize()
    {
        return this.suffixTextSize;
    }

    protected int getSuggestedMinimumHeight()
    {
        return this.min_size;
    }

    protected int getSuggestedMinimumWidth()
    {
        return this.min_size;
    }

    public String getTargetText()
    {
        return this.targetText;
    }

    public float getTargetTextSize()
    {
        return this.targetTextSize;
    }

    public int getUnfinishedStrokeColor()
    {
        return this.unfinishedStrokeColor;
    }

    protected void initByAttributes(TypedArray attributes)
    {
        this.finishedStrokeColor = attributes.getColor(R.styleable.ArcProgress_ec_arc_unfinished_color, -1);
        this.unfinishedStrokeColor = attributes.getColor(R.styleable.ArcProgress_ec_arc_unfinished_color, this.default_unfinished_color);
        this.contentTextColor = attributes.getColor(R.styleable.ArcProgress_ec_arc_content_text_color, this.default_text_color);
        this.contentTextSize = attributes.getDimension(R.styleable.ArcProgress_ec_arc_content_text_size, this.default_text_size);
        this.arcAngle = attributes.getFloat(R.styleable.ArcProgress_ec_arc_angle, 360.0F);
        setMax(attributes.getInt(R.styleable.ArcProgress_ec_arc_max, 100));
        setProgress(attributes.getInt(R.styleable.ArcProgress_ec_arc_progress, 0));
        this.strokeWidth = attributes.getDimension(R.styleable.ArcProgress_ec_arc_stroke_width, this.default_stroke_width);
        this.suffixTextSize = attributes.getDimension(R.styleable.ArcProgress_ec_arc_suffix_text_size, this.default_suffix_text_size);
        this.suffixText = (TextUtils.isEmpty(attributes.getString(R.styleable.ArcProgress_ec_arc_suffix_text))) ? default_suffix_text : attributes
                .getString(R.styleable.ArcProgress_ec_arc_suffix_text);

        this.suffixTextPadding = attributes.getDimension(R.styleable.ArcProgress_ec_arc_suffix_text_padding, this.default_suffix_padding);
        this.targetTextSize = attributes.getDimension(R.styleable.ArcProgress_ec_arc_suffix_text_size, this.default_bottom_text_size);
        this.targetText = attributes.getString(R.styleable.ArcProgress_ec_arc_target_text);
        this.nameTextSize = attributes.getDimension(R.styleable.ArcProgress_ec_arc_target_text_size, this.default_bottom_text_size);
        this.nameText = attributes.getString(R.styleable.ArcProgress_ec_arc_name_text);
        this.otherTextColor = attributes.getColor(R.styleable.ArcProgress_ec_arc_other_text_color, this.default_text_color);
    }

    protected void initPainters()
    {
        this.textPaint = new TextPaint();
        this.textPaint.setColor(this.contentTextColor);
        this.textPaint.setTextSize(this.contentTextSize);
        this.textPaint.setAntiAlias(true);
        this.paint = new Paint();
        this.paint.setColor(this.default_unfinished_color);
        this.paint.setAntiAlias(true);
        this.paint.setStrokeWidth(this.strokeWidth);
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeCap(Paint.Cap.ROUND);
    }

    public void invalidate()
    {
        initPainters();
        super.invalidate();
    }

    protected void onDraw(Canvas paramCanvas)
    {
        super.onDraw(paramCanvas);
        float f1 = 270.0F - this.arcAngle / 2.0F;
        float f2 = this.progress / getMax() * this.arcAngle;
        if (this.progress > getMax())
            f2 = this.arcAngle;
        this.paint.setColor(this.unfinishedStrokeColor);
        paramCanvas.save();
        this.paint.setStrokeWidth(dip2px(getContext(), 2.0F));
        paramCanvas.translate(getWidth() / 2, getHeight() / 2);
        paramCanvas.rotate(f1 - 90.0F);
        int i = dip2px(getContext(), 1.0F);
        float f3 = getWidth() / 2 - this.strokeWidth + i;
        float f4 = getWidth() / 2 - i;
        for (int j = 0; j <= this.arcAngle; j += 3)
        {
            paramCanvas.drawLine(0.0F, f3, 0.0F, f4, this.paint);
            paramCanvas.rotate(3.0F);
        }
        paramCanvas.restore();
        this.paint.setStrokeWidth(this.strokeWidth);
        this.paint.setColor(this.finishedStrokeColor);
        paramCanvas.drawArc(this.rectF, f1, f2, false, this.paint);
        String str = String.valueOf(getProgress());
        if (!TextUtils.isEmpty(str))
        {
            this.textPaint.setColor(this.contentTextColor);
            this.textPaint.setTextSize(this.contentTextSize);
            if (this.isStopStep)
            {
                str = "暂停";
                this.textPaint.setTextSize(this.contentTextSize / 2.0F);
            }
            float f7 = this.textPaint.descent() + this.textPaint.ascent();
            float f8 = (getHeight() - f7) / 2.0F;
            float f9 = (getWidth() - this.textPaint.measureText(str)) / 2.0F;
            Paint localPaint = this.textPaint;
            paramCanvas.drawText(str, f9, f8, localPaint);
            this.textPaint.setTextSize(this.suffixTextSize);
            this.textPaint.setColor(this.otherTextColor);
            float f10 = (float)(0.25D * getHeight() - (this.textPaint.descent() + this.textPaint.ascent()) / 2.0F);
            paramCanvas.drawText(this.suffixText, (getWidth() - this.textPaint.measureText(this.suffixText)) / 2.0F, f10, this.textPaint);
        }
        if (!TextUtils.isEmpty(getTargetText()))
        {
            this.textPaint.setTextSize(this.targetTextSize);
            this.textPaint.setColor(this.otherTextColor);
            float f6 = (float)(0.7D * getHeight() - (this.textPaint.descent() + this.textPaint.ascent()) / 2.0F);
            paramCanvas.drawText(getTargetText(), (getWidth() - this.textPaint.measureText(getTargetText())) / 2.0F, f6, this.textPaint);
        }
        if (!TextUtils.isEmpty(getNameText()))
        {
            this.textPaint.setTextSize(this.nameTextSize);
            this.textPaint.setColor(this.otherTextColor);
            float f5 = (float)(0.85D * getHeight() - (this.textPaint.descent() + this.textPaint.ascent()) / 2.0F);
            paramCanvas.drawText(getNameText(), (getWidth() - this.textPaint.measureText(getNameText())) / 2.0F, f5, this.textPaint);
        }
    }

    protected void onMeasure(int paramInt1, int paramInt2)
    {
        this.rectF.set(this.strokeWidth / 2.0F, this.strokeWidth / 2.0F, View.MeasureSpec.getSize(paramInt1) - this.strokeWidth / 2.0F, View.MeasureSpec.getSize(paramInt2) - this.strokeWidth / 2.0F);
        this.innerRadius = ((float)(0.8D * (getWidth() / 2.0F)));
        this.cx = (getWidth() / 2);
        this.cy = (getHeight() / 2);
        setMeasuredDimension(paramInt1, paramInt2);
    }

    protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
    }

    public void setArcAngle(float paramFloat)
    {
        this.arcAngle = paramFloat;
        invalidate();
    }

    public void setContentTextColor(int paramInt)
    {
        this.contentTextColor = paramInt;
        invalidate();
    }

    public void setContentTextSize(float paramFloat)
    {
        this.contentTextSize = paramFloat;
        invalidate();
    }

    public void setFinishedStrokeColor(int paramInt)
    {
        this.finishedStrokeColor = paramInt;
        invalidate();
    }

    public void setMax(int paramInt)
    {
        if (paramInt > 0)
        {
            this.max = paramInt;
            invalidate();
        }
    }

    public void setNameText(String paramString)
    {
        this.nameText = paramString;
        invalidate();
    }

    public void setNameTextSize(float paramFloat)
    {
        this.nameTextSize = paramFloat;
        invalidate();
    }

    public void setProgress(int paramInt)
    {
        this.progress = paramInt;
        invalidate();
    }

    public void setStopStep(boolean paramBoolean)
    {
        this.isStopStep = paramBoolean;
        invalidate();
    }

    public void setStrokeWidth(float paramFloat)
    {
        this.strokeWidth = paramFloat;
        invalidate();
    }

    public void setSuffixText(String paramString)
    {
        this.suffixText = paramString;
        invalidate();
    }

    public void setSuffixTextPadding(float paramFloat)
    {
        this.suffixTextPadding = paramFloat;
        invalidate();
    }

    public void setSuffixTextSize(float paramFloat)
    {
        this.suffixTextSize = paramFloat;
        invalidate();
    }

    public void setTargetText(String paramString)
    {
        this.targetText = paramString;
        invalidate();
    }

    public void setTargetTextSize(float paramFloat)
    {
        this.targetTextSize = paramFloat;
        invalidate();
    }

    public void setUnfinishedStrokeColor(int paramInt)
    {
        this.unfinishedStrokeColor = paramInt;
        invalidate();
    }

    public float sp2px(Context paramContext, float paramFloat)
    {
        return paramFloat * paramContext.getResources().getDisplayMetrics().scaledDensity;
    }
}

