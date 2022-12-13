package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import dao.CashDao;
import util.DBUtil;

public class CashService {
	private CashDao cashDao;
	
	public HashMap<String,Object> getMaxMinYear()throws Exception{
		HashMap<String,Object> map = null;
		Connection conn = null;
		try {
			DBUtil dbUtil = new DBUtil();
			conn = dbUtil.getConnection();
			CashDao cashDao = new CashDao();
			map = cashDao.selectMaxMinYear(conn);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			conn.close();
		}
		
		return map;
	}
	
	public ArrayList<HashMap<String,Object>> getCashListByYear() {
		Connection conn = null;
		ArrayList<HashMap<String,Object>> list = null;
		try {
			DBUtil dbUtil = new DBUtil();
			conn = dbUtil.getConnection();
			CashDao cashDao = new CashDao();
			list = cashDao.selectYearStatistics(conn);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	public ArrayList<HashMap<String,Object>> getCashListByCategory(String category) {
		Connection conn = null;
		ArrayList<HashMap<String,Object>> list = null;
		try {
			conn = new DBUtil().getConnection();
			cashDao = new CashDao();
			list = cashDao.selectCashListByCategory(conn, category);
			//conn.setAutoCommit(false);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	public ArrayList<HashMap<String,Object>> getCashSumByMonth(int year,String category) {
		Connection conn = null;
		ArrayList<HashMap<String,Object>> list = null;
		try {
			conn = new DBUtil().getConnection();
			cashDao = new CashDao();
			list = cashDao.selectCashSumByMonth(conn, year, category);
			//conn.setAutoCommit(false);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}	
	
	
	public ArrayList<HashMap<String,Object>> getCashListByMonth(int year) {
		Connection conn = null;
		ArrayList<HashMap<String,Object>> list = null;
		try {
			DBUtil dbUtil = new DBUtil();
			conn = dbUtil.getConnection();
			CashDao cashDao = new CashDao();
			list = cashDao.selectMonthStatistics(conn,year);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}	
}
