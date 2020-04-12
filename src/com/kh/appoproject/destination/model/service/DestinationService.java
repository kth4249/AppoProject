package com.kh.appoproject.destination.model.service;

import static com.kh.appoproject.common.JDBCTemplate.*;
import java.sql.Connection;

import com.kh.appoproject.destination.model.dao.DestinationDao;
import com.kh.appoproject.destination.model.vo.Destination;

public class DestinationService {

	/** 배송지 추가
	 * @param dest
	 * @return result
	 * @throws Exception
	 */
	public int addDest(Destination dest) throws Exception{
		Connection conn = getConnection();
		DestinationDao destDao = new DestinationDao();
		int destinationNo = destDao.destNextNo(conn);
		dest.setDestinationNo(destinationNo);
		System.out.println(dest);
		
		int result = 0;
		
		if(destinationNo > 0) {
			result = destDao.addDest(conn, dest);
			
			if(result > 0) {
				commit(conn);
				result = destinationNo;
			} else {
				rollback(conn);
			}
		}
		
		close(conn);
		
		return result;
	}
	
	
	public int updateDest(Destination dest) throws Exception{
		Connection conn = getConnection();
		DestinationDao destDao = new DestinationDao();
		
		int result = destDao.updateDest(conn, dest);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	public Destination selectDest(int destinationNo) throws Exception{
		Connection conn = getConnection();
		Destination dest = new DestinationDao().selectDest(conn, destinationNo);
		
		close(conn);
		return dest;
	}



}
