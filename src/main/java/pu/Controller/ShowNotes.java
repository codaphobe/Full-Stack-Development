package pu.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ShowNotes
 */
//@WebServlet("/ShowNotes")
public class ShowNotes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowNotes() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuilder tableData = new StringBuilder();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c=DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/pres","root","root@123");

            // Prepare the SQL query
            String sql = "SELECT * FROM notes";
            
	        
	        // Retrieve data from the session
            
            PreparedStatement statement = c.prepareStatement(sql);
            

            // Execute the query and get the result set
            ResultSet resultSet = statement.executeQuery();

            tableData.append("<table border='0'>\n");
            
            // Add table headers
            tableData.append("<tr><th>ID</th><th>User ID</th><th>Title</th><th>Content</th></tr>\n");

            // Iterate through the result set
            while (resultSet.next()) {
                // Retrieve data from each row
                int id = resultSet.getInt("id");
                int userId = resultSet.getInt("user_id");
                String title = resultSet.getString("title");
                String content = resultSet.getString("content");

                // Add row data to the HTML table
                tableData.append("<tr>");
                tableData.append("<td>").append(id).append("</td>");
                tableData.append("<td>").append(userId).append("</td>");
                tableData.append("<td>").append(title).append("</td>");
                tableData.append("<td>").append(content).append("</td>");
                tableData.append("</tr>\n");
            }

            // End the HTML table
            tableData.append("</table>");
            // Close the resources
            resultSet.close();
            statement.close();
            c.close();
        } catch (Exception e) {
            System.out.println(e);;
        }

        // Print or use the formatted table data
        out.println(tableData.toString());
        
    
		
	}

}
