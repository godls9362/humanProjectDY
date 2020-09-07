package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

class DAObase {
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:orcl1";
	private String id = "system";
	private String pwd = "1111";
	protected Connection conn = null; // oracle �����ϱ� ���� ���� ���ؼ�
	//�ٸ� �޼ҵ忡�� ��ӹ޾� ����Ҽ� �ְ� 'protected'�� �ɾ��־���.
	protected ResultSet rs = null; // �������� ����� �����ϴ� ����
	protected PreparedStatement ppst=null;
	protected Statement st;
	
	public Connection connect() {
		try {
			Class.forName(driver);
		} catch (Exception e) {
			System.out.println("����̹� �ε� ����");
		} 
		try {
			conn = DriverManager.getConnection(url, id, pwd);
			return conn;
		} catch (Exception e) {
			System.out.println("���� ���� ");
		}
		return null;
	}
	
	public void disconnect () {
		try {
			if(conn!=null) conn.close();
			if(ppst!=null) ppst.close();
			if(st!=null)st.close();
		} catch (Exception e) {
			System.out.println("�ݱ⸦ �����߽��ϴ�.");
		}
	}
	
	
}
