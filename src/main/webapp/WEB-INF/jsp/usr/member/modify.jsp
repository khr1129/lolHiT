<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="title" value="회원정보 수정" />
<%@ include file="../part/head.jspf"%>



<form action="doModify" method="POST" class="con margin-top-50" onsubmit="modifyFormSubmit(this); return false;">
	<div class="form-control-box ">
		<div>번호</div>
		<input type="text" value="${loginedMember.id}" readonly />
	</div>
	<div class="form-control-box">
		<div>회원가입일</div>
		<input type="text" value="${loginedMember.regDate} "  readonly/>
	</div>
	<div class="form-control-box">
		<div>이름</div>
		<input type="text" name="name" value="${loginedMember.name }" />
	</div>
	<div class="form-control-box">
		<button type="submit">수정</button>
		<button type="button" onclick="history.back();">뒤로가기</button>
	</div>
</form>


<script>
var modifyFormSubmitDone = false;
function modifyFormSubmit(form) {
	if ( modifyFormSubmitDone ) {
		alert('처리중입니다.');
		return;
	}

	form.name.value = form.name.value.trim();
	if ( form.name.value.length == 0 ) {
		alert('이름을 입력해주세요.');
		form.name.focus();
		return;
	}

	form.submit();
	modifyFormSubmitDone = true;
}
</script>






<%@ include file="../part/foot.jspf"%>
