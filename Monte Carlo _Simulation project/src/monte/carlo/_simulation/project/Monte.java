/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monte.carlo._simulation.project;

/**
 *
 * @author Shrouk
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import static java.lang.Math.random;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Monte {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
         // create JFrame and JTable
        JFrame frame = new JFrame();
        JTable table = new JTable(); 
        JTable table2 = new JTable(); 
        

        // create a table model and set a Column Identifiers to this model 
        Object[] columns = {"Demand","Frequency","Probability","cumulative probability","RandomNo. Range"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        
        // set the model to the table
        table.setModel(model);
        Random rand = new Random();
        int rand_int1 = rand.nextInt(100); 
       
        
        // Change A JTable Background Color, Font Size, Font Color, Row Height
        Font font = new Font("",1,16);
        table.setFont(font);
        table.setRowHeight(30);
      
        
        // create a table model and set a Column Identifiers to this model 
        Object[] column = {"day","RandomNo.","Simulated daily demand"};
        DefaultTableModel models = new DefaultTableModel();
        models.setColumnIdentifiers(column);
        // set the model to the table
        table2.setModel(models);
        // Change A JTable Background Color, Font Size, Font Color, Row Height
        Font f = new Font("",1,16);
        table2.setFont(f);
        table2.setRowHeight(30);
      
        
        // create JTextFields
        JTextField Demand = new JTextField();
        JTextField Frequency = new JTextField();
        JTextField sum1 = new JTextField(); 
        JTextField day = new JTextField();
        JTextField getendvalue = new JTextField();
       
        
        // create JButtons
        JButton btnAdd = new JButton("Add");
        JButton btnDelete = new JButton("Delete");
        JButton btnCalculate = new JButton("Calculate");
        JButton btnUpdate = new JButton("Add Random interval");
        JButton CalculateRand = new JButton("CalculateRand");
        JButton addDay = new JButton("Add Day");
        JButton AVRDailydemand = new JButton("Expected Daily");
      
        
        // create JLabel
        JLabel label1 =new JLabel("Enter Demand") ;
        JLabel label2 =new JLabel("Enter Frequency") ;
        JLabel label3 =new JLabel("Sum") ;
        JLabel label4 =new JLabel("Enter Day") ;
        
        
        Demand.setBounds(100, 370, 100, 25);
        Frequency.setBounds(250, 370, 100, 25);
        sum1.setBounds(100, 470, 100, 25);
        day.setBounds(100, 530, 100, 25);
        getendvalue.setBounds(120,630, 200, 25);
      
        
        btnAdd.setBounds(100, 400, 100, 25);
        btnDelete.setBounds(250, 400, 100, 25); 
        btnCalculate.setBounds(160, 430,100, 25);
        AVRDailydemand.setBounds(120,590,200,25);
        btnUpdate.setBounds(120,500,200, 25);
        addDay.setBounds(230,530,100,25);

        
        label1.setBounds(100, 340, 100,25);
        label2.setBounds(250, 340, 100,25);
        label3.setBounds(20, 470, 100,25);
        label4.setBounds(20, 530, 100,25);


        // create JScrollPane table 1
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(20, 20, 1140, 300);
        // create JScrollPane table 2
        JScrollPane pane2 = new JScrollPane(table2);
        pane2.setBounds(500,340,660,335);
        
        
        frame.setLayout(null);
        
        frame.add(pane);
        frame.add(pane2);
        // add JTextFields to the jframe
        frame.add(Demand);
        frame.add(Frequency);
        frame.add(sum1);
        frame.add(day);
        frame.add(getendvalue);
        frame.add(addDay);
        // add JButtons to the jframe
        frame.add(btnAdd);
        frame.add(btnDelete);
        frame.add(btnCalculate);
        frame.add(btnUpdate);
      //  frame.add(CalculateRand);
        frame.add(AVRDailydemand);
        
        
        frame.add(label1);
        frame.add(label2);
        frame.add(label3);
        frame.add(label4);
        






// create an array of objects to set the row data
        Object[] row = new Object[15];
        
        // button add row
        btnAdd.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
             
                row[0] = Demand.getText();
                row[1] = Frequency.getText();
                       
                // add row to the model
                model.addRow(row);
            }});
        
        // button remove row
        btnDelete.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
            
                // i = the index of the selected row
                int i = table.getSelectedRow();
                if(i >= 0){
                    // remove a row from jtable
                    model.removeRow(i);
                }
                else{
                    System.out.println("Delete Error");
                }
            }
        });
        
        // get selected row data From table to textfields 
        table.addMouseListener(new MouseAdapter(){
        
        @Override
        public void mouseClicked(MouseEvent e){
            // i = the index of the selected row
            int row=table.getSelectedRow();
            int column=table.getSelectedRow();
            Demand.setText(model.getValueAt(row, 0).toString());
            Frequency.setText(model.getValueAt(row, 1).toString());
}
        });
          // button update row
        btnCalculate.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
             
                // i = the index of the selected row
                int i = table.getSelectedRow();
                if(i >= 0) 
                {
                   model.setValueAt(Demand.getText(), i, 0);
                   model.setValueAt(Frequency.getText(), i, 1);
                }
                else{
                    System.out.println("Update Error");
                }
            }
        });
        String [][]array1=new String[200][10];
         // button calculate to calc th cumulative probability
        btnCalculate.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
             
                // i = the index of the selected row
            int i = table.getSelectedRow();
            int total = 0 ;        
            for (  i = 0 ; i < table.getRowCount() ; i++)
            {
                total = total + Integer.parseInt(table.getValueAt(i,1).toString());
            }
            sum1.setText(Integer.toString(total));

            float y=0;

            for (i=0;i< table.getRowCount(); i++)
            {
                float a =((Integer.parseInt(sum1.getText()) ) );
                float b = Integer.parseInt(table.getValueAt(i,1).toString());
                y = b/a;
                table.setValueAt(y, i , 2);
                array1 [i] [0]= Float.toString (y) ;               
    }
   
        array1[0][1]=array1[0][0];
       
    for ( i=0;i< table.getRowCount(); i++){
           float x = Float.parseFloat( array1[i][1])+ Float.parseFloat( array1[i+1][0]);
          
               array1[i+1][1]= Float.toString(x);
               table.setValueAt(array1[0][0],0,3);
               table.setValueAt(x , i+1 , 3);
           }
    }});             
 
  
    
       btnUpdate.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
   
    array1[0][2] = 1 + " to " +(Float.parseFloat(array1[0][0])*100) + "";

             for  ( int i=0;i<table.getRowCount(); i++)
        {
     
          array1[i+1][2] =(Float.parseFloat(array1[i][1])*100+1) +" to "+
                           (Float.parseFloat(array1[i+1][1])*100);
          
          table.setValueAt(array1[0][2],0,4);
          table.setValueAt(array1 [i+1][2] , i+1 , 4);
        }       
    }});
       
       
       Object[] row2 = new Object[15];
          addDay.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
             
                row2[0] = day.getText();
                // add row to the model
                models.addRow(row2);
            }
        });
        
       table2.addMouseListener(new MouseAdapter(){
        
        @Override
        public void mouseClicked(MouseEvent e){
            // row = the index of the selected row
            int row=table.getSelectedRow();
            int column=table.getSelectedRow();
            day.setText(models.getValueAt(row, 0).toString());
          }
        });   
       
       String[][] simulation=new String[200][];
   
         AVRDailydemand.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                
                int rand [ ]= new int[10]; 
                int i = table2.getSelectedRow();

                
                
                for(i=0; i<table2.getRowCount();i++){
                     
                     rand[i] = new Random().nextInt(100);
                      table2.setValueAt(i+1,i,0);
                      table2.setValueAt(rand[i],i,1);
                }
       
                
                 rand[i] = new Random().nextInt(100);
                 
                for(i=0; i<table2.getRowCount();i++){
                    
                     for(int j=0 ; j<table.getRowCount();j++){
                         
                          if(rand[i] >=(Float.parseFloat(array1[j][1])*100+1)  && 
                             rand[i] <=(Float.parseFloat(array1[j+1][1])*100+1) )
                          {
                               table2.setValueAt((table.getValueAt(i,0)),j,2);
                          }
                     }
                      
                       
                     
                } 
            float sum = 0 ;
                for  (i=0;i< table2.getRowCount(); i++)
                    {
                    float z = Integer.parseInt(table.getValueAt(i,0).toString());
                    float a = Float.parseFloat(array1[i][0]);
                    sum = sum + (a*z);

                    }
                getendvalue.setText(Float.toString(sum));
       
            }
        }
        );
    
            
     
        frame.setSize(1200,750);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
    }

    }
   

