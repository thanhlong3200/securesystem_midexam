package view.dialogs;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import ciphers.ShiftCipher;
import view.ContentView;

public class ShiftCipherDialog extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField shiftKey;
	private JButton shiftApply;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShiftCipherDialog frame = new ShiftCipherDialog(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ShiftCipherDialog(JPanel contentView) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 450, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Enter key");
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 50, 100, 40);
		contentPane.add(lblNewLabel);

		shiftKey = new JTextField();
		shiftKey.setFont(new Font("Tahoma", Font.PLAIN, 14));
		shiftKey.setBounds(120, 50, 180, 40);
		contentPane.add(shiftKey);
		shiftKey.setColumns(10);

		shiftApply = new JButton("Apply");
		shiftApply.setFont(new Font("Tahoma", Font.BOLD, 14));
		shiftApply.setBounds(310, 50, 90, 40);
		shiftApply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					((ContentView) contentView).setCipher(new ShiftCipher().setKey(Integer.parseInt(shiftKey.getText())));
					setVisible(false);
				} catch (Exception ex) {
					
				}
			}
		});
		contentPane.add(shiftApply);
	}

	public JTextField getShiftKey() {
		return shiftKey;
	}

	public void setShiftKey(JTextField shiftKey) {
		this.shiftKey = shiftKey;
	}

	public JButton getShiftApply() {
		return shiftApply;
	}

	public void setShiftApply(JButton shiftApply) {
		this.shiftApply = shiftApply;
	}

}
