<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="title" value="회원가입" />
<%@ include file="../part/head.jspf"%>


<form action="doJoin" method="POST"
	onsubmit="joinFormSubmit(this); return false;" class="con">
	<div class="form-control-box">
		<div>로그인 아이디</div>
		<input type="text" name="loginId" placeholder="로그인 아이디를 입력해주세요." />
	</div>
	<div class="form-control-box">
		<div>로그인 비밀번호</div>
		<input type="password" name="loginPw" placeholder="비밀번호를 입력해주세요." />
	</div>
	<div class="form-control-box">
		<div>비밀번호 확인</div>
		<input type="password" name="loginPwConfirm"
			placeholder="비밀번호 확인을 입력해주세요." />
	</div>
	<div class="form-control-box">
		<div>이름</div>
		<input type="text" name="name" placeholder="이름을 입력해주세요." />
	</div>
	<div class="form-control-box">
		<div>이메일</div>
		<input type="email" name="email" placeholder="이메일을 입력해주세요."/>
	</div>
	<div class="form-control-box " >
		<button type="submit" class="margin-top-30" >가입</button>
	</div>
</form>


<script>

var joinFormSubmitDone = false;
function joinFormSubmit(form) {

	if ( joinFormSubmitDone ) {
		alert('처리중입니다.');
		return;
	}

	form.loginId.value = form.loginId.value.trim();
	if ( form.loginId.value.length == 0 ) {
		alert('로그인 아이디를 입력해주세요.');
		form.loginId.focus();
		return;
	}

	form.loginPw.value = form.loginPw.value.trim();
	if ( form.loginPw.value.length == 0 ) {
		alert('비밀번호를 입력해주세요.');
		form.loginPw.focus();
		return;
	}

	form.loginPwConfirm.value = form.loginPwConfirm.value.trim();
	if ( form.loginPwConfirm.value.length == 0 ) {
		alert('비밀번호 확인을 입력해주세요.');
		form.loginPwConfirm.focus();
		return;
	}

	if ( form.loginPw.value != form.loginPwConfirm.value ) {
		alert('비밀번호 확인이 일치하지 않습니다.');
		form.loginPwConfirm.focus();
		return;
	}

	form.name.value = form.name.value.trim();
	if ( form.name.value.length == 0 ){
		alert('이름을 입력해주세요.');
		form.name.focus();
		return;
	}

	form.email.value = form.email.value.trim();
	if ( form.email.value.length == 0 ) {
		alert('이메일을 입력해주세요.');
		form.email.focus();
		return;
	}

	form.submit();
	joinFormSubmitDone = true;



	
}


</script>










<%@ include file="../part/foot.jspf"%>