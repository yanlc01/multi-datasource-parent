package com.multi.sessionFactory.component;

import java.sql.Connection;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.TransactionIsolationLevel;

public class MultiSessionFactory implements SqlSessionFactory {

	@Override
	public SqlSession openSession() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SqlSession openSession(boolean autoCommit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SqlSession openSession(Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SqlSession openSession(TransactionIsolationLevel level) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SqlSession openSession(ExecutorType execType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SqlSession openSession(ExecutorType execType, boolean autoCommit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SqlSession openSession(ExecutorType execType, TransactionIsolationLevel level) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SqlSession openSession(ExecutorType execType, Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Configuration getConfiguration() {
		// TODO Auto-generated method stub
		return null;
	}

}
