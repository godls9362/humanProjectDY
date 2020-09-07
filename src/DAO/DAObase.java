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
	protected Connection conn = null; // oracle 접속하기 위한 연결 컨넥션
	//다른 메소드에서 상속받아 사용할수 있게 'protected'를 걸어주었다.
	protected ResultSet rs = null; // 쿼리문의 결과를 저장하는 변수
	protected PreparedStatement ppst=null;
	protected Statement st;
	
	public Connection connect() {
		try {
			Class.forName(driver);
		} catch (Exception e) {
			System.out.println("드라이버 로딩 실패");
		} 
		try {
			conn = DriverManager.getConnection(url, id, pwd);
			return conn;
		} catch (Exception e) {
			System.out.println("연결 실패 ");
		}
		return null;
	}
	
	public void disconnect () {
		try {
			if(conn!=null) conn.close();
			if(ppst!=null) ppst.close();
			if(st!=null)st.close();
		} catch (Exception e) {
			System.out.println("닫기를 실패했습니다.");
		}
	}
	
	
}
