package com.Zero.controller.member;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.Zero.model.MemberDao;
import com.Zero.model.MemberDto;


@WebServlet("/member/modifyPassword")
public class PasswordModifyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public PasswordModifyController() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/member/modify-password.jsp");
		dispatcher.forward(request, response);	
		
	}
}
