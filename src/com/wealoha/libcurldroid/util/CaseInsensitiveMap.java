package com.wealoha.libcurldroid.util;

import android.annotation.SuppressLint;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 
 * @author javamonk
 * @createTime 2015-01-31 10:28:32
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class CaseInsensitiveMap extends HashMap {

	private static final long serialVersionUID = -3480280688459075349L;

	@SuppressLint("DefaultLocale") private Object normalizeKey(Object key) {
		if (key != null && key instanceof String) {
			return ((String)key).toLowerCase();
		}
		return key;
	}
	
	@Override
	public Object put(Object key, Object value) {
		return super.put(normalizeKey(key), value);
	}
	
	@Override
	public void putAll(Map map) {
		for (Object key : map.keySet()) {
			put(key, map.get(key));
		}
	}
	@Override
	public Object get(Object key) {
		return super.get(normalizeKey(key));
	}
}
