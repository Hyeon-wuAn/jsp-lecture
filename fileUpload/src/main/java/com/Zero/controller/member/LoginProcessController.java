package com.Zero.controller.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Zero.utils.ScriptWriter;
import com.Zero.model.MemberDao;
import com.Zero.model.MemberDto;

@WebServlet("/member/loginProcess")
public class LoginProcessController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginProcessController() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDao memberDao = new MemberDao();
		MemberDto memberDto = new MemberDto();
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");
		memberDto.setId(userId);
		memberDto.setPassword(userPw);
		MemberDto loggedMember = memberDao.loginMember(memberDto);
		HttpSession session = request.getSession();
		if(loggedMember!=null) {
			System.out.println(loggedMember.toString());
//			session.setAttribute("loggedMemberId",loggedMember.getId());
//			session.setAttribute("loggedMemberName", loggedMember.getName());
//			session.setAttribute("loggedMemberProfile", loggedMember.getRealProfile());
			session.setAttribute("loggedMember", loggedMember);
			ScriptWriter.alertAndNext(response, loggedMember.getName()+"님 안녕하세요.", "../index/index");
		}
		else {
			ScriptWriter.alertAndBack(response, "알 수 없는 오류 발생.");
		}
	}
}






