package com.lq.myasyncimagelistview.fragment;

import java.util.ArrayList;

import com.lq.myasyncimagelistview.R;
import com.lq.myasyncimagelistview.adapter.ImageViewAdapter;
import com.lq.myasyncimagelistview.adapter.ImageViewBaseAdapter;
import com.lq.myasyncimagelistview.bean.ImageItemBean;
import com.lq.myasyncimagelistview.provider.Images;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class ImageCacheFragment extends Fragment {
	
	private ListView listView;
	private ImageViewAdapter viewAdapter;
	
	private View imagecache_listview;
	private ArrayList<ImageItemBean> imagesList;
	private ImageViewBaseAdapter baseAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		imagecache_listview = inflater.inflate(R.layout.imagecache, container, false);
		
		listView = (ListView) imagecache_listview.findViewById(R.id.listview);
//		viewAdapter = new ImageViewAdapter(getActivity(),R.layout.imagecache_item,imagesList);
//		listView.setAdapter(viewAdapter);
		baseAdapter = new ImageViewBaseAdapter(getActivity(), R.layout.imagecache_item, imagesList);
		listView.setAdapter(baseAdapter);
		return imagecache_listview;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initData();
	}

	private void initData() {
		imagesList = new ArrayList<ImageItemBean>();
		int len = Images.imageThumbUrls.length;
		for (int i = 0; i < len/3; i++) {
			ImageItemBean iBean = new ImageItemBean();
			iBean.setPicture(Images.imageThumbUrls[i]);
			iBean.setText("this is the : "+i);
			imagesList.add(iBean);
		}
	}
	
}
