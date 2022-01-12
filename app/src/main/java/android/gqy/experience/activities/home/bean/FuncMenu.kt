package android.gqy.experience.activities.home.bean

/**
 * @author gqy
 * @date 2020/3/31
 * @since 1.0.0
 * @see
 * @desc  功能菜单
 */
enum class FuncMenu(desc: String) {
    Parcelable("Parcelable使用"),
    NestedScrollView("NestedScrollView嵌套RecyclerView使用"),
    FileProvider("FileProvider使用"),
    CircleProgressBar("Canvas绘制圆形进度条"),
    GRPC_GO("grpc在Go中的使用"),
    Scroller("Scroller实现下拉回弹"),
    ScrollParallax("滚动视差效果"),
    DrawViewPagerIndicator("Canvas绘制ViewPager指示器"),
    CoordinatorLayout("CoordinatorLayout+TabLayout+ViewPager嵌套滚动"),
    GoogleScrollingActivity("Google官方Demo中折叠工具栏效果"),
    MatialDesignBehavior("自定义Behavior"),
    BottomSheetBehavior("Google官方BottomSheetBehavior使用之BottomSheetDialog"),
    CoverHeaderScrollBehavior("自定义Behavior实现RecyclerView上滑覆盖Header效果"),
    ImageSelector("知乎图片选择器"),
    ScrollShowAndHideTitlebar("滑动显示或隐藏标题栏效果"),
    KuaishouAppDetail("快手APP视频详情页"),
    PullToZoomListView("下拉缩放Header的ListView"),
    PullToZoomScrollView("下拉缩放Header的ScrollView"),
    PullToZoomRecyclerView("下拉缩放Header的RecyclerView"),
    TouchEventDemo("触摸事件测试"),
    RequestDisallowInterceptTouchEvent("getParent().requestDisallowInterceptTouchEvent方法的使用");

    var desc = desc
}