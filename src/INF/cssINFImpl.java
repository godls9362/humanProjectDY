package INF;

import java.util.ArrayList;
import java.util.Scanner;

import DAO.Basket_DAO;
import DAO.LocalFood_DAO;
import DTO.Basket_DTO;
import DTO.LocalFood_DTO;

public class cssINFImpl implements cssINF {
	private Basket_DAO bkdao = Basket_DAO.getinstance();
	private LocalFood_DAO lfdao = LocalFood_DAO.getinstance();
	private Scanner in = new Scanner(System.in);

	@Override	//�޼��� ������ 
	public void buy() {
		listAll();
		int selNo = -1;
		String userid = null;
		int Cnt = -1;

		System.out.println("���Ÿ� ���Ͻø� Y�� �ƴϸ� N�� �����ּ���.");
		System.out.println("-----------------------------------");
		String answer = in.nextLine();
		if (answer.equals("Y")) {
			Basket_DTO bkDTO = new Basket_DTO();
			System.out.println("���Ÿ� �����մϴ�.");
			System.out.println("--------------------");
			System.out.println("���Ͻô� ��ǰ�� ��ȣ�� �Է��ϼ���.");
			selNo = in.nextInt();
			in.nextLine();
			bkDTO.setNo(selNo);
			System.out.println("����ڴ��� id�� �Է����ּ���.");
			userid = in.nextLine();
			bkDTO.setUserid(userid);
			System.out.println("�����Ͻ� ������ �Է����ּ���.");
			Cnt = in.nextInt();
			in.nextLine();
			bkDTO.setCnt(Cnt);

			bkdao.addToCart(bkDTO);
			lfdao.updateCnt(Cnt, selNo);

			System.out.println("�������ּż� �����մϴ�.");
			System.out.println("-----------------------");
		}
	}
	@Override
	public void listAll() {
		ArrayList<LocalFood_DTO> selectall=new ArrayList<>();
		System.out.println("�ȳ��ϼ��� ����!");
		System.out.println("�츮 ��깰�� ��!LOCAL FOOD ��ǰ��Ϻ����Դϴ�--------------");
		for(int i=0;i<selectall.size();i++) {
			LocalFood_DTO temp =new LocalFood_DTO();
			temp=selectall.get(i);
			System.out.println(i+" �� �����Դϴ�.");
			System.out.println("���ǹ�ȣ: "+temp.getNo());
			System.out.println("�����̸�: "+temp.getName());
			System.out.println("��ǰ����: "+temp.getPrice());
			System.out.println("���: "+temp.getMadeBy());
			System.out.println("��������: "+temp.getStock());
			System.out.println("-----------------------------------------------");
			
		}
	}
	@Override
	public void myList() {
		System.out.println("�ȳ��ϼ��� ����!");
		System.out.println("�츮 ��깰�� ��!LOCAL FOOD�Դϴ�------------");
		System.out.println("id�� �Է����ּ���.");
		String userid=in.nextLine();
		System.out.println(userid+"���� ���Ÿ���Դϴ�.");
		System.out.println("--------------------------");
		ArrayList<Basket_DTO> blist=bkdao.myList(userid);
		for(int i=0;i<blist.size();i++) {
			Basket_DTO bkDTO =new Basket_DTO();
			bkDTO=blist.get(i);
			System.out.println(i+" �� �����Դϴ�.");
			System.out.println("���ǹ�ȣ: "+bkDTO.getNo());
			System.out.println("��      ��: "+bkDTO.getCnt());
			System.out.println("-----------------------------");
		}
	}
}
