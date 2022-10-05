package TDDE22.kattis;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStream;

public class FerryLoading {

	public static void main(String[] args) {

		int testCases, ferryLength, ferryCapacity, cars, carLength;

		Queue<Integer> leftBankCars;
		Queue<Integer> rightBankCars;

		Kattio io = new Kattio(System.in);

		testCases = io.getInt();

		for (int i = 0; i < testCases; i++) {

			int crossings = 0;
	
			ferryLength = io.getInt() * 100;
			cars = io.getInt();
			leftBankCars = new PriorityQueue<>();
			rightBankCars = new PriorityQueue<>();

			for (int j = 0; j < cars; j++) {
				carLength = io.getInt();
				String carBank = io.getWord();
				
				if(carLength > ferryLength) {
					continue;
				}
				if (carBank.equals("left")) {
					leftBankCars.add(carLength);
				} 
				
				if(carBank.equals("right")) {
					rightBankCars.add(carLength);
				}

			}
			

			
			while ((!leftBankCars.isEmpty()) || (!rightBankCars.isEmpty())) {
				
				
				ferryCapacity = ferryLength;
				while(!leftBankCars.isEmpty() && ferryCapacity >= leftBankCars.peek()) {
					ferryCapacity -= leftBankCars.poll();
				}
				crossings++;
				
				
				if(leftBankCars.isEmpty() && rightBankCars.isEmpty()) {
					break;
				}
				
				ferryCapacity = ferryLength;
				while(!rightBankCars.isEmpty() && ferryCapacity >= rightBankCars.peek()) {
					ferryCapacity -= rightBankCars.poll();
				}
				crossings++;

			}
			
			System.out.println(crossings);
		}
	}
}

class Kattio extends PrintWriter {
	public Kattio(InputStream i) {
		super(new BufferedOutputStream(System.out));
		r = new BufferedReader(new InputStreamReader(i));
	}

	public Kattio(InputStream i, OutputStream o) {
		super(new BufferedOutputStream(o));
		r = new BufferedReader(new InputStreamReader(i));
	}

	public boolean hasMoreTokens() {
		return peekToken() != null;
	}

	public int getInt() {
		return Integer.parseInt(nextToken());
	}

	public double getDouble() {
		return Double.parseDouble(nextToken());
	}

	public long getLong() {
		return Long.parseLong(nextToken());
	}

	public String getWord() {
		return nextToken();
	}

	private BufferedReader r;
	private String line;
	private StringTokenizer st;
	private String token;

	private String peekToken() {
		if (token == null)
			try {
				while (st == null || !st.hasMoreTokens()) {
					line = r.readLine();
					if (line == null)
						return null;
					st = new StringTokenizer(line);
				}
				token = st.nextToken();
			} catch (IOException e) {
			}
		return token;
	}

	private String nextToken() {
		String ans = peekToken();
		token = null;
		return ans;
	}
}
