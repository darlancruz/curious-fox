<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
    <header class="flex justify-center items-center bg-slate-700">
      <div class="flex flex-col items-center gap-1 p-4 max-w-6xl">
        <img class="w-40 rounded-full" src="<%out.print(request.getAttribute("picture"));%>">
        <h2 class="text-center text-slate-50 font-bold text-2xl">
          <%out.print(request.getAttribute("name"));%>
        </h2>
        <span
          class="border border-slate-500 hover:border-slate-300 text-slate-300 hover:text-slate-100 text-sm font-medium rounded-md px-2 py-1 transition duration-50 cursor-pointer">
          <%out.print(request.getAttribute("username"));%>
        </span>
        <p class="max-w-xl text-slate-300 text-medium leading-6 text-center">
          <%out.print(request.getAttribute("bio"));%>
        </p>
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

      </div>
    </main>
    <script src="scripts/Comment.js"></script>
  </body>

  </html>