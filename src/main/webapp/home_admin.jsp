<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="jakrtha.http.HttpSession" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="ISO-8859-1">
    <title>Admin Home Page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <button class="btn btn-danger mb-3" onclick="document.location='index.html'" >Log out</button>
        <h2 class="text-center">ADMIN DASHBOARD CONTROLS</h2>
        <div class="row mt-4">
            <div class="col-md-3 mb-3">
                <a href="addNote.jsp" class="btn btn-primary btn-block">Add Note</a>
            </div>
            <div class="col-md-3 mb-3">
                <a href="showNotes.jsp" class="btn btn-secondary btn-block">Show Notes</a>
            </div>
            <div class="col-md-3 mb-3">
                <a href="deleteNote.jsp" class="btn btn-danger btn-block">Delete Notes</a>
            </div>
            <div class="col-md-3 mb-3">
                <a href="userList.jsp" class="btn btn-info btn-block">User List</a>
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <%--  <script>
	   function redirect(){
		   		<%
		   		out.println("Invalidating");
		   		HttpSession sessions = request.getSession(false); // Do not create a new session if it doesn't exist
		        if (sessions != null) {
		            sessions.invalidate(); // Invalidate the session
		        }
		   		%>
	   			document.location='index.html';
	   }
	   document.getElementById("logout").onclick = redirect;
   </script>  --%>
</body>
</html>
