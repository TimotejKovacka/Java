
/**
 * @author Timotej Kovacka
 * Tests for various cases of credit cards
 */
public class Main {

	public static void main(String[] args) {
		CreditCard c = new CreditCard("371449635398431");
		CreditCard c1 = new CreditCard("371449635398432");
		CreditCard c2 = new CreditCard("5444009197548666");
		CreditCard c3 = new CreditCard("1111111");
		CreditCard c4 = new CreditCard("51123");
		CreditCard c5 = new CreditCard("2223000048400011");
		CreditCard c6 = new CreditCard("4917610000000000003");
		CreditCard c7 = new CreditCard("6223050272365522");
		CreditCard c8 = new CreditCard("62230502a2365522");
		System.out.println(c.test());
		System.out.println(c1.test());
		System.out.println(c2.test());
		System.out.println(c3.test());
		System.out.println(c4.test());
		System.out.println(c5.test());
		System.out.println(c6.test());
		System.out.println(c7.test());
		System.out.println(c8.test());
	}

}
