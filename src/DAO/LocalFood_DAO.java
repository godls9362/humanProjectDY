package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import DTO.LocalFood_DTO;

public class LocalFood_DAO { // 로컬푸드 물건들을 관리하는 DAO
	private Connection conn = null; // oracle 접속하기 위한 연결 컨넥션
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:orcl1";
	private String id = "system";
	private String pwd = "1111";
	private ResultSet rs = null; // 쿼리문의 결과를 저장하는 변수
	
	private LocalFood_DAO(){
		
	}
	
	public static LocalFood_DAO lfdao=null;
	public static LocalFood_DAO getinstance() {
		if(lfdao==null) {
			lfdao=new LocalFood_DAO();
		}
		return lfdao;
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
	public LocalFood_DTO  cal() { //지금까지 구매한 물품들의 금액을 구하는 메서드
		String sql="select * from view2";
		Statement st = null;
		LocalFood_DTO lfDTO=null;
		rs=null;
		if(conn()!=null) {
			try {
				lfDTO=new LocalFood_DTO();
				st=conn.createStatement();
				rs=st.executeQuery(sql);
				if(rs.next());
				lfDTO.setPrice(rs.getInt("price"));
			} catch (Exception e) {
				// TODO: handle exception
			}finally {
				try {
					if (conn != null)
						conn.close();
					if (st != null)
						st.close();
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		}
		return lfDTO;
	}
	

	public void insertGoods(LocalFood_DTO lfDTO) { // 물건입고
		String sql = "insert into localfood values(seq_lf.nextval,?,?,?,?)";
		PreparedStatement ppst = null;

		if (conn() != null) {
			try {
				ppst = conn.prepareStatement(sql);
				ppst.setString(1, lfDTO.getName());
				ppst.setInt(2, lfDTO.getPrice());
				ppst.setString(3, lfDTO.getMadeBy());
				ppst.setInt(4, lfDTO.getStock());
				ppst.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null)
						conn.close();
					if (ppst != null)
						ppst.close();
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		}
	}

	public ArrayList<LocalFood_DTO> selectAll() {  //물건보기
		ArrayList<LocalFood_DTO> lflist = new ArrayList<>();
		String sql = "select * from localfood";
		Statement st = null;
		rs = null;
		LocalFood_DTO lfDTO = null;
		if (conn() != null) {
			try {
				lfDTO = new LocalFood_DTO();
				st = conn.createStatement();
				rs = st.executeQuery(sql);
				while (rs.next()) {
					lfDTO.setNo(rs.getInt("no"));
					lfDTO.setName(rs.getString("name"));
					lfDTO.setPrice(rs.getInt("price"));
					lfDTO.setMadeBy(rs.getString("madeby"));
					lfDTO.setStock(rs.getInt("stock"));
					lflist.add(lfDTO);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null)
						conn.close();
					if (st != null)
						st.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		return lflist;
	}

	public LocalFood_DTO findOne(int no) { //고유번호로 물건찾기
		String sql = "select * from localfood where no=?";
		PreparedStatement ppst = null;
		LocalFood_DTO lfDTO = null;
		if (conn() != null) {
			try {
				lfDTO = new LocalFood_DTO();
				ppst = conn.prepareStatement(sql);
				ppst.setInt(1, no);
				rs = ppst.executeQuery();
				if (rs.next()) {
					lfDTO.setNo(rs.getInt("no"));
					lfDTO.setName(rs.getString("name"));
					lfDTO.setPrice(rs.getInt("price"));
					lfDTO.setMadeBy(rs.getString("madeby"));
					lfDTO.setStock(rs.getInt("stock"));
				}
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
		return lfDTO;
	}
	public void updateCnt(int cnt,int No) {  //고객의 구매량에 따라 재고 감소
		String sql="update localfood set stock=stock-? where no=?";
		PreparedStatement ppst=null;
		if(conn()!=null) {
			try {
				ppst=conn.prepareStatement(sql);
				ppst.setInt(1, cnt);
				ppst.setInt(2, No);
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
	public void deleteOne(int no) {  //물건 삭제하기 
		String sql="delete from localfood where no=?";
		PreparedStatement ppst=null;
		if(conn()!=null) {
			try {
				ppst=conn.prepareStatement(sql);
				ppst.setInt(1, no);
				ppst.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
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
}