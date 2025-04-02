package projectnvidia;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class UserDashboard extends JFrame {
	static String status;

	public UserDashboard() {
		SetUpFrame();
		lookAndFeel();
		JPanel headerPanel = createHeaderPanel();
		add(headerPanel, BorderLayout.NORTH);
		JPanel mainPanel = createMainPanel();
		add(mainPanel, BorderLayout.CENTER);
	}

	private void SetUpFrame() {
		setSize(1920, 1080);
		Image image = Toolkit.getDefaultToolkit()
				.getImage("D:\\Qspiders\\Project\\NvidiaFibernetBuildingSystem\\src\\main\\java\\icons\\logo.jpg");

		setIconImage(image);
		setVisible(true);
		setLayout(new BorderLayout());
		setTitle("Nvidia Fibernet");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void lookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private JButton createStyleButton(String text) {
		JButton button = new JButton(text);
		button.setFont(new Font("Arial", Font.PLAIN, 14));
		button.setForeground(new Color(51, 51, 51));
		button.setBackground(Color.WHITE);
		button.setFocusable(false);
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));

		button.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				button.setForeground(new Color(255, 51, 85));

			}

			@Override
			public void mouseExited(MouseEvent e) {
				button.setForeground(new Color(51, 51, 51));
			}

		});
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				switch (text) {
				case "Pay Bills":
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							try {
								UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
							} catch (Exception ex) {
								ex.printStackTrace();
							}
							JOptionPane.showMessageDialog(UserDashboard.this, "Going To Payment Gateway", "Message",
									JOptionPane.INFORMATION_MESSAGE);
//							 new PaymentGUI().setVisible(true); 
						}
					});
					break;
				case "Services Request":
					if (UserDashboard.status == "INACTIVE") {
						JOptionPane.showMessageDialog(UserDashboard.this,
								"Inactive Plan. Service Request Cannot be Raised", "Failed",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						try {
							UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
						} catch (Exception ex) {
							ex.printStackTrace();
						}
						// new ServiceRequest();
					}
					break;
				case "New Connection":
					try {
						UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
							| UnsupportedLookAndFeelException ex) {
						ex.printStackTrace();
					}
					JOptionPane.showMessageDialog(UserDashboard.this, "Going to Buy New Connection Here", "Message",
							JOptionPane.INFORMATION_MESSAGE);
					 new BuyConnection();
					break;

				case "FAQ":
					JOptionPane.showMessageDialog(button, "FAQ Section Coming Soon...");
					break;
				}
			}
		});

		return button;
	}

	private JPanel createHeaderPanel() {
		JPanel headerPanel = new JPanel(new BorderLayout());
		headerPanel.setBackground(Color.WHITE);
		headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

		JLabel titleLabel = new JLabel("Nvidia Fibernet");
		titleLabel.setForeground(new Color(255, 51, 85));
		titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
		headerPanel.add(titleLabel, BorderLayout.WEST);

		JPanel navMenu = new JPanel(new FlowLayout(FlowLayout.CENTER));
		navMenu.setBackground(Color.WHITE);
		String[] menuItems = { "Pay Bills", "Serivce Request", "New Connection", "FAQ" };
		for (String data : menuItems) {
			JButton menuButton = createStyleButton(data);
			navMenu.add(menuButton);
		}
		headerPanel.add(navMenu, BorderLayout.CENTER);

		JPanel dropdownPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
		dropdownPanel.setBackground(Color.WHITE);

		JButton dropdownButton = new JButton("Options");
		dropdownButton.setForeground(new Color(51, 51, 51));
		dropdownButton.setBackground(Color.WHITE);
		dropdownButton.setFont(new Font("Arial", Font.PLAIN, 14));
		dropdownButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		dropdownButton.setFocusPainted(false);

		JPopupMenu popUpMenu = new JPopupMenu();
		JMenuItem profileOption = new JMenuItem("Profile");
		JMenuItem logOutOption = new JMenuItem("Log Out");

		profileOption.addActionListener(e -> {
			JOptionPane.showMessageDialog(this, "Profile is coming soon");
		});
		logOutOption.addActionListener(e -> {
			int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to Logout?", "Logout",
					JOptionPane.YES_NO_OPTION);
			if (choice == JOptionPane.YES_OPTION) {

				dispose();
				new HomeGUI();
			}

		});

		popUpMenu.add(profileOption);
		popUpMenu.add(logOutOption);

		dropdownButton.addActionListener(e -> popUpMenu.show(dropdownButton, 0, dropdownButton.getHeight()));

		dropdownPanel.add(dropdownButton);
		headerPanel.add(dropdownPanel, BorderLayout.EAST);
		add(headerPanel);
		return headerPanel;
	}

	private JPanel createMainPanel() {
		JPanel mainPanel = new JPanel(new GridLayout(1, 2));
		mainPanel.setBackground(Color.WHITE);

		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new GridBagLayout());
		leftPanel.setBackground(new Color(255, 182, 193));

		JLabel offerLabel = new JLabel(
				"<html>Pay your Nvidia Fibernet<br>bill via CRED UPI and Get<br>up to Rs. 250*<br>Cashback</html>");
		offerLabel.setFont(new Font("Arial", Font.BOLD, 18));
		offerLabel.setForeground(Color.WHITE);

		offerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		leftPanel.add(offerLabel);

		JPanel rightPanel = new JPanel(new BorderLayout());
		rightPanel.setBackground(Color.WHITE);
		rightPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

		JPanel detailsPanel = new JPanel();
		detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
		detailsPanel.setBackground(Color.WHITE);

		detailsPanel.add(createInfoLabel("Ultra", true));
		detailsPanel.add(Box.createRigidArea(new Dimension(0, 100)));

		detailsPanel.add(createInfoLabel("500 Mbps | 4000GB", false));
		detailsPanel.add(Box.createRigidArea(new Dimension(0, 100)));

		detailsPanel.add(createInfoLabel("19th December", true));
		detailsPanel.add(Box.createRigidArea(new Dimension(0, 100)));

		detailsPanel.add(createInfoLabel("Due Date", false));
		detailsPanel.add(Box.createRigidArea(new Dimension(0, 100)));

		detailsPanel.add(createInfoLabel("Used: 53.6GB", false));

		rightPanel.add(detailsPanel, BorderLayout.NORTH);

		JPanel upgradePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		upgradePanel.setBackground(new Color(243, 207, 198));

		JLabel upgradeLabel = new JLabel("Upgrade to enjoy Netflix at no extra cost, higher speed, and great offers");
		upgradeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		JButton upgradeButton = new JButton("Upgrade");
		upgradeButton.setFont(new Font("Arial", Font.PLAIN, 14));
		upgradeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

		upgradePanel.add(upgradeLabel);
		upgradePanel.add(upgradeButton);
		rightPanel.add(upgradePanel, BorderLayout.SOUTH);

		mainPanel.add(leftPanel);
		mainPanel.add(rightPanel);

		return mainPanel;
	}

	private JLabel createInfoLabel(String text, boolean bold) {
		JLabel label = new JLabel(text);
		label.setFont(new Font("Arial", bold ? Font.BOLD : Font.PLAIN, 16));
		label.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
		return label;
	}



	public static void main(String[] args) {
		new UserDashboard();
	}
}