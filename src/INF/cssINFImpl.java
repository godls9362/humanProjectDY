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

	@Override // �޼��� ������
	public void buy() {
		listAll();
		int selNo = -1;
		String userid = null;
		int Cnt = -1;
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

	@Override
	public void listAll() {
		ArrayList<LocalFood_DTO> lfDAO=lfdao.selectAll();
		System.out.println("�ȳ��ϼ��� ����!");
		System.out.println("�츮 ��깰�� ��!LOCAL FOOD ��ǰ��Ϻ����Դϴ�--------------");
		for (int i = 0; i < lfDAO.size(); i++) {
			LocalFood_DTO temp = new LocalFood_DTO();
			temp = lfDAO.get(i);
			System.out.println((i+1) + " �� �����Դϴ�.");
			System.out.println("���ǹ�ȣ: " + temp.getNo());
			System.out.println("�����̸�: " + temp.getName());
			System.out.println("��ǰ����: " + temp.getPrice());
			System.out.println("���: " + temp.getMadeBy());
			System.out.println("��������: " + temp.getStock());
			System.out.println("-----------------------------------------------");

		}
	}

	@Override
	public void myList() {
		System.out.println("�ȳ��ϼ��� ����!");
		System.out.println("�츮 ��깰�� ��!LOCAL FOOD�Դϴ�------------");
		System.out.println("id�� �Է����ּ���.");
		String userid = in.nextLine();
		System.out.println(userid + "���� ���Ÿ���Դϴ�.");
		System.out.println("--------------------------");
		ArrayList<Basket_DTO> blist = new ArrayList<>();
		blist=bkdao.myList(userid);
		for (int i = 0; i < blist.size(); i++) {
			Basket_DTO bkDTO = new Basket_DTO();
			bkDTO = blist.get(i);
			System.out.println((i+1) + " �� �����Դϴ�.");
			System.out.println("���ǹ�ȣ: " + bkDTO.getNo());
			System.out.println("��      ��: " + bkDTO.getCnt());
			System.out.println("-----------------------------");
		}
	}

	@Override
	public void pay() {
		System.out.println("--------------------");
		System.out.println("�����Ͻ� �ݾ��Դϴ�.");
		ArrayList<LocalFood_DTO> list=new ArrayList<>();
		list=lfdao.cal();
		for(int i=0;i<list.size();i++) {
			System.out.println(list.get(i).getPrice()+"�Դϴ�.");
		}
	}
	
	@Override
	public void moneyById() {
		ArrayList<LocalFood_DTO> list=new ArrayList<>();
		System.out.println("id �� �Է��ϼ���.");
		list=lfdao.yourPay(in.nextLine());
		for(int i=0;i<list.size();i++) {
			System.out.println(list.get(i).getPrice()+"�Դϴ�");
		}
	}
}
