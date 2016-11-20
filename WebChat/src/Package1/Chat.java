//C:\Users\HP\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\WebChat
// root directory

package Package1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.jdbc.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class Chat
 */
@WebServlet("/Chat")
public class Chat extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Connection con1;
    static java.sql.PreparedStatement stmt;
    int counter = 0;
    String query;
    String message1, disp;
   // private static String ServerId = "10.6.6.157:3306";
    
           
    /**
     * @throws SQLException 
     * @see HttpServlet#HttpServlet()
     */
        
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
       
         
    	counter = getCount();
    	    	
    	try{
        	message1=""; 
    		message1 = request.getParameter("message");
    		if(message1!=null&&message1!="null")
    		message1 = message1.trim();
        	 }
         catch(NullPointerException e){
        	 throw e;
    	 }
        
         
        try {
        	Class.forName("com.mysql.jdbc.Driver").newInstance();
			con1 = (Connection) DriverManager.getConnection("jdbc:mysql://10.6.14.45:3306/abc","root", "");
        	
        }catch (SQLException e) {
				
				e.printStackTrace();
			}		   	
		 catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		 
        if(message1!="null"&&message1!=null&&message1!=""&&message1!=" "&&message1!="   "&&message1!="    " ){

        	 String ipAddress = request.getHeader("X-Real-IP");
             if (ipAddress == null) {
                 ipAddress = request.getRemoteAddr();
             }
             InetAddress i = InetAddress.getByName(ipAddress);
             String ip =i.getHostName();
             
        	
        	
        	message1 = ip +" :"+ message1;
	        System.out.println(message1);
	        try {
				save(++counter);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
	        stmt = (PreparedStatement) con1.prepareStatement("INSERT INTO table3 VALUES (?,'"+counter+"');");
	        stmt.setString(1, message1);
	        
	        
	        try {        	 
	        	 
					 stmt.executeUpdate();
					} catch (SQLException e) {
					e.printStackTrace();
					}
		         counter++;
        }
        disp ="\n";
		String query1 = "SELECT * FROM table3";
		try
	    {
			
	        ResultSet rs = stmt.executeQuery(query1);
	        while(rs.next()){
	        disp+= rs.getString("message") + "\n";}
	          
	    }
	    catch(Exception e)
	    {
	    	System.out.println("SQL Exception4" + e);
	    }
		
		request.setAttribute("disp", disp);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/chat.jsp");
		rd.forward(request, response);  
		
    
    }
      /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
	}
	
	public void destroy(){
		try {
			if(stmt!=null&&con1!=null){
			stmt.close();
			con1.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
		
		
		public int getCount() {
		    int count = 0;
		    // Load the file with the counter
		    FileReader fileReader = null;
		    BufferedReader bufferedReader = null;
		    PrintWriter writer = null;
		    try {
		      File f = new File("FileCounter.initial");
		      if (!f.exists()) {
		        f.createNewFile();
		        writer = new PrintWriter(new FileWriter(f));
		        writer.println(0);
		      }
		      if (writer != null) {
		        writer.close();
		      }

		      fileReader = new FileReader(f);
		      bufferedReader = new BufferedReader(fileReader);
		      String initial = bufferedReader.readLine();
		      count = Integer.parseInt(initial);
		    } catch (Exception ex) {
		      if (writer != null) {
		        writer.close();
		      }
		    }
		    if (bufferedReader != null) {
		      try {
		        bufferedReader.close();
		      } catch (IOException e) {
		        e.printStackTrace();
		      }
		    }
		    return count;
		  }
		
		public void save(int count) throws Exception {
		    FileWriter fileWriter = null;
		    PrintWriter printWriter = null;
		    fileWriter = new FileWriter("FileCounter.initial");
		    printWriter = new PrintWriter(fileWriter);
		    printWriter.println(count);

		    // make sure to close the file
		    if (printWriter != null) {
		      printWriter.close();
		    }
		}
}
