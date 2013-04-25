package com.lq.myasyncimagelistview.adapter;

import java.util.ArrayList;
import java.util.List;

import com.lq.myasyncimagelistview.R;
import com.lq.myasyncimagelistview.bean.ImageItemBean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageViewAdapter extends ArrayAdapter<ImageItemBean> {

	private ArrayList<ImageItemBean> itemList;
	private LayoutInflater inflater;
	private int layoutID;

	public ImageViewAdapter(Context context, int Resource,
			ArrayList<ImageItemBean> list) {
		super(context, Resource, list);
		this.itemList = list;
		this.inflater = LayoutInflater.from(context);
		this.layoutID = Resource;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (null == convertView) {
			convertView = inflater.inflate(layoutID, null);
			viewHolder = new ViewHolder();

			viewHolder.imageView = (ImageView) convertView.findViewById(R.id.image_item);
			viewHolder.textView = (TextView) convertView.findViewById(R.id.textview);
		} else {

		}
		return super.getView(position, convertView, parent);
	}

	class ViewHolder {
		private ImageView imageView;
		private TextView textView;
	}
}
