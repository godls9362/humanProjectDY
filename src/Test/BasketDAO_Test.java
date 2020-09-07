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
		Basket_DTO bkdto = new Basket_DTO();
		bkdto.setNo(2);
		bkdto.setUserid("b");
		bkdto.setCnt(2);
		bkDAO.addToCart(bkdto);
		if (bkdto != null) {
			System.out.println("구매목록이 저장되었습니다.");
		}
	}

	@Test
	public void mylist() {
		ArrayList<Basket_DTO> blist=new ArrayList<>();
		blist = bkDAO.myList("a");
		for (int i = 0; i < blist.size(); i++) {
			Basket_DTO bkdto=new Basket_DTO();
			bkdto=blist.get(i);
				System.out.println("고객님의 구매목록입니다.");
				System.out.println("물건번호: " + bkdto.getNo());
				System.out.println("수      량: " + bkdto.getCnt());
			}
		}

	

}
