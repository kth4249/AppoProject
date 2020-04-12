package com.kh.appoproject.cart.model.service;

import static com.kh.appoproject.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.kh.appoproject.cart.model.dao.WishDao;
import com.kh.appoproject.cart.model.vo.Wish;
import com.kh.appoproject.member.model.vo.Member;

public class WishService {
	/** wish 추가 중복 확인용 서비스
	 * @return
	 * @throws Exception
	 */
	public int selectNoCount(int productNo, Member loginMember) throws Exception{
		Connection conn = getConnection();
		//close(conn);
		return new WishDao().selectNoCount(conn, productNo, loginMember);
	}

	public int addWish(int productNo, int memberNo) throws Exception {
		Connection conn = getConnection();
		int result = new WishDao().addWish(conn, productNo, memberNo);
		
		if(result>0) commit(conn);
		else		 rollback(conn);
		close(conn);
		return result;
	}

	public List<Wish> selectWish(int memberNo) throws Exception {
		Connection conn = getConnection();
		
		List<Wish> wList = new WishDao().selectWish(conn,memberNo);
		close(conn);
		return wList;
	}

	public int deleteWish(String checkArr, int memberNo) throws Exception {
		Connection conn = getConnection();
		int result = new WishDao().deleteWish(conn, checkArr, memberNo);
		
		if(result>0) commit(conn);
		else		rollback(conn);
		close(conn);
		return result;
	}
}
