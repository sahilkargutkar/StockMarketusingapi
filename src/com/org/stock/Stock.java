package com.org.stock;

public class Stock
{
	private String n;
	private int initNumShares,curNumShares;
	private double initSharePrice,curSharePrice;
	
	public String getN() {
		return n;
	}
	public void setN(String n) {
		this.n = n;
	}
	public int getInitNumShares() {
		return initNumShares;
	}
	public void setInitNumShares(int initNumShares) {
		this.initNumShares = initNumShares;
	}
	public int getCurNumShares() {
		return curNumShares;
	}
	public void setCurNumShares(int curNumShares) {
		this.curNumShares = curNumShares;
	}
	public double getInitSharePrice() {
		return initSharePrice;
	}
	public void setInitSharePrice(double initSharePrice) {
		this.initSharePrice = initSharePrice;
	}
	public double getCurSharePrice() {
		return curSharePrice;
	}
	public void setCurSharePrice(double curSharePrice) {
		this.curSharePrice = curSharePrice;
	}
	public Stock(String n, int initNumShares, int curNumShares, double initSharePrice, double curSharePrice) {
		super();
		this.n = n;
		this.initNumShares = initNumShares;
		this.curNumShares = curNumShares;
		this.initSharePrice = initSharePrice;
		this.curSharePrice = curSharePrice;
	}

	
	//Stocks Initial Market Value
	public double initMarketVal() 
	{
	    double initmarketval =initNumShares * initSharePrice;
		return initmarketval;
		
		}
	//Stocks Current Market Value
	
	public double curMarketVal()
	{
		double curmarketval = curNumShares * curSharePrice;
		return curmarketval;
	}
	//Change in current shares
	
	public void changeCurrentShares(int numShares) { 
		this.curNumShares += numShares; 
	}
	
	public String toCsv () {
		return n + "," + curNumShares + "," + curSharePrice + "\n";
	}


}
