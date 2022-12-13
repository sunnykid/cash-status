package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

public class CashDao {
	
	public ArrayList<HashMap<String,Object>> selectMonthStatistics(Connection conn, int year) throws Exception{
		ArrayList<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
		String sql = "SELECT MONTH(t2.cashDate) cashMonth"
				+ "	  , COUNT(t2.importCash) importCashCount"
				+ "	  , IFNULL(SUM(t2.importCash),0) importCashSum"
				+ "	  , IFNULL(AVG(t2.importCash),0) importCashAvg"
				+ "	  , COUNT(t2.exportCash) exportCashCount"
				+ "	  , IFNULL(SUM(t2.exportCash),0) exportCashSum"
				+ "	  , IFNULL(AVG(t2.exportCash),0) exportCashAvg"
				+ " FROM "
				+ "	(SELECT memberId, cashNo,cashDate"
				+ "	       ,if(categoryKind = '수입', cashPrice, null) importCash"
				+ "	       ,if(categoryKind = '지출', cashPrice, null) exportCash"
				+ "	FROM "
				+ "		(SELECT cs.cash_no cashNo"
				+ "		      , cs.cash_date cashDate"
				+ "				, cs.cash_price cashPrice"
				+ "				, cg.category_kind categoryKind"
				+ "				, cs.member_id memberId"
				+ "	  	   FROM cash cs "
				+ "	  	  INNER JOIN category cg ON cs.category_no = cg.category_no) t) t2"
				+ " WHERE t2.memberId='goodee' AND YEAR(t2.cashDate) = ?"
				+ " GROUP BY MONTH(t2.cashDate)"
				+ " ORDER BY MONTH(t2.cashDate)";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, year);
		ResultSet rs = stmt.executeQuery();
	      while(rs.next()) {
	    	  HashMap<String,Object> map = new HashMap<String,Object>();	

	    	  map = new HashMap<String, Object>();
	          map.put("cashMonth", rs.getInt("cashMonth"));
	          map.put("importCashCount", rs.getInt("importCashCount"));
	          map.put("importCashSum", rs.getInt("importCashSum"));
	          map.put("importCashAvg", rs.getInt("importCashAvg"));
	          map.put("exportCashCount", rs.getInt("exportCashCount"));
	          map.put("exportCashSum", rs.getInt("exportCashSum"));
	          map.put("exportCashSum", rs.getInt("exportCashSum"));
	          map.put("exportCashAvg", rs.getInt("exportCashAvg"));
	          list.add(map);
	       }
	       stmt.close();
	       rs.close();				
		return list;
	}
	public ArrayList<HashMap<String,Object>> selectYearStatistics(Connection conn) throws Exception{
		ArrayList<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();		
		String sql = "SELECT YEAR(t2.cashDate) cashYear"
				+ "	  , COUNT(t2.importCash) importCashCount"
				+ "	  , IFNULL(SUM(t2.importCash),0) importCashSum"
				+ "	  , IFNULL(AVG(t2.importCash),0) importCashAvg"
				+ "	  , COUNT(t2.exportCash) exportCashCount"
				+ "	  , IFNULL(SUM(t2.exportCash),0) exportCashSum"
				+ "	  , IFNULL(AVG(t2.exportCash),0) exportCashAvg"
				+ " FROM "
				+ "	(SELECT memberId, cashNo,cashDate"
				+ "	       ,if(categoryKind = '수입', cashPrice, null) importCash"
				+ "	       ,if(categoryKind = '지출', cashPrice, null) exportCash"
				+ "	FROM "
				+ "		(SELECT cs.cash_no cashNo"
				+ "		      , cs.cash_date cashDate"
				+ "				, cs.cash_price cashPrice"
				+ "				, cg.category_kind categoryKind"
				+ "				, cs.member_id memberId"
				+ "	  	   FROM cash cs "
				+ "	  	  INNER JOIN category cg ON cs.category_no = cg.category_no) t) t2"
				+ " WHERE t2.memberId='goodee'"
				+ " GROUP BY year(t2.cashDate)"
				+ " ORDER BY year(t2.cashDate)";
		PreparedStatement stmt = conn.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();
	      while(rs.next()) {
	    	  HashMap<String,Object> map = new HashMap<String,Object>();	

	    	  map = new HashMap<String, Object>();
	          map.put("cashYear", rs.getInt("cashYear"));
	          map.put("importCashCount", rs.getInt("importCashCount"));
	          map.put("importCashSum", rs.getInt("importCashSum"));
	          map.put("importCashAvg", rs.getInt("importCashAvg"));
	          map.put("exportCashCount", rs.getInt("exportCashCount"));
	          map.put("exportCashSum", rs.getInt("exportCashSum"));
	          map.put("exportCashSum", rs.getInt("exportCashSum"));
	          map.put("exportCashAvg", rs.getInt("exportCashAvg"));
	          list.add(map);
	       }
	       stmt.close();
	       rs.close();		
		
		return list;
	}
	
	public HashMap<String,Object> selectMaxMinYear(Connection conn) throws Exception{
		HashMap<String,Object> map = null;
		String sql = "SELECT "
	               + "   (SELECT MIN(YEAR(cash_date)) FROM cash) minYear"
	               + ", (SELECT MAX(YEAR(cash_date))FROM cash) maxYear"
	               + " FROM DUAL";
		PreparedStatement stmt = conn.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();
	      if(rs.next()) {
	          map = new HashMap<String, Object>();
	          map.put("minYear", rs.getInt("minYear"));
	          map.put("maxYear", rs.getInt("maxYear"));
	          
	       }
	       stmt.close();
	       rs.close();
	       return map;
		
	}
	
	//월별 수입/지출 합
	public ArrayList<HashMap<String,Object>> selectCashSumByMonth(
			Connection conn,int year,String category) throws Exception{
		ArrayList<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
		String sql = "SELECT MONTH(cash_date) month, SUM(cash_price) price"
				+ " FROM cash"
				+ " WHERE YEAR(cash_date) = ? AND category = ?"
				+ " GROUP BY MONTH(cash_date)"
				+ " ORDER BY MONTH(cash_date) ASC";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		System.out.print(sql);
		stmt.setInt(1, year);
		stmt.setString(2, category);
		
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			HashMap<String,Object> m = new HashMap<String,Object>();
			m.put("month", rs.getInt("month"));
			m.put("price", rs.getInt("price"));
			list.add(m);
		}
		stmt.close();
		rs.close();
		return list;
	}
	
	public ArrayList<HashMap<String,Object>> selectCashListByCategory(
			Connection conn, String category) throws Exception {
		ArrayList<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
		String sql = "SELECT YEAR(cash_date) year, SUM(cash_price) price"
				+ " FROM cash WHERE category=?"
				+ " GROUP BY YEAR(cash_date)"
				+ " ORDER BY YEAR(cash_date) ASC";
		PreparedStatement stmt = conn.prepareStatement(sql);
		System.out.print(sql);

		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			HashMap<String,Object> m = new HashMap<String,Object>();
			m.put("year", rs.getInt("year"));
			m.put("price", rs.getInt("price"));
			list.add(m);
		}
		
		
		return list;
	}
}
