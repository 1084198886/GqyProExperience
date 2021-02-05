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
    Scroller("Scroller实现下拉回弹"),
    DrawViewPagerIndicator("Canvas绘制ViewPager指示器"),
    CoordinatorLayout("CoordinatorLayout+TabLayout+ViewPager嵌套滚动"),
    GoogleScrollingActivity("Google官方Demo中折叠工具栏效果"),
    MatialDesignBehavior("自定义Behavior"),
    BottomSheetBehavior("Google官方BottomSheetBehavior使用之BottomSheetDialog"),
    CoverHeaderScrollBehavior("自定义Behavior实现RecyclerView上滑覆盖Header效果"),
    ImageSelector("知乎图片选择器");

    var desc = desc
}