<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="service.*" %>
<%@ page import="java.util.*" %>
<%
	int year = 0;
	if(request.getParameter("year") == null){
		Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		System.out.print(year);
	}else{
		year = Integer.parseInt(request.getParameter("year"));
	}

	CashService cashService = new CashService();

	ArrayList<HashMap<String,Object>> list = cashService.getCashListByMonth(year);
	HashMap<String,Object> map = cashService.getMaxMinYear();	
	int minYear = (Integer)(map.get("minYear"));
	int maxYear = (Integer)(map.get("maxYear"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1> <%=year %>년도 월별 수입/지출 통계</h1>
	<table border="1">
		<tr>
			<th>월</th>
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
				<td><%=m.get("cashMonth")%></td>
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
	<%
		if(year> minYear){
	%>
		<a href="<%=request.getContextPath() %>/cashMonthStatistics.jsp?year=<%=year-1 %>">이전</a>
	<%
		}
	%>
	<% 
      if(year < maxYear) {
    %>
         <a href="<%=request.getContextPath()%>/cashMonthStatistics.jsp?year=<%=year+1%>">다음</a>   
    <%      
      }
    %>	
</body>
</html>