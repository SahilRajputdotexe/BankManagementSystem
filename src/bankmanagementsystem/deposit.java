package bankmanagementsystem;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class deposit extends JFrame implements ActionListener {


    JButton deposit ,back;
    JTextField amount;
    String pinnumber;
    deposit(String pinnumber){
        this.pinnumber = pinnumber;


        ImageIcon i1= new ImageIcon(getClass().getResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900,900 ,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);

        JLabel image = new JLabel(i3);
        image.setBounds(0,0,900,900);
        add(image);

        JLabel text = new JLabel("Enter the amount you want to deposit :");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("Raleway",Font.BOLD,16));
        text.setBounds(170,280,400,20);
        image.add(text);

        amount =new JTextField();
        amount.setFont(new Font("Raleway",Font.BOLD,16));
        amount.setBounds(170,350,300,25);
        image.add(amount);

        deposit = new JButton("Deposit");
        deposit.setBounds(355,460,150,25);
        deposit.addActionListener(this);
        image.add(deposit);

        back = new JButton("Back");
        back.setBounds(355,495,150,25);
        back.addActionListener(this);
        image.add(back);





        setSize(900,900);
        setLocation(300,0);
        setVisible(true);





    }

    public void actionPerformed(ActionEvent ae){
        if (ae.getSource()== deposit){
            String number = amount.getText();
            Date date = new Date();
            if (number.equals("")){
                JOptionPane.showMessageDialog(null,"Please enter the amount");
            }else {
                try{
                connection c = new connection();
                String query = "insert into bank values('"+pinnumber+"','"+date+"','Deposit','"+number+"')";
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null,"Rs"+ number +"Deposited Successfully");
                setVisible(false);
                new transactions(pinnumber).setVisible(true);
                }catch (Exception e){
                    System.out.println(e);
                }

            }



        }else if ((ae.getSource()== back)){
            setVisible(false);
            new transactions(pinnumber).setVisible(true);

        }




    }

    










    public static void main(String args[]){
        new deposit("");

    }



}
