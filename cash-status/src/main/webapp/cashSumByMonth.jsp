<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="service.*" %>
<%
	int year = 0;
	if(request.getParameter("year") == null){
		Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		System.out.print(year);
	}else{
		year = Integer.parseInt(request.getParameter("year"));
	}
	String category = request.getParameter("category");
	CashService cashService = new CashService();
	ArrayList<HashMap<String,Object>> list = cashService.getCashSumByMonth(year, category);	
	
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
	<h1><%=year %>년 월별 <%=category %>합계</h1>
	<table border="1">
		<tr>
			<th>월</th>
			<th><%=category %>합계</th>
		</tr>
			<%
				for(HashMap<String,Object> m:list){
			%>
	 		<tr>
					<td><%=m.get("month")%></td>
					<td><%=m.get("price") %></td>
		   </tr>
		   <%
				}
		   %>

	</table>
	
	<%
		if(year> minYear){
	%>
		<a href="<%=request.getContextPath() %>/cashSumByMonth.jsp?category=<%=category %>&year=<%=year-1 %>">이전</a>
	<%
		}
	%>
	<% 
      if(year < maxYear) {
    %>
         <a href="<%=request.getContextPath()%>/cashSumByMonth.jsp?category=<%=category%>&year=<%=year+1%>">다음</a>   
    <%      
      }
    %>


</body>
</html>