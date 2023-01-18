import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.awt.Color;
import javax.swing.table.*;
 

class HomePage extends JFrame{
	
 	JLabel id,fname,lname,age,jid,jfname,jlname,jage,pos;
	JTextField Tid,Tfname,Tlname,Tage;
	JButton add,update,delete;
	JComboBox ComboPos;
	HomePage(){
		setLayout(null);
		id = new JLabel("ID:");
		id.setFont(new Font("Time New Roman",Font.PLAIN,18));
		id.setForeground(Color.BLUE);
		id.setBounds(60,20,1000,30);
		add(id);
		
		fname= new JLabel("FirstName:");
		fname.setFont(new Font("Time New Roman",Font.PLAIN,18));
		fname.setForeground(Color.BLUE);
		fname.setBounds(60,60,1000,30);
		add(fname);
		
		lname = new JLabel("LastName:");
		lname.setFont(new Font("Time New Roman",Font.PLAIN,18));
		lname.setForeground(Color.BLUE);
		lname.setBounds(60,100,1000,30);
		add(lname);
		
		age= new JLabel("Age:");
		age.setFont(new Font("Time New Roman",Font.PLAIN,18));
		age.setForeground(Color.BLUE);
		age.setBounds(60,140,1000,30);
		add(age);
		
	
		
		Tid =  new JTextField(60);
		Tid.setBounds(150,20,100,30);
		add(Tid);
		
		Tfname=  new JTextField(60);
		Tfname.setBounds(150,60,100,30);
		add(Tfname);
		
		Tlname=  new JTextField(60);
		Tlname.setBounds(150,100,100,30);
		add(Tlname);
		
		Tage=  new JTextField(60);
		Tage.setBounds(150,140,50,30);
		add(Tage);
		
		pos = new JLabel("Position:");
		pos.setFont(new Font("Time New Roman",Font.PLAIN,18));
		pos.setForeground(Color.BLUE);
		pos.setBounds(295, 21, 165, 25);
		add(pos);
		
		
		String combopos[] = {"Teacher","Student","Registrar"};
        ComboPos= new JComboBox(combopos);
        ComboPos.setBounds(400, 21, 165, 25);
        add(ComboPos);
  
		JTable table = new JTable();
		table.setBounds(20,250,900,150);
		table.setVisible(true);
		add(table);
		
		Object[] columns = {"ID","POSITION","FirstName","LastName ","Age"};
		DefaultTableModel model= new DefaultTableModel();
		model.setColumnIdentifiers(columns);
		table.setModel(model);
		table.setBackground(Color.white);
		Font font = new Font("",1,12);
		table.setFont(font);
		table.setRowHeight(30);
		
		JScrollPane pane = new JScrollPane(table);
		pane.setBounds(20,250,900,150);
		add(pane);
			
			
			
			add =  new JButton("Add");
			add.setBounds(230,180,100,30);
			add.setForeground(Color.BLUE);
			add.setFont(new Font("Time New Roman",Font.BOLD,14));
			add(add);
			
			Object[] row = new Object[6];
			add.addActionListener(new ActionListener(){ 
				public void actionPerformed(ActionEvent ae){
					String ID = Tid.getText().toString();
					String FN = Tfname.getText().toString();
					String LN = Tlname.getText().toString();
					String AGE = Tage.getText().toString();
					String POS = ComboPos.getSelectedItem().toString();
					
				if(ID.equals("")||POS.equals("")||FN.equals("")||LN.equals("")||AGE.equals("")){
					JFrame f = new JFrame();
					JOptionPane.showMessageDialog(f,"Please Enter All Data");
				}
				else{
					
					try{
							FileWriter fw = new FileWriter("Jtable.txt",true);
							fw.write(Tid.getText()+"\t"+ComboPos.getSelectedItem()+"\t"+Tfname.getText()+"\t"+Tlname.getText()+"\t"+Tage.getText());
							fw.close();
						}catch(Exception e){
						}
					row[0] = Tid.getText();
					row[1] = ComboPos.getSelectedItem();
					row[2] = Tfname.getText();
					row[3] = Tlname.getText();
					row[4] = Tage.getText();
					model.addRow(row);

					Tid.setText("");
					Tfname.setText("");
					Tlname.setText("");
					Tage.setText("");
					}
				}
			});
			
			table.addMouseListener(new MouseAdapter(){
			@Override 
			public void mouseClicked(MouseEvent e){
				int i = table.getSelectedRow();
				Tid.setText(model.getValueAt(i,0).toString());
				String selectedPos = model.getValueAt(i,1).toString();
				Tfname.setText(model.getValueAt(i,2).toString());
				Tlname.setText(model.getValueAt(i,3).toString());
				Tage.setText(model.getValueAt(i,4).toString());
				
					switch(selectedPos){
						case "Teacher":
							ComboPos.setSelectedIndex(0);
							break;
						case "Student":
							ComboPos.setSelectedIndex(1);
							break;
						case "Registrar":
							ComboPos.setSelectedIndex(2);
							break;
					}
					
			}
			});
			
			
			
			update =  new JButton("Update");
			update.setBounds(350,180,100,30);
			update.setForeground(Color.BLUE);
			update.setFont(new Font("Time New Roman",Font.BOLD,14));
			add(update);
			update.addActionListener(new ActionListener(){ 
				@Override
				public void actionPerformed(ActionEvent ae){
					int i = table.getSelectedRow();
					if(i >=0){
					model.setValueAt(Tid.getText(),i,0);
					model.setValueAt(ComboPos.getSelectedItem(),i,1);
					model.setValueAt(Tfname.getText(),i,2);
					model.setValueAt(Tlname.getText(),i,3);
					model.setValueAt(Tage.getText(),i,4);
					}
					else{
					JFrame f = new JFrame();
					JOptionPane.showMessageDialog(f,"Update Error");
					}
					
				}
			});
			
		
			delete =  new JButton("Delete");
			delete.setBounds(470,180,100,30);
			delete.setFont(new Font("Time New Roman",Font.BOLD,14));
			delete.setForeground(Color.RED);
			add(delete);
			delete.addActionListener(new ActionListener(){ 
				public void actionPerformed(ActionEvent ae){
					int i =table.getSelectedRow();
					if(i>=0){
						model.removeRow(i);
						Tid.setText("");
						Tfname.setText("");
						Tlname.setText("");
						Tage.setText("");
					}
					else{
						JFrame f = new JFrame();
						JOptionPane.showMessageDialog(f,"Delete Error");
					}
					
				}
			});
			
	}
}

public class Ricafrente{
	public static void main(String[]args){
		HomePage hp=  new HomePage();
		hp.setBounds(170,100,1000,500);
		hp.setVisible(true);
		hp.setTitle("School Information System");
		
	}
}