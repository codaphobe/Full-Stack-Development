<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="javax.servlet.*" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="ISO-8859-1">
    <title>User List</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .container {
            margin-top: 50px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2 class="text-center">Users Fetched</h2>

        <!-- Search Form -->
        <form action="userList.jsp" method="get" class="mb-4">
            <div class="input-group">
                <input type="text" name="query" class="form-control" placeholder="Search users by username" required>
                <div class="input-group-append">
                    <button class="btn btn-primary" type="submit">Search</button>
                </div>
            </div>
        </form>

        <table class="table table-striped">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Username</th>
                    <th>Password</th>
                    <th>Role</th>
                </tr>
            </thead>
            <tbody>
                <% 
                String query = request.getParameter("query");
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/pres", "root", "root");

                    String sql;
                    PreparedStatement ps;
                    
                    if (query != null && !query.trim().isEmpty()) {
                        sql = "SELECT * FROM user WHERE username LIKE ?";
                        ps = c.prepareStatement(sql);
                        ps.setString(1, "%" + query + "%");
                    } else {
                        sql = "SELECT * FROM user";
                        ps = c.prepareStatement(sql);
                    }

                    ResultSet rs = ps.executeQuery();

                    // Iterate through the result set
                    while (rs.next()) {
                        // Retrieve data from each row
                        int id = rs.getInt("id");
                        String username = rs.getString("username");
                        String password = rs.getString("password");
                        String role = rs.getString("role");

                        // Add row data to the HTML table
                        out.println("<tr>");
                        out.println("<td>" + id + "</td>");
                        out.println("<td>" + username + "</td>");
                        out.println("<td>" + password + "</td>");
                        out.println("<td>" + role + "</td>");
                        out.println("</tr>");
                    }

                    // Close the resources
                    rs.close();
                    ps.close();
                    c.close();
                }
                catch(Exception e){
                    out.println(e);
                }
                %>
            </tbody>
        </table>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
