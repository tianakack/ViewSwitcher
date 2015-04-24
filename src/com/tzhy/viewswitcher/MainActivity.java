package com.tzhy.viewswitcher;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    protected LinearLayout        mImageIndicator;

    protected int                 mDrawableSpaces[];

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide fragments for each of the sections. We use a {@link FragmentPagerAdapter} derivative,
     * which will keep every loaded fragment in memory. If this becomes too memory intensive, it may be best to switch to a
     * {@link android.support.v13.app.FragmentStatePagerAdapter}.
     */
    ImageViewFragmentPagerAdapter mFragmentPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager                     mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // //
        mImageIndicator = (LinearLayout) findViewById(R.id.imageIndicator);

        // //
        mDrawableSpaces = new int[] { R.drawable.space0, R.drawable.space1, R.drawable.space2, R.drawable.space3,
                R.drawable.space4, R.drawable.space5, R.drawable.space6, R.drawable.space7 };

        for (int i = 0; i < mDrawableSpaces.length; i++) {

            TextView textView = new TextView(MainActivity.this);

            textView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

            if (i == 0) {

                textView.setBackgroundResource(R.drawable.indicator_s);

            } else {

                textView.setBackgroundResource(R.drawable.indicator_n);
            }

            mImageIndicator.addView(textView);
        }

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mFragmentPagerAdapter = new ImageViewFragmentPagerAdapter(getFragmentManager());
        
        mFragmentPagerAdapter.setImageArray(mDrawableSpaces);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mFragmentPagerAdapter);
        mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

            private int mLastPosition = 0;

            @Override
            public void onPageSelected(int arg0) {

                mImageIndicator.getChildAt(mLastPosition).setBackgroundResource(R.drawable.indicator_n);

                mImageIndicator.getChildAt(arg0).setBackgroundResource(R.drawable.indicator_s);

                mLastPosition = arg0;
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

                // TODO Auto-generated method stub

            }
        });
    }

    public class ImageViewFragmentPagerAdapter extends FragmentPagerAdapter {

        int mImageArray[];

        public ImageViewFragmentPagerAdapter(FragmentManager fm) {

            super(fm);
        }

        public void setImageArray(int[] imageArray) {

            if (imageArray != null) {

                mImageArray = imageArray;

            } else {

                mImageArray = new int[] {};
            }
        }

        @Override
        public Fragment getItem(int position) {

            return ImageViewFragment.newInstance(mImageArray[position]);
        }

        @Override
        public int getCount() {

            return mImageArray.length;
        }
    }

    public static class ImageViewFragment extends Fragment {

        private static final String ARG_IMAGE_ID = "arg_image_id";

        public static ImageViewFragment newInstance(int imageID) {

            // //
            Bundle args = new Bundle();

            args.putInt(ARG_IMAGE_ID, imageID);

            // //
            ImageViewFragment fragment = new ImageViewFragment();

            fragment.setArguments(args);

            return fragment;
        }

        public ImageViewFragment() {

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            // //
            Bundle args = getArguments();

            int imageID = args.getInt(ARG_IMAGE_ID);

            // //
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            ImageView imageView = (ImageView) rootView.findViewById(R.id.imageView);

            imageView.setImageResource(imageID);

            return rootView;
        }
    }

}
