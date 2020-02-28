<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/style.css" type="text/css"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Library</title>
</head>
<body>
<div class = "main">
	<div class="title">My Library</div>
	<div class="titl"><a href="addBook.jsp">Insert a new book</a></div>
	<form action="/search" method="post" name="frm">
		<div class = "search">
			Search a Book  <br> <input type="text" name="title" size="30">
			<input type="submit" value="Search">
		</div>
		<div id = "srch">
			Search an Author <br> <input type="text" name="author" size="30">
			<input type="submit" value="Search">
		</div>
	</form>
	<c:forEach var="search" items="${listba}">	
		<div class="result">
			<div class="col" id="colmn2">Title</div>
			<div class="col" id="colmn4">Author</div>
		</div>
	</c:forEach>
	<div class="table">
		<div class="col" id="colmn1">ID</div>
		<div class="col" id="colmn2">Title</div>
		<div class="col" id="colmn3">ISBN</div>
		<div class="col" id="colmn4">Author</div>
		<div class="colm" id="">Action</div> 
		<c:forEach var="book" items="${listLibrary}">
			<div class="col" id="colmn1"><c:out value="${book.id}" /></div>
			<div class="col" id="colmn2"><c:out value="${book.title}" /></div>
			<div class="col" id="colmn3"><c:out value="${book.isbn}" /></div>
			<div class="col" id="colmn4"><c:out value="${book.author}" /></div>
			<div class="colm" id="">
				<a href="/delete?id=<c:out value='${book.id}' />">Delete</a>
			</div>
		</c:forEach>
	</div>
</div>

</body>
</html>