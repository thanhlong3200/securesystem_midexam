package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import ciphers.ShiftCipher;
import view.dialogs.ShiftCipherDialog;

public class MainView {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView window = new MainView();
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
	public MainView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("Chương trình mã hóa - X");
		frame.setBounds(100, 100, 1020, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new ContentView();
		panel.setBounds(10, 92, 1000, 500);
		frame.getContentPane().add(panel);

		JButton btnShiftCipher = new JButton("<html>D\u1ECBch<br />chuy\u1EC3n</html>");
		btnShiftCipher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShiftCipherDialog dialog = new ShiftCipherDialog(panel);
				dialog.setVisible(true);
			}
		});
		btnShiftCipher.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnShiftCipher.setBounds(10, 10, 100, 50);
		frame.getContentPane().add(btnShiftCipher);

		JButton btnNewButton_1 = new JButton("<html>Thay<br />th\u1EBF</html>");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.setBounds(120, 10, 100, 50);
		frame.getContentPane().add(btnNewButton_1);

		JLabel lblNewLabel = new JLabel("M\u00C3 H\u00D3A D\u1ECACH CHUY\u1EC2N");
		lblNewLabel.setBounds(10, 60, 986, 32);
		frame.getContentPane().add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	}
}
