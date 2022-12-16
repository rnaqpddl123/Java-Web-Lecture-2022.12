<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>표현언어</title>
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
        <p>1. 연산자</p>
    </div>
    
    <!-- 결과는 맞게 나오는데 에러표시떠서 다주석처리 해놓음 -->
    <div class="container mt-5">
        <div class="row">
            <div class="col-2"></div>
            <div class="col-8">
                <table class="table">
                    <tr><th>계산식</th><th>결과</th></tr>
                    <%-- <tr><td>\${100}</td><td>${100}</td></tr>
                    <tr><td>\${"안녕하세요"}</td><td>${"안녕하세요"}</td></tr>
                    <tr><td>\${10+1}</td><td>${10+1}</td></tr>
                    <tr><td>\${"10" + 1}</td><td>${"10" + 1}</td></tr>
                    <tr><td>\${null + 100}</td><td>${null + 100}</td></tr>
                    <tr><td>\${"안녕" + 11}</td> <td>${"안녕" + 11}</td></tr>  			<!--에러  -->
                    <tr><td>\${"Hello" + "world"}</td> <td>${"Hello" + "world"}</td></tr> <!--에러  -->
                    
                    <tr><td>산술연산자</td><td>결과</td></tr>
                    <tr><td>\${10+10}</td> <td>${10+10}</td></tr>
                    <tr><td>\${3 div 2}</td><td>${3 div 2}</td></tr>	<!-- 나누기 -->
                    <tr><td>\${100 % 9}</td><td>${100 % 9}</td></tr>
                    <tr><td>\${100 mod 9}</td><td>${100 mod 9}</td></tr>	<!-- 나머지  -->
                    
                    <tr><td>비교연산자</td><td>결과</td></tr>
                    <tr><td>\${10==10}</td><td>${10 == 10}</td></tr>
                    <tr><td>\${10 eq 10}</td><td>${10 eq 10}</td></tr>
                    <tr><td>\${10 > 8}</td><td>${10 > 8}</td></tr>
                    <tr><td>\${10 gt 10}</td><td>${10 gt 10}</td></tr>	<!-- gt(grater than) >와 같다-->
                    <tr><td>\${10 gt 10}</td><td>${11 gt 10}</td></tr>
                    <tr><td>\${"hello" == "hello"}</td><td>${"hello" == "hello"}</td></tr>
                    <tr><td>\${"hello" eq "hello"}</td><td>${"hello" eq "hello"}</td></tr>
                    
                    <tr><td>논리식</td><td></td></tr>
                    <tr><td>\${10 ==10 && 20== 20}</td> <td>${10 ==10 && 20== 20}</td></tr>
                    <tr><td>\${10==11 and 20==20}</td><td>${10==11 and 20==20}</td></tr>
                    <tr><td>\${10==9 or 20==10}</td><td>${10==9 or 20==10}</td></tr>
                    <tr><td>\${not 10==10}</td><td>${not 10==10}</td></tr>
                    
                    <tr><td>논리식</td><td>결과</td></tr>
                    <tr><td>\${empty "hello"}</td> <td>${empty "hello"}</td></tr>
                    <tr><td>\${empty ""}</td><td>${empty ""}</td></tr>
                    <tr><td>\${empty null}</td><td>${empty null}</td></tr> --%>
                </table>
            </div>
            <div class="col-2"></div>
        </div>
        <strong>파라메터 값은 ${empty param.num ? "입력하지않음" : param.num} 입니다.</strong>	
	</div>
</body>
</html>