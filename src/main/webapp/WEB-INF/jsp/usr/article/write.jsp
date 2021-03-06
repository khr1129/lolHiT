<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<c:set var="title" value="게시물 작성"/>
<%@ include file="../part/head.jspf" %>




<form action="doWrite" method="POST" onsubmit="writeFormSubmit(this); return false; " class="con">
	<div class="form-control-box">
		<div>제목</div>
		<input type="text" name="title" placeholder="제목을 입력해주세요."/>
	</div>
	<div class="form-control-box">
		<div>내용</div>
		<textarea name="body" placeholder="내용을 입력해주세요."></textarea>
	</div>
	<div class="form-control-box">
		<button type="submit">작성</button>
	</div>
</form>

<script>

var writeFormSubmitDone = false;

function writeFormSubmit(form) {

	if ( writeFormSubmitDone ) {
		alert('처리중입니다.');
		return;
	}

	form.title.value = form.title.value.trim();
	if ( form.title.value.length == 0 ) {
		alert('제목을 입력해주세요.');
		form.title.focus();
		return;
	}

	form.body.value = form.body.value.trim();
	if ( form.body.value.length == 0 ) {
		alert('내용을 입력해주세요.');
		form.body.focus();
		return;
	}

	form.submit();
	writeFormSubmitDone = true;
}
</script>














<%@ include file="../part/foot.jspf" %>