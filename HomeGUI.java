package projectnvidia;

import java.awt.Color;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

//JFrame used to display the frame and contents

public class HomeGUI extends JFrame {

	private JButton exsistingUserButton;
	private JButton adminLoginButton;
	private JButton newConnectionButton;

	private Color exsistingUserColor = Color.red;
	private Color adminLoginColor = Color.magenta;
	private Color newConnectioncolor = Color.blue;

// add a constructor and inside that we call the methods
	public HomeGUI() {
		SetUpFrame();
		intializeButtonComponents();
		addComponents();
		initializeListeners();

	}

//create method to setup frame by providing functions

	private void SetUpFrame() {
		setSize(1920, 1080);
		setVisible(true);
		setTitle("Home");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setContentPane(createBackgroundPanel());
	}

//create method for adding background image
	private JPanel createBackgroundPanel() {
//create a inner class of JPanel

		return new JPanel() {
			@Override
//Graphics is used to providing Graphical rendering operations

			protected void paintComponent(Graphics g) {
//ImageIcon used to paint icons from image which is provided by us.
				ImageIcon imageIcon = new ImageIcon(getClass().getResource("/icons/logo.jpg"));

//Image that represent graphical images.
				Image image = imageIcon.getImage();

//getWidth/Height Returns the current width/height of this component.
				double panelWidth = getWidth();
				double panelHeight = getHeight();
				double imageWidth = image.getWidth(this);
				double imageHeight = image.getHeight(this);

//Math used for performing basic numeric operations
				double scale = Math.max(panelWidth / imageWidth, panelHeight / imageHeight);

				int scaledWidth = (int) (imageWidth * scale);
				int scaledHeight = (int) (imageHeight * scale);

				int x = (int) ((panelWidth - scaledWidth) / 2);
				int y = (int) ((panelHeight - scaledHeight) / 2);

//drawImage used to load and draw the image by providing above scales inside()
				g.drawImage(image, x, y, scaledWidth, scaledHeight, this);

//setColor set current color to the specified color. 
				g.setColor(new Color(0, 0, 0, 150));
//fillRect used to fill color to all part of the image
				g.fillRect(0, 0, getWidth(), getHeight());
			}
		};
	}

//	private JPanel createBackgroundPanel() {
//        return new JPanel() {
//            private Image backgroundImage = new ImageIcon(getClass().getResource("/icons/logo.jpg")).getImage();
//
//            @Override
//            protected void paintComponent(Graphics g) {
//                super.paintComponent(g);
//
//                if (backgroundImage != null) {
//                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
//
//                    g.setColor(new Color(0, 0, 0, 100));
//                    g.fillRect(0, 0, getWidth(), getHeight());
//                } else {
//                    g.setColor(Color.LIGHT_GRAY);
//                    g.fillRect(0, 0, getWidth(), getHeight());
//                }
//            }
//        };
//    }
	
	private void intializeButtonComponents() {
		exsistingUserButton = createStyleButton("Existing User?", exsistingUserColor);
		newConnectionButton = createStyleButton("Buy New Connection: ", newConnectioncolor);
		adminLoginButton = createStyleButton("Admin Sign in: ", adminLoginColor);
	}

	private JButton createStyleButton(String text, Color backgroundColor) {
		JButton button = new JButton(text);
		button.setFont(new Font("Arial", Font.BOLD, 19));
		button.setBackground(backgroundColor);
		button.setForeground(Color.black);
		button.setFocusable(false);

		button.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Color.WHITE, 2),
				BorderFactory.createEmptyBorder(12, 25, 12, 25)
				));

		button.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				button.setBackground(backgroundColor.GRAY);

			}

			@Override
			public void mouseExited(MouseEvent e) {
				button.setBackground(backgroundColor);
			}

		});
		return button;
	}

	private void initializeListeners() {
		exsistingUserButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				handledExsistingUser();
			}
			
		});
		adminLoginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				handledAdminUser();
			}
			
		});
		newConnectionButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				handledConnection();
			}
			
		});
	}

// create method for adding some component
	private void addComponents() {

//we add mainPanel where we control structure/text/buttons/every thing

		JPanel mainPanel = new JPanel();
		mainPanel.setOpaque(false);

//setLayout used to set up the layout to our page/GUI and Box/Flow Layout used to apply layout in different direction 

		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		titlePanel.setOpaque(false);

// for adding title/text we use JLabel

		JLabel label = new JLabel("Welcome To Nvidia Fibernet");
		label.setFont(new Font("Arial", Font.BOLD, 24));

// setForeground() used to adding color or styling to text

		label.setForeground(Color.WHITE);
		titlePanel.add(label);

//to add space from vertical we use this method and giving a size

		mainPanel.add(Box.createVerticalStrut(185));
		mainPanel.add(titlePanel);
		mainPanel.add(Box.createVerticalStrut(185));
		JPanel buttonPanel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		buttonPanel1.setOpaque(false);
		buttonPanel1.add(exsistingUserButton);
		buttonPanel1.add(adminLoginButton);
		buttonPanel1.add(newConnectionButton);

		mainPanel.add(buttonPanel1);
		add(mainPanel);
	}

	private void handledExsistingUser() {
		int choice = JOptionPane.showConfirmDialog(this, "Do you want to got to login page?", "Confirm",
				JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
		if (choice == JOptionPane.YES_OPTION) {
			JOptionPane.showMessageDialog(this, "Going To Login GUI....", "Message", JOptionPane.INFORMATION_MESSAGE);
			new LoginGUI();
		}
	}
	private void handledAdminUser() {
		int choice = JOptionPane.showConfirmDialog(this, "Do you want to Login as a Admin?", "Confirm",
				JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
		if (choice == JOptionPane.YES_OPTION) {
			JOptionPane.showMessageDialog(this, "Going To Admin GUI....", "Message", JOptionPane.INFORMATION_MESSAGE);
			new AdminLogin();
		}
	}
	private void handledConnection() {
		int choice = JOptionPane.showConfirmDialog(this, "Do you want to Buy new Connection?", "Confirm",
				JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
		if (choice == JOptionPane.YES_OPTION) {
			JOptionPane.showMessageDialog(this, "Going To Connection GUI....", "Message", JOptionPane.INFORMATION_MESSAGE);
			return;
			
		}
	}

	public static void main(String[] args) {

//call a homegui method inside main method to run our project
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new HomeGUI();
			}
		});
	}
}
