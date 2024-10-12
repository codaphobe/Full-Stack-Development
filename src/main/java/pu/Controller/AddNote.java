package pu.Controller;

import java.io.IOException;
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
 * Servlet implementation class AddNote
 */
//@WebServlet("/AddNote")
public class AddNote extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNote() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c=DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/pres","root","root");
			
			String st = "insert into notes(user_id,title,content) values (?,?,?);";
			PreparedStatement ps = c.prepareStatement(st);
			
			String title = request.getParameter("t");
			String desc = request.getParameter("d");
//			int uid = 1;
			int id=12;

			
			
			ps.setString(2,title);
			ps.setString(3, desc);
//			ps.setInt(1,uid);
			
			HttpSession session = request.getSession();
	        
	        // Retrieve data from the session
			
	        String username = (String) session.getAttribute("username");
	        String role = (String) session.getAttribute("role");
//	        System.out.println(username);
			
	        String id_st = "select id from user where username=?";
	        PreparedStatement p = c.prepareStatement(id_st);
	        
	        p.setString(1, username);
	        
	        ResultSet rs = p.executeQuery();
//	        System.out.println(rs.next());
	        
	        
//	        System.out.println(rs.next());
	        if (rs.next()) {
	        	 id  = rs.getInt(1);
	        	 System.out.println(id);
	        	 rs.close();
	        	 p.close();
	        }
			
	        ps.setInt(1,id);
	        
			int r=ps.executeUpdate();
			response.setContentType("text/html");
			response.getWriter().print("<h2>"+"Note Successfully Inserted"+"</h2>");
			String homePage = (role != null && role.equals("user")) ? "home_user.jsp" : "home_admin.jsp";
			response.getWriter().print("<a href="+homePage+">GO Back</a>");
			
			
//		
        
		}
		catch(Exception e){
			System.out.println(e);
		}
	}

}
