package nvidia.in;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class BillLogics 
{
	double gstTax;
	long accountNumber=UserDashBoard.accountNumber;
	double billAmount;
	String transactionID;
	String paymentType;
	double dueFine;
	String date;
	
	
	public void getDetails() throws SQLException
	{
		 ConnectionJDBC con = new ConnectionJDBC();
	       String query ="select * from bill where accountNumber='"+accountNumber+"'"+";";
	       System.out.println(query);
	       ResultSet rs=con.s.executeQuery(query);
	       if(rs.next())
	       {
	    	   gstTax=rs.getDouble("gstTax");
	    	  billAmount= rs.getDouble("billAmount");
	    	  transactionID= rs.getString("transactionID");
	    	   paymentType=rs.getString("paymentType");
	    	  dueFine= rs.getDouble("dueFine");
	    	   date=rs.getString("date");
	       }
	}
	
	public void generateTransactionID()
	{
		int min=1;
		long max=100000000l;
		
		Long billAmt=(long) (Math.random()*(max-min))+1;
		System.out.println(billAmt);
		this.transactionID=billAmt.toString();
	}
	
	
	
	public void pay() throws SQLException
	{
		generateTransactionID();
		 ConnectionJDBC con = new ConnectionJDBC();
	        String query=null;
	        billAmount=0.0;
	        dueFine=0.0;
	       
	        LocalDate currentDate = LocalDate.now();
	        // Convert the date to a String
	        String dateAsString = currentDate.toString();
	        this.date=dateAsString;
	       
	        query = "UPDATE bill SET " +
	                "billAmount = " + billAmount + ", " +
	                "transactionID = '" + transactionID + "', " +
	                "paymentType = '" + paymentType + "', " +
	                "dueFine = " + dueFine + ", " +
	                "date = '" + date + "' " +
	                "WHERE accountNumber = " + accountNumber + ";";
	        try {
				con.s.executeUpdate(query);
				
				System.out.println("Bill Payed");
				
			} catch (SQLException e) {
				e.printStackTrace();
				}
	}
	
	public void generateBill()
	{
		double gstTax=4.90;
		
	}

}
