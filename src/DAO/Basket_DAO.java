package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.Basket_DTO;

public class Basket_DAO {  //고객의 구매를 관리하는 DAO
	private Connection conn = null; // oracle 접속하기 위한 연결 컨넥션
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	private String id = "system";
	private String pwd = "1111";
	private ResultSet rs = null; // 쿼리문의 결과를 저장하는 변수

	private Basket_DAO () {
		
	}
	public static Basket_DAO bkdao=null;
	public static Basket_DAO getinstance() {
		if(bkdao==null) {
			bkdao=new Basket_DAO();
		}
		return bkdao;
	}
	public Connection conn() {
		try { // try catch 구문은 예외가 발생할 경우 시스템의 오동작을 방지 하기 위한 구문
			Class.forName(driver); // DB에 접속하기 위한 드라이버 로딩.
			conn = DriverManager.getConnection(url, id, pwd); // db에 접속
			System.out.println("DB연결이 되었습니다.");
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public void addToCart(Basket_DTO bkDTO) {    //고객이 물건을 구매하는 메서드
		String sql="insert into basket values(?,?,?)";
		PreparedStatement ppst=null;
		if(conn()!=null) {
			try {
				ppst=conn.prepareStatement(sql);
				ppst.setInt(1, bkDTO.getNo());
				ppst.setString(2, bkDTO.getUserid());
				ppst.setInt(3, bkDTO.getCnt());
				ppst.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if (conn != null)
						conn.close();
					if (ppst != null)
						ppst.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	}
	public ArrayList<Basket_DTO> myList (String id){  //고객의 구매목록을 보는메서드
		ArrayList<Basket_DTO> blist=new ArrayList<>();
		String sql="select * from basket where id=?";
		PreparedStatement ppst=null;
		rs=null;
		Basket_DTO bkDTO=null;
		if(conn()!=null) {
			try {
				bkDTO=new Basket_DTO();
				ppst=conn.prepareStatement(sql);
				ppst.setString(1, id);
				rs=ppst.executeQuery();
				while(rs.next()) {
					bkDTO.setNo(rs.getInt("no"));
					bkDTO.setUserid(rs.getString("userid"));
					bkDTO.setCnt(rs.getInt("cnt"));
					blist.add(bkDTO);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}finally {
				try {
					if (conn != null)
						conn.close();
					if (ppst != null)
						ppst.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		return blist;
	}
}
