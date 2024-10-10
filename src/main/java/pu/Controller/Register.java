package pu.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c=DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/pres","root","root");
			
			String st = "INSERT INTO user (username,password, role, email) values (?,?,?,?)";
			
			PreparedStatement ps = c.prepareStatement(st);
			
			ps.setString(1,request.getParameter("u"));
			ps.setString(2,request.getParameter("p"));
			ps.setString(3,"user");
			ps.setString(4,request.getParameter("e"));
			int r = 0;
			String e="";
			try {
				r=ps.executeUpdate();
			}catch(java.sql.SQLIntegrityConstraintViolationException ex) {
				e = ex.toString();
				System.out.println(ex);				
			}
			
			if (r==1){				
				RequestDispatcher dispatcher = request.getRequestDispatcher("redirectLogin.html");
		        dispatcher.forward(request, response);
			}
			else {
				System.out.println("Error Inserting");
				response.setContentType("text/html");
				response.getWriter().print("<h4>"+"Error in input details"+"</h4><br>");
				
			}
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}

}
