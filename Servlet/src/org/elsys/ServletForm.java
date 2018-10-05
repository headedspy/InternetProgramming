package org.elsys;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletForm extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	private String key, value;

	public ServletForm(){
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		ServletOutputStream out = response.getOutputStream();
		
		out.println("<html>");
		out.println("<head><title>Key-Value Server</title></head>");
		
		out.println("<body>");
		out.println("<h4>Key: " + this.key + "</h4>");
		out.println("<h4>Value: " + this.value + "</h4>");
		
		out.println("<hr>");
		
		out.println("<form action=\"\" method=\"post\">");
		out.println("<h3>Key </h3>");
		out.println("<input type=\"text\" name=\"keyField\">");
		out.println("<h3>Value <h3>");
		out.println("<input type=\"text\" name=\"valueField\">");
		out.println("<br>");
		out.println("<input type=\"submit\" value=\"Save\">");
		out.println("</form>");
		
		out.println("</body>");
		out.println("</html>");
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		this.key = request.getParameter("keyField");
		this.value = request.getParameter("valueField");
		doGet(request, response);
	}
}
