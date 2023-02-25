<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="java.util.Map.Entry"%>
<%@page import="com.curiousfox.model.User"%>
<%@page import="com.curiousfox.model.Comment"%>
<%@page import="com.curiousfox.utils.TimeAgo"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
  	<c:import url="./components/nav.jsp"/>
  
    <div class="flex flex-col items-center gap-1 p-4 max-w-6xl">
      <img class="w-40 rounded-full" src="<%out.print(request.getAttribute("picture"));%>">
      <h2 class="text-center text-slate-50 font-bold text-2xl">
        <%out.print(request.getAttribute("name"));%>
      </h2>
      <span
        class="border border-slate-500 hover:border-slate-300 text-slate-300 hover:text-slate-100 text-sm font-medium rounded-md px-2 py-1 transition duration-50 cursor-pointer">
        <%out.print(request.getAttribute("username"));%>
      </span>
      <c:choose>
      	<c:when test="${not empty user and user.getId() == id}">
      		 <form method="POST" id="bio_form" action="update-bio" class="max-w-xl flex flex-col relative space-y-2" >
      		 	<div id="bio" class="flex items-center justify-center w-full space-x-2">
	      		 	<p class="text-slate-300 text-medium leading-6 text-center break-all">
						${bio}
	      		 	</p>
	      		 	<span onClick="handleEditBio();" class="material-symbols-rounded text-slate-300 hover:text-white cursor-pointer">
						edit_square
					</span>
      		 	</div>
      		 	<textarea id="new_bio" name="new_bio" required maxlength="160"  rows="3" cols="40" 
      		 	class="hidden focus:ring-2 focus:ring-blue-500 focus:outline-none appearance-none h-32 text-sm leading-6 text-slate-50 bg-slate-900 placeholder-slate-400 rounded-md py-2 px-5 ring-1 ring-slate-500 shadow-sm resize-none"
      		 	placeholder="Your new bio...">${bio}</textarea>
      		 	<div id="bio_buttons" class="hidden space-x-4 self-end">
      		 		<button onClick="handleEditBio();" class="text-slate-300 hover:text-white">Cancel</button>
      		 		<input type="submit" value="Save"
      		 		class="w-full md:w-24 h-10 text-base font-medium rounded-lg bg-sky-500 hover:bg-sky-400 text-white py-2 text-center cursor-pointer highlight-white transition duration-50" >
      		 	</div>
      		 </form>
      	</c:when>
      	<c:otherwise>
      		   <p class="max-w-xl text-slate-300 text-medium leading-6 text-center">
		      	<c:choose>
		      		<c:when test="${not empty bio}">
		      			${bio}
		      		</c:when>
		      		<c:otherwise>
		      			No info.
		      		</c:otherwise>
		      	</c:choose>
		      </p>
      	</c:otherwise>
      </c:choose>
    </div>
  </header>

  <main class="w-screen flex flex-col items-center gap-8">
    <form name="comment" id="comment-area" method="POST" action="send" class="max-w-2xl w-11/12 flex flex-col gap-2">
      <input type="hidden" name="receiver_id" value="<%out.print(request.getAttribute("id"));%>">
      <input type="hidden" name="receiver_username" value="<%out.print(request.getAttribute("username"));%>">
      <textarea name="text" id="comment-box" required maxlength="280"
        class="focus:ring-2 focus:ring-blue-500 focus:outline-none appearance-none h-32 text-sm leading-6 text-slate-50 bg-slate-900 placeholder-slate-400 rounded-md py-2 px-5 ring-1 ring-slate-500 shadow-sm resize-none"
        aria-label="Ask a question..." placeholder="Ask a question..."></textarea>
      <input type="submit" value="Send" onClick="validator();"
        class="self-end w-full md:w-24 h-10 text-base font-medium rounded-lg bg-sky-500 hover:bg-sky-400 text-white py-2 text-center cursor-pointer highlight-white transition duration-50">
    </form>

    <div class="flex flex-col gap-4 w-11/12 items-center">
      <c:forEach items="${comments}" var="comment">
        <div class="flex flex-row gap-2 max-w-2xl w-full border-b border-slate-500 pb-2">
           <c:choose>
          	<c:when test="${not empty comment.key.getId()}">
          	 <a class="w-16 shrink-0 cursor-pointer" href="./profile?username=${comment.key.getUsername()}">
           	 	<img src="${comment.key.getPictureUrl()}" class="w-full rounded-full">
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
          	<c:when test="${not empty comment.key.getId()}">
          	    <a class="text-slate-500 cursor-pointer" href="./profile?username=${comment.key.getUsername()}">
	              <span class="text-slate-50 font-medium text-lg">${comment.key.getName()}</span>
	              
	              <span>@${comment.key.getUsername()}</span>
	              <span>·</span>
	              <span><%
	              	 Entry<User, Comment> commentMap = (Entry<User, Comment>) pageContext.getAttribute("comment");
	              	 out.print(TimeAgo.howLongAgo(commentMap.getValue().getCreatedAt()));
	              %></span>
           		</a>
          	</c:when>
          	<c:otherwise>
          		 <div class="text-slate-500 select-none">
	              <span class="text-slate-50 font-medium text-lg">Anonymous</span>
	              <span>·</span>
	              <span><%
	              	 Entry<User, Comment> commentMap = (Entry<User, Comment>)  pageContext.getAttribute("comment");
	              	 out.print(TimeAgo.howLongAgo(commentMap.getValue().getCreatedAt()));
	              %></span>
           		</div>
          	</c:otherwise>
          </c:choose>
        
            <p class="w-full text-slate-300 md:text-justify pt-2 md:pt-0">
              ${comment.value.getText()}
              </p>

            <a
              class="self-start md:self-end flex items-center justify-center gap-1 w-11/12 md:w-fit rounded-md text-slate-300 align text-sm bg-slate-700 hover:bg-slate-500 p-2 md:py-1 mt-3 md:mt-0 cursor-pointer transition duration-50">
              <span class="material-symbols-rounded">
                comment
              </span>
              <span class="font-medium">
                Reply
              </span>
            </a>
          </div>
        </div>
      </c:forEach>
    </div>
  </main>
  <script src="scripts/Comment.js"></script>
   <script src="scripts/Bio.js"></script>
</body>
</html>