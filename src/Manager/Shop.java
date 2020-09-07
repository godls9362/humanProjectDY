package Manager;

import java.util.Scanner;

import INF.cssINFImpl;

public class Shop {
	private Scanner in = new Scanner(System.in);
	private cssINFImpl css = new cssINFImpl();

	public Shop() {
		int selNum = -1;
		while (true) {
			menu();
			System.out.println("선택 >>");
			selNum = in.nextInt();
			in.nextLine();

			switch (selNum) {
			case 1:
				showList();
				break;
			case 2:
				shopping();
				break;
			case 3:
				mycart();
				break;
			case 4:
				paynow();
				break;
			case 5:
				test();
				break;
			default:
			}
		}
	}

	private void test() {
		css.moneyById();

	}

	private void paynow() {
		css.pay();
	}

	private void mycart() {
		css.myList();
	}

	private void shopping() {
		css.buy();

	}

	private void showList() {
		css.listAll();

	}

	public void menu() {
		System.out.println("1. 물건보기");
		System.out.println("2. 쇼핑하기");
		System.out.println("3. 장바구니보기");
		System.out.println("4. 결제하기");

	}

}
