package com.lq.myasyncimagelistview.utils;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

public class HttpUtils {
	private HttpClient defaultClient;
	public HttpUtils(){
		this.defaultClient = DefaultClient.getDefaultClientInstance();
	}
	
	public HttpResponse requestGet(String url,Map<String,String> headers){
		HttpGet httpGet = new HttpGet(url);
		if(null != headers){
			Iterator<Entry<String, String>> iterator = headers.entrySet().iterator();
			while(iterator.hasNext()){
				Entry<String,String> entry = iterator.next();
				String headerName = entry.getKey();
				String headerValues = entry.getValue();
				httpGet.setHeader(headerName, headerValues);
			}
		}
		HttpResponse response = null;
		try {
			response = defaultClient.execute(httpGet);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		return response;
	}
}
