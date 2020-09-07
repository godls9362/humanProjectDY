package DAO;

import java.util.ArrayList;

import DTO.LocalFood_DTO;

public class LocalFood_DAO extends DAObase { // 로컬푸드 물건들을 관리하는 DAO
	private LocalFood_DAO(){
		
	}
	
	public static LocalFood_DAO lfdao=null;
	public static LocalFood_DAO getinstance() {
		if(lfdao==null) {
			lfdao=new LocalFood_DAO();
		}
		return lfdao;
	}

	public ArrayList<LocalFood_DTO>  cal() { //고객의 장바구니 총금액을 보는 메서드
		String sql="select * from view2";
		 st = null;
		LocalFood_DTO lfDTO=null;
		rs=null;
		ArrayList<LocalFood_DTO> list=new ArrayList<>();
		if(connect()!=null) {
			try {
				lfDTO=new LocalFood_DTO();
				st=conn.createStatement();
				rs=st.executeQuery(sql);
				if(rs.next());
				lfDTO.setPrice(rs.getInt("price"));
				list.add(lfDTO);
			} catch (Exception e) {
				// TODO: handle exception
			}finally {
				disconnect();
			}
		}
		return list;
	}
	public ArrayList<LocalFood_DTO> yourPay(String userid) {
		ppst = null;
		String sql = "select sum(localfood.price)price from localfood inner join basket on basket.no=localfood.no where basket.userid=?";
		LocalFood_DTO lfdto=null;
		ArrayList<LocalFood_DTO> list=new ArrayList<>();
		try {
			lfdto=new LocalFood_DTO();
			ppst = conn.prepareStatement(sql);
			ppst.setString(1, userid);
			rs = ppst.executeQuery();
			while(rs.next()) {
			lfdto.setPrice(rs.getInt("price"));
			list.add(lfdto);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			disconnect();
		}
		return list;
	}

	

	public void insertGoods(LocalFood_DTO lfDTO) { // 물건입고
		String sql = "insert into localfood values(seq_lf.nextval,?,?,?,?)";
		 ppst = null;

		if (connect() != null) {
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
				disconnect();
			}
		}
	}

	public ArrayList<LocalFood_DTO> selectAll() {  //물건보기
		ArrayList<LocalFood_DTO> lflist = new ArrayList<>();
		String sql = "select * from localfood";
		 st = null;
		rs = null;
		if (connect() != null) {
			try {
				st = conn.createStatement();
				rs = st.executeQuery(sql);
				while (rs.next()) {
					LocalFood_DTO lfDTO = new LocalFood_DTO();
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
				disconnect();
			}
		}
		return lflist;
	}

	public LocalFood_DTO findOne(int no) { //고유번호로 물건찾기
		String sql = "select * from localfood where no=?";
		 ppst = null;
		LocalFood_DTO lfDTO = null;
		if (connect() != null) {
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
				disconnect();
			}
		}
		return lfDTO;
	}
	public void updateCnt(int cnt,int No) {  //고객의 구매량에 따라 재고 감소
		String sql="update localfood set stock=stock-? where no=?";
		ppst=null;
		if(connect()!=null) {
			try {
				ppst=conn.prepareStatement(sql);
				ppst.setInt(1, cnt);
				ppst.setInt(2, No);
				ppst.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				disconnect();
			}
		}
	}
	public void deleteOne(int no) {  //물건 삭제하기 
		String sql="delete from localfood where no=?";
		ppst=null;
		if(connect()!=null) {
			try {
				ppst=conn.prepareStatement(sql);
				ppst.setInt(1, no);
				ppst.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				disconnect();
			}
		}
		
	}
}