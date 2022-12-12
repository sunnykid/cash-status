package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

public class CashDao {
	
	public HashMap<String,Object> selectMaxMinYear(Connection conn) throws Exception{
		HashMap<String,Object> map = null;
		String sql = "SELECT "
	               + "   (SELECT MIN(YEAR(cash_date)) FROM cash) minYear"
	               + ", (SELECT MAX(YEAR(cash_date))FROM cash) maxYear"
	               + " FROM DUAL";
		PreparedStatement stmt = conn.prepareStatement(sql);
		System.out.print(sql);

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
		stmt.setString(1, category);
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
