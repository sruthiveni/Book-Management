package Crud;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
public class JavaCrud1 {

	private JFrame frame;
	private JTextField txtbname;
	private JTextField txtedition;
	private JTextField txtprice;
	private JTable table;
	private JTextField txtbid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JavaCrud1 window = new JavaCrud1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JavaCrud1() {
		initialize();
		Connect();
		table_load();
	}
	
	Connection con;
	PreparedStatement pat;
	ResultSet rs;
	
	
	
	
	public void Connect()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/javacrud","root","");
			
		}
		catch(ClassNotFoundException ex)
		{
			
		}
		catch(SQLException ex)
		{
			
		}
	}
	
	public void table_load()
	{
		try
		{
			pat = con.prepareStatement("select * from book");
			rs = pat.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1249, 678);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("BOOK SHOP");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(496, 21, 238, 44);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(45, 98, 540, 297);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Book Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(44, 64, 168, 71);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Edition");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(44, 123, 168, 71);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Price");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_1_1.setBounds(44, 192, 168, 71);
		panel.add(lblNewLabel_1_1_1);
		
		txtbname = new JTextField();
		txtbname.setBounds(180, 75, 285, 38);
		panel.add(txtbname);
		txtbname.setColumns(10);
		
		txtedition = new JTextField();
		txtedition.setColumns(10);
		txtedition.setBounds(180, 144, 285, 38);
		panel.add(txtedition);
		
		txtprice = new JTextField();
		txtprice.setColumns(10);
		txtprice.setBounds(180, 208, 285, 38);
		panel.add(txtprice);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bname,edition,price;
				
				bname = txtbname.getText();
				edition = txtedition.getText();
				price = txtprice.getText();
				
				try
				{
					pat =con.prepareStatement("insert into book(name,edition,price)values(?,?,?)");
					pat.setString(1, bname);
					pat.setString(2, edition);
					pat.setString(3,  price);
					pat.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Adeddddd!!!!!!");
					table_load();
					txtbname.setText("");
					txtedition.setText("");
					txtprice.setText("");
					txtbname.requestFocus();
					
				}
				catch(SQLException e1)
				{
					e1.printStackTrace();
				}
				
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.setBounds(55, 405, 133, 69);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnExit.setBounds(226, 405, 133, 69);
		frame.getContentPane().add(btnExit);
		
		JButton btnNewButton_1_1 = new JButton("Clear");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
                txtbname.setText("");
                txtedition.setText("");
                txtprice.setText("");
                txtbname.requestFocus();

			}
		});
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_1_1.setBounds(400, 405, 133, 69);
		frame.getContentPane().add(btnNewButton_1_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(616, 97, 540, 377);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(45, 507, 540, 99);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Book ID");
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_1_2.setBounds(31, 10, 168, 71);
		panel_1.add(lblNewLabel_1_1_2);
		
		txtbid = new JTextField();
		txtbid.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				try
				{
					String id = txtbid.getText();
					pat = con.prepareStatement("select name,edition,price from book where id = ?");
					pat.setString(1, id);
					ResultSet rs = pat.executeQuery();
					
					if(rs.next() == true)
					{
						String name = rs.getString(1);
						String edition = rs.getString(2);
						String price = rs.getString(3);
						
						txtbname.setText(name);
						txtedition.setText(edition);
						txtprice.setText(price);
					}
					else
					{
						txtbname.setText("");
						txtedition.setText("");
						txtprice.setText("");
					}
				}
				catch(SQLException ex)
				{
					
				}
			}
		});
		txtbid.setColumns(10);
		txtbid.setBounds(144, 23, 300, 38);
		panel_1.add(txtbid);
		
		JButton btnNewButton_1_1_1 = new JButton("Update");
		btnNewButton_1_1_1.setForeground(new Color(0, 0, 0));
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					String bname,edition,price,bid;
                
                
                bname = txtbname.getText();
                edition = txtedition.getText();
                price = txtprice.getText();
                bid  = txtbid.getText();
                
                 try {
                        pat = con.prepareStatement("update book set name= ?,edition=?,price=? where id =?");
                        pat.setString(1, bname);
                        pat.setString(2, edition);
                        pat.setString(3, price);
                        pat.setString(4, bid);
                        pat.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Record Updated!!!!!");
                        table_load();
                       
                        txtbname.setText("");
                        txtedition.setText("");
                        txtprice.setText("");
                        txtbname.requestFocus();
                    }

                    catch (SQLException e1) {
                        
                        e1.printStackTrace();
                    }
			}
		});
		btnNewButton_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_1_1_1.setBounds(716, 507, 174, 69);
		frame.getContentPane().add(btnNewButton_1_1_1);
		
		JButton btnNewButton_1_1_1_1 = new JButton("Delete");
		btnNewButton_1_1_1_1.setForeground(new Color(0, 0, 0));
		btnNewButton_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bid;
		           bid  = txtbid.getText();
		           
		            try {
		                   pat = con.prepareStatement("delete from book where id =?");
		           
		                   pat.setString(1, bid);
		                   pat.executeUpdate();
		                   JOptionPane.showMessageDialog(null, "Record Deleted!!!!!");
		                   table_load();
		                  
		                   txtbname.setText("");
		                   txtedition.setText("");
		                   txtprice.setText("");
		                   txtbname.requestFocus();
		               }

		               catch (SQLException e1) {
		                   
		                   e1.printStackTrace();
		               }
			}
		});
		btnNewButton_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_1_1_1_1.setBounds(916, 507, 174, 69);
		frame.getContentPane().add(btnNewButton_1_1_1_1);
	}
}