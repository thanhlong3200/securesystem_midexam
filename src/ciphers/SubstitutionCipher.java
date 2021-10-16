package ciphers;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class SubstitutionCipher implements Cipher{
	private final List<Character> ALPHABET = Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I' ,'J', 'K', 'L',
														'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');
	private Map<Character, Character> key;
	
	private void generateKey(){
		List<Character> alphabetClone = new ArrayList<Character>(ALPHABET);
		List<Character> values = new ArrayList<Character>();
		
		while(!alphabetClone.isEmpty()) {
			Random random = new Random();
			int index = random.nextInt(alphabetClone.size());
			
			values.add(alphabetClone.get(index));
			alphabetClone.remove(index);
		}
		
		key = new HashMap<Character, Character>();
		for (int i = 0; i < values.size(); i++) {
			key.put(ALPHABET.get(i), values.get(i));
		}
	}
	
	public Map<Character, Character> getKey(){
		if (key==null) {
			this.generateKey();
		}
		return this.key;
	}
	
	private Character getKeyOfValue(Character value) {
		for (Map.Entry<Character, Character> entry : key.entrySet()) {
			if (value.equals(entry.getValue())) {
				return entry.getKey();
			}
		}
		return value;
	}
	
	@Override
	public String encrypt(String plainText) {
		char[] chars = plainText.toCharArray();
		StringBuilder cipherText = new StringBuilder();
		for (char c : chars) {
			if (ALPHABET.contains(c)) {
				c = key.get(c);
			}
			cipherText.append(c);
		}
		return cipherText.toString();
	}
	
	@Override
	public String decrypt(String cipherText) {
		char[] chars = cipherText.toCharArray();
		StringBuilder plainText = new StringBuilder();
		for (char c : chars) {
			plainText.append(getKeyOfValue(c));
		}
		return plainText.toString();
	}
	
	public static void main(String[] args) {
		SubstitutionCipher substitutionCipher = new SubstitutionCipher();
		Map<Character, Character> key  = substitutionCipher.getKey();	
		
		System.out.println("Key - Value");
		for (Map.Entry<Character,Character> entry : key.entrySet()) {
			System.out.print(entry.getKey() + " ");
		}
		System.out.println();
		for (Map.Entry<Character,Character> entry : key.entrySet()) {
			System.out.print(entry.getValue() + " ");
		}
		System.out.println();
		
		
		String plainText = "NGUYEN THANH LONG";
		System.out.println("PlainText:  " + plainText);
		
		String encyptText = substitutionCipher.encrypt(plainText);
		System.out.println("EncyptText: " + encyptText);
		
		String decyptText = substitutionCipher.decrypt(encyptText);
		System.out.println("DecyptText: " + decyptText);
	}
}
