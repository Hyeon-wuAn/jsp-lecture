package com.Zero.controller.board;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.Zero.model.BoardDao;
import com.Zero.utils.ScriptWriter;

@WebServlet("/board/delete")
public class BoardDeleteProcessController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public BoardDeleteProcessController() {
        super();
    }


	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		BoardDao boardDao = new BoardDao();
		int result = boardDao.deleteBoard(id);
		if(result>0) {
			ScriptWriter.alertAndNext(response, "삭제되었습니다.", "../board/list");
		} else {
			ScriptWriter.alertAndBack(response, "알 수 없는 오류... 삭제 안됨..");
		}
	}
}
