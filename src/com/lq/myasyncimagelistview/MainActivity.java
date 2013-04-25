package com.lq.myasyncimagelistview;

import java.util.Locale;

import com.lq.myasyncimagelistview.provider.Images;
import com.lq.myasyncimagelistview.widget.AsyncImageView;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements
		ActionBar.TabListener {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;
	private static final String URLS_1 = "https://lh3.googleusercontent.com/_OHO4y8YcQbs/SoWDYIhFrjI/AAAAAAAAKX4/ETS4JGuUYX0/s400/P1080412.JPG";
	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
			actionBar.addTab(actionBar.newTab()
					.setText(mSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}
		
		@Override
		public Fragment getItem(int position) {
			Log.e("lq", "getItem");
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			Fragment fragment = null;
			switch (position) {
			case 0:
				fragment = new DummySectionFragment();
				Bundle args = new Bundle();
				args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
				fragment.setArguments(args);
				
				break;
			case 1:
				fragment = new ImageFragment();
				break;
			case 2:
				fragment = new ImageLocalFragment();
				break;
			default:
				break;
			}
			return fragment;
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();//获取本地语言
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			case 2:
				return getString(R.string.title_section3).toUpperCase(l);
			}
			return null;
		}
	}
	//加载本地图片
	public static class ImageLocalFragment extends Fragment{
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View image_layout = inflater.inflate(R.layout.image_layout, container,false);
			AsyncImageView asyncimage = (AsyncImageView) image_layout.findViewById(R.id.asyncimage);
			String url = "file:///android_asset/265.jpg";
			String url2 = "file:///android_asset/xuyuanshu.png";
			asyncimage.setUrl(url2);
			return image_layout;
		}
	}
	//加载网络图片
	public static class ImageFragment extends Fragment{
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View image_layout = inflater.inflate(R.layout.image_layout, container,false);
			AsyncImageView asyncimage = (AsyncImageView) image_layout.findViewById(R.id.asyncimage);
			asyncimage.setUrl(Images.imageThumbUrls[1]);
			return image_layout;
		}
	}
	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class DummySectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";

		public DummySectionFragment() {
			
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main_dummy,
					container, false);
			RelativeLayout rLayout = (RelativeLayout) rootView.findViewById(R.id.rLayout);
			ListView lView = new ListView(getActivity());
			MyAdapter mAdapter = new MyAdapter();
			lView.setAdapter(mAdapter);
			RelativeLayout.LayoutParams rParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
					RelativeLayout.LayoutParams.MATCH_PARENT);
			
			rLayout.addView(lView,rParams);
			TextView dummyTextView = (TextView) rootView
					.findViewById(R.id.section_label);
			dummyTextView.setText(Integer.toString(getArguments().getInt(
					ARG_SECTION_NUMBER)));
			return rootView;
		}
		public class MyAdapter extends BaseAdapter{
			
			/*public MyAdapter(){
				
			}*/
			private int itemWidth = 90;
			
			@Override
			public int getCount() {
				return 20;
			}
			
			@Override
			public Object getItem(int position) {
				return position;
			}
			
			@Override
			public long getItemId(int position) {
				return position;
			}
			
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				LinearLayout lLayout = new LinearLayout(getActivity());
				lLayout.setOrientation(LinearLayout.HORIZONTAL);
				AsyncImageView aView = new AsyncImageView(getActivity());
				aView.setMaxHeight(itemWidth);
				aView.setMaxWidth(itemWidth);
				aView.setDefaultImageResource(R.drawable.ic_launcher);
				aView.setUrl(Images.imageThumbUrls[position]);
//				aView.setUrl(URLS_1);
				
				TextView tView = new TextView(getActivity());
//				tView.setPadding(5, 5, 5, 5);
				
//				AbsListView.LayoutParams aParams = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT,50);
				LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,itemWidth);
				tView.setGravity(Gravity.RIGHT|Gravity.CENTER_VERTICAL);
				tView.setLeft(10);
				tView.setLayoutParams(lParams);
				tView.setText("this is : "+position);
				
				LinearLayout.LayoutParams lParams_aView = new LinearLayout.LayoutParams(itemWidth,itemWidth);
				lParams_aView.gravity = Gravity.LEFT |Gravity.CENTER_VERTICAL;
				aView.setLayoutParams(lParams_aView);
				lLayout.addView(aView);
				lLayout.addView(tView);
				
				AbsListView.LayoutParams aParams_layout = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT,itemWidth);
				lLayout.setLayoutParams(aParams_layout);
				return lLayout;
			}
			
		}
	}

}
