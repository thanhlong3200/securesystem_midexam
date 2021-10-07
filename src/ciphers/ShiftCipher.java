package ciphers;

public class ShiftCipher {

	private final char[] ALPHABET = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
			'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
	private final int CEASAR = 3;

	public String shiftEncrypt(String plaintext) {
		return shift(plaintext, 0);
	}

	public String shiftDecrypt(String plaintext) {
		return shift(plaintext, 1);
	}

	/**
	 * Dịch chuyển các ký tự trong một chuỗi bằng Mã hóa dịch chuyển.
	 * 
	 * @param plant text
	 * @param typeCrypt: 0 -> encrypt, 1 -> decrypt
	 * @return cipher text
	 */
	public String shift(String plaintext, int typeCrypt) {
		if (plaintext == null || plaintext.trim().isEmpty())
			throw new NullPointerException("Plaintext is null or empty");

		char[] letters = plaintext.toCharArray();
		char[] result = new char[letters.length];

		for (int i = 0; i < letters.length; i++) {
			result[i] = isAlphabet(letters[i])
					? (typeCrypt == 0 ? encryptLetter(letters[i]) : decryptletter(letters[i]))
					: letters[i];
		}
		return new String(result);
	}

	private char encryptLetter(char letter) {
		return cryptLetter(letter, 0);
	}

	private char decryptletter(char letter) {
		return cryptLetter(letter, 1);
	}

	/**
	 * Dịch chuyển ký tự trong alphabet
	 * 
	 * @param vd:A, b, C, 1
	 * @return vd:X, y, Z, 1
	 */
	private char cryptLetter(char letter, int typeCrypt) {
		char upLetter = toUppercase(letter);
		int alphabetNum = alphabetNum(upLetter);
		char newLetter = 0;
		if (alphabetNum != -1) {
			if (typeCrypt == 0) { // encrypt
				// index more than 23 -> index + CEASAR - 26, else -> 26 - index
				// sample: index = 24 -> new index = 24 + 3 - 26 = 1
				newLetter = (alphabetNum < (ALPHABET.length - CEASAR))
						? ALPHABET[alphabetNum + CEASAR]
						: ALPHABET[alphabetNum + CEASAR - ALPHABET.length];
			} else { // decrypt
				// index less than 3 -> 26 + index - 3, else -> index + CEASAR
				// sample: index = 2 -> new index = 26 + 2 - 3 = 25
				newLetter = (alphabetNum >= (CEASAR)) 
						? ALPHABET[alphabetNum - CEASAR]
						: ALPHABET[ALPHABET.length + alphabetNum - CEASAR];
			}
		}
		return upLetter == letter ? newLetter : Character.toLowerCase(newLetter);
	}

	/**
	 * 
	 * @param a, b, c
	 * @return A, B, C
	 */
	private char toUppercase(char letter) {
		if (letter >= 'a' || letter <= 'z')
			return Character.toUpperCase(letter);
		return letter;
	}

	/**
	 * get index of letter in ALPHABET
	 * 
	 * @param letter
	 * @return 0-25 || -1(No Exist)
	 */
	private int alphabetNum(char letter) {
		for (int i = 0; i < ALPHABET.length; i++)
			if (ALPHABET[i] == letter)
				return i;
		return -1;
	}

	private boolean isAlphabet(char letter) {
		return ((letter >= 'a' && letter <= 'z') || (letter >= 'A' && letter <= 'Z'));
	}

	public static void main(String[] args) {
		ShiftCipher sc = new ShiftCipher();
		String letters = "ABC--18130035 Phung Minh Dat--GHJ XYZ";

		String newLetter = sc.shiftEncrypt(letters);
		System.out.println("Encrypt: " + letters + " -> " + newLetter);

		String oldLetter = sc.shiftDecrypt(newLetter);
		System.out.println("Decrypt: " + newLetter + " -> " + oldLetter);
	}

}
