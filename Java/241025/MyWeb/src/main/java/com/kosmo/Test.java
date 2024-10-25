package com.kosmo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("Driver Loading Success");
		String url ="jdbc:oracle:thin:@localhost:1521:xe";
		String user="c##scott", pwd="tiger";
		String sql="select * from memo order by no desc";
//		PreparedStatement ps=null;
		ResultSet rs=null; 
		try(Connection con=DriverManager.getConnection(url,user,pwd);PreparedStatement ps=con.prepareStatement(sql);){
			System.out.println("DB Connected...");
			rs=ps.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getString("msg"));
			}
		}

	}

}
