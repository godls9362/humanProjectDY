package Test;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import DAO.LocalFood_DAO;
import DTO.LocalFood_DTO;

public class LocalFoodDAO_Test {
	LocalFood_DAO lfDAO = null;

	@Before
	public void loading() {
		lfDAO = LocalFood_DAO.getinstance();
	}

	@Test
	public void insertOne() {
		LocalFood_DTO lfDTO = new LocalFood_DTO();
		lfDTO.setName("포도");
		lfDTO.setPrice(10000);
		lfDTO.setMadeBy("조홍식");
		lfDTO.setStock(3);
		lfDAO.insertGoods(lfDTO);
	}

	@Test
	public void selectAll() {
		ArrayList<LocalFood_DTO> lflist = new ArrayList<>();
		lflist = lfDAO.selectAll();
		for (int i = 0; i < lflist.size(); i++) {
			LocalFood_DTO temp = new LocalFood_DTO();
			temp = lflist.get(i);
			System.out.println((i+1) + " 번 물건입니다.");
			System.out.println("물건번호: " + temp.getNo());
			System.out.println("물건이름: " + temp.getName());
			System.out.println("상품가격: " + temp.getPrice());
			System.out.println("농부: " + temp.getMadeBy());
			System.out.println("남은수량: " + temp.getStock());
		}
	}

	@Test
	public void search() {
		LocalFood_DTO temp = new LocalFood_DTO();
		temp = lfDAO.findOne(1);
		if (temp != null) {
			System.out.println(temp.getNo());
			System.out.println(temp.getName());
			System.out.println(temp.getPrice());
			System.out.println(temp.getMadeBy());
			System.out.println(temp.getStock());
		}else {
			System.out.println("찾으시는 물건의 번호가 잘못되었습니다.");
		}
	}
	@Test
	public void minusCnt() {
		lfDAO.updateCnt(2, 1);
	}
	@Test
	public void delete() {
		lfDAO.deleteOne(1);
	}
}
