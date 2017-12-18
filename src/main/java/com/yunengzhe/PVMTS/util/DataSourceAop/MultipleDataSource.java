package com.yunengzhe.PVMTS.util.DataSourceAop;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class MultipleDataSource extends AbstractRoutingDataSource{

	protected static final ThreadLocal<String> dataSourceKey = new ThreadLocal<String>();
	public static final void setDataSourceKey(String dataSource) {
		dataSourceKey.set(dataSource);
	}
	public static final String getDataSourceKey() {
		return dataSourceKey.get();
	}
	public static final void removeDataSource() {
		dataSourceKey.remove();
	}
	@Override
	protected Object determineCurrentLookupKey() {
		return dataSourceKey.get();
	}
	
	
}
