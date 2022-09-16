 package com.valanikevin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class gameSelection
 */
@WebServlet("/gameSelection")
public class gameSelection extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public gameSelection() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int gameSelection = Integer.parseInt(request.getParameter("gameSelection"));
		int gameRow = Integer.parseInt(request.getParameter("gameRow"));
		int gameCol = Integer.parseInt(request.getParameter("gameCol"));
		boolean redirect = false;
		HttpSession session = request.getSession();
		
		PrintWriter out = response.getWriter();
		
		if(gameSelection == 1) {
			session.setAttribute("userRow", gameRow);
			session.setAttribute("userCol", gameCol);
			redirect = true;
		}
		else if(gameSelection == 2) {
			session.setAttribute("userRow", gameRow);
			session.setAttribute("userCol", gameCol);
			redirect = true;
		}
		else {
			out.println("Invalid Game Selection.");
		}
		
		if(redirect == true) {
			if(gameSelection == 1) {
				RequestDispatcher rd = request.getRequestDispatcher("nonIntelligentGame");
				rd.forward(request, response);	
			}
			else if(gameSelection == 2) {
				RequestDispatcher rd = request.getRequestDispatcher("intelligentGame");
				rd.forward(request, response);
			}
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
