package com.kh.appoproject.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.appoproject.common.ExceptionForward;
import com.kh.appoproject.member.model.service.MemberService;
import com.kh.appoproject.member.model.vo.Member;
import com.kh.appoproject.product.model.vo.Product;

@WebServlet("/member/*")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MemberServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String uri = request.getRequestURI();

		String contextPath = request.getContextPath();

		String command = uri.substring((contextPath + "/member").length());

		String path = null;
		RequestDispatcher view = null;
		String msg = null;

		MemberService memberService = new MemberService();

		if (command.equals("/loginForm")) {

			path = "/WEB-INF/views/member/memberLogin.jsp";
			view = request.getRequestDispatcher(path);
			view.forward(request, response);

		} else if (command.equals("/login")) {

			request.setCharacterEncoding("UTF-8");

			String memberId = request.getParameter("member_Id");
			String memberPwd = request.getParameter("member_Pwd");

			System.out.println(memberId + memberPwd);

			Member member = new Member(memberId, memberPwd);

			try {
				Member loginMember = memberService.loginMember(member);

				System.out.println("loginMember : " + loginMember);

				response.setContentType("text/html); charset=UTF-8");

				HttpSession session = request.getSession();

				if (loginMember != null) {

					//session.setMaxInactiveInterval(600);

					session.setAttribute("loginMember", loginMember);

					String save = request.getParameter("save");
					System.out.println("save : " + save);

					Cookie cookie = new Cookie("saveId", memberId);

					if (save != null) {
						cookie.setMaxAge(60 * 60 * 24 * 7);
					} else {
						cookie.setMaxAge(0); // 쿠키 만료
					}

					cookie.setPath("/");
					response.addCookie(cookie);
					session.setAttribute("msg", "로그인 성공!");
					response.sendRedirect(request.getContextPath());

				} else {

					session.setAttribute("msg", "로그인 정보가 유효하지 않습니다.");
					response.sendRedirect(request.getHeader("referer"));
				}

			} catch (Exception e) {

				request.setAttribute("errorMsg", "로그인 과정에서 오류가 발생하였습니다.");
				e.printStackTrace();

				path = "/WEB-INF/views/common/errorPage.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
			}
		}

		else if (command.equals("/logoutForm")) {
			request.getSession().invalidate();
			response.sendRedirect(request.getContextPath() + "/member/loginForm");
		}

		else if (command.equals("/agreeForm")) {
			path = "/WEB-INF/views/member/memberAgree.jsp";
			view = request.getRequestDispatcher(path);
			view.forward(request, response);
		}

		else if (command.equals("/joinForm")) {

			path = "/WEB-INF/views/member/memberJoin.jsp";
			view = request.getRequestDispatcher(path);
			view.forward(request, response);
		}

		else if (command.equals("/join")) {

			String member_Id = request.getParameter("id");
			String member_Pwd = request.getParameter("pwd1");
			String member_NM = request.getParameter("name");

			String member_Email = request.getParameter("email");

			String member_Phone = request.getParameter("phone1") + "-" + request.getParameter("phone2") + "-"
					+ request.getParameter("phone3");

			String post = request.getParameter("post");
			String member_Address = post + "," + request.getParameter("address1") + ","
					+ request.getParameter("address2");

			String bank = request.getParameter("bank");
			String member_Account = bank + "," + request.getParameter("accNo");

			Member member = new Member(member_Id, member_Pwd, member_NM, member_Phone, member_Email, member_Account,
					member_Address);

			System.out.println("join :" + member);

			try {

				int result = memberService.join(member);

				// response.sendRedirect(request.getContextPath());

				response.sendRedirect("joinSuccess");

			} catch (Exception e) {

				request.setAttribute("errorMsg", "회원가입 과정에서 오류가 발생 하였습니다.");
				e.printStackTrace();

				path = "/WEB-INF/views/common/errorPage.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);

			}

		} else if (command.equals("/idDupCheck")) {

			String id = request.getParameter("id");
			try {

				int result = memberService.idDupCheck(id);

				PrintWriter out = response.getWriter();

				if (result > 0)
					out.append("no");
				else
					out.append("yes");

			} catch (Exception e) {
				request.setAttribute("errorMsg", "아이디 중복 확인 과정에서 오류가 발생하였습니다.");
				e.printStackTrace();

				path = "/WEB-INF/views/common/errorPage.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
			}
		}

		else if (command.equals("/idDupForm")) {
			path = "/WEB-INF/views/member/idDupCheck.jsp";
			view = request.getRequestDispatcher(path);
			view.forward(request, response);
		}

		else if (command.equals("/joinSuccess")) {

			path = "/WEB-INF/views/member/memberJoinSuccess.jsp";
			view = request.getRequestDispatcher(path);
			view.forward(request, response);

		}

		else if (command.equals("/Findacc")) {

			path = "/WEB-INF/views/member/memberFindIdPwd.jsp";
			view = request.getRequestDispatcher(path);
			view.forward(request, response);
		}

		// 아이디 찾기

		else if (command.equals("/FindId")) {
			

			String hidden = request.getParameter("hide");


			if (hidden.equals("1")) { // 이메일로 인증할때

				String member_NM = request.getParameter("inputName");
				String member_Email = request.getParameter("inputEmail");

				Member member = new Member();

				member.setMember_NM(member_NM);
				member.setMember_Email(member_Email);
				
				try {
					
					String member_Id = memberService.FindIdEm(member);
					PrintWriter out = response.getWriter();
					
					if(member_Id != null) {
						
						request.setAttribute("member_Id", member_Id);
						path = "/WEB-INF/views/member/memberFindIdResult.jsp";
						view = request.getRequestDispatcher(path);
						view.forward(request, response);
						
					}else {
						
						out.println("<script>");
						out.println("alert('존재하지 않는 회원정보 입니다.');");
						out.println("location.href = '"+request.getHeader("referer")+"';");
						out.println("</script>");
					}
			
				} catch (Exception e) {

					request.setAttribute("errorMsg", "아이디 찾기 과정에서 오류가 발생 하였습니다.");
					e.printStackTrace();

					path = "/WEB-INF/views/common/errorPage.jsp";
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
				}

			} else if (hidden.equals("2")) {
				
				String member_NM = request.getParameter("inputName");
				String member_Phone = request.getParameter("phone1") + "-" + request.getParameter("phone2") + "-"
						+ request.getParameter("phone3");
			

				Member member = new Member();

				member.setMember_NM(member_NM);
				member.setMember_Phone(member_Phone);

				try {
					
					String member_Id = memberService.FindIdPh(member);
					PrintWriter out = response.getWriter();
					
					if(member_Id != null) {
					
						request.setAttribute("member_Id", member_Id);
						path = "/WEB-INF/views/member/memberFindIdResult.jsp";
						view = request.getRequestDispatcher(path);
						view.forward(request, response);
						
					} else {

						out.println("<script>");
						out.println("alert('존재하지 않는 회원정보 입니다.');");
						out.println("location.href = '"+request.getHeader("referer")+"';");
						out.println("</script>");
						
					}
					

				} catch (Exception e) {

					request.setAttribute("errorMsg", "아이디 찾기 과정에서 오류가 발생 하였습니다.");
					e.printStackTrace();

					path = "/WEB-INF/views/common/errorPage.jsp";
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
				}

			}

		}

		// 패스워드 찾기->변경페이지로 
		else if (command.equals("/FindPwd")) {
			
			String member_Id = request.getParameter("inputId");
			String member_NM = request.getParameter("inputName2");
			String member_Email = request.getParameter("inputEmail2");

			Member member = new Member(member_Id, member_NM, member_Email);
			
			System.out.println(member_Email);

			try {
				String memberId = memberService.FindPwd(member);
				PrintWriter out = response.getWriter();
				
				if(memberId != null) {
					
					request.setAttribute("memberId", memberId);
					
					path = "/WEB-INF/views/member/memberFindPwdChange.jsp";
					request.getSession().setAttribute("changeMember", member);
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
					
				}else {
					
					out.println("<script>");
					out.println("alert('존재하지 않는 회원정보 입니다.');");
					out.println("location.href = '"+request.getHeader("referer")+"';");
					out.println("</script>");
					
				}
				
			}

			catch (Exception e) {

				request.setAttribute("errorMsg", "비밀번호 찾기 중 오류가 발생 하였습니다.");
				e.printStackTrace();

				path = "/WEB-INF/views/common/errorPage.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
			}
		}
		else if(command.equals("/FindPwdChange")) {
			
			Member changeMember = (Member)request.getSession().getAttribute("changeMember");
			request.getSession().removeAttribute("changeMember");
			
			System.out.println("여기!: "+changeMember);
			
			String inputNewPwd = request.getParameter("inputNewPassword1");
			
			try {
				
				PrintWriter out = response.getWriter();
				
				int result = memberService.updatePwd(changeMember, inputNewPwd);
				
				
				
				if(result > 0) {
					
					out.println("<script>");
					out.println("alert('비밀번호가 변경되었습니다.');");
					out.println("location.href = 'loginForm'");
					out.println("</script>");
					
				}else{
					
					out.println("<script>");
					out.println("alert('비밀번호가 변경에 실패하였습니다.');");
					out.println("location.href = '"+request.getHeader("referer")+"';");
					out.println("</script>");
					
				}
			
			}catch(Exception e) {
				request.setAttribute("errorMsg", "비밀번호 수정 과정에서 오류가 발생하였습니다.");
				
				e.printStackTrace();
				
				path="/WEB-INF/views/common/errorPage.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request,response);
			}
			
		}
		
		
		else if(command.equals("/infoForm")) {
			HttpSession session = request.getSession();
			Member loginMember = (Member)session.getAttribute("loginMember");
			String memberId = loginMember.getMember_Id();
			
			try {
				Member selectMember = memberService.selectMember(memberId);
				System.out.println("selectMember : " + selectMember);
				
				request.setAttribute("member", selectMember);
				
				path = "/WEB-INF/views/member/memberInfo.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
				
				
			}catch(Exception e) {
				request.setAttribute("errorMsg", "회원정보조회 과정에서 오류가 발생하였습니다.");
				e.printStackTrace();
				
				path = "/WEB-INF/views/common/errorPage.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
			}
		}
		
		
		// 박준건님
		
		
		else if(command.equals("/infoModify")) {
			HttpSession session = request.getSession();
			Member loginMember = (Member)session.getAttribute("loginMember");
			String memberId = loginMember.getMember_Id();
			
			String memberPwd = loginMember.getMember_Pwd();
			
			String memberName = loginMember.getMember_NM();
			String phone1 = request.getParameter("phone1");
			String phone2 = request.getParameter("phone2");
			String phone3 = request.getParameter("phone3");
			
			String memberPhone = phone1 + "-" + phone2 + "-" + phone3;
			
			String memberEmail = request.getParameter("email");
			
			String post = request.getParameter("post");
			String address1 = request.getParameter("address1");
			String address2 = request.getParameter("address2");
			
			String memberAddress = post + "," + address1 + "," + address2;
			
			String memberAccount = request.getParameter("account");
			
			Member member = new Member(memberId, memberPwd, memberName, memberPhone, memberEmail, memberAddress, memberAccount);
			
			try {
				
				int result = memberService.updateMember(member);
				
				msg = null;
				
				if(result>0) {
					msg = "회원 정보가 수정되었습니다.";
					
					
					path = "/WEB-INF/views/member/memberInfo.jsp";
					request.setAttribute("member", member);
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
					
				}else {
					msg = "회원 정보 수정 실패";
				}
				
//				session.setAttribute("msg", msg);
//				response.sendRedirect("/infoForm");
				
			}catch(Exception e) {
				request.setAttribute("errorMsg", "회원정보수정 과정에서 오류가 발생하였습니다.");
				
				e.printStackTrace();
				
				path = "/WEB-INF/views/common/errorPage.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
			}
		}
		
		
	




		// 낙찰된 경매 목록 조회
		else if(command.equals("/alarm")) { 
				// 낙찰된 경매 게시글 출력
			try {
					String memberId = request.getParameter("memberId");
				
				// 낙찰된 상품게시글 정보 가져오기
				List<Product> pList = memberService.selectCompletAuction(memberId);
				for(Product product : pList) {
					System.out.println(product);
				}
	
				request.setAttribute("pList", pList);
				path="/WEB-INF/views/member/alarm.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request,response);
			} catch(Exception e) {
				ExceptionForward.errorPage(request, response, "게시판 조회 ", e);
			}
							
		}
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
