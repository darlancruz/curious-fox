<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="flex justify-between items-center py-2 px-8 grow-1 w-full ">
	<a href="./" class="w-12 cursor-pointer">
		<img class="w-full" src="./img/fox-without-bg.svg">
	</a>
	<c:choose>
		<c:when test="${not empty user}">
			<nav class="text-white relative">
				<button class="flex items-center space-x-2 cursor-pointer" onClick="handleOpenMenu();">
					<figure class="w-14 overflow-hidden rounded-full">
						<img class="w-full" src=${user.getPictureUrl()}>
					</figure>
					<div class="flex items-center font-bold ">
						<span class="hidden md:inline">
							${user.getUsername()}
						</span>
						<span id="arrow" class="material-symbols-rounded">
							arrow_drop_down
						</span>
					</div>
				</button>
				<menu id="menu" class="hidden absolute flex flex-col items-start left-16 top-12 bg-slate-800 border border-slate-600 rounded-md px-4 py-2 space-y-2">
					<li>
						<a href="./profile?username=${user.getUsername()}">My Profile</a>
					</li>
					<div class="bg-slate-600 w-full py-px"></div>
					<li class="text-red-400 font-medium">
						<a href="./logout">Logout</a>
					</li>
				</menu>
			</nav>
		</c:when>
		<c:otherwise>
			<span class="space-x-2 w-fit text-white">
				<a class="font-medium rounded-md bg-sky-500 hover:bg-sky-400 px-4 py-2 text-white text-center cursor-pointer highlight-white transition duration-50" href="./login">
					Login
				</a>
				<span>or</span>
				<a class="font-medium rounded-md border border-sky-500 hover:border-sky-300 px-4 py-2 text-sky-500 hover:text-sky-300 text-center cursor-pointer highlight-white transition duration-50" href="./sign-up">
					Sign Up
				</a>
			</span>
		</c:otherwise>
	</c:choose>
</div>
  <script src="./scripts/Menu.js"></script>