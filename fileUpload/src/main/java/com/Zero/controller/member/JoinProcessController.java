package com.Zero.controller.member;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Zero.model.MemberDao;
import com.Zero.model.MemberDto;
import com.Zero.utils.ScriptWriter;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


@WebServlet("/member/joinProcess")
public class JoinProcessController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public JoinProcessController() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		System.out.println("왜 안넘어오니...");
		
		request.setCharacterEncoding("utf-8");
		String encoding = "utf-8";
		
		int fileSize = 1024 * 1024 * 10;
		String savePath = "upload";
		ServletContext context = this.getServletContext();
		String realPath = context.getRealPath(savePath);
		
		File currentDir = new File(realPath);
		if(!currentDir.exists()) {
			currentDir.mkdir();
		}
		System.out.println("currentDir==="+currentDir);
		DefaultFileRenamePolicy fileReNamePolicy = new DefaultFileRenamePolicy();
		
		MultipartRequest multipartRequest = new MultipartRequest(request, realPath, fileSize, encoding, 
				fileReNamePolicy);

		
		String userId = multipartRequest.getParameter("userId"); 
		String userPw = multipartRequest.getParameter("userPw"); 
		String userName = multipartRequest.getParameter("userName"); 
		String userEmail = multipartRequest.getParameter("userEmail"); 
		int zonecode = Integer.parseInt(multipartRequest.getParameter("zonecode")); 
		String userAddress = multipartRequest.getParameter("userAddress"); 
		String detailAddress = multipartRequest.getParameter("detailAddress"); 
		String extraAddress = multipartRequest.getParameter("extraAddress"); 
		
		String originalFile = multipartRequest.getOriginalFileName("profile");
		String renameFile = multipartRequest.getFilesystemName("profile");
		
		MemberDao memberDao = new MemberDao();
		MemberDto memberDto = new MemberDto();
		memberDto.setId(userId);
		memberDto.setPassword(userPw);
		memberDto.setName(userName);
		memberDto.setZonecode(zonecode);
		memberDto.setAddress(userAddress);
		memberDto.setExtraAddress(extraAddress);
		memberDto.setDetailAddress(detailAddress);
		memberDto.setEmail(userEmail);
		memberDto.setProfile(originalFile);
		memberDto.setRealProfile(renameFile);
		System.out.println(memberDto);
		
		
		int result = memberDao.insertMember(memberDto);
		if(result>0) {
			ScriptWriter.alertAndNext(response, "회원가입 되었습니다.", "../member/login");
		} else {
			ScriptWriter.alertAndBack(response, "알 수 없는 오류가 발생 되었습니다. 다시 시도해 주세요");
		}
	}
	
	private String getToday() {
		// 오늘 날짜 돌려받기
		return new SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis());
	}
}






