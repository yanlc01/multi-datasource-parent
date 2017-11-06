package com.multi.sessionFactory.component;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * MultipleDataSource实现AbstractRoutingDataSource抽象类，然后实现了determineCurrentLookupKey方法，
 * 		这个方法用于选择具体使用targetDataSources中的哪一个数据源
 * 
 * 当MyBatis执行查询时会先选择数据源，选择顺序时现根据determineCurrentLookupKey方法返回的值到targetDataSources中去找，
 * 		若能找到怎返回对应的数据源，若找不到返回默认的数据源defaultTargetDataSource
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
