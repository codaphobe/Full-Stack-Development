<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="ISO-8859-1">
    <title>User Page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <button class="btn btn-danger" onclick="document.location='index.html'">Log Out</button>
        <h1 class="text-center">My Notes</h1>
        <div class="row mt-4">
            <div class="col-md-6 mb-3">
                <a href="addNote.jsp" class="btn btn-primary btn-block">Add Note</a>
            </div>
            <div class="col-md-6 mb-3">
                <a href="showNotes.jsp" class="btn btn-secondary btn-block">Show Notes</a>
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <%-- <script>
        function redirect() {
            <% HttpSession sessions = request.getSession(false);
               if (sessions != null) {
                   sessions.invalidate(); // Invalidate the session
               }
            %>
            document.location = 'index.html';
        }
    </script> --%>
</body>
</html>
