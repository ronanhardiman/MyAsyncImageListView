package com.lq.myasyncimagelistview.imagecache;

import java.util.Collections;
import java.util.WeakHashMap;

import android.graphics.Bitmap;
import android.text.TextUtils;

public class MemoryCache {
	private WeakHashMap<String, Bitmap> cache = new WeakHashMap<String, Bitmap>();
	
	public Bitmap getBitmap(String key){
		if(null == key){
			return null;
		}
		return cache.get(key);
	}
	public Boolean putBitmap(String key,Bitmap bitmap){
		if(!TextUtils.isEmpty(key) && !cache.containsKey(key) && null != bitmap){
			Collections.synchronizedMap(cache).put(key, bitmap);//线程安全
//			cache.put(key, bitmap);
			return true;
		}
		return false;
	}
	
	public void clear(){
		cache.clear();
	}
}
