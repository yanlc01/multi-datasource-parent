package com.multi.datasource.component;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 数据源会根据determineCurrentLookupKey()的返回值来选取
 */
public class MultiDataSource extends AbstractRoutingDataSource {
	
	private final ThreadLocal<String> sourceKey = new InheritableThreadLocal<String>();

	public void setDataSource(String sourceName) {
		sourceKey.set(sourceName);
	}

	@Override
	protected Object determineCurrentLookupKey() {
		return sourceKey.get();
	}

}
