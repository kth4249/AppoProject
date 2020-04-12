package com.kh.appoproject.member.model.service;

import java.sql.Connection;
import java.util.List;

import static com.kh.appoproject.common.JDBCTemplate.*;

import com.kh.appoproject.member.model.dao.MemberDao;
import com.kh.appoproject.member.model.vo.Member;
import com.kh.appoproject.product.model.vo.Product;

public class MemberService {

	/**
	 * 로그인용 DAO
	 * 
	 * @param member
	 * @return loginMember
	 * @throws Exception
	 */
	public Member loginMember(Member member) throws Exception {

		Connection conn = getConnection();

		Member loginMember = new MemberDao().loginMember(conn, member);

		return loginMember;
	}

	/**
	 * 회원가입용 Service
	 * 
	 * @param member
	 * @return result
	 * @throws Exception
	 */
	public int join(Member member) throws Exception {

		Connection conn = getConnection();
		int result = new MemberDao().join(conn, member);

		if (result > 0)
			commit(conn);
		else
			rollback(conn);

		return result;
	}

	/**
	 * 아이디 중복 확인용 Service
	 * 
	 * @param id
	 * @return result
	 * @throws Exception
	 */
	public int idDupCheck(String id) throws Exception {
		Connection conn = getConnection();

		int result = new MemberDao().idDupCheck(conn, id);

		close(conn);
		return result;
	}

	/**
	 * 아이디 찾기용 Service
	 * 
	 * @param member
	 * @return
	 * @throws Exception
	 */
	public String FindIdEm(Member member) throws Exception {

		Connection conn = getConnection();
		String member_Id = new MemberDao().FindIdEm(conn, member);
		close(conn);
		return member_Id;
	}

	public String FindIdPh(Member member) throws Exception {
		Connection conn = getConnection();
		String member_Id = new MemberDao().FindIdPh(conn, member);
		close(conn);
		return member_Id;
	}

	public String FindPwd(Member member) throws Exception {
		Connection conn = getConnection();
		String memberId = new MemberDao().FindIdPwd(conn, member);
		close(conn);
		return memberId;
	}

	public int updatePwd(Member changeMember, String inputNewPwd) throws Exception{

		Connection conn = getConnection();
		MemberDao memberDao = new MemberDao();
		
		int result = memberDao.checkAcc(conn,changeMember);
		
		if (result > 0) {
			
			changeMember.setMember_Pwd(inputNewPwd);
			result = memberDao.updateNewPwd(conn, changeMember);
			
			if (result > 0)
				commit(conn);
			else
				rollback(conn);

			return result;
			
		}else {
			return -1;
		}
	}
	
	/** 회원 정보 조회용 Service
	 * @param memberId
	 * @return selectMember
	 * @throws Exception
	 */
	public Member selectMember(String memberId)throws Exception {
		
		Connection conn = getConnection();
		
		
		
		return new MemberDao().selectMember(conn, memberId);
	}
	

	/** 회원정보 수정용 Service
	 * @param member
	 * @return result
	 * @throws Exception
	 */
	public int updateMember(Member member)throws Exception {
		
		Connection conn = getConnection();
		
		int result = new MemberDao().updateMember(member, conn);
		
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		
		return result;
	}


	/** 마감된 경매목록 가져오기 Service
	 * @return pList
	 * @throws Exception
	 */
	public List<Product> selectCompletAuction(String memberId) throws Exception{
		Connection conn = getConnection();
		List<Product> pList = new MemberDao().selectCompleteAuction(conn,memberId);
		return pList;
	}



}
