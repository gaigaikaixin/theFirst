package com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Requests {


	HttpURLConnection connection;

	public Requests(String url) throws IOException {
		// TODO Auto-generated constructor stub


		this.connection = (HttpURLConnection) (new URL(url)).openConnection();

	}

	public String getContext() throws IOException {

		this.connection.connect();

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				this.connection.getInputStream()));

		String line;

		String textString = "";

		while ((line = reader.readLine()) != null) {
			textString += line;

		}

		this.connection.disconnect();
		
		return textString;

	}

	public static void main(String[] args) throws IOException {

		final String urlString = "http://www.15tianqi.com/beijing/";

		Requests requests = new Requests(urlString);

		System.out.print(requests.getContext());

	}
}
