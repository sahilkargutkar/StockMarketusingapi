package com.org.stock;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;



public class MainPortfolio {

public static void main(String[] args) throws IOException, InterruptedException {
		
		Output display = new Output();
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("Stock Market Simulation");
		
		PortfolioDetails myPortfolio = initPortfolio(keyboard);
		myPortfolio.displayPosition();
	
		DecimalFormat formatter = new DecimalFormat("#0.00");
		System.out.println("\nCash Balance: " + "$" + formatter.format(myPortfolio.getBalance()) + "\t% Portfolio: " + formatter.format((myPortfolio.getBalance() / myPortfolio.totalValAcc())*100));
		display.displayMainMenu();
		int choice = keyboard.nextInt();
		
		while (choice != 3) {
			if (choice == 1) {
				display.displayStockChoices(myPortfolio);
				int choice2 = keyboard.nextInt();
				System.out.print("How many shares? (+number to buy, -number to sell) ");
				int numofShares = keyboard.nextInt();
				myPortfolio.chngPosition(choice2, numofShares);
				myPortfolio.displayPosition();
				System.out.println("\nCash Balance: " + formatter.format(myPortfolio.getBalance()) + "\t% Portfolio: " + formatter.format((myPortfolio.getBalance() / myPortfolio.totalValAcc())*100));	
			}
			else if (choice == 2) {
				myPortfolio.updcurrPrice();
				myPortfolio.displayPosition();
				System.out.println("\nCash Balance: " + formatter.format(myPortfolio.getBalance()) + "\t% Portfolio: " + formatter.format((myPortfolio.getBalance() / myPortfolio.totalValAcc())*100));
			}
			else {
				System.out.println("Please enter a valid choice!");
			}
			display.displayMainMenu();
			choice = keyboard.nextInt();
		}
		System.out.println("\nSaving portfolio for next time...");
		myPortfolio.savePortfolio("C:\\Users\\CT\\workspace\\Investment\\SavedPortfolio.csv");
		myPortfolio.saveCashBalance("C:\\Users\\CT\\workspace\\Investment\\SavedCashBalance.csv");
		System.out.println("\nSimulation complete. Thank you!");
		keyboard.close();
	}
       //Method used to read portfolio and cash balance from csv files or display options for a first-time user 
	
public static PortfolioDetails initPortfolio(Scanner keyboard) throws IOException, InterruptedException {
		File savedPortfolio = new File("C:\\Users\\CT\\workspace\\Investment\\SavedPortfolio.csv");
		File savedCashBalance = new File("C:\\Users\\CT\\workspace\\Investment\\SavedCashBalance.csv");
		PortfolioDetails portfolio = new PortfolioDetails();
		
		if(savedPortfolio.length() == 0 || savedCashBalance.length() == 0) {
			portfolio = initNewPortfolio(keyboard);
		} 
		else {
			try {
				Scanner scanner = new Scanner(new FileReader("C:\\Users\\CT\\workspace\\Investment\\SavedPortfolio.csv"));
				scanner.useDelimiter(",");
				int counter = 0;
			
				while(scanner.hasNext())
				{
					String data = scanner.nextLine();
					String[] dataArray = data.split(",");
					String symbol = null;
					int initialShares;
					double initialPrice, currentPrice;
					symbol = dataArray[0];
					initialShares = Integer.parseInt(dataArray[1]);
					initialPrice = Double.parseDouble(dataArray[2]);
					currentPrice = GetPrice.getPrice(symbol);

					Stock stock = new Stock(symbol, initialShares, initialShares, initialPrice, currentPrice);
					
					switch(counter) {
					case 0:
						portfolio.setStock1(stock);
						break;
					case 1:
						portfolio.setStock2(stock);
						break;
					case 2: 
						portfolio.setStock3(stock);
						break;
					case 3:
						portfolio.setStock4(stock);
						break;
					default:
						break;
					}
					counter++;
				}
				scanner.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				Scanner scanner = new Scanner(new FileReader("C:\\Users\\CT\\workspace\\Investment\\SavedCashBalance.csv"));
				scanner.useDelimiter(",");
			
				while(scanner.hasNext())
				{
					double cash = scanner.nextDouble();
					portfolio.setBalance(cash);
				}
				scanner.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return portfolio;
	}
//Method used to prompt user for information on stocks
	public static PortfolioDetails initNewPortfolio(Scanner keyboard) throws IOException, InterruptedException {
		PortfolioDetails portfolio = new PortfolioDetails();
		String[] symbol = new String[4];
		int[] currentShares = new int[4];
		double[] currentPrices = new double[4];
				
		System.out.println("Please enter information for your four desired stocks");
		
		for(int i = 0; i < 4; i++) {
			System.out.println("\nStock ");
			System.out.print("Please enter the stock symbol: ");
			symbol[i] = keyboard.next();
			System.out.print("Please enter number of shares: ");
			currentShares[i] = keyboard.nextInt();
			currentPrices[i] = GetPrice.getPrice(symbol[i]);
		}
		
		System.out.print("Please enter cash balance: ");
		double cash = keyboard.nextDouble();
		
		Stock stock1 = new Stock(symbol[0], currentShares[0], 0, currentPrices[0], cash);
		Stock stock2 = new Stock(symbol[1], currentShares[1], 0, currentPrices[1], cash);
		Stock stock3 = new Stock(symbol[2], currentShares[2], 0, currentPrices[2], cash);
		Stock stock4 = new Stock(symbol[3], currentShares[3], 0, currentPrices[3], cash);
		
		portfolio.setStock1(stock1);
		portfolio.setStock2(stock2);
		portfolio.setStock3(stock3);
		portfolio.setStock4(stock4);		
		portfolio.setBalance(cash);
		
		return portfolio;
	}
	
					
					
					
}
