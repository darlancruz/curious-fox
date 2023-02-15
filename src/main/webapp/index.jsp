<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Curious Fox</title>
  <script src="https://cdn.tailwindcss.com"></script>
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;700&display=swap" rel="stylesheet">
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
		<c:import url="./components/nav.jsp"></c:import>
	</header>
	
	<main class="flex flex-col space-y-4 max-w-xl w-11/12 self-center">
		<form action="./"
		class="flex items-center border rounded-md border-slate-400 bg-slate-900 w-full overflow-hidden">
			<input name="search" type="text" placeholder="Search..." class="w-full h-14 outline-none appearance-none h-10 text-sm leading-6 text-slate-50 bg-slate-900 placeholder-slate-400 py-2 px-5 shadow-sm resize-none">
			<button type="submit">
				<span class="material-symbols-rounded text-slate-400 p-2">
					search
				</span>
			</button>
		</form>
		<c:choose>
			<c:when test="${not empty results_for}">
				<span class="text-slate-300 font-medium text-xl">Results for "${results_for}"</span>
			</c:when>
			<c:otherwise>
				<span class="text-slate-300 font-medium text-xl">Discover</span>
			</c:otherwise>
		</c:choose>
		
		<div class="flex flex-row flex-wrap gap-2 justify-evenly">
			<c:choose>
				<c:when test="${list_users.size() gt 0}">
					 <c:forEach items="${list_users}" var="user">
						  <a href="./profile?username=${user.getUsername()}" class="flex flex-col justify-center items-center w-44 h-52 border border-slate-500 bg-slate-900 rounded-md cursor-pointer">
								<img class="w-1/2 rounded-full" src="${user.getPictureUrl()}">
								<span class="text-center text-slate-50 font-bold text-xl">${user.getName()}</span>
								<span class="border border-slate-500 hover:border-slate-300 text-slate-300 hover:text-slate-100 text-sm font-medium rounded-md px-2 py-1 transition duration-50 ">${user.getUsername()}</span>		
							</a>
			 		 </c:forEach>
				</c:when>
				<c:otherwise>
					<span class="text-slate-300 font-medium text-xl">User not found</span>
				</c:otherwise>
			</c:choose>
		</div>
	</main>
</body>
</html>