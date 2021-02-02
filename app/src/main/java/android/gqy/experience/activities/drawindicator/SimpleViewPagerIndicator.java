package android.gqy.experience.activities.drawindicator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * created by gqy on 2021/02/02
 *
 * @desc ViewPager指示器
 * @since 1.0.1
 */
public class SimpleViewPagerIndicator extends LinearLayout {
    private int tabCount;
    private float mTranslationX;
    private Paint paint;

    public SimpleViewPagerIndicator(Context context) {
        super(context);
        init();
    }

    public SimpleViewPagerIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(5);
        paint.setColor(Color.RED);
    }

    /**
     * 开始滚动
     */
    public void scroll(int position, float positionOffset) {
        mTranslationX = getWidth() / tabCount * (position + positionOffset);
        invalidate();
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        // 保存画布状态；
        canvas.save();
        // 移动画布；
        canvas.translate(mTranslationX, getHeight());
        // 划线；
        canvas.drawLine(0, 0, getWidth() / tabCount, 0, paint);
        // 复位；
        canvas.restore();
    }

    public void setTabCount(int tabCount) {
        this.tabCount = tabCount;
    }
}
