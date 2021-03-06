import java.util.Scanner;
public class Sieve_of_Eratosthenes {
	public static void main(String[] args) {
		boolean finished = false;
		Scanner userInputScanner = new Scanner(System.in);
		while (!finished) {
			System.out.print("Enter int >= 2 (or type 'quit' to exit): ");
			if (userInputScanner.hasNextInt()) {
				int maxNum = userInputScanner.nextInt();
				String clcLine = userInputScanner.nextLine();
				if (maxNum >= 2) {
					System.out.println(nonCrossedOutSubseqToString(sieve(maxNum)));
				} else {
					System.out.println("The number entered must be greater than or equal to 2");
				}
			} else if (userInputScanner.hasNext()) {
				String userInput = userInputScanner.next();
				String clcLine = userInputScanner.nextLine();
				if (userInput.equals("quit")) {
					finished = true;
				} else {
					System.out.println("Sorry, the input was not recognised.");
				}
			} else {
				System.out.println("You must enter a number or type 'quit' to exit.");
			}
		}
		userInputScanner.close();
		System.out.println("Goodbye...");
	}

	public static String[] createSequence(int maxNumber) {
		String[] numArray = new String[maxNumber - 1];
		for (int index = 0; index < maxNumber - 1; index++)
			numArray[index] = Integer.toString(index + 2);
		return numArray;
	}

	public static String sequenceToString(String[] arrayToPrint, String separator, boolean printString) {
		String stringToPrint = "";
		for (int index = 0; index < arrayToPrint.length; index++) {
			stringToPrint += arrayToPrint[index] + ((index != arrayToPrint.length - 1)?separator:"");
		}
		if(printString)
			System.out.println(stringToPrint);
		return(stringToPrint);
	}

	public static String[] convertToStringArray(int[] numArray) {
		String[] stringArray = new String[numArray.length];
		for (int index = 0; index < numArray.length; index++)
			stringArray[index] = Integer.toString(numArray[index]);
		return stringArray;
	}

	public static void crossOutHigherMultiples(String[] numArray, int numToCross, int indexOfNum) {
		for (int index = indexOfNum + 1; index < numArray.length; index++) {
			try {
				int currentNum = Integer.parseInt(numArray[index]);
				if (currentNum % numToCross == 0)
					numArray[index] = "[" + numArray[index] + "]";
			} catch (NumberFormatException e) {
			} 
		}
	}

	public static String[] sieve(int maxNum) {
		String[] numArray = createSequence(maxNum);
		for (int index = 0; index < numArray.length; index++) {
			try {
				crossOutHigherMultiples(numArray, Integer.parseInt(numArray[index]), index);
			} catch (NumberFormatException e) {
			}
		}
		//sequenceToString(numArray, ", ", true);
		return numArray;
	}

	public static String nonCrossedOutSubseqToString(String[] arrayToConvert) {
		int numberValid = 0;
		for (int index = 0; index < arrayToConvert.length; index++) {
			try {
				Integer.parseInt(arrayToConvert[index]);
				numberValid++;
			} catch (NumberFormatException e) {
			}
		}	
		String[] primeArray = new String[numberValid];
		for (int index = 0, primeArrayIndex = 0; primeArrayIndex < primeArray.length; index++) {
			try {
				primeArray[primeArrayIndex] = Integer.toString(Integer.parseInt(arrayToConvert[index]));
				primeArrayIndex++;
			} catch (NumberFormatException e) {
			} 
		}
		
		return sequenceToString(primeArray, ", ", false);
		
	}
}

