<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	request.setCharacterEncoding("utf-8");
	String pUserId = request.getParameter("userId");
	String pUserPw = request.getParameter("userPw");
	String pUserName = request.getParameter("userName");
	String pUserEmail = request.getParameter("userEmail");
	
	int pZonecode = Integer.parseInt(request.getParameter("zonecode"));
	String pUserAddress = request.getParameter("userAddress");
	String pDetailAddress = request.getParameter("detailAddress");
	String pExtraAddress = request.getParameter("extraAddress");
	
	
	
	String driver = "oracle.jdbc.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String id = "jjang051";
	String pw = "1234";
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	String sql = "insert into member values (?,?,?,?,?,?,?,?)";
	
	Class.forName(driver);
	conn = DriverManager.getConnection(url, id, pw);
	pstmt = conn.prepareStatement(sql);
	pstmt.setString(1, pUserId);
	pstmt.setString(2, pUserName);
	pstmt.setString(3, pUserPw);
	pstmt.setString(4, pUserEmail);
	pstmt.setInt(5, pZonecode);
	pstmt.setString(6, pUserAddress);
	pstmt.setString(7, pDetailAddress);
	pstmt.setString(8, pExtraAddress);
	
	int result = pstmt.executeUpdate(); // select 를 제외한 나머지 update, delete , insert
	if(result > 0) {
		response.sendRedirect("login-form.jsp");
	} else {
		out.println("<script>alert('서버오류입니다. 잠시뒤에 다시 시도해 주세요.'); history.back();</script>");
	}
%>









