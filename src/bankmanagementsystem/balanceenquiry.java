package bankmanagementsystem;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;


public class balanceenquiry extends JFrame implements ActionListener {
    JButton back;
    String pinnumber ;
    balanceenquiry(String pinnumber ){
        this.pinnumber= pinnumber;

        setLayout(null);

        ImageIcon i1= new ImageIcon(getClass().getResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,900,900);
        add(image);

        back = new JButton("BACK");
        back.setBounds(355,520,150,30);
        back.addActionListener(this);
        image.add(back);

         connection conn = new connection();
         int bal= 0;
            try{
                ResultSet rs= conn.s.executeQuery("select * from bank where pin = '"+pinnumber+"' ");
                
                while (rs.next()){
                    if (rs.getString("type").equals("Deposit")){
                        bal += Integer.parseInt(rs.getString("amount"));
                    } else  {
                        bal-= Integer.parseInt(rs.getString("amount"));
                    }
                }
            }catch(Exception e){
                System.out.println(e);
            }

            JLabel text = new JLabel("Your current account balance is :Rs "+ bal );
            text.setForeground(Color.WHITE);
            text.setBounds(170,300,400,30);
            image.add(text);



        setSize(900,900);
        setLocation(300,0);
        setUndecorated(true);
        setVisible(true);


    }

    public void actionPerformed(ActionEvent ae ){
        setVisible(false);
        new transactions(pinnumber).setVisible(true);

    }


    public static void main(String args[]){

        new balanceenquiry("");
    }
    
}
