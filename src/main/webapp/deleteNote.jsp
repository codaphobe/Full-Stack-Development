<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>DELETE NOTES</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center">DELETE NOTES</h2>
        <form action="deleteNote" method="get" class="mt-4">
            <div class="form-group">
                <label for="userName">Enter User Name to delete from:</label>
                <input type="text" class="form-control" id="userName"  placeholder="User Name" name='u'>
                <label for="id">Enter id to delete from:</label>
                <input type="number" class="form-control" id="id"  placeholder="Id" name='id'>
            </div>
            <button type="submit" class="btn btn-danger">Delete</button>
        </form>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
