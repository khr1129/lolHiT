<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="title" value="게시물 리스트" />
<%@ include file="../part/head.jspf"%>



<div class="list-box con margin-top-50">
	<c:forEach items="${articles }" var="article">
		<div class="list-box-content margin-top-10">
			<div class="article-content">
				<div>번호 : ${article.id}</div>
				<div>작성일 : ${article.regDate }</div>
				<div>수정일 : ${article.updateDate }</div>
				<div>
					제목 : <a href="../article/detail?id=${article.id }">${article.title }</a>
				</div>
			</div>

			<div class="btn-box">
				<button type="button" onclick="location.replace('../article/modify?id=${article.id}')">수정</button>
				<button type="button" onclick="if ( confirm('삭제하시겠습니까?') == false )  return false;  location.replace('../article/doDelete?id=${article.id}')">삭제</button>
			</div>
		</div>
		<div class="border-gray-2 margin-top-10"></div>
	</c:forEach>

</div>


<style>
.list-box .list-box-content {
	display: flex;
	justify-content: space-between;
	align-items: flex-end;
}
</style>










<%@ include file="../part/foot.jspf"%>