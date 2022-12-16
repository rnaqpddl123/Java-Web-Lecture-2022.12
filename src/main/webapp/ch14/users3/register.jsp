<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원 가입</title>
    <style>
        td {
            text-align: center;
            padding: 3px;
        }
    </style>
</head>
<body style="margin: 40px;">
    <h1>회원 가입</h1>
    <hr>
    <form action="/jw/ch14/users3/register" method="post">
        <table>
            <tr>
                <td>사용자 ID</td>
                <td><input type="text" name="uid"></td>
            </tr>
            <tr>
                <td>비밀번호</td>
                <td><input type="password" name="pwd"></td>
            </tr>
            <tr>
                <td>이름</td>
                <td><input type="text" name="uname"></td>
            </tr>
            <tr>
                <td>email</td>
                <td><input type="email" name="email"></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="가입"></td>
            </tr>
            
        </table>
    </form>
    
</body>
</html>