<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	pageContext.setAttribute("pName", "페이지");
	pageContext.setAttribute("name", "페이지");

%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>내장 객체</title>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://kit.fontawesome.com/d119a19f5a.js" crossorigin="anonymous"></script>
</head>
<body>
    <div class="container-fluid p-5 bg-primary text-white text-center">
        <h1>JSP EL(Expression Language, 표현언어)</h1>
        <p>2. 내장객체</p>
    </div>
    
    <div class="container mt-5">
        <div class="row">
            <div class="col-2"></div>
            <div class="col-8">
                <table class="table">
                    <tr><th>표현식</th><th>결과</th></tr>
                    <tr><td>\${param.id}</td><td>${param.id}</td></tr>         
                    <tr><td>\${param.name}</td><td>${param.name}</td></tr>      
                    
                    <!-- 내장객체 보는법 -->
                    <tr><td></td><td></td></tr>      
                    <tr><td>\${application.aName}</td><td>${applicationScope.aName}</td></tr>      
                    <tr><td>\${sessionScope.sName}</td><td>${sessionScope.sName}</td></tr>      
                    <tr><td>\${requestScope.rName}</td><td>${requestScope.rName}</td></tr>     
                    <tr><td>\${pageScope.pName}</td><td>${pageScope.pName}</td></tr>     
                    <tr><td>\${aName}</td><td>${aName}</td></tr>   <!--applicationScope. 같이 스코프 없이 이름만 적어도 가능  --> 
                    <tr><td>\${sName}</td><td>${sName}</td></tr>      
                    <tr><td>\${rName}</td><td>${rName}</td></tr>    
                    <tr><td>\${pName}</td><td>${pName}</td></tr>  
                    <!-- 우선순위(page우선) page > request > session > application -->  
                    <tr><td>\${name}</td><td>${name}</td></tr>    
                    
                    <!-- 브라우저에서 f12눌렀을때 네트워크에서 나오는 헤더정보들 보는법  -->
                    <tr><td></td><td></td></tr>  
                    <tr><td>\${header.host}</td><td>${header.host}</td></tr>   
                    <tr><td>\${header.cookie}</td><td>${header.cookie}</td></tr>   
                    <tr><td>\${header["User-Agent"]}</td><td>${header["User-Agent"]}</td></tr>   
                    
                    
                    <tr><td></td><td></td></tr>  
                    <tr><td>\${header.host}</td><td>${header.host}</td></tr>   
                    <tr><td>\${header["User-Agent"]}</td><td>${header["User-Agent"]}</td></tr>   
                    
                    
                       	
                </table>
            </div>
            <div class="col-2"></div>
        </div>
	</div>
</body>
</html>