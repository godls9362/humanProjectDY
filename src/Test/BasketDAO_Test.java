package Test;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import DAO.Basket_DAO;
import DTO.Basket_DTO;

public class BasketDAO_Test {
	Basket_DAO bkDAO = null;

	@Before
	public void loading() {
		bkDAO = Basket_DAO.getinstance();
	}
	@Test
	public void addtocart() {
		Basket_DTO bkdto=new Basket_DTO();
		bkdto.setNo(0);
		bkdto.setUserid("홍식");
		bkdto.setCnt(2);
	}
	@Test
	public void mylist() {
		ArrayList<Basket_DTO> blist=bkDAO.myList("sam");
		Basket_DTO bkdto=new Basket_DTO();
		for(int i=0;i<blist.size();i++) {
			bkdto=blist.get(i);
			System.out.println(i+" 번 물건입니다.");
			System.out.println("물건번호: "+bkdto.getNo());
			System.out.println("수      량: "+bkdto.getCnt());
		}
			
	}
	
}
