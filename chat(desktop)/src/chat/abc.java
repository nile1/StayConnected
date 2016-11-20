package chat;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.*;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import com.mysql.jdbc.Statement;
import java.awt.SystemColor;



public class abc extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private static String ip;
    private static String ServerId = "10.6.14.45:3306";              //"10.6.6.157:3306";
    private int counter;
    private static int updated=0;
    private static int temp;
    private static long k1=0;
    private static long k=0;
    static JTextField textArea_1;
    static TextArea textArea;
    static JTextArea textArea_2;
    //private JScrollPane scrollBar;
    static Connection con1;
    static java.sql.Statement stmt;
   
      
    
    
    

    public static void main(String[] args) throws Exception {
        
    	EventQueue.invokeLater(new Runnable() {
            
    		public void run() {
                try {
                    abc frame = new abc();
                    frame.setVisible(true);
                    textArea_1.requestFocusInWindow();
                                        
              } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("SQL Exception1" + e);
                }
            }
        });
    
	    
    	
    	try {
    		Class.forName ("com.mysql.jdbc.Driver").newInstance();
			con1 = (Connection) DriverManager.getConnection ("jdbc:mysql://"+ ServerId +"/abc","root", "");
		} catch (SQLException e1) {
		
			System.out.println("SQL Exception2" + e1);
			JOptionPane.showMessageDialog (null, e1.getMessage());
            }
        
        	loop();
    		
    	}
    

    
    public abc() {
        setAlwaysOnTop(false);
        setTitle("CHAT");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(700, 100, 650, 550);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        textArea = new TextArea();
        textArea.setSelectionEnd(100);
        textArea.setEditable(false);
        //textArea.setLineWrap(true);
        //textArea.setWrapStyleWord(true);
        textArea.setBackground(new Color(255, 255, 204));
        textArea.setBounds(10, 11, 598, 311);
        final  JScrollPane scrollPane =  new JScrollPane();
        //scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        contentPane.add(scrollPane);
        contentPane.add(textArea);
        
        //textArea.scroll
        //Scrollbar.VERTICAL();
        //contentPane.add(textArea);
        textArea_1 = new JTextField();
        textArea_1.setFont(new Font("Andalus", Font.PLAIN, 14));
        textArea_1.setBackground(new Color(102, 255, 255));
        textArea_1.setBounds(10, 374, 598, 69);
        textArea_1.requestFocus();
        contentPane.add(textArea_1);
        textArea_2 = new JTextArea();
        textArea_2.setBackground(SystemColor.inactiveCaption);
        textArea_2.setEditable(false);
        textArea_2.setBounds(10, 333, 598, 30);
        contentPane.add(textArea_2);
        
        
        try {
            InetAddress ipAddr = InetAddress.getLocalHost();
          ip =  ipAddr.getHostName().toString();
        	} catch (UnknownHostException ex) {
            ex.printStackTrace();
        	}
        
        
        final JButton button = new JButton("Send");
        button.setBounds(10, 461, 70, 22);
        contentPane.getRootPane().setDefaultButton(button);
        button.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent arg0) {
            
            /*code after clicking send button*/
            final String Message= ip+"   :     "+textArea_1.getText();
            if(!(textArea_1.getText().equals(""))){
                String query1 = "SELECT COUNT(*) FROM table3";
                try
            {
                	stmt = (Statement) con1.createStatement();
                ResultSet rs = stmt.executeQuery(query1);
                rs.next();
                counter = rs.getInt(1)+1;
                
                if(counter!=1){
                String c = Integer.toString(counter-2);    
                String query2 = "SELECT count FROM table3 LIMIT "+c+" ,1;";
                rs = stmt.executeQuery(query2);
                rs.next();
                counter = rs.getInt(1)+1;
                }
                
                if(counter >5000){
                    
                    String query3 ="delete from table3  order by count limit 1;"; 
                    stmt.executeUpdate(query3);
                    
                }
                
                String query="INSERT INTO table3 VALUES ('"+Message+"','"+counter+"');";
                stmt.executeUpdate(query);
                
                String query5 = "truncate table4;";
                stmt.executeUpdate(query5);
                
                textArea_2.setText("");
                textArea_1.setText("");
                
            }
            catch(Exception e)
            {
            	System.out.println("SQL Exception4" + e);
            	JOptionPane.showMessageDialog (null, e.getMessage());
            }
            
            textArea_2.setText("");
            
            }
            else    
            {
            	String query5 = "truncate table4;";
            	try {
					stmt = (Statement) con1.createStatement();
					stmt.executeUpdate(query5);
	                
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
            	
            	
            }
        
        
        }});
        
        contentPane.add(button);
        
        Button button_1 = new Button("Exit");
        button_1.setBounds(532, 461, 70, 22);
        button_1.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent arg0) {
                //System.exit(0);
                JOptionPane.showMessageDialog(null, "Are you sure to exit");
                
                String query1 = "truncate table4;";
                try
            {
                	stmt = (Statement) con1.createStatement();
                	stmt.executeUpdate(query1);
               }                    
            catch(Exception e)
            {
            	System.out.println("SQL Exception5" + e);
            }
                
                try {
					con1.close();
				} catch (SQLException e) {
					System.out.println("SQL Exception6" + e);
					e.printStackTrace();
				}
                System.exit(0);
                //JOptionPane.showMessageDialog(null, "Are you sure to exit");
            }
        });
        contentPane.add(button_1);
        
       
    }
    
    static void loop() throws Exception
    {

        /*  Timer timer = new Timer(1000, new ActionListener() {
        	  @Override
        	  public void actionPerformed(ActionEvent arg0) {
        	    // Code to be executed
        		  try {
					update();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                  update2();
                  
                  
                  String query1 = "SELECT * FROM table4;";
                 
                  try
                  {
                  	stmt = (Statement) con1.createStatement();
                  	ResultSet rs = stmt.executeQuery(query1);
                      if(rs.isBeforeFirst()){
              			rs.next();
              			String b = rs.getString("message");
              			textArea_2.setText(b + " is typing \n");
              		}
              		else
              		{
              			textArea_2.setText("");
                      
                  }} catch(Exception e)
                  {
                  	System.out.println("SQL Exception3" + e);
                  	
                  }  	  
        	  }
        	});
        	timer.setRepeats(false); // Only execute once
        	timer.start(); // Go go go!*/
        
        
        
      k1=System.currentTimeMillis();
      k=k1;
      while(true){
            
            k1=System.currentTimeMillis();
            if((Math.abs(k1-k))>1000)
            {
                update();
                update2();
                
                
                String query1 = "SELECT * FROM table4;";
               
                try
                {
                	stmt = (Statement) con1.createStatement();
                	ResultSet rs = stmt.executeQuery(query1);
                    if(rs.isBeforeFirst()){
            			rs.next();
            			String b = rs.getString("message");
            			textArea_2.setText(b + " is typing \n");
            		}
            		else
            		{
            			textArea_2.setText("");
                    
                }} catch(Exception e)
                {
                	System.out.println("SQL Exception3" + e);
                	
                }
                
                
                       		
           }}
    	
    }
    
    static void update() throws Exception{
        
        String query3 = "SELECT COUNT(*) FROM table3";
         try{
        	 stmt = (Statement) con1.createStatement();      
        	 ResultSet rs  = stmt.executeQuery(query3);
        	 if(rs.isBeforeFirst()){
        	 rs.next();
                   temp =rs.getInt(1);
                   if(temp>updated){
                                
                	   String query4 = "SELECT * FROM table3 LIMIT "+(updated)+", "+(temp-updated)+";";
                      stmt = (Statement) con1.createStatement(); 
                      
                      rs = stmt.executeQuery(query4);
                      
                       while(rs.next()){
                    	      
                    	   String b = rs.getString("message");
                     
                              textArea.append(b + "\n");
                              textArea_2.setText("");
                     
                          }
                          
                       
                      updated = temp;
                   }     }
                     
         
         }
         

                     
             catch(Exception e){
                 System.out.println("SQL Exception7" + e);
                 loop();
                 //throw e;
                 
                
                
                 
             }
         
    
    
    }
    


static void update2(){
    
    
    if(!(textArea_1.getText().equals(""))){
        
        String query5 = "truncate table4;";
        String query6 = "INSERT INTO table4 VALUES ('"+ip+"');";
        try{
        	stmt = (Statement) con1.createStatement();      
        	stmt.executeUpdate(query5);
                  stmt.executeUpdate(query6);
                      
        } catch(SQLException e){
                System.out.println("SQL Exception8" + e);
            }}}	
}
