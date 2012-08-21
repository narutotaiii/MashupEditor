package RestfulService.servlet;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
//import java.util.*;
import java.net.*;
import java.sql.*;
import java.util.ArrayList;

public class SystemFlow extends HttpServlet
{	
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws IOException, ServletException
	{	
		/*request.setCharacterEncoding("UTF8");
		//response.setCharacterEncoding("utf8");
		RequestDispatcher display;
		HttpSession session=request.getSession();
		Connection conn =  (Connection) getServletContext().getAttribute("dbConn");//���oDB��T
		FilmUpdater updater = new FilmUpdater(conn);
		if(request.getParameter("method")!=null)//�P�_&����\��
		{
			if(request.getParameter("method").equals("insert_manual"))//��ʷs�W�\��
			{
				//String str = new String(request.getParameter("film_cht_name").getBytes("ISO8859-1"),"UTF8");
				//System.out.println(str);				
				updater.setFilm(new Film(0,new String(request.getParameter("film_cht_name").getBytes("ISO8859-1"),"UTF8"),
										request.getParameter("film_eng_name"),0,
										Date.valueOf(request.getParameter("release_date")),
										new String(request.getParameter("nation").getBytes("ISO8859-1"),"UTF8"),
										new String(request.getParameter("type").getBytes("ISO8859-1"),"UTF8"),
										new String(request.getParameter("level").getBytes("ISO8859-1"),"UTF8"),
										Integer.parseInt(request.getParameter("length")),
										new String(request.getParameter("director").getBytes("ISO8859-1"),"UTF8"),
										new String(request.getParameter("writer").getBytes("ISO8859-1"),"UTF8"),
										new String(request.getParameter("cast").getBytes("ISO8859-1"),"UTF8"),
										new String(request.getParameter("publisher").getBytes("ISO8859-1"),"UTF8"),
										new String(request.getParameter("box_office").getBytes("ISO8859-1"),"UTF8"),
										request.getParameter("official"),request.getParameter("picture"),
										new String(request.getParameter("introduction").getBytes("ISO8859-1"),"UTF8"),
										request.getParameter("trailer"),request.getParameter("source"),request.getParameter("truemovie_source")));
				updater.executeInsert();
				getServletContext().setAttribute("result",updater.getResult());
				display = request.getRequestDispatcher("manager.jsp");
				display.forward(request, response);
			}
			else if(request.getParameter("method").equals("find"))//�j�M�@���q�v��T�\��
			{				
				updater.setFilmID(request.getParameter("film_id"));
				updater.executeFind();
				Film myFilm =new Film();
				myFilm = updater.getFilm();
				getServletContext().setAttribute("film",myFilm);
				getServletContext().setAttribute("result",null);
				display = request.getRequestDispatcher("correct.jsp");
				display.forward(request, response);
			}
			else if(request.getParameter("method").equals("correct"))//�ק�@���q�v��T�\��
			{
				
				System.out.println(request.getParameter("length"));
				updater.setFilmID(request.getParameter("film_id"));
				updater.setFilm(new Film(Integer.parseInt(request.getParameter("film_id")),request.getParameter("film_cht_name"),
										request.getParameter("film_eng_name"),Integer.parseInt(request.getParameter("score")),
										Date.valueOf(request.getParameter("release_date")),request.getParameter("nation"),
										request.getParameter("type"),request.getParameter("level"),
										Integer.parseInt(request.getParameter("length")),request.getParameter("director"),
										request.getParameter("writer"),request.getParameter("cast"),
										request.getParameter("publisher"),request.getParameter("box_office"),
										request.getParameter("official"),request.getParameter("picture"),
										request.getParameter("introduction"),request.getParameter("trailer"),
										request.getParameter("source"),request.getParameter("truemovie_source")));
				updater.executeUpdate();				
				getServletContext().setAttribute("result",updater.getResult());
				display = request.getRequestDispatcher("manager.jsp");
				display.forward(request, response);
			}
			else if(request.getParameter("method").equals("delete"))//�R���@���q�v��T�\��
			{				
				updater.setFilmID(request.getParameter("film_id"));
				updater.executeDelete();
				getServletContext().setAttribute("result",updater.getResult());//�x�s���浲�G
				display = request.getRequestDispatcher("manager.jsp");//�ɦ^�A����
				display.forward(request, response);
			}
			else if(request.getParameter("method").equals("loadErrorReport"))//Ū�����~�^���\��
			{				
				updater.setUserID("0");
				updater.executeLoadReport();
				ArrayList<Report> errorReport = (ArrayList<Report>)updater.getReportList();
				getServletContext().setAttribute("report",errorReport);
				getServletContext().setAttribute("result",null);//�x�s���浲�G
				display = request.getRequestDispatcher("loadErrorReport.jsp");
				display.forward(request, response);
			}
			else if(request.getParameter("method").equals("insert_auto"))//�t�Φ۰ʷs�W�s�q�v��T�\��
			{
				updater.autoInsert();
				getServletContext().setAttribute("result",updater.getResult());//�x�s���浲�G			
				display = request.getRequestDispatcher("manager.jsp");
				display.forward(request, response);
			}
			else if(request.getParameter("method").equals("update_auto"))//�t�Φ۰ʧ�s�\��
			{
				updater.executeAutoUpdate();
				getServletContext().setAttribute("result",updater.getResult());//�x�s���浲�G			
				display = request.getRequestDispatcher("manager.jsp");
				display.forward(request, response);
			}
			else if(request.getParameter("method").equals("clearTable"))//�t�βM����Ʈw�\��
			{
				updater.executeClearTable(request.getParameter("table"));
				getServletContext().setAttribute("result",updater.getResult());//�x�s���浲�G			
				display = request.getRequestDispatcher("manager.jsp");
				display.forward(request, response);
			}
			else if(request.getParameter("method").equals("insert_manualByURL"))//�t�Φ۰ʷs�W�s�q�v��T�\��
			{
				updater.executeInsertByURL(request.getParameter("url"));
				getServletContext().setAttribute("result",updater.getResult());//�x�s���浲�G			
				display = request.getRequestDispatcher("manager.jsp");
				display.forward(request, response);
			}
			else
			{
				getServletContext().setAttribute("result","�ާ@����");
				display = request.getRequestDispatcher("manager.jsp");
				display.forward(request, response);
			}
		}		
		
		
	
		/*response.setCharacterEncoding("utf-8");
		RequestDispatcher display;
		Message newMessage;		
		String action,username,content;
		ArrayList<Message> plurkMessage;
		HttpSession session=request.getSession();
		session.setMaxInactiveInterval(30*60);
		if(request.getParameter("type")!=null)
		{
			action=new String(request.getParameter("action").getBytes("ISO-8859-1"),"utf-8");
			content=new String(request.getParameter("content").getBytes("ISO-8859-1"),"utf-8");
			System.out.println(action);
			if(request.getParameter("type").equals("new"))
			{
				newMessage=new Message(session.getAttribute("username").toString(),action,content,"new");
				plurkMessage=(ArrayList<Message>) getServletContext().getAttribute("plurkMessage");
				plurkMessage.add(newMessage);
				getServletContext().setAttribute("plurkMessage",plurkMessage);
			}
			else if(request.getParameter("type").equals("response"))
			{
				newMessage=new Message(session.getAttribute("username").toString(),action,content,"response");
				plurkMessage=(ArrayList<Message>) getServletContext().getAttribute("plurkMessage");
				plurkMessage.get(Integer.parseInt(request.getParameter("number"))).addResponse(newMessage);
			}
		}
		else
		{
			username=new String(request.getParameter("username").getBytes("ISO-8859-1"),"u");
			System.out.println(username);
			session.setAttribute("username",username);
		}	
		display = request.getRequestDispatcher("plurkSystem.jsp");
		display.forward(request, response);*/
	}
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws IOException, ServletException
	{
		response.sendRedirect("index.jsp");		
	}
	
}