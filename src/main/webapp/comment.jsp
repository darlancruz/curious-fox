<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="com.curiousfox.model.User"%>
<%@page import="com.curiousfox.model.Comment"%>
<%@page import="com.curiousfox.utils.TimeAgo"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Curious Fox</title>
	<script src="https://cdn.tailwindcss.com"></script>
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link
		href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;700&display=swap"
		rel="stylesheet">
	<link rel="stylesheet"
		href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded:opsz,wght,FILL,GRAD@48,400,0,0" />
	<style type="text/css">
	body {
		font-family: 'Inter', sans-serif;
	}
	</style>

</head>

<body class="bg-slate-800 w-screen flex flex-col gap-4">
	<header class="flex justify-center flex-wrap items-center bg-slate-700">
		<c:import url="./components/nav.jsp" />

		<div class="max-w-2xl w-full space-y-4">
			<div class="w-full">
				<a 
				onClick="handleBackButton();"
				class="flex items-center text-slate-50 text-xl font-medium w-fit p-1 cursor-pointer space-x-1 hover:rounded-2xl hover:bg-slate-500">
					<span class="material-symbols-rounded"> arrow_back </span>
					<span>Back</span>
				</a>
			</div>
			<div class="flex flex-row gap-2 pb-2">
				<c:choose>
					<c:when test="${not empty comment.key.getId()}">
						<a class="w-16 shrink-0 cursor-pointer"
							href="./profile?username=${comment.key.getUsername()}"> <img
							src="${comment.key.getPictureUrl()}"
							class="w-full rounded-full">
						</a>
					</c:when>
					<c:otherwise>
		          		<div class="w-16 shrink-0">
		          				<img src="./img/fox.svg" class="w-full rounded-full">
		          		</div>
	          		</c:otherwise>	
				</c:choose>
				<div class="flex w-full flex-col space-y-2">
					<c:choose>
						<c:when test="${not empty comment.key.getId()}">
							<a class="text-slate-500 cursor-pointer"
								href="./profile?username=${comment.key.getUsername()}"> 
								<span
								class="text-slate-50 font-medium text-lg">${comment.key.getName()}</span> 
								<span>@${comment.key.getUsername()}</span>
							</a>
						</c:when>
						<c:otherwise>
							<span class="text-slate-50 font-medium text-lg">Anonymous</span>
						</c:otherwise>
					</c:choose>
					<p
						class="w-full text-slate-300 md:text-justify text-lg pt-2 md:pt-0">
						${comment.value.getText()}</p>
					<span class="text-slate-500">
						<% 
						Entry<User, Comment> commentEntry = (Entry<User, Comment>) request.getAttribute("comment");
					
						String pattern = "hh:mm aa · dd MMM yyyy";
						SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
						Date date = Date.from(commentEntry.getValue().getCreatedAt());
						String formattedDate = simpleDateFormat.format(date);
					
		              	 out.print(formattedDate);
		              	%>
					</span>
				</div>
			</div>
		</div>
	</header>

	<main class="w-screen flex flex-col items-center gap-8">
		<form name="comment" id="comment-area" method="POST" action="reply"
			class="max-w-2xl w-11/12 flex flex-col gap-2">
			<input type="hidden" name="parent_id"
				value="${comment.value.getId()}"> 
			<textarea name="text" id="comment-box" required maxlength="280"
				class="focus:ring-2 focus:ring-blue-500 focus:outline-none appearance-none h-32 text-sm leading-6 text-slate-50 bg-slate-900 placeholder-slate-400 rounded-md py-2 px-5 ring-1 ring-slate-500 shadow-sm resize-none"
				aria-label="Reply..." placeholder="Reply..."></textarea>
			<input type="submit" value="Send" onClick="validator();"
				class="self-end w-full md:w-24 h-10 text-base font-medium rounded-lg bg-sky-500 hover:bg-sky-400 text-white py-2 text-center cursor-pointer highlight-white transition duration-50">
		</form>

		<div class="flex flex-col gap-4 w-11/12 items-center">
			<c:forEach items="${replies}" var="reply">
				<div
					class="flex flex-row gap-2 max-w-2xl w-full border-b border-slate-500 pb-2">
					<c:choose>
						<c:when test="${not empty reply.key.getId()}">
							<a class="w-16 shrink-0 cursor-pointer"
								href="./profile?username=${reply.key.getUsername()}"> <img
								src="${reply.key.getPictureUrl()}" class="w-full rounded-full">
							</a>
						</c:when>
						<c:otherwise>
							<div class="w-16 shrink-0">
								<img src="./img/fox.svg" class="w-full rounded-full">
							</div>
						</c:otherwise>
					</c:choose>
					<div class="flex w-full flex-col">
						<c:choose>
							<c:when test="${not empty reply.key.getId()}">
								<a class="text-slate-500 cursor-pointer"
									href="./profile?username=${reply.key.getUsername()}"> <span
									class="text-slate-50 font-medium text-lg">${reply.key.getName()}</span>

									<span>@${reply.key.getUsername()}</span> <span>·</span> <span>
							             <%Entry<User, Comment> commentMap = (Entry<User, Comment>) pageContext.getAttribute("reply");
							              out.print(TimeAgo.howLongAgo(commentMap.getValue().getCreatedAt()));%>
								</span>
								</a>
							</c:when>
							<c:otherwise>
								<div class="text-slate-500 select-none">
									<span class="text-slate-50 font-medium text-lg">Anonymous</span>
									<span>·</span> <span>
					              	 <%Entry<User, Comment> commentMap = (Entry<User, Comment>)  pageContext.getAttribute("reply");
					              	 out.print(TimeAgo.howLongAgo(commentMap.getValue().getCreatedAt()));%>
									</span>
								</div>
							</c:otherwise>
						</c:choose>

						<p class="w-full text-slate-300 md:text-justify pt-2 md:pt-0">
							${reply.value.getText()}</p>

						<a
							onClick="addToHistory();"
							href="comment?id=${reply.value.getId()}"
							class="self-start md:self-end flex items-center justify-center gap-1 w-11/12 md:w-fit rounded-md text-slate-300 align text-sm bg-slate-700 hover:bg-slate-500 p-2 md:py-1 mt-3 md:mt-0 cursor-pointer transition duration-50">
							<span class="material-symbols-rounded"> comment </span> <span
							class="font-medium"> Reply </span>
						</a>
					</div>
				</div>
			</c:forEach>
		</div>
	</main>
	<script src="scripts/Comment.js"></script>
	<script src="scripts/BackButton.js"></script>
</body>
</html>