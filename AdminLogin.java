package projectnvidia;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class AdminLogin extends JFrame {

	JTextField username;

	JTextField pass;

	JButton login;

	JButton goback;

	public AdminLogin() {
		createFrame();
		createComponents();
	}

	public void createFrame() {
		setTitle("Admin Login");

		setSize(1920, 1080);

		Image image = Toolkit.getDefaultToolkit()
				.getImage("D:\\Qspiders\\Project\\NvidiaFibernetBuildingSystem\\src\\main\\java\\icons\\logo.jpg");

		setIconImage(image);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setContentPane(createBackgroundPanel());

		setLayout(null);

		setVisible(true);
	}

	private JPanel createBackgroundPanel() {
		return new JPanel() {
			private Image backgroundImage = new ImageIcon(getClass().getResource("/icons/logo.jpg")).getImage();

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);

				if (backgroundImage != null) {
					g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

					g.setColor(new Color(0, 0, 0, 100));
					g.fillRect(0, 0, getWidth(), getHeight());
				} else {
					g.setColor(Color.LIGHT_GRAY);
					g.fillRect(0, 0, getWidth(), getHeight());
				}
			}
		};
	}

	private void createComponents() {
		JLabel titleLabel = new JLabel("ADMIN LOGIN");
		titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setBounds(720, 230, 150, 25);

		JLabel userLabel = new JLabel("Username:");
		userLabel.setFont(new Font("Arial", Font.BOLD, 16));
		userLabel.setForeground(Color.WHITE);
		userLabel.setBounds(610, 300, 150, 25);

		username = new JTextField();
		username.setBounds(700, 300, 200, 25);

		JLabel phoneLabel = new JLabel("Password:");
		phoneLabel.setFont(new Font("Arial", Font.BOLD, 16));
		phoneLabel.setForeground(Color.WHITE);
		phoneLabel.setBounds(610, 350, 150, 25);

		pass = new JTextField();
		pass.setBounds(700, 350, 200, 25);

		JCheckBox termsCheckbox = new JCheckBox("Agree to Terms and Conditions");
		termsCheckbox.setBounds(695, 385, 220, 35);
		termsCheckbox.setOpaque(false);
		termsCheckbox.setForeground(Color.WHITE);
		termsCheckbox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				login.setEnabled(termsCheckbox.isSelected());
			}
		});

		login = new JButton("Login");
		login.setFont(new Font("Arial", Font.BOLD, 15));
		login.setBounds(690, 430, 100, 25);
		login.setEnabled(false);
		login.setForeground(Color.black);
		login.setBackground(Color.green);

		login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String username1 = username.getText();
				String password1 = new String(pass.getText());

				// Validate using DatabaseConnection
				boolean isValid = dbAdmin.validateAdmin(username1, password1);

				if (isValid) {
					JOptionPane.showMessageDialog(login, "Login Successful!");
					// login.dispose(); // Close Login Page
//	                  
					// Show Home Page
					new HomeGUI();

				} else {
					JOptionPane.showMessageDialog(login, "Invalid Username or Password!");

				}
			}
		});

//		login.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				handlelogin();
//			}
//		});

		goback = new JButton("GoBack");
		goback.setFont(new Font("Arial", Font.BOLD, 15));
		goback.setBounds(810, 430, 100, 25);
		goback.setForeground(Color.black);
		goback.setBackground(Color.red);
		goback.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		add(titleLabel);
		add(userLabel);
		add(username);
		add(phoneLabel);
		add(pass);
		add(termsCheckbox);
		add(login);
		add(goback);

	}

//	private void handlelogin() {
//		
//		
//		String user = username.getText().trim();
//		String phone = phno.getText().trim();
//
//		if (user.isEmpty() || phone.isEmpty()) {
//			JOptionPane.showMessageDialog(this, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
//			return;
//		}
//		if (!phone.matches("\\d{10}")) {
//			JOptionPane.showMessageDialog(this, "Enter a valid 10-digit phone number!", "Error",
//					JOptionPane.ERROR_MESSAGE);
//			return;
//		}
//     
//		JOptionPane.showMessageDialog(this, "Sign Up Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
//	  new HomeGUI();
//	}
//	

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new AdminLogin();
			}
		});
	}
}
