package com.org.stock;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GetPrice 
{

	public static double getPrice(String n) throws IOException, InterruptedException
	{
		BigDecimal price = new BigDecimal(0);
		
		var url="http://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=MSFT&apikey=GBWASD2YGYNUM0HF";	
//		
//		var request=HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
//		
//		var client=HttpRequest.newBuilder().build();
//		
//		
//		var response=client.send()
		try
		{
			HttpClient client =HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder()
				.GET()
				.header("accept","application/json")
				.uri(URI.create(url))
				.build();
		
			HttpResponse<String> response=client.send(request, HttpResponse.BodyHandlers.ofString());
	
			System.out.println(response.body());
	
				
		
		 	
        }
		catch(Exception e) 
		{
			System.out.println("Couldn't connect to the server");
			System.exit(0);
			
		}
		return price.doubleValue();
	}
}
