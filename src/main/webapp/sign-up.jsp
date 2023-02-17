<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

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

<body class="bg-slate-800 w-screen flex flex-col gap-8">
  <header class="flex flex-col justify-center items-center text-white mt-10 select-none">
    <img width="72" height="auto" src="./img/fox-without-bg.svg">
    <h1 class="text-3xl font-bold">Curious Fox</h1>
    <span class="font-medium">Create a Account</span>
  </header>

  <main class="w-screen flex flex-col items-center gap-8">
    <form name="sign-up" id="sign-up" method="POST" action="sign-up" class="max-w-sm w-11/12 flex flex-col gap-4">
    <c:if test="${not empty error}">
    	<span class="py-2 px-4 rounded-md bg-pink-500 font-bold text-white text-sm">${error.message}</span>
    </c:if>
	  <input name="name" id="name-input" required maxlength="50"
	    class="focus:ring-2 focus:ring-blue-500 focus:outline-none appearance-none text-sm leading-6 text-slate-50 bg-slate-900 placeholder-slate-400 rounded-md py-2 px-5 ring-1 ring-slate-500 shadow-sm resize-none"
	    aria-label="Name" placeholder="Name" value="${name}"></input>
      <input name="username" id="username-input" required maxlength="15"
        class="focus:ring-2 focus:ring-blue-500 focus:outline-none appearance-none text-sm leading-6 text-slate-50 bg-slate-900 placeholder-slate-400 rounded-md py-2 px-5 ring-1 ring-slate-500 shadow-sm resize-none"
        aria-label="Username" placeholder="Username" value="${username}"></input>
      <input type="password"  name="password" id="passowrd-input" required maxlength="50"
        class="focus:ring-2 focus:ring-blue-500 focus:outline-none appearance-none text-sm leading-6 text-slate-50 bg-slate-900 placeholder-slate-400 rounded-md py-2 px-5 ring-1 ring-slate-500 shadow-sm resize-none"
        aria-label="Password" placeholder="Password" value="${password}"></input>
      <input type="password"  name="confirm_password" id="confirm-password" required maxlength="50"
        class="focus:ring-2 focus:ring-blue-500 focus:outline-none appearance-none text-sm leading-6 text-slate-50 bg-slate-900 placeholder-slate-400 rounded-md py-2 px-5 ring-1 ring-slate-500 shadow-sm resize-none"
        aria-label="Confirm Password" placeholder="Confirm Password" value="${confirm_password}"></input>
      <input type="submit" value="Sign Up"
        class="w-full h-10 text-base font-medium rounded-lg bg-sky-500 hover:bg-sky-400 text-white py-2 text-center cursor-pointer highlight-white transition duration-50">
    </form>
   <span class="text-white mb-10">Already a User? <a class="font-bold cursor-pointer" href="./login">Login</a></span>
  </main>
</body>

</html>