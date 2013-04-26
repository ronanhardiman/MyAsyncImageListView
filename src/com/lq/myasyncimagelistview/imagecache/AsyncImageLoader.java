package com.lq.myasyncimagelistview.imagecache;

import java.io.File;
import java.io.IOException;

import org.apache.http.HttpResponse;

import com.lq.myasyncimagelistview.R;
import com.lq.myasyncimagelistview.utils.Config;
import com.lq.myasyncimagelistview.utils.HttpUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.widget.ImageView;

public class AsyncImageLoader {
	private MemoryCache mCache;
	private FileCache fCache;
	private static AsyncImageLoader asyncImageLoader;
	
	private AsyncImageLoader(Context context){
		this.mCache = new MemoryCache();
		this.fCache = new FileCache(context);
	}
	
	class AsyncImageDownTask extends AsyncTask<Void, Void, Bitmap>{
		
		private ImageView imageView;
		private String fileName;
		public AsyncImageDownTask(ImageView imageView,String fileName){
			this.imageView = imageView;
			this.fileName = fileName;
		}
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
//			imageView.setImageResource(R.drawable.ic_launcher);
		}
		
		@Override
		protected Bitmap doInBackground(Void... params) {
			String url = fileName;
			HttpResponse httpResponse = new HttpUtils().requestGet(url, null);
			Bitmap bitmap = null;
			if(null != httpResponse && httpResponse.getEntity() != null){
				try {
					bitmap = BitmapFactory.decodeStream(httpResponse.getEntity().getContent());
				} catch (IllegalStateException e) {
					e.printStackTrace();
					return null;
				} catch (IOException e) {
					e.printStackTrace();
					return null;
				}
			}
			return bitmap;
		}
		
		@Override
		protected void onPostExecute(Bitmap result) {
			super.onPostExecute(result);
			if(null != result && null != imageView)
			imageView.setImageBitmap(result);
			
			//setting caches 
			mCache.putBitmap(fileName, result);
			fCache.putFile(fileName, result);
		}
		
	}
	
	public void displayBitmap(ImageView imageView,String fileName){
		if(TextUtils.isEmpty(fileName))
			return;
		Bitmap bitmap = getBitmapFromCache(fileName);
		
		if(null != bitmap){
			imageView.setImageBitmap(bitmap);
		}else{
			//if no cache create downloadTask
			new AsyncImageDownTask(imageView,fileName).execute();
		}
		
	}
	
	private Bitmap getBitmapFromCache(String fileName) {
		Bitmap bitmap = null;
		
		//first search from memoryCache
		bitmap = mCache.getBitmap(fileName);
		//second search from fileCache
		if(null == bitmap){
			File file = fCache.getFile(fileName);
			if(null != file){
				bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), null);
			}
		}
		//third 
		return bitmap;
	}

	public static AsyncImageLoader getInstance(Context context){
		if(null == asyncImageLoader)
			asyncImageLoader = new AsyncImageLoader(context);
		
		return asyncImageLoader;
	}
}
