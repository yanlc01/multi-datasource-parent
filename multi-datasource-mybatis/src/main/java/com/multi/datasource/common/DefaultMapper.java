package com.multi.datasource.common;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface DefaultMapper<T> extends Mapper<T>, MySqlMapper<T> {

}
