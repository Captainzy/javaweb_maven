package controllerTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * @ClassName: JdbcTest
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zouyang
 * @date 2016年7月18日 下午9:45:13
 * 
 */

public class JdbcTest {

	/**
	 * @Title: main
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @author zouyang
	 * @date 2016年7月18日 下午9:45:13
	 * @param args void  返回类型
	 * @throws
	 */

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement psta = null;
		Statement sta = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String userName = "root";
			String password = "123456";
			String url = "jdbc:mysql://127.0.0.1:3306/javaweb?user="+userName+"&password="+password+"&useUnicode=true&characterEncoding=UTF8";
			conn = DriverManager.getConnection(url);
			
			String sql = "select * from t_test";
			psta = conn.prepareStatement(sql);
			rs = psta.executeQuery();
			while(rs.next()){
				String name = rs.getString("name");
				String pass = rs.getString("password");
				System.out.println(name + "   " + pass);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
