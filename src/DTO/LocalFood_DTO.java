package DTO;

public class LocalFood_DTO {   //로컬푸드 물건관리
	private int no;
	private String name;
	private	int price;
	private String madeBy;
	private int stock;
	public int getStock() {
		return stock;
	}
	public void setStock(int cnt) {
		this.stock = cnt;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getMadeBy() {
		return madeBy;
	}
	public void setMadeBy(String madeBy) {
		this.madeBy = madeBy;
	}
	
	
	
}
