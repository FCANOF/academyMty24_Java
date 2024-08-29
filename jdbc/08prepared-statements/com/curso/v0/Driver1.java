package com.curso.v0;

import java.sql.*;

public class Driver1 {

	public static void main(String[] args) throws SQLException {

		
		try (Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "jdbcstudent" , "jdbcstudent");
			 PreparedStatement myStmt = createPreparedStatement(myConn,80000,"Legal");
			 ResultSet myRs = myStmt.executeQuery();
			 PreparedStatement myStmt2 = createPreparedStatement(myConn,25000,"HR");
			 ResultSet myRs2 = myStmt2.executeQuery();
			) {
					
			System.out.println("***salary > 80000,  department = Legal");
			
			display(myRs);

			System.out.println("***salary > 25000,  department = HR");

			display(myRs2);

		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		
	}
	
	static private PreparedStatement createPreparedStatement(Connection con, double salary, String departamento ) throws SQLException {
	    String sql = "select * from employees where salary > ? and department= ?";
	    PreparedStatement myStmt = con.prepareStatement(sql);
	    // 3. Set the parameters
	 	myStmt.setDouble(1, salary);
	 	myStmt.setString(2, departamento);
	    return myStmt;
	}

	private static void display(ResultSet myRs) throws SQLException {
		while (myRs.next()) {
			String lastName = myRs.getString("last_name");
			String firstName = myRs.getString("first_name");
			double salary = myRs.getDouble("salary");
			String department = myRs.getString("department");
			
			System.out.printf("%s, %s, %.2f, %s\n", lastName, firstName, salary, department);
		}
	}
}
