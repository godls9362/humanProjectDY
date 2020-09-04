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
		lfDTO.setName("����");
		lfDTO.setPrice(10000);
		lfDTO.setMadeBy("��ȫ��");
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
			System.out.println((i+1) + " �� �����Դϴ�.");
			System.out.println("���ǹ�ȣ: " + temp.getNo());
			System.out.println("�����̸�: " + temp.getName());
			System.out.println("��ǰ����: " + temp.getPrice());
			System.out.println("���: " + temp.getMadeBy());
			System.out.println("��������: " + temp.getStock());
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
			System.out.println("ã���ô� ������ ��ȣ�� �߸��Ǿ����ϴ�.");
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
