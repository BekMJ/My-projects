import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.util.List;
/**
 * @author Bek
 * Blocks Object containing Block number and its miner.
 *
 */
public class Blocks { 
	
	/**
	 * Block number.
	 */
	private int number; 
	/**
	 * ID of the miner who updated the block.
	 */
	private String miner; 
	/**
	 * arraylist containing all the blocks objects read from the file.
	 */
	private static ArrayList<Blocks> blocks; 
	
	private final static String DELIMETER = ",";
	
	
	/**
	 * This constructor simply creates an empty Blocks object.
	 */
	public Blocks() { 
		
		
		 number = 0;
		 miner = null;
		 
		} 
	
	/**
	 * @param number block number
	 * This one constructs a block object, initialize the block number with the given number, and also initializes the miner null (keep it empty).
	 */
	public Blocks(int number) {
		
	
		 
		this.number = number;
		miner = null;
	} 
	
	/**
	 * @param number block number
	 * @param miner ID of the miner who updated the block
	 * This one constructs a block object, initialize the block number with the given number, and also initializes the miner with the given miner.
	 */
	public Blocks(int number, String miner) {
		this.number = number;
		this.miner = miner;
	} 
	
	/**
	 * @return returns the number of the block object
	 */
	public int getNumber() {
		return this.number;
	} 
	
	/**
	 * @return returns the miner of the block object
	 */
	public String getMiner() {
		return this.miner;
	} 
	
	/**
	 * @return returns the copy of the arraylist containing Blocks read from the file
	 */
	public static ArrayList<Blocks> getBlocks() {
		ArrayList<Blocks> blockCopy = new ArrayList<Blocks> (blocks.size());
		for (int i =0; i < blocks.size(); i++) {
			blockCopy.add(blocks.get(i));
		}
		return blockCopy;
	} 
	
	/**
	 * print to output the number of unique miners in the data, 
	 * and its miner address and the frequency at which it appears. 
	 */
	public static void calUniqMiners() {
		int count;
		int result = 1;
		int i;
		int j;
		ArrayList <String> miners = new ArrayList<String>();
		ArrayList <Integer> freq = new ArrayList<Integer>();
		miners.add(blocks.get(0).getMiner());
		for (i = 1; i < blocks.size(); i++) {
			count = 0;
			for (j = i - 1; j >= 0; j--) {
				if (blocks.get(i).getMiner().equalsIgnoreCase(blocks.get(j).getMiner())) {
					count++;
				}
			}
			if ( count == 0) {
				result++;
				miners.add(blocks.get(i).getMiner());
			}
		}
		
		
		
		for (i = 0; i < miners.size(); i++) {
			count = 0;
			for (j=0; j <blocks.size();j++) {
				if (miners.get(i).equalsIgnoreCase(blocks.get(j).getMiner())) {
					count++;
				}
			}
			
			freq.add(count);
		}
		
		System.out.println("Number of unique Miners: " + result);
		System.out.println("");
		System.out.println("Each unique Miner and its frequency:");
		for (i = 0; i < miners.size(); i++) {
			System.out.println("Miner Address: " + miners.get(i));
			System.out.println("Miner Frequency: " + freq.get(i));
			if (i != miners.size()-1) {
			System.out.println("");
			}
		}
		
		
	} 
	
	/**
	 * @param A a Blocks object
	 * @param B a Blocks object
	 * @return returns the difference between the block numbers of two given objects
	 */
	public static int blockDiff(Blocks A, Blocks B) {
		return A.getNumber() - B.getNumber();
	} 
	
	/**
	 * @param num a given Blocks number
	 * @return returns the block object with the given number
	 */
	public static Blocks getBlockByNumber (int num) {
		
		int count = 0;
		int index = 0;
		for(int i = 0; i< blocks.size(); i++) {
			if (blocks.get(i).getNumber() == num) {
				index = i;
				count++;
			}
		}
		if (count == 0) {
			return null;
		}
		
		return blocks.get(index);
	} 
	
	/**
	 * prints Block object number and its miner
	 * if the miner is null, then only prints the number
	 * if both are empty prints the block object is empty
	 */
	public String toString() {
		String result = "";
		if (this.number == 0 && this.miner == null) {
			result = "Empty Block";
		}
		else if (this.number != 0 && this.miner == null) {
			result = "Block Number: " + String.valueOf(number);
		}
		else if (this.number != 0 && this.miner != null){
			result = "Block Number: " + String.valueOf(number) + " Miner Address: " + miner;
		}
		return result;
	} 
	
	/**
	 * @param filename the name of the file that you are reading
	 * @throws IOException an exception that might occur from reading or editing data
	 * reads from the given file into an arraylist
	 */
	public static void readFile(String filename) throws IOException {
		String [] column;
	
		blocks = new ArrayList<Blocks>();
		Scanner scn = new Scanner (new FileReader (filename));
		;
		while(scn.hasNextLine()) {
			column = scn.nextLine().split(DELIMETER);
			Blocks a = new Blocks(Integer.parseInt(column[0]), column[9]);
			blocks.add(a);
			
		}
		scn.close();
	} 
	
}