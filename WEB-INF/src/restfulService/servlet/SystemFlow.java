package restfulService.servlet;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
//import java.util.*;
import java.net.*;
import java.sql.*;
import java.util.ArrayList;
import restfulService.editor.*;

public class SystemFlow extends HttpServlet
{	
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws IOException, ServletException
	{		
		request.setCharacterEncoding("UTF-8");
		//response.setCharacterEncoding("utf8");
		RequestDispatcher display;
		HttpSession session=request.getSession();
		Connection conn =  (Connection) getServletContext().getAttribute("dbConn");//���oDB��T
		ResourceOrder run = new ResourceOrder(conn);
		MashupDocumentProduce editor = null;
		String query = null;
		String data = null;
		PrintWriter out = null;
		if(request.getParameter("service").equals("search"))//�j�M�@���q�v��T�\��
		{				
			query = "{queryKey:'"+request.getParameter("query")+"'}";//���o����r
			//�N���G�ରString���A�A���x�s
			out = response.getWriter();
			out.print(run.searchResource(query));
			//display = request.getRequestDispatcher("correct.jsp");//�ɤJ����
			//display.forward(request, response);
		}
		else if(request.getParameter("service").equals("choose"))
		{
			query = request.getParameter("resources");			
			out = response.getWriter();
			out.print(run.chooseResource(query));
		}
		else if(request.getParameter("service").equals("fieldNameChnanged"))
		{
			data = request.getParameter("data");
			ServiceProcess product = new ServiceProcess("01");
			product.produceFieldNameData(data);			
		}
		else if(request.getParameter("service").equals("flowSetup"))
		{
			data = request.getParameter("data");
			editor = new ServiceProcess("01");
			editor.produce(data);			
		}
		else if(request.getParameter("service").equals("mashupSetup"))
		{
			data = request.getParameter("data");
			editor = new DataComposition("01");
			editor.produce(data);			
		}
		else if(request.getParameter("service").equals("widgetData"))
		{
			data = request.getParameter("data");
			editor = new WidgetDesign("01");
			editor.produce(data);			
		}
		else if(request.getParameter("service").equals("widgetSetup"))
		{
			data = request.getParameter("data");
			WidgetDesign document = new WidgetDesign("01");
			document.produceWidgetSetup(data);			
		}
		
	}
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws IOException, ServletException
	{
		response.sendRedirect("index.jsp");
	}
	
}