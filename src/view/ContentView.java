package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import ciphers.ShiftCipher;

public class ContentView extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public ContentView() {
		setLayout(null);

		JTextArea plaintextArea = new JTextArea();
		plaintextArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
		plaintextArea.setBounds(10, 40, 420, 400);
		add(plaintextArea);

		JTextArea ciphertextArea = new JTextArea();
		ciphertextArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
		ciphertextArea.setBounds(570, 40, 420, 400);
		add(ciphertextArea);

		JButton btnEncrypt = new JButton("Encrypt");
		btnEncrypt.setIcon(null);
		btnEncrypt.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnEncrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShiftCipher shiftCipher = new ShiftCipher();
				String plaintext = plaintextArea.getText();
				String ciphertext = shiftCipher.shiftEncrypt(plaintext);
				ciphertextArea.setText(ciphertext);
				System.out.println(ciphertext);
			}
		});
		btnEncrypt.setBounds(450, 200, 100, 35);
		add(btnEncrypt);

		JButton btnDecrypt = new JButton("Decrypt");
		btnDecrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShiftCipher shiftCipher = new ShiftCipher();
				String ciphertext = ciphertextArea.getText();
				String plaintext = shiftCipher.shiftDecrypt(ciphertext);
				plaintextArea.setText(plaintext);
				System.out.println(plaintext);
			}
		});
		btnDecrypt.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnDecrypt.setBounds(450, 240, 100, 35);
		add(btnDecrypt);

		JLabel lblPlaintext = new JLabel("Plaintext");
		lblPlaintext.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlaintext.setLabelFor(plaintextArea);
		lblPlaintext.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPlaintext.setBounds(165, 10, 100, 30);
		add(lblPlaintext);

		JLabel lblCiphertext = new JLabel("Ciphertext");
		lblCiphertext.setHorizontalAlignment(SwingConstants.CENTER);
		lblCiphertext.setLabelFor(ciphertextArea);
		lblCiphertext.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCiphertext.setBounds(730, 10, 100, 30);
		add(lblCiphertext);
		
		

		JButton btnCiphertextOpen = new JButton("Open");
		btnCiphertextOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnCiphertextOpen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCiphertextOpen.setBounds(780, 467, 100, 30);
		add(btnCiphertextOpen);

		JLabel lblPlaintextFile = new JLabel("");
		lblPlaintextFile.setHorizontalAlignment(SwingConstants.LEFT);
		lblPlaintextFile.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblPlaintextFile.setBounds(10, 439, 490, 30);
		add(lblPlaintextFile);

		JLabel lblCiphertextFile = new JLabel("");
		lblCiphertextFile.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCiphertextFile.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblCiphertextFile.setBounds(510, 439, 480, 30);
		add(lblCiphertextFile);

		JButton btnPlaintextSave = new JButton("Save");
		btnPlaintextSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnPlaintextSave.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnPlaintextSave.setBounds(120, 467, 100, 30);
		add(btnPlaintextSave);

		JButton btnCiphertextSave = new JButton("Save");
		btnCiphertextSave.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCiphertextSave.setBounds(890, 467, 100, 30);
		add(btnCiphertextSave);

		JButton btnPlaintextOpen = new JButton("Open");
		btnPlaintextOpen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnPlaintextOpen.setBounds(10, 467, 100, 30);
		add(btnPlaintextOpen);
		btnPlaintextOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser j = new JFileChooser("E:\\NAM4_HOCKY1\\Antoan&BaoMatHeThong\\workspace\\mid_exam\\saves");
				// Open the save dialog
				int r = j.showOpenDialog(null);
				if (r == JFileChooser.APPROVE_OPTION) {
					System.out.println(j.getSelectedFile().getAbsolutePath());

					File file = j.getSelectedFile();
					System.out.println("newfile: " + file.getPath());

					String data = readFromInputStream(file);
					plaintextArea.setText(data);
					lblPlaintextFile.setText(file.getAbsolutePath());
				}
			}
		});

	}

	private String readFromInputStream(File file) {
		StringBuilder resultStringBuilder = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = br.readLine()) != null) {
				resultStringBuilder.append(line).append("\n");
			}
		}catch(IOException e) {
			return "";
		}
		return resultStringBuilder.toString();
	}
}
