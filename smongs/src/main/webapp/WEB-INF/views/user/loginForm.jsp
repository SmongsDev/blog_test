<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="../layout/header.jsp" %>

<div class="container">
    <form action="/auth/loginProc" method="post">
        <div class="form-group">
            <label for="username">Username:</label>
            <input type="text" name="username" class="form-control" id="username" placeholder="Enter username" name="username">
          </div>

        <div class="form-group">
          <label for="password">Password:</label>
          <input type="password" name="password" class="form-control" id="password" placeholder="Enter password" name="password">
        </div>
        
        <button id="btn-login" class="btn btn-primary">Sign In</button>  
        <a href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=${REST_API_KEY}&redirect_uri=${REDIRECT_URI}"><img height="38px" src="/images/kakao_login_button.png"></a>
      </form>
</div>

<%@ include file="../layout/footer.jsp" %>