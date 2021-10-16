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

import ciphers.Cipher;
import ciphers.ShiftCipher;

public class ContentView extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTextArea plaintextArea, ciphertextArea;
	private JButton btnEncrypt, btnDecrypt;
	private JButton btnPlaintextOpen, btnPlaintextSave, btnCiphertextOpen, btnCiphertextSave;
	private JLabel lblPlaintextFile, lblCiphertextFile;

	private static final String ROOT_DIR = "E:\\NAM4_HOCKY1\\Antoan&BaoMatHeThong\\workspace\\mid_exam\\saves";
	private Cipher cipher = new ShiftCipher().setKey(3);
	public ContentView setCipher(Cipher cipher) {
		this.cipher = cipher;
		return this;
	}
	public Cipher getCipher() {
		return this.cipher;
	}

	/**
	 * Create the panel.
	 */
	public ContentView() {
		setLayout(null);
		
		// Value of plaintext
		plaintextArea = new JTextArea();
		plaintextArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
		plaintextArea.setBounds(10, 40, 420, 400);
		add(plaintextArea);
		
		// Value of ciphertext
		ciphertextArea = new JTextArea();
		ciphertextArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
		ciphertextArea.setBounds(570, 40, 420, 400);
		add(ciphertextArea);
		
		// Encrypt Button
		btnEncrypt = new JButton("Encrypt");
		btnEncrypt.setIcon(null);
		btnEncrypt.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnEncrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String plaintext = plaintextArea.getText();
				String ciphertext = cipher.encrypt(plaintext);
				ciphertextArea.setText(ciphertext);
			}
		});
		btnEncrypt.setBounds(450, 200, 100, 35);
		add(btnEncrypt);
		
		// Decrypt Button
		btnDecrypt = new JButton("Decrypt");
		btnDecrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ciphertext = ciphertextArea.getText();
				String plaintext = cipher.decrypt(ciphertext);
				plaintextArea.setText(plaintext);
			}
		});
		btnDecrypt.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnDecrypt.setBounds(450, 240, 100, 35);
		add(btnDecrypt);
		
		// LABEL
		lblPlaintextFile = new JLabel("Plaintext");
		lblPlaintextFile.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlaintextFile.setLabelFor(plaintextArea);
		lblPlaintextFile.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPlaintextFile.setBounds(165, 10, 100, 30);
		add(lblPlaintextFile);

		lblCiphertextFile = new JLabel("Ciphertext");
		lblCiphertextFile.setHorizontalAlignment(SwingConstants.CENTER);
		lblCiphertextFile.setLabelFor(ciphertextArea);
		lblCiphertextFile.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCiphertextFile.setBounds(730, 10, 100, 30);
		add(lblCiphertextFile);
		
		// Label display file name for plaintext
		lblPlaintextFile = new JLabel("");
		lblPlaintextFile.setHorizontalAlignment(SwingConstants.LEFT);
		lblPlaintextFile.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblPlaintextFile.setBounds(10, 439, 490, 30);
		add(lblPlaintextFile);
		
		// Button OPEN for plaintext
		btnPlaintextOpen = new JButton("Open");
		btnPlaintextOpen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnPlaintextOpen.setBounds(10, 467, 100, 30);
		add(btnPlaintextOpen);
		btnPlaintextOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser j = new JFileChooser(ROOT_DIR);
				int r = j.showOpenDialog(null);
				if (r == JFileChooser.APPROVE_OPTION) {
					System.out.println(j.getSelectedFile().getAbsolutePath());

					File file = j.getSelectedFile();
					String data = readFileToString(file);
					plaintextArea.setText(data);
					lblPlaintextFile.setText(file.getAbsolutePath());
				}
			}
		});
		
		// Button SAVE for plaintext
		btnPlaintextSave = new JButton("Save");
		btnPlaintextSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnPlaintextSave.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnPlaintextSave.setBounds(120, 467, 100, 30);
		add(btnPlaintextSave);
		
		// Label display file name for ciphertext
		lblCiphertextFile = new JLabel("");
		lblCiphertextFile.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCiphertextFile.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblCiphertextFile.setBounds(510, 439, 480, 30);
		add(lblCiphertextFile);
		
		// Button OPEN file for ciphertext
		btnCiphertextOpen = new JButton("Open");
		btnCiphertextOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnCiphertextOpen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCiphertextOpen.setBounds(780, 467, 100, 30);
		add(btnCiphertextOpen);
		
		// Button SAVE file for ciphertext
		btnCiphertextSave = new JButton("Save");
		btnCiphertextSave.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCiphertextSave.setBounds(890, 467, 100, 30);
		add(btnCiphertextSave);
	}

	/**
	 * get contents inside file
	 * @param file
	 * @return textarea in file
	 */
	private String readFileToString(File file) {
		StringBuilder resultStringBuilder = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = br.readLine()) != null) {
				resultStringBuilder.append(line).append("\n");
			}
		} catch (IOException e) {
			return "Exception at readFile function!";
		}
		return resultStringBuilder.toString();
	}
}
