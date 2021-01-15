package logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MapCode {
	
	private char[][] mapCode;
	private String url;
	
	public MapCode(String url) {
		
		setUrl(url);
		mapCode = new char[25][30];
		
		ReadMapFile();
		
	}
	
	public void ReadMapFile() {
		
	    try {
	    	
	    	InputStream inputStream = ClassLoader.getSystemResourceAsStream(url);
			InputStreamReader streamReader = new InputStreamReader(inputStream, "UTF-8");
			BufferedReader in = new BufferedReader(streamReader);
			
			int row = 0;
			for (String line; (line = in.readLine()) != null;) {
				for (int col = 0; col < 30; col++) {
	    			mapCode[row][col] = line.charAt(col);
	    		}
				row++;
			}
	      
	    } catch (IOException e) {
	      System.out.println("Error: Map file not found.");
	    }

	}

	public char[][] getMapCode() {
		return mapCode;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
