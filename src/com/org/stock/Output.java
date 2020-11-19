package com.org.stock;


	public class Output
	{
		// Method used to display the menu and prompt the user for input
		public void displayMainMenu() {
			System.out.println("\n1. Edit a position");
			System.out.println("2. Update to current prices");
			System.out.println("3. Quit");
			System.out.print("\nPlease choose 1-3: ");
		}
		
		// Method used to display the menu and prompt the user for input
		public void displayStockChoices(PortfolioDetails portfolio) {
			System.out.println("\n1. " + portfolio.getStock1().getN());
			System.out.println("2. " + portfolio.getStock2().getN());
			System.out.println("3. " + portfolio.getStock3().getN());
			System.out.println("4. " + portfolio.getStock4().getN());
			System.out.print("\nPlease choose 1-4: ");
		}
		
	}


