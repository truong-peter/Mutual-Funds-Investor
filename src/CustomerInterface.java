
/**
* CustomerInterface.java
* @author Peter Truong
* @authoer Tammy Dinh
* CIS 22C, Lab 5
*/
import java.io.*;
import java.text.DecimalFormat;
import java.util.Scanner;

public class CustomerInterface {
	public static void main(String[] args) throws FileNotFoundException {
		BST<MutualFundAccount> account_value = new BST<>();
		BST<MutualFundAccount> account_name = new BST<>();

		NameComparator nC = new NameComparator();
		ValueComparator vC = new ValueComparator();
		List<MutualFund> funds = new List<>();

		// String first, last, email, password, mutualName, ticker; <-to be used in lab
		// 6
		String mutualN, tick, tempPps;
		double pPs;
		char inPut;
		double cash, sharePrice, numShares, fee;

		File file = new File("D:\\Users\\peter\\eclipse-workspace\\mutual_funds_investor\\src\\mutual_funds.txt");
		Scanner input = new Scanner(file);

		MutualFund acc = null;

		// code to read in from file
		while (input.hasNextLine()) {
			mutualN = input.nextLine();
			tick = input.nextLine();
			tempPps = input.nextLine();
			pPs = Double.parseDouble(tempPps);

			acc = new MutualFund(mutualN, tick, pPs);
			funds.addLast(acc);
		}

		input.close();
		input = new Scanner(System.in);

		MutualFundAccount accBuy = null;
		int numS = 0;

		System.out.println("Welcome to Mutual Fund InvestorTrack (TM)!\n");
		while (true) {
			System.out.println("Please select from the following options:\n\nA. Purchase a Fund\nB. Sell a Fund"
					+ "\nC. Display Your Current Funds\nX. Exit\n");
			System.out.print("Enter your choice: ");
			inPut = input.next().charAt(0);
			System.out.print("\n");

			if (inPut == 'A' || inPut == 'B' || inPut == 'C') {
				switch (inPut) {

				case 'A': // Buy stock
					System.out.println("Please select from the options below:\n");
					funds.printNumberedList();

					System.out.print("Enter your choice: (1-7): ");
					funds.iteratorToIndex(input.nextInt());

					System.out.print("\nEnter the number of shares to purchase: ");
					numS = input.nextInt();
					System.out.print("\n");

					accBuy = new MutualFundAccount(funds.getIterator(), numS);

					MutualFundAccount tempp = account_name.search(accBuy, nC); // tempp references to the location of
																				// the current fund thats bought.

					if (tempp != null) { // if have duplicate
						tempp.updateShares(numS); // update the shares of both.

					} else {
						account_name.insert(accBuy, nC);
						account_value.insert(accBuy, vC);
					}
					break;

				case 'B':
					if (account_name.isEmpty()) {
						System.out.println("You don't have any funds to sell at this time.\n");
					} else {

						String fundN = "";
						String fundShare = "";
						double sellFunds = 0;
						System.out.println("You own the following mutual funds:");
						account_name.inOrderPrint();

						System.out.print("Enter the name of the fund to sell: ");
						input.nextLine();

						fundN = input.nextLine();
						System.out.print("Enter the number of shares to sell or \"all\" to sell everything: ");
						fundShare = input.nextLine();
						System.out.print("\n");

						MutualFundAccount temp = account_name.search(new MutualFundAccount(new MutualFund(fundN)), nC);
						if (fundShare.compareTo("all") == 0) { // if input equals to all
							account_name.remove(temp, nC);
							account_value.remove(temp, vC);
						} else {
							sellFunds = Double.parseDouble(fundShare);
							temp.updateShares(-sellFunds); // negative number to update

							account_value.remove(temp, vC); // removes the current node

							account_value.insert(temp, vC); // reinsert to change position of node.
						}
					}
					break;

				case 'C':
					if (account_name.isEmpty()) {
						System.out.println("You don't have any funds to sell at this time.\n");
					} else {
						int display = 0;
						System.out.println("1.Name\n2.Value\n");
						System.out.print("Enter your choice (1 or 2): ");
						display = input.nextInt();
						System.out.print("\n");
						if (display == 1) { // name
							account_name.inOrderPrint();
						} else if (display == 2) { // value
							account_value.inOrderPrint();
						} else {
							System.out.println("Invalid Choice!\n");
						}

					}
				}

			}

			else if (inPut == 'X') {
				System.out.println("Goodbye!");
				System.exit(0);
			} else {
				System.out.print("Invalid menu option. Please enter A-C or X to exit.\n\n");
			}

		}

	}
}
