package com.restapi;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.nimbus.State;

public class CarRespositoryDB {
	
	 Connection con = null;
	 
	public  CarRespositoryDB() {
		
		System.out.println("Database");
		String url = "jdbc:sqlserver://localhost:1433;database=mydb;encrypt=true;TrustServerCertificate=true";
		   String user="sa";
		   String password ="reallyStrongPwd123";
		   
		   try {
			   System.out.println("Inside database");
			   Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			   con = DriverManager.getConnection(url, user, password);
			   if (con != null) {
	                DatabaseMetaData dm = (DatabaseMetaData) con.getMetaData();
	                System.out.println("Driver name: " + dm.getDriverName());
	                System.out.println("Driver version: " + dm.getDriverVersion());
	                System.out.println("Product name: " + dm.getDatabaseProductName());
	                System.out.println("Product version: " + dm.getDatabaseProductVersion());
	            }
		   	} 
		   catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		   } 
		   catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			System.out.println(e1);
		   }

	}
	
	public List<Car> getCars(){
		List<Car> car = new ArrayList<Car>();
		 String query = "select * from cars";
		 Statement st = null;
		 try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next()) {
				Car c = new Car();
				c.setModel(rs.getInt(1));
				c.setName(rs.getString(2));
				c.setColor(rs.getString(3));
				
				car.add(c);
			}
			
		} catch (SQLException e) {
			System.out.println(e);
		}finally {
         // Closing resources
         try {
             if (st != null) {
                 st.close();
             }
             if (con != null) {
                 con.close();
             }
         } catch (final SQLException e) {
             e.printStackTrace();
         }
         
		}

		
		return car;
	}
	
	public Car getCar(int id){
		Car car = new Car();
		String query = "Select * from cars where model=" +id;
		Statement st = null;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			if(rs.next()) {
				car.setModel(rs.getInt(1));
				car.setName(rs.getString(2));
				car.setColor(rs.getString(3));
			}
			
		} catch (SQLException e) {
			System.out.println(e);
		}finally {
            // Closing resources
            try {
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (final SQLException e) {
                e.printStackTrace();
            }
            
		}
		
		return car;
	}
	
	public void setCar(Car c){
		PreparedStatement st = null;
		String query = "insert into cars(model,name,color) values(?,?,?)";
		try {
	        st =  con.prepareStatement(query);
			st.setInt(1, c.getModel());
			st.setString(2, c.getName());
			st.setString(3, c.getColor());
			st.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}finally {
            // Closing resources
            try {
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (final SQLException e) {
                e.printStackTrace();
            }
            
		}	
	}
	
	public String deleteCar(int id ) {
		
		String query = "delete from cars where model=" +id;
		String msg = null;
		Statement st = null;
		try {
			st = con.createStatement();
			int rs = st.executeUpdate(query);
			System.out.println(rs);
			if(rs>0) {
				 msg = "Data deleted";
			}
			else {
				msg ="data not present";
			}
		} catch (SQLException e) {
			System.out.println(e);
		}finally {
            // Closing resources
            try {
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (final SQLException e) {
                e.printStackTrace();
            }
            
		}

		System.out.println(msg);
		return msg;
	}
	
	public String updateCar(Car c) {
		
		String msg = null;
		String query  = "update cars set name=?, color=? where model =?";
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, c.getName());
			ps.setString(2, c.getColor());
			ps.setInt(3, c.getModel());
			int rs = ps.executeUpdate();
			System.out.println(rs);

			if(rs>0) {
				msg = "Data updated";
			}
			else {
                setCar(c);
                msg ="Created Data";
			}
		} catch (SQLException e) {
			System.out.println(e);
		}finally {
            // Closing resources
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (final SQLException e) {
                e.printStackTrace();
            }
            
		}
		System.out.println(msg);
		return msg;
	}
	
	
}
