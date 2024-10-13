package pu.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteNotes
 */
@WebServlet("/deleteNote")
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection to the database
            Connection c = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/pres", "root", "root"
            );
            
            String st = "delete from notes where id=?";
            
            PreparedStatement ps = c.prepareStatement(st);

            int n_id = Integer.parseInt(request.getParameter("id"));
            System.out.println(n_id);
            ps.setInt(1, n_id);

            
            int r=ps.executeUpdate();

		}catch(Exception e) {
			System.out.println(e);
		}
        response.sendRedirect("showNotes.jsp");
	}
}
