package pu.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class LoginUser
 */
public class LoginUser extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginUser() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        	
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection to the database
            Connection c = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/pres", "root", "root@123"
            );

            // SQL query to check the username and password
            String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
            PreparedStatement p = c.prepareStatement(sql);

            // Get the username and password from the request
            String username = request.getParameter("u");
            String password = request.getParameter("p");
            String role_1= request.getParameter("r");
            String role = "User";
            
//            System.out.println(username+password+role);

            // Set the parameters for the prepared statement
            p.setString(1, username);
            p.setString(2, password);
            
            //Http session
            HttpSession session = request.getSession(true);
            
            // Set attributes in the session
            session.setAttribute("username", username);
            session.setAttribute("role", role);
//          System.out.println(session.getAttribute("role"));
            // Execute the query
            ResultSet rs = p.executeQuery();

            // Check if a record was found
            if (rs.next()) {
                // Successful login, redirect to the home page
            	if (role.equals("user")){
            			response.sendRedirect("home_user.jsp");
            	}else if(role.equals("admin")) {
            		response.sendRedirect("home_admin.jsp");
            	}
            } else {
                // Unsuccessful login, send an error message
                response.setContentType("text/html");
                response.getWriter().print("<h2>Invalid username or password</h2>");
            }

            // Close the resources
            rs.close();
            p.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.setContentType("text/html");
            response.getWriter().print("<h2>An error occurred: " + e.getMessage() + "</h2>");
        }
    }
}
