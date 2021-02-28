package first;

import java.io.IOException;
import java.util.ArrayList;

//Yiyi Zhou; NUID: 001051305
public class passwordEntry {
	public static void main(String[] args) throws IOException {
		new passwordEntry(); 
	}
	public passwordEntry() throws IOException{
		char entry = ' ';
		boolean done = false;
		System.out.println("Enter choice (1,2,3,4) or 'q' to quit, then press <enter>");
		ArrayList<String> passwordHistory = new ArrayList<String>();
		String specialPassword = null;

//using loop to accept keyboard input until done to make a choice
		while(!done) {
			entry = (char) System.in.read();
			String password = "";
			
			if(entry == '\n' || entry == '\r') continue;
			
			switch(entry) {			
///print sum of ASCII values of input password  
			case '1':
				password = getPassword();
				int asciiSum = calculateString(password);
				System.out.println("The sum of ASCII values of the password:" + asciiSum);
				//keep track of password history when not empty				
				if(password != "") {
					passwordHistory.add(password);
					password = "";
				}
				System.out.println(passwordHistory);
				break;
				
//print each character's value of input password				
			case '2':
				password = getPassword();
				//check if it‘s special password
				if (password.equals(specialPassword)) {
					System.out.println("Special password matched!");
					passwordHistory.add(password);
					password = "";
					break;
				}				
				char wordArray [] = password.toCharArray();
				for (int i=0; i<wordArray.length; i++) {
					char c = wordArray[i];
					System.out.println("The ASCII values of character " + c + ": " + (int)c);
				}
				if(password != "") {
					passwordHistory.add(password);
					password = "";
				}
				break;
				
//print the last three attempts of all password entries
			case '3':
				int start = Math.max(0, passwordHistory.size() -3);
				int index = 1;
				System.out.println("Last three passwords attempts are: ");
				for(int i=start; i<passwordHistory.size(); i++) {
					String word = passwordHistory.get(i);
					System.out.println(index + "." + word + "; The sum of ASCII values of "+ word + "is "+ calculateString(word));
					index ++;
				}
				break;
				
// only after invoking choice 4, special password is created			
			case '4':
				specialPassword = "yiyizh";
				System.out.println("Special password is created now: "+specialPassword);
				break;
				
//quit
			case 'q':
				System.out.println("exit");
				done = true;
				break;
				
//	Any other input will output invalid choice
			default:
				System.out.println("Invalid choice, please choose from (1,2,3,4) or 'q' for quit");
				break;	
			}
		}
	}
	
	//calculate the ASCII sum of string
	Integer calculateString(String password) throws IOException {
		int asciiSum = 0;
		for (int i=0; i<password.length(); i++) {
			char character = password.charAt(i);
			asciiSum += (int) character;
		}
		return asciiSum;
	}
	
	//read keyboard input for password
	String getPassword() throws IOException {
		System.in.skip(80);
		System.out.println("Please enter a password:");
		String password = "";
		boolean done = false;
		char inChar;
		while(!done){ 
		  inChar = (char) System.in.read();
			if (inChar == '\n' || inChar == '\r') {
				done = true;
				continue; 
			}
		  password += inChar;
		}
		return password;
	}
	
}
