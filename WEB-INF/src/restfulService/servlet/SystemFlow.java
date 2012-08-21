package restfulService.servlet;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
//import java.util.*;
import java.net.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import restfulService.editor.*;
import mashup.*;

public class SystemFlow extends HttpServlet
{	
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws IOException, ServletException
	{		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		RequestDispatcher display;
		HttpSession session=request.getSession();
		Connection conn =  (Connection) getServletContext().getAttribute("dbConn");//取得DB資訊
		ResourceOrder run = new ResourceOrder(conn);
		
		MashupDocumentProduce editor = null;
		String query = null;
		String data = null;		
		if(request.getParameter("service").equals("search"))//搜尋服務資源功能
		{				
			query = "{queryKey:'"+request.getParameter("query")+"'}";//取得關鍵字
			out.print(run.searchResource(query));//將結果傳入前端	
		}
		else if(request.getParameter("service").equals("choose"))
		{
			query = request.getParameter("resources");
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
		else if(request.getParameter("service").equals("relationalRecommand"))
		{
			data = request.getParameter("data");
			System.out.println(data);
			try 
			{				
				Recommender recommand = new Recommender( (String) getServletContext().getAttribute("user"), (String) getServletContext().getAttribute("password") , "services" );
				Set<String> services = recommand.recommend( data , 2 );
				out.println(run.relationalRecommand(services));
			}
			catch (Exception e) 
			{				
				e.printStackTrace();
			}			
		}
		else if(request.getParameter("service").equals("patternRecommand"))
		{
			data = request.getParameter("data");
			try 
			{				
				SPRecommender recommander = new SPRecommender( CombinationCategory.Selection );
				List<String> services = recommander.recommend( data , 2 );
				getServletContext().setAttribute("recommandServices", services);
			}
			catch (Exception e) 
			{				
				e.printStackTrace();
			}
					
		}
		else if(request.getParameter("service").equals("overallRecommand"))
		{
			data = request.getParameter("data");
			OverallRecommend recommander;
			try 
			{
				recommander = new OverallRecommend();
				List<WeightedSimpleMashup> mashups = recommander.recommend( data , 2);
			}
			catch (Exception e) 
			{				
				e.printStackTrace();
			}			
						
		}
		
	}
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws IOException, ServletException
	{
		response.sendRedirect("index.jsp");
	}	
}