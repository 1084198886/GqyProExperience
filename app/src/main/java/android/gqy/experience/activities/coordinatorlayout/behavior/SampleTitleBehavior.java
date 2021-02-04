package android.gqy.experience.activities.coordinatorlayout.behavior;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

/**
 * created by gqy on 2021/02/03
 *
 * @desc
 * @since 1.0.1
 */
public class SampleTitleBehavior extends CoordinatorLayout.Behavior<View> {
    private static final String TAG = "SampleTitleBehavior";
    // 列表顶部和title底部重合时，列表的滑动距离。
    private float deltaY;

    public SampleTitleBehavior() {
    }

    public SampleTitleBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * @param parent     表示CoordinatorLayout
     * @param child      child代表使用该Behavior的View
     * @param dependency 代表要监听的View。这里要监听RecyclerView
     * @return 如果child 是依赖的指定的View 返回true,否则返回false
     * @desc 表示是否给应用了Behavior 的View 指定一个依赖的布局
     */
    @Override
    public boolean layoutDependsOn(@NotNull CoordinatorLayout parent, @NotNull View child, @NotNull View dependency) {
//        Log.e(TAG, "layoutDependsOn=child:" + child + ",dependency:" + dependency);
        return dependency instanceof RecyclerView;
    }

    /**
     * 当被依赖的View状态（如：位置、大小）发生变化时，这个方法被调用
     * 所以我们重写该方法，当RecyclerView的位置变化时，进而改变title的位置
     *
     * @param parent
     * @param child
     * @param dependency
     * @return
     */
    @Override
    public boolean onDependentViewChanged(@NotNull CoordinatorLayout parent, @NotNull View child, @NotNull View dependency) {
        Log.e(TAG, "onDependentViewChanged=child:" + child + ",dependency:" + dependency);
        if (deltaY == 0) {
            deltaY = dependency.getY() - child.getHeight();
        }
        float dy = dependency.getY() - child.getHeight();
        dy = dy < 0 ? 0 : dy;
        float y = -(dy / deltaY) * child.getHeight();
        child.setTranslationY(y);
        /**
         * 改变透明度
         */
        float alpha = 1 - (dy / deltaY);
        child.setAlpha(alpha);
        return true;
    }

    /**
     * @param coordinatorLayout 和Behavior 绑定的View的父CoordinatorLayout
     * @param child             和Behavior 绑定的View
     * @param directTargetChild
     * @param target
     * @param axes              嵌套滑动 应用的滑动方向
     * @param type              {@link ViewCompat#TYPE_TOUCH}
     * @return
     * @desc 当coordinatorLayout 的子View试图开始嵌套滑动的时候被调用。当返回值为true的时候表明
     * coordinatorLayout 充当nested scroll parent 处理这次滑动，需要注意的是只有当返回值为true
     * 的时候，Behavior 才能收到后面的一些nested scroll 事件回调（如：onNestedPreScroll、onNestedScroll等）
     * 这个方法有个重要的参数axes，表明处理的滑动的方向。{@link ViewCompat#SCROLL_AXIS_HORIZONTAL}
     */
    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        Log.e(TAG, "onStartNestedScroll=child:" + child + ",directTargetChild:" + directTargetChild + ",target:" + target + ",axes:" + axes + ",type:" + type);
        return super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, axes, type);
    }

    /**
     * 嵌套滚动发生之前被调用
     * 在nested scroll child 消费掉自己的滚动距离之前，嵌套滚动每次被nested scroll child
     * 更新都会调用onNestedPreScroll。注意有个重要的参数consumed，可以修改这个数组表示你消费
     * 了多少距离。假设用户滑动了100px,child 做了90px 的位移，你需要把 consumed［1］的值改成90，
     * 这样coordinatorLayout就能知道只处理剩下的10px的滚动。
     *
     * @param coordinatorLayout
     * @param child
     * @param target
     * @param dx                用户水平方向的滚动距离
     * @param dy                用户竖直方向的滚动距离
     * @param consumed
     */
    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        Log.e(TAG, "onNestedPreScroll=child:" + child + ",target:" + target + ",dy:" + dy + ",consumed[1]:" + consumed[1] + ",type:" + type);
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
    }


    /**
     * 进行嵌套滚动时被调用
     *
     * @param coordinatorLayout
     * @param child
     * @param target
     * @param dxConsumed        target 已经消费的x方向的距离
     * @param dyConsumed        target 已经消费的y方向的距离
     * @param dxUnconsumed      x 方向剩下的滚动距离
     * @param dyUnconsumed      y 方向剩下的滚动距离
     */
    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type, @NonNull int[] consumed) {
        Log.e(TAG, "onNestedScroll=child:" + child + ",target:" + target + ",dyConsumed:" + dyConsumed + ",dyUnconsumed:" + dyUnconsumed + ",type:" + type + ",consumed[1]:" + consumed[1]);
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type, consumed);
    }

    /**
     * onStartNestedScroll返回true才会触发这个方法，接受滚动处理后回调，可以在这个
     * 方法里做一些准备工作，如一些状态的重置等。
     *
     * @param coordinatorLayout
     * @param child
     * @param directTargetChild
     * @param target
     * @param axes
     */
    @Override
    public void onNestedScrollAccepted(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        Log.e(TAG, "onNestedScrollAccepted=child:" + child + ",directTargetChild:" + directTargetChild + ",target:" + target + ",axes:" + axes + ",type:" + type);
        super.onNestedScrollAccepted(coordinatorLayout, child, directTargetChild, target, axes, type);
    }

    /**
     * 嵌套滚动结束时被调用，这是一个清除滚动状态等的好时机。
     *
     * @param coordinatorLayout
     * @param child
     * @param target
     */
    @Override
    public void onStopNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int type) {
        Log.e(TAG, "onStopNestedScroll=child:" + child + ",target:" + target + ",type:" + type);
        super.onStopNestedScroll(coordinatorLayout, child, target, type);
    }

    /**
     * 用户松开手指并且会发生惯性动作之前调用，参数提供了速度信息，可以根据这些速度信息
     * 决定最终状态，比如滚动Header，是让Header处于展开状态还是折叠状态。返回true 表
     * 示消费了fling.
     *
     * @param coordinatorLayout
     * @param child
     * @param target
     * @param velocityX         x 方向的速度
     * @param velocityY         y 方向的速度
     * @return
     */
    @Override
    public boolean onNestedPreFling(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, float velocityX, float velocityY) {
        Log.e(TAG, "onNestedPreFling=child:" + child + ",target:" + target + ",velocityX:" + velocityX + ",velocityY:" + velocityY);
        return super.onNestedPreFling(coordinatorLayout, child, target, velocityX, velocityY);
    }

    @Override
    public boolean onNestedFling(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, float velocityX, float velocityY, boolean consumed) {
        Log.e(TAG, "onNestedFling=child:" + child + ",target:" + target + ",velocityX:" + velocityX + ",velocityY:" + velocityY + ",consumed:" + consumed);
        return super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed);
    }

    /**
     * @param parent
     * @param child
     * @param layoutDirection
     * @return
     * @desc 可以重写这个方法对子View 进行重新布局
     */
    @Override
    public boolean onLayoutChild(@NonNull CoordinatorLayout parent, @NonNull View child, int layoutDirection) {
        Log.e(TAG, "onLayoutChild=child:" + child + ",layoutDirection:" + layoutDirection);
        return super.onLayoutChild(parent, child, layoutDirection);
    }
}
