package com.Zero.controller.board;

import java.io.IOException;
import java.util.ArrayList;



import com.Zero.model.BoardDao;
import com.Zero.model.BoardDto;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/board/list")
public class ListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ListController() {
        super();
    }


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		BoardDao boardDao = new BoardDao();
		ArrayList<BoardDto> boardList = boardDao.getList();
		request.setAttribute("boardList", boardList);
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("/WEB-INF/board/list.jsp");
		dispatcher.forward(request, response);
	}
}


