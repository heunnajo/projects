<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>    <!-- script 실행할 수 있도록 라이브러리 불러옴 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width", initial-scale="1">
<title>흔나의 게시판</title>
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/custom.css">
</head>
<body>
	<%
		String userID = null;
		if(session.getAttribute("userID") != null){
			userID = (String) session.getAttribute("userID");	
		}
	%>
	<nav class="navbar navbar-default">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="main.jsp">JSP 게시판 웹 사이트</a>
		</div>
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="main.jsp">메인</a></li>
				<li><a href="bbs.jsp">게시판</a></li>
				<li class="active"><a href="smartbill.jsp">세금계산서 발행</a></li>
			</ul>
			<%
				if(userID == null){//로그인 안 되어있을 때
			%>		
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">접속하기<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="login.jsp">로그인</a></li>
						<li><a href="join.jsp">회원가입</a></li>
					</ul>
				</li>
			</ul>
			<%
				} else{//로그인 되어있을 때
			%>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">회원관리<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="logoutAction.jsp">로그아웃</a></li>
					</ul>
				</li>
			</ul>
			
			<%
				}
			%>
			
		</div>
	</nav>
	<div class="container">
		<div class="row">
		<form method="post" action="dtiInvoiceAction.jsp">
			<table class="table table-striped" style="text-align; center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th colspan="2" style="background-color: #eeeeee; text-align: center;">세금계산서 발행</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>관리번호</td>
						<td><input type="text" class="form-control" placeholder="관리번호" name="conversationID" maxlength="50"></td>
					</tr>
					<tr>
						<td>매입/매출</td>
						<td><textarea class="form-control" placeholder="매입/매출" name="supBuyType"></textarea></td>
					</tr>
					<tr>
						<td>정/역</td>
						<td><input type="text" class="form-control" placeholder="정/역" name="direction" maxlength="50"></td>
					</tr>
					<tr>
						<td>세금계산서 일자</td>
						<td><textarea class="form-control" placeholder="세금계산서 일자" name="dtiDate" maxlength="2048"></textarea></td>
					</tr>
					<tr>
						<td>세금계산서 종류</td>
						<td><input type="text" class="form-control" placeholder="세금계산서 종류" name="dtiType" maxlength="50"></td>
					</tr>
					<tr>
						<td>영수 구분</td>
						<td><textarea class="form-control" placeholder="영수 구분" name="taxDemand" ></textarea></td>
					</tr>
					<tr>
						<td>공급자 사업자등록번호</td>
						<td><input type="text" class="form-control" placeholder="공급자 사업자등록번호" name="supComRegno" maxlength="50"></td>
					</tr>
					<tr>
						<td>공급자 대표자 성명</td>
						<td><textarea class="form-control" placeholder="공급자 대표자 성명" name="supRepName" maxlength="2048" ></textarea></td>
					</tr>
					<tr>
						<td>공급자 상호</td>
						<td><input type="text" class="form-control" placeholder="공급자 상호" name="supComName" maxlength="50"></td>
					</tr>
					<tr>
						<td>공급자 주소</td>
						<td><textarea class="form-control" placeholder="공급자 주소" name="supComAddr" maxlength="2048" ></textarea></td>
					</tr>
					<tr>
						<td>공급받는 자 사업자등록번호</td>
						<td><textarea class="form-control" placeholder="공급받는 자 사업자등록번호" name="byrComRegno" maxlength="2048" ></textarea></td>
					</tr>
					<tr>
						<td>공급받는 자 대표자 성명</td>
						<td><textarea class="form-control" placeholder="공급받는 자 대표자 성명" name="byrRepName" maxlength="2048" ></textarea></td>
					</tr>
					<tr>
						<td>공급받는 자 상호</td>
						<td><textarea class="form-control" placeholder="공급받는 자 상호" name="byrComName" maxlength="2048" ></textarea></td>
					</tr>
					<tr>
						<td>공급받는 자 주소</td>
						<td><textarea class="form-control" placeholder="공급받는 자 주소" name="byrComAddr" maxlength="2048" ></textarea></td>
					</tr>
					<tr>
						<td>공급받는 자 담당자 이메일</td>
						<td><textarea class="form-control" placeholder="공급받는 자 담당자 이메일" name="byrEmail" maxlength="2048" ></textarea></td>
					</tr>
					<tr>
						<td>공급가액 합계</td>
						<td><textarea class="form-control" placeholder="공급가액 합계" name="supAmount" maxlength="2048" ></textarea></td>
					</tr>
					<tr>
						<td>세액 합계</td>
						<td><textarea class="form-control" placeholder="세액 합계" name="taxAmount" maxlength="2048" ></textarea></td>
					</tr>
					<tr>
						<td>총 금액</td>
						<td><textarea class="form-control" placeholder="총 금액" name="totalAmount" maxlength="2048" ></textarea></td>
					</tr>
					<tr>
						<td>거래명세서 구분</td>
						<td><textarea class="form-control" placeholder="거래명세서 구분" name="DttYn" maxlength="2048" ></textarea></td>
					</tr>
					<tr>
						<td>수정코드</td>
						<td><textarea class="form-control" placeholder="수정코드" name="amendCode" maxlength="2048" ></textarea></td>
					</tr>
					<tr>
						<td>당초승인번호</td>
						<td><textarea class="form-control" placeholder="당초승인번호" name="oriIssueId" maxlength="2048" ></textarea></td>
					</tr>
					
				</tbody>
			</table>
			<input type="submit" class="btn btn-primary pull-right" value="발행">
		</form>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
</html>