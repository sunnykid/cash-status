<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="service.*" %>
<%
	CashService cashService = new CashService();
	
	ArrayList<HashMap<String,Object>> list = cashService.getCashListByYear();

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1> 연도별 수입/지출 통계</h1>
	<table border="1">
		<tr>
			<th>연도</th>
			<th>수입총건수</th>
			<th>수입합계</th>
			<th>수입평균</th>
			<th>지출총건수</th>
			<th>지출합계</th>
			<th>지출평균</th>
		</tr>
		<%
			for(HashMap<String,Object> m: list){
		%>
			<tr>
				<td><a href="<%=request.getContextPath()%>/cashMonthStatistics.jsp?year=<%=m.get("cashYear")%>"><%=m.get("cashYear")%></a></td>
				<td><%=m.get("importCashCount")%></td>
				<td><%=m.get("importCashSum")%></td>
				<td><%=m.get("importCashAvg")%></td>
				<td><%=m.get("exportCashCount")%></td>
				<td><%=m.get("exportCashSum")%></td>
				<td><%=m.get("exportCashAvg")%></td>
			</tr>
		<%
			}
		%>
	</table>
</body>
</html>