package lab6;

public class BadTransactionException extends Exception {

	public int amountNumber;
	public BadTransactionException(int badAmountNumber) {
		super("Invalid amount number: " + badAmountNumber);
		// TODO Auto-generated constructor stub
		amountNumber = badAmountNumber;
	}


}
