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

	@Override	//메서드 재정의 
	public void buy() {
		listAll();
		int selNo = -1;
		String userid = null;
		int Cnt = -1;

		System.out.println("구매를 원하시면 Y를 아니면 N을 눌러주세요.");
		System.out.println("-----------------------------------");
		String answer = in.nextLine();
		if (answer.equals("Y")) {
			Basket_DTO bkDTO = new Basket_DTO();
			System.out.println("구매를 시작합니다.");
			System.out.println("--------------------");
			System.out.println("원하시는 제품의 번호를 입력하세요.");
			selNo = in.nextInt();
			in.nextLine();
			bkDTO.setNo(selNo);
			System.out.println("사용자님의 id를 입력해주세요.");
			userid = in.nextLine();
			bkDTO.setUserid(userid);
			System.out.println("구매하실 수량을 입력해주세요.");
			Cnt = in.nextInt();
			in.nextLine();
			bkDTO.setCnt(Cnt);

			bkdao.addToCart(bkDTO);
			lfdao.updateCnt(Cnt, selNo);

			System.out.println("구매해주셔서 감사합니다.");
			System.out.println("-----------------------");
		}
	}
	@Override
	public void listAll() {
		ArrayList<LocalFood_DTO> selectall=new ArrayList<>();
		System.out.println("안녕하세요 고객님!");
		System.out.println("우리 농산물의 힘!LOCAL FOOD 제품목록보기입니다--------------");
		for(int i=0;i<selectall.size();i++) {
			LocalFood_DTO temp =new LocalFood_DTO();
			temp=selectall.get(i);
			System.out.println(i+" 번 물건입니다.");
			System.out.println("물건번호: "+temp.getNo());
			System.out.println("물건이름: "+temp.getName());
			System.out.println("상품가격: "+temp.getPrice());
			System.out.println("농부: "+temp.getMadeBy());
			System.out.println("남은수량: "+temp.getStock());
			System.out.println("-----------------------------------------------");
			
		}
	}
	@Override
	public void myList() {
		System.out.println("안녕하세요 고객님!");
		System.out.println("우리 농산물의 힘!LOCAL FOOD입니다------------");
		System.out.println("id를 입력해주세요.");
		String userid=in.nextLine();
		System.out.println(userid+"님의 구매목록입니다.");
		System.out.println("--------------------------");
		ArrayList<Basket_DTO> blist=bkdao.myList(userid);
		for(int i=0;i<blist.size();i++) {
			Basket_DTO bkDTO =new Basket_DTO();
			bkDTO=blist.get(i);
			System.out.println(i+" 번 물건입니다.");
			System.out.println("물건번호: "+bkDTO.getNo());
			System.out.println("수      량: "+bkDTO.getCnt());
			System.out.println("-----------------------------");
		}
	}
}
