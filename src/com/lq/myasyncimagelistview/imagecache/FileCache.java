package com.lq.myasyncimagelistview.imagecache;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

public class FileCache {
	private File cacheDir;
	
	public FileCache(Context context){
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			cacheDir = new File(Environment.getExternalStorageDirectory(), "AsyncImage/images");
		}else{
			cacheDir = context.getCacheDir();
		}
		if(!cacheDir.exists()){
			cacheDir.mkdirs();
		}
	}
	
	public File getFile(String key){
		File file = new File(cacheDir, key);
		if(file.exists()){
			return file;
		}else{
			
		}
		return null;
	}
	
	public boolean putFile(String key,Bitmap bitmap){
		File  file = new File(cacheDir,key);
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if(null == file || null == bitmap){
			return false;
		}else{
			try {
				BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
				return bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return false;
			}
		}
	}
	
	public void clear(){
		File[] files = cacheDir.listFiles();
		for (File file : files) {
			file.delete();
		}
	}
}
