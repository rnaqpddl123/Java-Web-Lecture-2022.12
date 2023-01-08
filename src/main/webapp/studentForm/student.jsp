<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>과제 2번</title>
</head>

<body style="margin: 40px;">
	<h1>과제 2번</h1>
	<hr>
	<h3>테이블생성</h3>
	<form action="/student/create" method="post">테이블 생성
	<tr><td colspan="2"><input type="submit" value="생성"></td></tr>
	</form>
	<br>
	<br>
	<hr>
	<h3>데이터 입력</h3>
	<form action="/student/insert" method="post">
        <table>
            <tr>
                <td>학생 ID</td>
                <td><input type="text" name="sid"></td>
            </tr>
            <tr>
                <td>학생이름</td>
                <td><input type="text" name="sname"></td>
            </tr>
            <tr>
                <td>성별</td>
                <td><input type="text" name="gender"></td>
            </tr>
            <tr>
                <td>입학년도</td>
                <td><input type="text" name="enterYear"></td>
            </tr>
            <tr>
                <td>학과명</td>
                <td><input type="text" name="deptName"></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="가입"></td>
            </tr>
        </table>
    </form>
    <br>
	<br>
	<hr>
	<h3>데이터 수정</h3>
	<form action="/student/update" method="post">
        <table>
            <tr>
                <td>학생 ID</td>
                <td><input type="text" name="sid"></td>
            </tr>
            <tr>
                <td>학생이름</td>
                <td><input type="text" name="sname"></td>
            </tr>
            <tr>
                <td>성별</td>
                <td><input type="text" name="gender"></td>
            </tr>
            <tr>
                <td>입학년도</td>
                <td><input type="text" name="enterYear"></td>
            </tr>
            <tr>
                <td>학과명</td>
                <td><input type="text" name="deptName"></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="수정"></td>
            </tr>
        </table>
    </form>
    <br><br><hr>
    <h3>리스트보기</h3>
    <button onclick="div_show()" id="bt1">리스트보기</button>
    <button onclick="div_hide()" id="bt2" class="d-none">리스트그만보기</button>
    <table id="list" style="display:none">
	    <tr>
	    	<th>학번</th><th>이름</th><th>성별</th><th>입학년도</th><th>학과명</th>
	    </tr>
    	<c:forEach var="student" items="${studentList}">
    	<tr>
    		<td>${student.sid}</td>
    		<td>${student.sname}</td>
    		<td>${student.gender}</td>
    		<td>${student.enterYear}</td>
    		<td>${student.deptName}</td>
    	</tr>
    	</c:forEach>
    </table>
    <br><br><hr>
    <h3>데이터 삭제</h3>
	<form action="/student/delete" method="post">
        <table>
            <tr>
                <td>학생 ID</td>
                <td><input type="text" name="sid" value="3"></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="수정"></td>
            </tr>
        </table>
    </form>
    
    <script>
	    function div_show() {
	     document.getElementById("list").style.display = "block";
	    }
	    function div_hide() {
	     document.getElementById("list").style.display = "none";
	    }
    </script>
</body>
</html>