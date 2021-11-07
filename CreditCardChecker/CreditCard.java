/**
 * @author Timotej Kovacka
 * Object representing credit card
 */
public class CreditCard {
	private String cardNumber;
	
	/**
	 * Constructor of credit card setting object param to the following argument
	 * @param cardNumber
	 */
	public CreditCard(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	/**
	 * Performs Luhn Algorithm
	 * @return
	 */
	private boolean checkDigit() {
		char[] charArray = this.cardNumber.toCharArray();
		int checkSum = 0;
		for(int i=charArray.length-2;0<=i;i-=2) {
			int value = Character.getNumericValue(charArray[i]);
			value *= 2;
			if (value > 9) {
				value -= 9;
			}
			charArray[i] = Character.forDigit(value, 10);
		}
		for (int i=0;i<charArray.length;i++) {
			checkSum+= Character.getNumericValue(charArray[i]);
		}
		if( checkSum%10 == 0) return true;
		else return false;
	}
	
	/**
	 * Accessor which finds out the name of credit card issuer from its number
	 * @return
	 */
	private String getCompanyName() {
		char[] charArray = this.cardNumber.toCharArray();
		for (int i=0; i<charArray.length; i++) {
			if (!Character.isDigit(charArray[i])) return "Invalid";
		}
		int firstTwoDigits = Integer.parseInt(this.cardNumber.substring(0, 2));
		int firstFourDigits = Integer.parseInt(this.cardNumber.substring(0, 4));
		if(Character.getNumericValue(charArray[0]) == 4) return "Visa";
		if(firstTwoDigits == 62) return "UnionPay";
		if(firstTwoDigits == 34 || firstTwoDigits == 37) return "American Express";
		if((51 <= firstTwoDigits && firstTwoDigits <= 55) || (2221 <= firstFourDigits && firstFourDigits <= 2720)) return "MasterCard";
		return "Invalid";
	}
	
	/**
	 * Checks if the credit card number length corresponds to its issuer
	 * @param companyName
	 * @return
	 */
	private boolean checkLength(String companyName) {
		int CCNL = this.cardNumber.length();
		if(companyName.equals("Visa")) { if(CCNL == 13 || CCNL == 16 || CCNL == 19) return true; }
		if(companyName.equals("American Express")) { if(CCNL == 15) return true; }
		if(companyName.equals("MasterCard")) { if(CCNL == 16) return true; }
		if(companyName.equals("UnionPay")) { if(CCNL == 16 || CCNL == 17 || CCNL == 18 || CCNL == 19) return true; }
		return false;
	}
	
	/**
	 * Tests validity of credit card
	 * @return
	 */
	public boolean test() {
		String companyName = getCompanyName();
		if (companyName.equals("Inavalid")) return false;
		if (checkLength(getCompanyName()) && checkDigit()) return true;
		return false;
	}
}
