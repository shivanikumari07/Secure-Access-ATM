package bank.management.system;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
public class MiniStatement extends JFrame{

   MiniStatement(String pinnumber){
       setTitle("Mini Statement");
       
       setLayout(null);
       
       JLabel text=new JLabel();
       add(text);
       
       JLabel bank= new JLabel("State Bank Of India");
       bank.setBounds(150,20,300,20);
       add(bank);
       
       JLabel card= new JLabel("State Bank Of India");
       card.setBounds(20,80,300,20);
       add(card);
       
       JLabel mini= new JLabel("State Bank Of India");
       mini.setBounds(20,140,400,200);
       add(mini);
       
       JLabel balance= new JLabel();
       balance.setBounds(20,400,300,20);
       add(balance);
       
       try{
           Conn conn=new Conn();
           ResultSet rs= conn.s.executeQuery("Select * from login where pinNumber = '"+pinnumber+"'");
           while(rs.next()){
           card.setText("Card Number: "+rs.getString("cardNumber").substring(0,4)+"XXXXXXXX"+rs.getString("cardNumber").substring(12));
           
       }
       }catch(Exception e){
           System.out.println(e);
       }
       
       try{
           Conn conn=new Conn();
           int bal =0;
           ResultSet rs= conn.s.executeQuery("select * from bank where pinNumber='"+pinnumber+"'");
           while(rs.next()){
            mini.setText(mini.getText() + " "+rs.getString("date")+ " " + rs.getString("type") + " " + rs.getString("amount") + " ");
            if(rs.getString("type").equals("Deposit")){
                            bal += Integer.parseInt(rs.getString("amount"));
                        }else {
                            bal -= Integer.parseInt(rs.getString("amount"));
                        }
           }balance.setText("Your current account balance is Rs: "+bal);
       }catch(Exception e){
           System.out.println(e);
       }
       
       setSize(400,600);
       setLocation(20,20);
       getContentPane().setBackground(Color.WHITE);
       setVisible(true);
   }
    public static void main(String args[]) {
     new MiniStatement("");
    }
}
