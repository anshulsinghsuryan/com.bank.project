package com.bank.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bank.project.entity.BankEntity;

public class BankDao {

	public static void register(BankEntity bankEntity) {
		
		Connection con = ConnectionProvider.getConnection();
		String sql = "insert into bank(account,username,password,amount,dob) values(?,?,?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, bankEntity.getAccount());
			ps.setString(2, bankEntity.getUsername());
			ps.setString(3, bankEntity.getPassword());
			ps.setInt(4, bankEntity.getAmount());
			ps.setString(5, bankEntity.getDob());
			int result = ps.executeUpdate();
			
			if(result == 1) {
				System.out.println("Registration Succesfull");
			}else {
				System.out.println("Something went wrong");
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void diposit(String account, int amount) {
		Connection con = ConnectionProvider.getConnection();
		int prevAmount = getAmount(account);
		if(amount > 0) {
			String sql = "update bank set amount=? where account=?";
			try {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, prevAmount+amount);
				ps.setString(2, account);
				
				int result = ps.executeUpdate();
				if(result == 1) {
					System.out.println("Deposited Successfully");
				}
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			System.out.println("Balance Should Be Greater Than 0");
		}
	}
	
	public static void debit(String account, int amount) {
		Connection con = ConnectionProvider.getConnection();
		int prevAmount = getAmount(account);
		if(prevAmount >= amount) {
			String sql = "update bank set amount=? where account=?";
			try {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, prevAmount-amount);
				ps.setString(2, account);
				
				int result = ps.executeUpdate();
				if(result == 1) {
					System.out.println("Debited Successfully");
				}
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			System.out.println("Insufficient Balance");
		}
		
		
	}
	
	public static int getAmount(String account) {
		int amount = 0;
		Connection con = ConnectionProvider.getConnection();
		String sql = "select amount from bank where account=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, account);

			ResultSet result = ps.executeQuery();
			while(result.next()) {
				amount = result.getInt("amount");
			}
			con.close();
			return amount;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return amount;
	}
	
}
