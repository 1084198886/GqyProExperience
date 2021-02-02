package android.gqy.experience.activities.coordinatorlayout

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import java.util.ArrayList

/**
 * created by gqy on 2021/02/02
 *
 * @desc
 * @since 1.0.1
 */
class CoordinatorPageAdapter(fm: FragmentManager, behavior: Int) :
    FragmentPagerAdapter(fm, behavior) {
    private val fragments = ArrayList<Fragment>()
    private val titles= ArrayList<String>()

    fun setFragments(list: List<Fragment>) {
        fragments.clear()
        fragments.addAll(list)
    }
    fun setTitles(list: List<String>) {
        titles.clear()
        titles.addAll(list)
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    /**
     * 一定要重写
     */
    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }
}