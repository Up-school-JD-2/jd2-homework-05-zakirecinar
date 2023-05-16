import java.util.Random;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
		while(true) {
		Scanner input=new Scanner(System.in);
		try {
			System.out.print("Enter payment amount: ");
			String payAmount = input.next();
			 if (!validateAmount(payAmount)) {
			 throw new InvalidAmountException("payment must be in the correct format (no negative and double values)");
			 }
			 
			System.out.print("Enter card number: ");
			String cardNumber = input.next();
			if (!isValidCardNumber(cardNumber)) {
		        throw new InvalidCardNumberException("Card Number is invalid. Must be 16 characters and cannot contain letters.");
		      }
			System.out.print("Enter expiration year: ");
			String expirationYear = input.next();
			System.out.print("Enter expiration month: ");
			String expirationMonth = input.next();
			if(!isValidExpirationDate(expirationYear,expirationMonth)) {
				throw new InvalidExpirationDateException("Invalid Expiration Date Format.");
			}
			System.out.print("Enter security code: ");
			String securityCode = input.next();
			if (!isValidSecurityCode(securityCode)) {
		        throw new InvalidSecurityCodeException("Security Code is invalid. Must be 3 characters and cannot contain letters.");
		      }
			pay();
			break;
		}
		catch (InvalidAmountException | InvalidCardNumberException | InvalidExpirationDateException | InvalidSecurityCodeException e) {
			System.out.println("Payment failed: " + e.getMessage());
			
			}
		catch (SystemNotWorkingException e) {
			System.out.println("Payment failed due to system error: " + e.getMessage());
			System.out.println("Retrying payment...");
			try {
				pay();
			} catch (Exception ex) {
				System.out.println("Payment failed after retry: " + ex.getMessage());
			}
		}
		}
		

		}

	private static boolean validateAmount(String payAmount) {
		double amount;
		try {
		amount = Double.parseDouble(payAmount.replace(",", "."));//if the user uses a comma, convert it to a point
		}catch (NumberFormatException e) {
	        return false; 
	    }
		//can't input negative number
		if (amount < 0) {
			return false;
		}
		// can't input double number
		if (amount % 1 != 0) {
			return false;
		}
		return true;
	}
	
	private static boolean isValidCardNumber(String cardNumber) {
	    if (cardNumber.length() != 16) {
	      return false;
	    }
	    for (char c : cardNumber.toCharArray()) {
	        if (!Character.isDigit(c)) {
	          return false;
	        }
	      }
	      return true;
	    }
	
     private static boolean isValidExpirationDate(String expirationYear,String expirationMonth) {
    		int year, month;
    		year = Integer.parseInt(expirationYear);
    		month = Integer.parseInt(expirationMonth);
    		 
    		//expirate date must bigger than 2023 year and between 1 and 12 month
    		if (year < 2023 || month < 1 || month > 12) {
    			return false;
    		}
    		return true;
    	}
     private static boolean isValidSecurityCode(String securityCode) {
 	    if (securityCode.length() != 3) {
 	      return false;
 	    }
 	    for (char c : securityCode.toCharArray()) {
 	        if (!Character.isDigit(c)) {
 	          return false;
 	        }
 	      }
 	      return true;
 	    }
 	private static void pay() throws SystemNotWorkingException {
		Random random = new Random();
		// I created random number between 0-100
		int randomNumber = random.nextInt(101);
		if (randomNumber > 75) {
			throw new SystemNotWorkingException("Payment system is not working");
		}
		
		System.out.println("Payment processed successfully");
		
	}
     }


