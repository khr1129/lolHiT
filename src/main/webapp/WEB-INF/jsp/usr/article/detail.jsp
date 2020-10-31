<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="title" value="게시물 상세보기" />
<%@ include file="../part/head.jspf"%>

<div class="article-box con margin-top-50">
		<div>게시물 번호 : ${article.id }</div>
		<div>작성일 : ${article.regDate }</div>
		<div>수정일 : ${article.updateDate }</div>
		<div>제목 : ${article.title }</div>
		<div>내용 : ${article.body }</div>
		<div class="btn-box">
		<button type="button" onclick="location.replace('../article/list')">리스트</button>
		<button type="button" onclick="location.replace('../article/modify?id=${article.id}');" >수정</button>
		<button type="button" onclick="if(confirm('삭제하시겠습니까?') == false ) return false; location.replace('../article/doDelete?id=${article.id}')">삭제</button>
		</div>
</div>

<h2 class="con margin-top-100">댓글 작성</h2>
<form action="doWriteReply" method="POST" onsubmit="WriteReplyFormSubmit(this); return false;" class="con form">
	<input type="hidden" name="articleId" value="${article.id }"/>
	<input type="hidden" name="memberId" value="${loginedMemberId}" />
	<div class="form-control-box">
		<textarea name="body" rows="10" placeholder="댓글을 입력해주세요." ></textarea>
	</div>
	<div class="btn-box">
		<button type="submit">작성</button>
	</div>
</form>


<h2 class="con margin-top-50">댓글 리스팅</h2>
<div class="reply-list-box con margin-top-50">
	<c:forEach items="${replies }" var="reply">
		<div class="list-box-content margin-top-10">
			<div class="article-content">
				<div>번호 : ${reply.id}</div>
				<div>작성일 : ${reply.regDate }</div>
				<div>수정일 : ${reply.updateDate }</div>
				<div style="font-weight: bold;">작성자 : ${reply.extra.writer}</div>
				<div>
					댓글 : ${reply.body } 
				</div>
			</div>
			<div class="btn-box">
				<button type="button" onclick="location.replace('../article/modifyReply?id=${reply.id}')">수정</button>
				<button type="button" onclick="if ( confirm('삭제하시겠습니까?') == false )  return false;  location.replace('../article/doDeleteReply?id=${reply.id}')">삭제</button>
			</div>
		</div>
		<div class="border-gray-2 margin-top-10"></div>
	</c:forEach>

</div>

<script>

var WriteReplyFormSubmitDone = false;
function WriteReplyFormSubmit(form) {
	if ( WriteReplyFormSubmitDone ) {
		alert('처리중입니다.');
		return;
	}

	form.body.value = form.body.value.trim();
	if ( form.body.value.length == 0 ) {
		alert('댓글을 입력해주세요.');
		form.body.focus();
		return;
	}

	form.submit();
	WriteReplyFormSubmitDone = true;
}
</script>






<%@ include file="../part/foot.jspf"%>