package android.gqy.experience.activities.drawindicator;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * created by gqy on 2021/02/02
 *
 * @desc
 * @since 1.0.1
 */
public class ViewPagerFragmentPageAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments = new ArrayList<>();

    void setFragments(List<Fragment> list) {
        fragments.clear();
        fragments.addAll(list);
    }

    public ViewPagerFragmentPageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
