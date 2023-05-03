<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="javax.xml.bind.annotation.XmlElement" %>
<%@ page import="javax.xml.bind.annotation.XmlRootelement" %>
<%@ page import="javax.xml.bind.annotation.XmlType" %>
<%@ page import="dti.DtiDAO" %>
<%@ page language="java" import="java.sql.*"%>
<%@ page import="java.util.*" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="dti" class="dti.Dti" scope="page" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP 게시판 웹 사이트</title>
</head>
<body>
	<%
		String conversationID = (String) request.getParameter("conversationID");
		String supBuyType = (String) request.getParameter("supBuyType");
		String direction = (String) request.getParameter("direction");
		String dtiDate = (String) request.getParameter("dtiDate");
		String dtiType = (String) request.getParameter("dtiType");
		String taxDemand = (String) request.getParameter("taxDemand");
		String supComRegno = (String) request.getParameter("supComRegno");
		String supRepName = (String) request.getParameter("supRepName");
		String supComName = (String) request.getParameter("supComName");
		String supComAddr = (String) request.getParameter("supComAddr");
		String byrComRegno = (String) request.getParameter("byrComRegno");
		String byrRepName = (String) request.getParameter("byrRepName");
		String byrComName = (String) request.getParameter("byrComName");
		String byrComAddr = (String) request.getParameter("byrComAddr");
		String byrEmail = (String) request.getParameter("byrEmail");
		String supAmount = (String) request.getParameter("supAmount");
		String taxAmount = (String) request.getParameter("taxAmount");
		String totalAmount = (String) request.getParameter("totalAmount");
		String DttYn = (String) request.getParameter("DttYn");
		String amendCode = (String) request.getParameter("amendCode");
		String oriIssueId = (String) request.getParameter("oriIssueId");
		
		//데이터 확인
		if(conversationID == null) {//유효하지 않은 경우
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('해당 관리번호는 유효하지 않습니다.')");
			script.println("location.href = 'dtiInvoice.jsp'");
			script.println("</script>");
		} else{//유효한 경우
			if(conversationID == null || supBuyType == null || direction == null){
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('입력이 안된 사항이 있습니다.')");
				script.println("history.back()");
				script.println("</script>");
			} else{//실제 DB로 등록함.
				DtiDAO dtiDAO = new DtiDAO();
				int result = dtiDAO.write(conversationID, supBuyType, direction, dtiDate, dtiType,
						taxDemand, supComRegno, supRepName, supComName, supComAddr,
						byrComRegno, byrRepName, byrComName, byrComAddr, byrEmail,
						supAmount, taxAmount, totalAmount, DttYn, amendCode, oriIssueId);
				if(result == -1){
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("alert(세금계산서 발행에 실패했습니다.')");
					script.println("history.back()");
					script.println("</script>");
				} else {//db 저장 성공
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("alert(세금계산서 발행에 성공했습니다.')");
					script.println("location.href = 'dtiInvoice.jsp'");
					script.println("</script>");
				} 
			}
			
		}
		%>
	<!--
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
	<script>
		var conversationID = document.getElementsByName("conversationID").value[0];
		var supBuyType = document.getElementsByName("supBuyType").value;
		var direction = document.getElementsByName("direction").value;
		var dtiDate = document.getElementsByName("dtiDate").value;
		var dtiType = document.getElementsByName("dtiType").value;
		var taxDemand = document.getElementsByName("taxDemand").value;
		var supComRegno = document.getElementsByName("supComRegno").value;
		var supRepName = document.getElementsByName("supRepName").value;
		var supComName = document.getElementsByName("supComName").value;
		var supComAddr = document.getElementsByName("supComAddr").value;
		var byrComRegno = document.getElementsByName("byrComRegno").value;
		var byrRepName = document.getElementsByName("byrRepName").value;
		var byrComName = document.getElementsByName("byrComName").value;
		var byrComAddr = document.getElementsByName("byrComAddr").value;
		var byrEmail = document.getElementsByName("byrEmail").value;
		var supAmount = document.getElementsByName("supAmount").value;
		var taxAmount = document.getElementsByName("taxAmount").value;
		var totalAmount = document.getElementsByName("totalAmount").value;
		var DttYn = document.getElementsByName("DttYn").value;
		var amendCode = document.getElementsByName("amendCode").value;
		var oriIssueId = document.getElementsByName("oriIssueId").value;
		
		console.log('conversationID'+conversationID);
	</script>
	-->
</body>
</html>