package com.org.stock;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
public class PortfolioDetails 

{
	private Stock stock1,stock2,stock3,stock4;
	private double balance;
	
	public PortfolioDetails(){
		
	}

	public Stock getStock1() {
		return stock1;
	}

	public void setStock1(Stock stock1) {
		this.stock1 = stock1;
	}

	public Stock getStock2() {
		return stock2;
	}

	public void setStock2(Stock stock2) {
		this.stock2 = stock2;
	}

	public Stock getStock3() {
		return stock3;
	}

	public void setStock3(Stock stock3) {
		this.stock3 = stock3;
	}

	public Stock getStock4() {
		return stock4;
	}

	public void setStock4(Stock stock4) {
		this.stock4 = stock4;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	DecimalFormat formattt = new DecimalFormat("#0.00");
	
	// Display stocks Positions
	public void displayPosition() 
	{
		System.out.printf("%-11s%-11s %-15s%-15s %-14s%s\n", "Symbol", "Shares", "Last Price", "Market Value", "Change", "% Portfolio");
		System.out.printf("%-14s%-10s %-15s%-13s %-16s%s\n", stock1.getN(), stock1.getCurNumShares(), formattt.format(stock1.getCurSharePrice()), formattt.format(stock1.curMarketVal()), formattt.format(((stock1.curMarketVal()) - (stock1.initMarketVal()))), formattt.format((stock1.curMarketVal() / totalValAcc())*100));
		System.out.printf("%-14s%-10s %-15s%-13s %-16s%s\n", stock2.getN(), stock2.getCurNumShares(), formattt.format(stock2.getCurSharePrice()), formattt.format(stock2.curMarketVal()), formattt.format(((stock2.curMarketVal()) - (stock2.initMarketVal()))), formattt.format((stock2.curMarketVal() / totalValAcc())*100));
		System.out.printf("%-14s%-10s %-15s%-13s %-16s%s\n", stock3.getN(), stock3.getCurNumShares(), formattt.format(stock3.getCurSharePrice()), formattt.format(stock3.curMarketVal()), formattt.format(((stock3.curMarketVal()) - (stock3.initMarketVal()))), formattt.format((stock3.curMarketVal() / totalValAcc())*100));
		System.out.printf("%-14s%-10s %-15s%-13s %-16s%s\n", stock4.getN(), stock4.getCurNumShares(), formattt.format(stock4.getCurSharePrice()), formattt.format(stock4.curMarketVal()), formattt.format(((stock4.curMarketVal()) - (stock4.initMarketVal()))), formattt.format((stock4.curMarketVal() / totalValAcc())*100));
	
		
		
	}

	public double totalValAcc() {
		double totalvalacc = stock1.curMarketVal()+stock1.curMarketVal()+stock1.curMarketVal()+stock1.curMarketVal()+getBalance();
		return totalvalacc;
	}
	
		public void updcurrPrice() throws IOException, InterruptedException
	{
		stock1.setCurSharePrice(GetPrice.getPrice(stock1.getN()));
		stock2.setCurSharePrice(GetPrice.getPrice(stock2.getN()));
		stock3.setCurSharePrice(GetPrice.getPrice(stock3.getN()));
		stock4.setCurSharePrice(GetPrice.getPrice(stock4.getN()));
		
		
	}
	
	public void chngPosition(int choice,int numofShares)
	{
		if(choice==1) {
			this.stock1.changeCurrentShares(numofShares);
		    balance += -numofShares * stock1.getCurSharePrice();
		}
		else if(choice==2) {
			this.stock2.changeCurrentShares(numofShares);
		    balance += -numofShares * stock2.getCurSharePrice();
		}else if(choice==3) {
			this.stock3.changeCurrentShares(numofShares);
			balance += -numofShares * stock3.getCurSharePrice();
		}else if(choice==4) {
			this.stock4.changeCurrentShares(numofShares);
			balance += -numofShares * stock4.getCurSharePrice();
		}
	}
	
  // saving portfolio to csv file	
	public void savePortfolio(String fileName) {
		Stock firstStock = new Stock(stock1.getN(),stock1.getCurNumShares(),0, stock1.getCurSharePrice(), balance);
		Stock secondStock = new Stock(stock2.getN(), stock2.getCurNumShares(), 0, stock2.getCurSharePrice(), balance);
		Stock thirdStock = new Stock(stock3.getN(), stock3.getCurNumShares(), 0, stock3.getCurSharePrice(), balance);
		Stock fourthStock = new Stock(stock4.getN(), stock4.getCurNumShares(), 0, stock4.getCurSharePrice(), balance);
		
		List<Stock> stocks = new ArrayList<Stock>();
		stocks.add(firstStock);
		stocks.add(secondStock);
		stocks.add(thirdStock);
		stocks.add(fourthStock);
		
		FileWriter fileWriter = null;
	
		try {
			fileWriter = new FileWriter(fileName);
			
			for (Stock stock : stocks) {
				fileWriter.append(stock.toCsv());
			}
		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter!");
			e.printStackTrace();
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing fileWriter");
				e.printStackTrace();
			}
		}
	}
	
	// Saving balance to csv file
	public void saveCashBalance(String fileName) {
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(fileName);
			fileWriter.append(String.valueOf(getBalance()));
		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter!");
			e.printStackTrace();
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing fileWriter");
				e.printStackTrace();
			}
		}
	}
	
	



	}





