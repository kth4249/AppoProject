package com.kh.appoproject.cart.model.service;

import static com.kh.appoproject.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.kh.appoproject.cart.model.dao.CartDao;
import com.kh.appoproject.cart.model.vo.Cart;
import com.kh.appoproject.member.model.vo.Member;
public class CartService {

	/** 카트 추가 중복 확인용 서비스
	 * @return
	 * @throws Exception
	 */
	public int selectNoCount(int productNo, Member loginMember) throws Exception{
		Connection conn = getConnection();
		//close(conn);
		return new CartDao().selectNoCount(conn, productNo, loginMember);
	}

	/** 장바구니 추가용 Service
	 * @param productNo
	 * @param loginMember
	 * @return result
	 * @throws Exception
	 */
	public int addCart(int productNo, Member loginMember) throws Exception {
		Connection conn = getConnection();
		int result = new CartDao().addCart(conn, productNo, loginMember);
		
		if(result>0) commit(conn);
		else		 rollback(conn);
		close(conn);
		return result;
	}

	/** 장바구니 조회용 Service
	 * @param loginMember
	 * @return cList
	 * @throws Exception
	 */
	public List<Cart> selectCart(Member loginMember) throws Exception {
		Connection conn = getConnection();
		
		List<Cart> cList = new CartDao().selectCart(conn, loginMember);
		close(conn);
		return cList;
	}

	public int deleteCart(String checkArr, int memberNo) throws Exception{
		Connection conn = getConnection();
		
		int result = new CartDao().deleteCart(conn, checkArr, memberNo);
		
		if(result>0) commit(conn);
		else		 rollback(conn);
		close(conn);
		return result;
	}

}
