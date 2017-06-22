import java.util.Scanner;
import java.io.*;

public class HelpTickets {
	private static File fileIn;
	private static File fileOut;
	private static BST tree;
	private static Warning warning = new Warning();

	/**
	 * Main method that reads and writes the data into a file.
	 * @param args data input and output names.
	 * @throws IOException if file can't be written or read.
	 */
	public static void main(String[] args) throws IOException {
		tree = new BST();
		read();
	}
	
	/**
	 * Reads the file name and inputs the data into a list.
	 * @param name of file.
	 * @throws IOException if file can't be written or read.
	 */
	public static void read() throws IOException {
		Scanner scan = new Scanner(System.in);
		String tmp;
		
		while(scan.hasNextLine()){
			tmp = scan.nextLine();
			Scanner scanLine = new Scanner(tmp);
			
			int id = 0;
			String priorityS = "";
			String idS = "";
			int priority = 0;
			String info = "";
			String function = null;

			if(scanLine.hasNext()) {
				function = scanLine.next();
				if(function.equals("+")) {
					//Add
					if(scanLine.hasNext()) {
						priorityS = scanLine.next();
						if(!isInt(priorityS))
							System.out.print(function + " " + priorityS +  "\n" + warning.invalidPriority(priorityS));
						else {
							try {
								priority = Integer.parseInt(priorityS);
								int idValue = tree.Insert(priority);
								info = function + " " + priority + "\n" + "id = " + idValue + "\n";
							} catch (Warning w) {
								info = function + " " + priority + "\n" + warning.priorityExists(priority);
							}
							System.out.print(info);
						} 
					}
				} else if(function.equals("-")) {
					//Remove
					if(scanLine.hasNext()) {
						idS = scanLine.next();
						if(!isInt(idS))
							System.out.print(function + " " + idS +  "\n" + warning.invalidId(idS));
						else {
							try{
								id = Integer.parseInt(idS);
								int pos = tree.Getposition(id);
								int x = tree.removebyid(id);
								info = function + " " + id + "\n" + x + ", pos = " + pos + "\n";
							} catch(Warning w) {
								if(!tree.isEmpty())
									info = function + " " + id + "\n" + warning.removeDoesNotExist(id);
								else
									info = function + " " + id + "\n" + warning.queueEmpty();
							}
							System.out.print(info);
						}
					}
				} else if(function.equals("*")) {
					//Remove Max
					try {
					Node max = tree.removeMax();
					info = function + "\n" + "id = " + max.getid() + ", " + max.getPriority() + "\n";
					} catch (Warning w) {
						info = function + "\n" + warning.queueEmpty();
					}
					System.out.print(info);
				} else if(function.equals("?")) {
					//Get Position
					if(scanLine.hasNext()) {
						idS = scanLine.next();
						if(!isInt(idS))
							System.out.print(function + " " + idS +  "\n" + warning.invalidId(idS));
						else {
							try {
								id = Integer.parseInt(idS);
								int pos = tree.Getposition(id);
								info = function + " " + id +  "\n" + "pos = " + pos + "\n";
							} catch(Warning w) {
								info = function + " " + id + "\n" + warning.removeDoesNotExist(id);
							}
							System.out.print(info);
						}
					}
				} else {
					while(scanLine.hasNext()){
						idS += scanLine.next();
					}
					System.out.print(function + " " + idS + warning.invalidCommand(function));
				}
			}

			scanLine.close();
		}
		scan.close();
	}
	
	/**
	 * Checks if string is int
	 * @param str string
	 * @return true or false
	 */
	public static boolean isInt(String str)  
	{  
		try  
		{  
			int i = Integer.parseInt(str);  
		}	  
		catch(NumberFormatException nfe)  
		{  
			return false;  
		}  
		return true;  
	}
	
	/**
	 * Reads the file name and outputs the list into a file.
	 * @param name of file.
	 * @throws IOException if file can't be written or read.
	 */
	public static void write(PrintWriter printWriter, String info) throws IOException{
		printWriter.print(info);
	}

}
