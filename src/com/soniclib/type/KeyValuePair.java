package com.soniclib.type;


/**
 * A container for a single key:value, null values are not checked. This is better to hold a list of keyValuePairs 
 * instead of a Map as we can use dynamically generated key/values, even though we can do the same for Maps.
 * 
 * @author deepak.tripathy
 *
 */
public class KeyValuePair {
	private IDataHolder key;
	private IDataHolder value;
	
	public KeyValuePair(IDataHolder key, IDataHolder value) {
		this.key = key;
		this.value = value;
	}
	
	public IDataHolder getKey() {
		return key;
	}
	public void setKey(IDataHolder key) {
		this.key = key;
	}
	public IDataHolder getValue() {
		return value;
	}
	public void setValue(IDataHolder value) {
		this.value = value;
	}
	
}
