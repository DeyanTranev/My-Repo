package phone;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PhoneBookView{

	Scanner input;
	private boolean isRunning;
	PhoneBook book;

	public PhoneBookView() {
		input = new Scanner(System.in);
		isRunning = true;
		book = new PhoneBook();
	}

	public void startPhoneBook(){

		try {
			book.getPhonesFromFile();
		} catch (FileNotFoundException | PhoneException e) {
			e.printStackTrace();
		}

		menu();
	}

	private int selectOption() {

		Util.printLine("Select Option Menu: ");
		int choose = 0;
		try {
			choose = input.nextInt();
		} catch (InputMismatchException e) {
			Util.printNewLine("Oops!! Please enter only integral numbers");
			Util.printNewLine(input.next() + " was not valid input.");
		}
		return choose;
	}

	private void menu() {
		isRunning = true;


		while(isRunning) {
			visualMenu();
			switch(selectOption()) {
				case 1: showAllPhones(); break;
				case 2: showFiveMostOutgoingCalls(); break;
				case 3: addPhoneNumber(); break;
				case 4: removePhoneNumber(); break;
				case 5: exit(); break;
				default: Util.printNewLine("Invalid Options Menu. Please input correct Option."); break;
			}

		}

	}

	private void visualMenu() {
		Util.printNewLine("");
		Util.printNewLine(" ================================================");
		Util.printNewLine("||   Welcome to Phone Book. Select an Option:   ||");
		Util.printNewLine("||                                              ||");
		Util.printNewLine("||   1) -        Show All Phones.               ||");
		Util.printNewLine("||   2) - Show 5 Most Outgoing Calls Numbers.   ||");
		Util.printNewLine("||   3) -   Add Phone Number To Phone Book.     ||");
		Util.printNewLine("||   4) - Remove Phone Number From Phone Book.  ||");
		Util.printNewLine("||   5) -        Exit Phone Book                ||");
		Util.printNewLine("||                                              ||");
		Util.printNewLine(" ================================================");
	}

	/**
	 * Method which Shows all Phones in Phone Book.
	 */
	private void showAllPhones(){
		book.showAllNumbers();
	}

	/**
	 * Method which shows Five most Outgoing calls in Phone Book.
	 */
	private void showFiveMostOutgoingCalls() {

		try {
			book.showTopFiveOutgoingCallsPhones();
		}
		catch(FileNotFoundException | PhoneException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Method which add Number to the Phone Book.
	 * @throws PhoneException
	 */
	private void addPhoneNumber(){

		try {
			String name = null;
			String phone = null;
			int calls = -1;
			try {
				Util.printNewLine("Input Phone Name: ");
				name = input.next();
			}
			catch(InputMismatchException e) {
				e.printStackTrace();
			}

			try {
				Util.printNewLine("Input Phone: ");
				phone = input.next();
			}
			catch(InputMismatchException e) {
				e.printStackTrace();
			}

			Util.printNewLine("Input outgoing calls: ");

			try {
				calls = input.nextInt();
			} catch (InputMismatchException e) {
				Util.printNewLine("Oops!! Please enter only integral numbers");
				Util.printNewLine(input.next() + " was not valid input.");
			}

			if(name != null && phone != null && calls >= 0) {
				book.addPhoneInFile(new Phone(name, phone, calls));
			}
			else {
				Util.printErrLine("Wrong Number!");
			}
		}

		catch(IOException | PhoneException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Method which remove number from the Phone Book.
	 */
	private void removePhoneNumber() {
		Util.printNewLine("Select existing name to remove from Phone Book: ");
		String name = input.next();
		book.deletePhoneByName(name);
	}

	/**
	 * Method which terminates the program.
	 */
	private void exit() {
		if(isRunning == true) {
			isRunning = false;
		}
	}


}
