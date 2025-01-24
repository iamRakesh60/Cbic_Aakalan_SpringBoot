package com.Cbic_Aakalan_Project.dao.result;


import com.Cbic_Aakalan_Project.dao.pool.JDBCConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetExecutionSQL {


	public static ResultSet getResult(String query){
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			con= JDBCConnection.getTNConnection();
			ps=con.prepareStatement(query);
			rs=ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;					
	}

}
