package com.lq.myasyncimagelistview.adapter;

import java.util.ArrayList;

import com.lq.myasyncimagelistview.R;
import com.lq.myasyncimagelistview.bean.ImageItemBean;
import com.lq.myasyncimagelistview.imagecache.AsyncImageLoader;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageViewBaseAdapter extends BaseAdapter{

	private AsyncImageLoader asyncImageLoader;
	private ArrayList<ImageItemBean> itemList;
	private LayoutInflater inflater;
	private int layoutID;
	
	public ImageViewBaseAdapter(Context context, int Resource,
			ArrayList<ImageItemBean> list){
		this.itemList = list;
		this.inflater = LayoutInflater.from(context);
		this.layoutID = Resource;
		asyncImageLoader = AsyncImageLoader.getInstance(context);
	}
	
	@Override
	public int getCount() {
		return itemList.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	/*@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (null == convertView) {
			convertView = inflater.inflate(layoutID, null);
			viewHolder = new ViewHolder();

			viewHolder.imageView = (ImageView) convertView.findViewById(R.id.image_item);
			viewHolder.textView = (TextView) convertView.findViewById(R.id.textview);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		ImageItemBean iBean = itemList.get(position);
		if(null != iBean){
			asyncImageLoader.displayBitmap(viewHolder.imageView, iBean.getPicture());
			Log.e("lq", "getView iBean url : =="+iBean.getPicture()+"  this is : "+position);
			viewHolder.textView.setText(iBean.getText());
		}
		return convertView;
	}*/
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View linear = inflater.inflate(layoutID, null);
		ImageView image_view = (ImageView) linear.findViewById(R.id.image_item);
		ImageItemBean iBean = itemList.get(position);
		TextView textView = (TextView) linear.findViewById(R.id.textview);
		asyncImageLoader.displayBitmap(image_view,iBean.getPicture() );
		textView.setText(iBean.getText());
		return linear;
	}
	
	
	
	class ViewHolder {
		private ImageView imageView;
		private TextView textView;
	}

}
