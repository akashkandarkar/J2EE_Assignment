import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class Demo {

	public static int getResultSetRowCount(ResultSet resultSet) {
	    int size = 0;
	    try {
	        resultSet.last();
	        size = resultSet.getRow();
	        resultSet.first();
	        System.out.println("before first");
	    }
	    catch(Exception ex) {
	        return 0;
	    }
	    return size;
	}
	public static void main(String[] args) {

		//Load the Driver
		try {


			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver Loaded..!");


			//Get the connection
			Connection conn= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
			System.out.println("COnnection established");
			
			
			Scanner sc= new Scanner(System.in);
		/*	
			System.out.println("Enter Details");
		
			System.out.println("Enter ID:");
			int emp_id=sc.nextInt();
			System.out.println("Enter NAME:");
			String emp_name=sc.next();
			System.out.println("Enter DEPT:");
			String emp_dept=sc.next();
*/

		//	String update="INSERT INTO EMP VALUES("+emp_id+",'"+emp_name+"','"+emp_dept+"')";

			
			Statement st=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		

		//	st.executeUpdate(update);
			
			
			
			String query = "select * from emp";
			
			int count=0;
			ResultSet rs1= st.executeQuery(query);
			while(rs1.next()){
				count++;
			}
			System.out.println(count);
			
			
			
			ResultSet rs= st.executeQuery(query);
	
			String queryd = "select distinct dept from emp";
			int countd=0;
			ResultSet rsd1= st.executeQuery(queryd);
			while(rsd1.next()){
				countd++;
			}
			System.out.println(countd);
			
			
			
			ResultSet rsd= st.executeQuery(queryd);
	
			

		
			int f=0;
			while(f==0){
				System.out.println("Database Menu:");
				System.out.println("1 . Display All Employees");
				System.out.println("2 . Display First Employee");
				System.out.println("3 . Display Last Employee");
				System.out.println("4 . Display All Departments");  
				System.out.println("5 . Display First Department");
				System.out.println("6 . Display Last Department"); 
				System.out.println("7 . Exit");
				int key = sc.nextInt();
				switch (key) {
				case 1:
					System.out.println("ALL Data in EMP");
				
					while (rs.next()) 
					{ 
						int id = rs.getInt(1);
						String n = rs.getString(2);
						String d = rs.getString(3);

						System.out.println(id+"   "+ n+ "   " + d);
					}

					break;
				case 2:
					System.out.println("First EMP");
					
					rs.absolute(1);
					System.out.println(rs.getInt(1)+rs.getString(2)+rs.getString(3));

					break;

				case 3:
					

					break;
				case 4:

					
					
				case 5:

					

				case 6:

					

				case 7:
					f=1;
					System.out.println("Exited succesfully");
					break;

				default:
					System.out.println("Enter valid number");
					break;
				}


			}
			

		
		
			conn.close();
			rs.beforeFirst();
			st.close();



			



		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}




	}

}
