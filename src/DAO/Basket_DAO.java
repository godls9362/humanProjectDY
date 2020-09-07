package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.Basket_DTO;

public class Basket_DAO extends DAObase { // 고객의 구매를 관리하는 DAO

	private Basket_DAO() {

	}

	public static Basket_DAO bkdao = null;

	public static Basket_DAO getinstance() {
		if (bkdao == null) {
			bkdao = new Basket_DAO();
		}
		return bkdao;
	}

	public void addToCart(Basket_DTO bkDTO) { // 고객이 물건을 구매하는 메서드
		String sql = "insert into basket values(?,?,?)";
		ppst = null;
		if (connect() != null) {
			try {
				ppst = conn.prepareStatement(sql);
				ppst.setInt(1, bkDTO.getNo());
				ppst.setString(2, bkDTO.getUserid());
				ppst.setInt(3, bkDTO.getCnt());
				ppst.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				disconnect();
			}
		}
	}

	public ArrayList<Basket_DTO> myList(String id) { // 고객의 구매목록을 보는메서드
		ArrayList<Basket_DTO> blist = new ArrayList<>();
		String sql = "select * from basket where userid=?";
		ppst = null;
		rs = null;
		if (connect() != null) {
			try {
				Basket_DTO bkDTO = new Basket_DTO();
				ppst = conn.prepareStatement(sql);
				ppst.setString(1, id);
				rs = ppst.executeQuery();
				while (rs.next()) {
					bkDTO.setNo(rs.getInt("no"));
					bkDTO.setUserid(rs.getString("userid"));
					bkDTO.setCnt(rs.getInt("cnt"));
					blist.add(bkDTO);
				}
			} catch (Exception e) {
			} finally {
				disconnect();
			}
		}
		return blist;
	}

	
}
