<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="title" value="게시물 수정" />
<%@ include file="../part/head.jspf"%>


<form action="doModify" method="POST" class="con margin-top-50" onsubmit="modifyFormSubmit(this); return false;">
	<input type="hidden" name="id" value="${article.id }" />
	<div class="form-control-box ">
		<div>제목</div>
		<input type="text" name="title" value="${article.title}" />
	</div>
	<div class="form-control-box">
		<div>내용</div>
		<textarea name="body">${article.body }</textarea>
	</div>
	<div class="form-control-box">
		<button type="submit">수정</button>
		<button type="button" onclick="location.replace('../article/list')">취소</button>
	</div>
</form>


<script>
var modifyFormSubmitDone = false;
function modifyFormSubmit(form) {
	if ( modifyFormSubmitDone ) {
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
	modifyFormSubmitDone = true;
}
</script>






<%@ include file="../part/foot.jspf"%>
