<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="javax.servlet.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="ISO-8859-1">
    <title>Show Notes</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center mb-4">Notes</h2>
        
        <!-- Search Form -->
        <form action="showNotes.jsp" method="get" class="mb-4">
            <div class="input-group">
                <input type="text" name="query" class="form-control" placeholder="Search notes by title or description" >
                <div class="input-group-append">
                    <button class="btn btn-primary" type="search">Search</button>
                </div>
            </div>
        </form>
        
        <div class="table-responsive">
            <% 
            StringBuilder tableData = new StringBuilder();
            String query = request.getParameter("query");
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection c = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/pres", "root", "root");

                // Prepare the SQL query
                String sql;
                PreparedStatement statement;

                if (query != null && !query.trim().isEmpty()) {
                    sql = "SELECT * FROM notes WHERE title LIKE ? OR content LIKE ?";
                    statement = c.prepareStatement(sql);
                    String searchQuery = "%" + query + "%";
                    statement.setString(1, searchQuery);
                    statement.setString(2, searchQuery);
                } else {
                    sql = "SELECT * FROM notes";
                    statement = c.prepareStatement(sql);
                }

                // Execute the query and get the result set
                ResultSet resultSet = statement.executeQuery();

                // Start the HTML table
                tableData.append("<table class='table table-striped table-bordered'>\n");

                // Add table headers
                tableData.append("<thead><tr><th>ID</th><th>User Name</th><th>Title</th><th>Content</th></tr></thead>\n");
                tableData.append("<tbody>");

                // Iterate through the result set
                while (resultSet.next()) {
                    // Retrieve data from each row
                    int id = resultSet.getInt("id");
                    int userId = resultSet.getInt("user_id");
                    String title = resultSet.getString("title");
                    String content = resultSet.getString("content");

                    //Retrieve all user name
                    PreparedStatement st = c.prepareStatement("select (username) from users where id = ?");
                    st.setInt(1,userId);
                    ResultSet ur = st.executeQuery();
                    String username = "";
                    if (ur.next() && ur.isLast()){
                        username = ur.getString(1);
                    }


                    // Add row data to the HTML table
                    tableData.append("<tr>");
                    tableData.append("<td>").append(id).append("</td>");
                    tableData.append("<td>").append(username).append("</td>");
                    tableData.append("<td>").append(title).append("</td>");
                    tableData.append("<td>").append(content).append("</td>");

                    if (session.getAttribute("role").equals("admin")) {
                    tableData.append("<td>").append("<form action='deleteNote' method='post'><button class='btn btn-danger' type='submit' name='id' value=\'")
                    .append(id).append("\'>Delete</button>").append("</form></td>");
                    }

                    tableData.append("</tr>\n");
                }

                // End the HTML table
                tableData.append("</tbody></table>");

                // Close the resources
                resultSet.close();
                statement.close();
                c.close();
            } catch (Exception e) {
                System.out.println(e);
            }

            // Print or use the formatted table data
            out.println(tableData.toString());
            %>
        </div>
    </div>
    <br>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
