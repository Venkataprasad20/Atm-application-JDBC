package atm;
import java.util.*;
import java.sql.*;
import java.sql.DriverManager;
public class atmCode {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String URL = System.getenv("DB_URL");
        String user = System.getenv("DB_USERNAME");
        String password = System.getenv("DB_PASSWORD");
        Connection con = null;
 
        try {
            // Step 1: Load the MySQL Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 2: Establish connection
            con = DriverManager.getConnection(URL, user, password);
            System.out.println("✅ Connected successfully to the database!");

            // Step 3: Show menu
            while (true) {
                System.out.println("\n=== ATM MENU ===");
                System.out.println("1. Create Account");
                System.out.println("2. Check Balance");
                System.out.println("3. Deposit");
                System.out.println("4. Withdraw");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        createAccount(con,sc);
                        break;
                    case 2:
                       checkBalance(con,sc);
                        break;
                    case 3:
                    	deposit(con,sc);
                        break;
                    case 4:
                    	withDrawl(con,sc);
                        break;
                    case 5:
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        con.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sc.close();
        }
    }
    public static void createAccount(Connection con,Scanner sc) throws SQLException{
    	System.out.print("Enter Account Number: ");
    	int acc=sc.nextInt();
    	sc.nextLine();
    	
    	PreparedStatement check=con.prepareStatement("SELECT * FROM atm WHERE acc_no=?");
		check.setInt(1,acc);
    	ResultSet rs=check.executeQuery(); 
    	if(rs.next()) {
    		System.out.println("User Already Exist");
    		return;
    	}
    	System.out.print("Enter your Name: ");
    	String name=sc.nextLine();
    	System.out.print("Enter pin: ");
    	int pin=sc.nextInt();
    	System.out.print("Enter balance: ");
    	double bal=sc.nextDouble();
    	
    	PreparedStatement ps=con.prepareStatement("INSERT INTO atm (acc_no,name,pin,balance) VALUES (?,?,?,?)");
    	ps.setInt(1,acc);
    	ps.setString(2,name);
    	ps.setInt(3, pin);
    	ps.setDouble(4, bal);
    	ps.executeUpdate();
    	
    	System.out.println("Account created Successfully ");
    }
    public static void checkBalance(Connection con,Scanner sc) throws SQLException{
    	System.out.println("Enter Account Number: ");
    	int acc=sc.nextInt();
    	System.out.println("Enter Pin :");
    	int pin=sc.nextInt();
    	
    	PreparedStatement ps=con.prepareStatement("SELECT balance FROM atm WHERE acc_no=? && pin=?");
    	ps.setInt(1, acc);
    	ps.setInt(2,pin);
    	ResultSet rs=ps.executeQuery();
    	 
    	if(rs.next()) {
    		System.out.println("Current Balance :"+rs.getDouble("balance")); 
    	}
    	else {
    		System.out.print("Invalid account number Or pin");
    	}
    }
    public static void deposit(Connection con,Scanner sc) throws SQLException {
    	System.out.print("Enter account Number :");
    	int acc=sc.nextInt();
    	System.out.print("Enter Amount");
    	double bal=sc.nextDouble();
    	
    	if(bal<=0) {
    		System.out.print("Invalid deposit Amount: ");
    	}
    	
    	PreparedStatement ps=con.prepareStatement("UPDATE atm SET balance=balance+? WHERE acc_no=?");
    	ps.setDouble(1,bal);
    	ps.setInt(2, acc);    	
    	
    	int rows=ps.executeUpdate();
    	if(rows>0) {
    		System.out.print("Amount Deposited Successfully");
    	}
    	else {
    		System.out.print("Account not Found");
    	}
    }
    public static void withDrawl(Connection con,Scanner sc) throws SQLException{
    	System.out.print("Enter the Account number :");
    	int acc=sc.nextInt();
    	System.out.print("Enter the Pin :");
    	int pin=sc.nextInt();
    	System.out.print("Enter the amount to Withdrawl: ");
    	double bal=sc.nextDouble();
    	
    	if(bal<=0) {
    		System.out.print("Invalid amount withdrawl: ");
    		return;
    	}
    	
    	PreparedStatement ps=con.prepareStatement("SELECT balance FROM atm WHERE acc_no=? AND pin=?");
    	ps.setInt(1,acc);
    	ps.setInt(2,pin);
    	ResultSet rs=ps.executeQuery();
    	
    	if(rs.next()) {
    		double amt=rs.getDouble("balance");
    		if(amt>=bal) {
    			PreparedStatement ps2=con.prepareStatement("UPDATE atm SET balance=balance-? WHERE acc_no=?");
    			ps2.setDouble(1, bal);
    			ps2.setInt(2,acc);
    			ps2.executeUpdate();
    			System.out.print("WithDrawl Successfull");
    		}
    		else {
    			System.out.print("Insufficient Balance !");
    		}
    	}
    	else {
    		System.out.print("Invalid account number Or pin");
    	}
    }
}
