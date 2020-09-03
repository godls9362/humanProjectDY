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
		bkdto.setUserid("ȫ��");
		bkdto.setCnt(2);
	}
	@Test
	public void mylist() {
		ArrayList<Basket_DTO> blist=bkDAO.myList("sam");
		Basket_DTO bkdto=new Basket_DTO();
		for(int i=0;i<blist.size();i++) {
			bkdto=blist.get(i);
			System.out.println(i+" �� �����Դϴ�.");
			System.out.println("���ǹ�ȣ: "+bkdto.getNo());
			System.out.println("��      ��: "+bkdto.getCnt());
		}
			
	}
	
}
