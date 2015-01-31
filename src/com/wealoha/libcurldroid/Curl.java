package com.wealoha.libcurldroid;

import android.util.Log;

import com.wealoha.libcurldroid.CurlOpt.OptFunctionPoint;
import com.wealoha.libcurldroid.CurlOpt.OptLong;
import com.wealoha.libcurldroid.CurlOpt.OptObjectPoint;

/**
 * Curl Jni Wrapper</br>
 * 
 * Usage: See MainActivity in demo project<br/>
 * 
 * 
 * Curl object isn't thread safe, DO NOT share one Curl instant through multiple threads.
 * 
 * @author javamonk
 * @createTime 2015-01-29 12:39:39
 * @see http://curl.haxx.se/libcurl/c/
 */
public class Curl {
	
	private static final String TAG = Curl.class.getSimpleName();
	
	private long handle;
	
	public interface Callback {
		public int callback(byte[] data);
	}
	
	/**
	 * 
	 * @param flag {@link CurlConstant#CURL_GLOBAL_*}
	 * @return
	 */
	public CurlCode curlGlobalInit(int flag) {
		Log.v(TAG, "curlGlobalInit: " + flag);
		return CurlCode.fromValue(curlGlobalInitNative(flag));
	}
	
	private native int curlGlobalInitNative(int flags);
	
	public native void curlGlobalCleanup();
	
	public void curlEasyInit() throws CurlException  {
		Log.v(TAG, "curlEastInit");
		handle = curlEasyInitNative();
		if (handle == 0) {
			throw new CurlException();
		}
	}
	
	private native long curlEasyInitNative();
	
	public void curlEasyCleanup() {
		Log.v(TAG, "curlEastCleanup: " + handle);
		curlEasyCleanupNative(handle);
		handle = 0;
	}
	
	private native void curlEasyCleanupNative(long handle);
	
	/**
	 * 
	 * @param opt {@link OptLong}
	 * @param value
	 * @return
	 */
	public CurlCode curlEasySetopt(OptLong opt, long value) {
		Log.v(TAG, "curlEastSetopt: " + opt + "=" + value);
		return CurlCode.fromValue(curlEasySetoptLongNative(handle, opt.getValue(), value));
	}
	
	private native int curlEasySetoptLongNative(long handle, int opt, long value); 
	
	public CurlCode curlEasySetopt(OptFunctionPoint opt, Callback callback) {
		Log.v(TAG, "curlEastSetopt: " + opt + "=" + callback);
		return CurlCode.fromValue(curlEasySetoptFunctionNative(handle, opt.getValue(), callback));
	}
	
	private native int curlEasySetoptFunctionNative(long handle, int opt, Callback callback);
	
	public CurlCode curlEasySetopt(OptObjectPoint opt, String value) {
		Log.v(TAG, "curlEastSetopt: " + opt + "=" + value);
		return CurlCode.fromValue(curlEasySetoptObjectPointNative(handle, opt.getValue(), value));
	}
	
	private native int curlEasySetoptObjectPointNative(long handle, int opt, String value);
	
	public CurlCode curlEasyPerform() {
		Log.v(TAG, "curlEasyPerform");
		return CurlCode.fromValue(curlEasyPerformNavite(handle));
	}
	
	private native int curlEasyPerformNavite(long handle);
	
	static {
		System.loadLibrary("curldroid");
	}
}
