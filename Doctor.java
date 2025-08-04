package com.learnJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class Doctor {
	private Connection connection;
	public Doctor(Connection connection)
	{
		this .connection=connection;
	}
	//view doctors
	public void viewDoctors()
	{
		String query="select * from doctors";
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			ResultSet resultset=preparedStatement.executeQuery();
			System.out.println("Doctors: ");
			System.out.println("+--------+--------------+------+-----+---------+----------------+");
			System.out.println("|Doctor ID | Name  | Specilization  |");
			System.out.println("+--------+--------------+------+-----+---------+----------------+");
			while(resultset.next())
			{
				int id=resultset.getInt("id");
				String name=resultset.getString("name");
				String Specilization=resultset.getString("Specilization");
				
				System.out.printf("|%-13s|%-21s|%-6s|\n",id,name,Specilization);
				System.out.println("+--------+--------------+------+-----+---------+");
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	//getDoctorbyid method
	public boolean getDoctorByID(int id)
	{
		String query="select * from doctors where ID=?";
		try {
			PreparedStatement preparedstatement=connection.prepareStatement(query);
			preparedstatement.setInt(1, id);
			ResultSet resultset=preparedstatement.executeQuery();
			if(resultset.next())
			{
				return true;	
			}else {
				return false;
			}
		}catch (SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}
	

}