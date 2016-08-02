package mm;
import java.sql.*;
import java.util.*;
class OracleCon{
	public static int getDataOracle(String query){
		int result = 0;
		String ql;
		try{

			Class.forName("oracle.jdbc.driver.OracleDriver");

			Connection con=DriverManager.getConnection(
			"jdbc:oracle:thin:@localhost:1521:xe","mnorhamizan","password");

			Statement stmt=con.createStatement();
			ql = "SELECT COUNT(*) FROM docs WHERE CONTAINS(text, '";
			ql += query;
			ql += "', 1) > 0";
			ResultSet rs=stmt.executeQuery(ql);
			System.out.println(ql);

			while(rs.next())
			{
				result = rs.getInt(1);
			}
			

			System.out.println("oracle hits : " + result);
			
		
			con.close();
			return result;
			}catch(Exception e){ System.out.println(e);System.out.println("ayy");}
		return result;

	}
}