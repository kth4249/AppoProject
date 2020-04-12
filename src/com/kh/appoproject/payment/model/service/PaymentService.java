package com.kh.appoproject.payment.model.service;

import static com.kh.appoproject.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.kh.appoproject.destination.model.vo.Destination;
import com.kh.appoproject.payment.model.dao.PaymentDao;
import com.kh.appoproject.payment.model.vo.Payment;
import com.kh.appoproject.product.model.vo.Product;

public class PaymentService {

	public List<Product> billingInfo(String products) throws Exception{
		Connection conn = getConnection();
		String condition = "";
		condition = "(" + products + ")";
		
		System.out.println(condition);
		
		List<Product> pList = new PaymentDao().billingInfo(conn, condition);
		System.out.println("pList : " + pList);
		for(Product proc : pList) {
			// 주문된 상품 중 경매로 구매한 상품에 대해서
			if(proc.getProductForm().equals("A")) {
				// 입찰가를 일반구매가에 저장
				proc.setBasicPrice(proc.getAuctionReservePrice());
				proc.setBasicNo(proc.getBasicNo());
			}
		}
		
		close(conn);
		return pList;
		
		
	}

	public List<Destination> selectDestination(int member_No) throws Exception{
		Connection conn = getConnection();
		List<Destination> dList = new PaymentDao().selectDestination(conn, member_No);
		
		close(conn);
		return dList;
	}

	/*
	 * public String cardPayment(String name, String amount, String buyer_name)
	 * throws Exception {
	 * 
	 * Connection conn = getConnection();
	 * 
	 * PaymentDao paymentDao = new PaymentDao();
	 * 
	 * String merchantUid = paymentDao.createMerchantUid(conn);
	 * 
	 * int result = 0;
	 * 
	 * if(merchantUid != null) { result = paymentDao.insertOrder(conn, name, amount,
	 * buyer_name, merchantUid);
	 * 
	 * if(result > 0) { commit(conn); }else { rollback(conn); merchantUid = null; }
	 * }
	 * 
	 * 
	 * return merchantUid; }
	 */

	// 무통장 입금
	public int bankDeposit(Payment payment, int memberNo, String basicNo) throws Exception{
		
		Connection conn = getConnection();
		
		PaymentDao paymentDao = new PaymentDao();
		
		
		int result = 0;
		String[] basicNoArr = basicNo.split(",");
		
		for(String basictNo : basicNoArr) {
			payment.setBasicNo(Integer.parseInt(basictNo));
			result = paymentDao.insertProductOrder(conn, payment, memberNo);
			if(result == 0) {
				break;
			}else {
				paymentDao.deleteCart(conn, memberNo, basictNo);
			}
		}
		
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		return result;
	}
	
	 
	

}
