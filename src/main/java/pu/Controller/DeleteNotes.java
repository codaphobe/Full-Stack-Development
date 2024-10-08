package pu.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteNotes
 */

public class DeleteNotes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteNotes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection to the database
            Connection c = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/pres", "root", "root@123"
            );
            
            String st = "delete from notes where user_id=? and id=?";
            String id_st = "SELECT id from user where username=? ";
            
            PreparedStatement ps = c.prepareStatement(id_st);
            String username = request.getParameter("u");
            
            int id = 0;
            int n_id = Integer.parseInt(request.getParameter("id"));
            ps.setString(1, username);
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
            	id = rs.getInt(1);
            }
            PreparedStatement p = c.prepareStatement(st);
            p.setInt(1, id);
            p.setInt(2, n_id);
            
            
            int r=p.executeUpdate();
			response.setContentType("text/html");
			response.getWriter().print("<h2>"+"Note Successfully Deleted"+"</h2>");
			response.getWriter().print("<a href="+"home_admin.jsp"+">GO Back</a>");

		}catch(Exception e) {
			System.out.println(e);
		}
	}
}
