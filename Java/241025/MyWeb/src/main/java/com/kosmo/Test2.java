package com.kosmo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Test2 {

	public static void main(String[] args) 
			throws Exception
			{
				//1. Driver Loading
				Class.forName("com.mysql.cj.jdbc.Driver");
				System.out.println("드라이버 로딩 성공!!");
				//2. DB 연결
				String url="jdbc:mysql://localhost:3306/mydb?useUnicode=true&serverTimezone=Asia/Seoul";
				String user="root", pwd="1234";
				Connection con=DriverManager.getConnection(url,user,pwd);
				System.out.println("MySQL DB 연결 성공!!");
			
				/*
				 * String sql="CREATE TABLE java_member("; sql+=" id varchar(20) primary key,";
				 * sql+=" pw varchar(10) not null,"; sql+=" name varchar(30) not null,";
				 * sql+=" email varchar(50),"; sql+=" indate date default (current_date) )";
				 * System.out.println(sql);
				 * 
				 * Statement stmt=con.createStatement(); boolean b=stmt.execute(sql);
				 * System.out.println("b: "+b);
				 */
//				stmt.close();
				con.close();
			}

}
