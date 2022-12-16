<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>POJO</title>
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
        <p>4. POJO</p>
    </div>
    
    <div class="container mt-5">
        <div class="row">
            <div class="col-2"></div>
            <div class="col-8">
                <table class="table">
                    <tr><th>표현식</th><th>결과</th></tr>
                    <tr><td>\${m1.id}</td><td>${m1.id}</td></tr>         
                    <tr><td>\${m1.name}</td><td>${m1.name}</td></tr>         
                    <tr><td>\${m1.addr.city}</td><td>${m1.addr.city}</td></tr>            
                    <tr><th></th><th></th></tr>
                    <tr><td>\${m2.id}</td><td>${m2.id}</td></tr>         
                    <tr><td>\${m2.name}</td><td>${m2.name}</td></tr>         
                    <tr><td>\${m2.addr.country}</td><td>${m2.addr.country}</td></tr>     
                        
                    <tr><th></th><th></th></tr>
                    <tr><td>\${members[0].id}</td><td>${members[0].id}</td></tr>         
                    <tr><td>\${members[0].name}</td><td>${members[0].name}</td></tr>         
                    <tr><td>\${members[0].addr.city}</td><td>${members[0].addr.city}</td></tr>     
                    
                     <tr><th></th><th></th></tr>
                    <tr><td>\${members[1].id}</td><td>${members[1].id}</td></tr>         
                    <tr><td>\${members[1].name}</td><td>${members[1].name}</td></tr>         
                    <tr><td>\${members[1].addr.city}</td><td>${members[1].addr.country}</td></tr>     
                      
                                     	
                </table>
            </div>
            <div class="col-2"></div>
        </div>
	</div>
</body>
</html>