package com.lq.myasyncimagelistview.imagecache;

import java.util.ArrayList;

import com.lq.myasyncimagelistview.R;
import com.lq.myasyncimagelistview.adapter.ImageViewAdapter;
import com.lq.myasyncimagelistview.bean.ImageItemBean;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class ImageCacheFragment extends Fragment {
	
	private ListView listView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View imagecache_listview = inflater.inflate(R.layout.imagecache, container, true);
		listView = (ListView) imagecache_listview.findViewById(R.id.listview);
		
		return imagecache_listview;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ImageViewAdapter viewAdapter = new ImageViewAdapter(getActivity(),R.layout.imagecache_item,new ArrayList<ImageItemBean>());
		listView.setAdapter(viewAdapter);
	}
	
}
