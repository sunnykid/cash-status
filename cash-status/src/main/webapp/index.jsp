<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<ol>
		<li>
			<a href="<%=request.getContextPath()%>/cashList.jsp?category=수입">연도별 수입 목록</a> 
		</li>
		<li>
			<a href="<%=request.getContextPath()%>/cashList.jsp?category=지출">연도별 지출 목록</a> 
		</li>
		<li>
			<a href="<%=request.getContextPath()%>/cashSumByMonth.jsp?category=수입">월별 수입 목록</a> 
		</li>
		<li>
			<a href="<%=request.getContextPath()%>/cashSumByMonth.jsp?category=지출">월별 지출 목록</a> 
		</li>	
		<li>
			<a href="<%=request.getContextPath()%>/cashYearStatistics.jsp">연도별 통계</a> 
		</li>	

			
	</ol>
</body>
</html>