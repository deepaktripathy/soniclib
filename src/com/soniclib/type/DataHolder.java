package com.soniclib.type;

public abstract class DataHolder implements IDataHolder{
	
	public Class getType() {
		//optional, throw unsupported operation exception; subclasses must implement this.
		throw new UnsupportedOperationException("Optional method; not implemented in the subclass");		
	}

}
