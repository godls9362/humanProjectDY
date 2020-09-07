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
		if(lfDAO!=null) {
			System.out.println("저장되었습니다.");
		}else {
			System.out.println("다시시도해주십시오.");
		}
	}

	@Test
	public void selectAll() {
		ArrayList<LocalFood_DTO> lflist = new ArrayList<>();
		lflist = lfDAO.selectAll();
		LocalFood_DTO temp = new LocalFood_DTO();
		for (int i = 0; i < lflist.size(); i++) {
			temp = lflist.get(i);
			if(temp!=null) {
				System.out.println(temp.getNo());
				System.out.println(temp.getName());
				System.out.println(temp.getMadeBy());
				System.out.println(temp.getPrice());
				System.out.println(temp.getStock());
				
				
			}else {
				System.out.println("출력에 실패하였습니다.");
				break;
			}
		}
	}

	@Test
	public void search() {
		LocalFood_DTO temp = new LocalFood_DTO();
		temp = lfDAO.findOne(1);
		if (temp != null) {
			System.out.println("물건을 찾았습니다.");
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
