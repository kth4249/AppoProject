package com.kh.appoproject.payment.model.service;

import static com.kh.appoproject.common.JDBCTemplate.*;

import java.sql.Connection;

import com.kh.appoproject.payment.model.dao.OrderDao;
import com.kh.appoproject.payment.model.vo.Order;

public class OrderService{

	public String insertOrder(Order order) throws Exception{
		
		Connection conn = getConnection();
		
		OrderDao orderDao = new OrderDao();
		
		String merchantUid = orderDao.createMerchantUid(conn);
		
		int result = 0;
		
		if(merchantUid != null) {
			result = orderDao.insertOrder(conn, order, merchantUid);
			
			if(result > 0) {
				commit(conn);
			}else {
				rollback(conn);
				merchantUid = null;
			}
		}
		
		
		return merchantUid;
	}

}
