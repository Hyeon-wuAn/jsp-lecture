package com.Zero.controller.board;

import java.io.IOException;

import com.Zero.model.BoardDao;
import com.Zero.model.BoardDto;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/board/modify")
public class ModifyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ModifyController() {
        super();
    }


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		BoardDao boardDao = new BoardDao();
		BoardDto boardDto = boardDao.getView(id);
		request.setAttribute("boardDto", boardDto);
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("/WEB-INF/board/modify.jsp");
		dispatcher.forward(request, response);
	}
}



