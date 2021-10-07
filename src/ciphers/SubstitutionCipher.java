package ciphers;

public class SubstitutionCipher {

	private final char[] PLAINTEXT = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
			'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
	private final char[] CIPHERTEXT = { 'Z', 'Y', 'X', 'W', 'V', 'U', 'T', 'S', 'R', 'Q', 'P', 'O', 'N', 'M', 'L', 'K',
			'J', 'I', 'H', 'G', 'F', 'E', 'D', 'C', 'B', 'A' };

	public String substitutionEncrypt(String plaintext) {
		return substitution(plaintext, 0);
	}

	public String substitutionDecrypt(String plaintext) {
		return substitution(plaintext, 1);
	}

	public String substitution(String plaintext, int typeScrypt) {
		if (plaintext == null || plaintext.trim().isEmpty())
			throw new NullPointerException("Plaintext is null or empty");

		char[] letters = plaintext.toCharArray();
		char[] result = new char[letters.length];

		for (int i = 0; i < letters.length; i++) {
			int plaintextNum = getIndexOfText(letters[i], typeScrypt == 0 ? PLAINTEXT : CIPHERTEXT);
			result[i] = plaintextNum != -1 ? typeScrypt == 0 ? CIPHERTEXT[plaintextNum] : PLAINTEXT[plaintextNum]
					: letters[i];
		}
		return new String(result);
	}

	private int getIndexOfText(char letter, char[] arr) {
		for (int i = 0; i < arr.length; i++)
			if (arr[i] == letter)
				return i;
		return -1;
	}

	public static void main(String[] args) {
		SubstitutionCipher sc = new SubstitutionCipher();
		String letters = "ABC 18130305 GHJ XYZ";

		String newLetter = sc.substitutionEncrypt(letters);
		System.out.println("Encrypt: " + letters + " -> " + newLetter);

		String oldLetter = sc.substitutionDecrypt(newLetter);
		System.out.println("Decrypt: " + newLetter + " -> " + oldLetter);
	}
}
