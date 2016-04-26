package phone;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;


public class PhoneBook {

	private Map<String, Phone> phoneBook;

	public PhoneBook() {
		phoneBook = new TreeMap<String, Phone>();
	}

	/**
	 * add Phone object to the Map
	 * 
	 * @param phone
	 */

	public void addPhone(Phone phone) {

		if (ValidationPhoneNumber.getValidPhoneNumber(phone.getPhone())) {
			phoneBook.put(phone.getName(), phone);
		}

	}

	/**
	 * delete Phone from Map by name
	 * 
	 * @param name
	 */

	public void deletePhoneByName(String name) {

		if (name != null || phoneBook.containsKey(name)) {
			phoneBook.remove(name);
		}
	}

	public void showPhoneNumberByName(String name) {

		System.out.println(phoneBook.get(name));

	}

	/**
	 * shows all valid pairs name and phone number
	 */

	public void showAllNumbers() {

//		for (Map.Entry<String, Phone> entry : phoneBook.entrySet()) {
//			
//			Phone phone = entry.getValue();
//
//			System.out.println(phone.getName() + ": " + phone.getPhone() + " outgoing calls: " + phone.getOutgoingCalls());
//		}
		
		for (String key : phoneBook.keySet()) {
			System.out.println(phoneBook.get(key).getName() + ": " + phoneBook.get(key).getPhone() + " outgoing calls: " + phoneBook.get(key).getOutgoingCalls());
		}

	}

	/**
	 * shows the top five phones with the most outgoing calls
	 * 
	 * @throws FileNotFoundException
	 * @throws PhoneException
	 */
	public void showTopFiveOutgoingCallsPhones() throws FileNotFoundException, PhoneException {

		List<Phone> phones = new ArrayList<Phone>();
		

		
		for (String key : phoneBook.keySet()) {
			phones.add(phoneBook.get(key));
		}

		

		Collections.sort(phones, new Comparator<Phone>() {

			@Override
			public int compare(Phone obj1, Phone obj2) {

				return obj2.getOutgoingCalls() - obj1.getOutgoingCalls();
			}

		});

		int count = 0;
		for (Phone phoneCall : phones) {
			System.out.println(phoneCall.getName() + " " + phoneCall.getPhone() + " has " + phoneCall.getOutgoingCalls()
					+ " outgoing calls.");
			count++;
			if (count == 5) {
				break;
			}
		}

		
	}

	public void getPhonesFromFile() throws FileNotFoundException, PhoneException {
		File file = new File("phones.txt");

		Scanner fileReader = new Scanner(file);

		Phone phone = null;

		while (fileReader.hasNextLine()) {

			phone = getPhoneObject(fileReader);
			addPhone(phone);
		}

		fileReader.close();
	}

	/**
	 * get next phone object from file
	 * 
	 * @param fileReader
	 * @return
	 * @throws PhoneException
	 */
	private Phone getPhoneObject(Scanner fileReader) throws PhoneException {
		Phone phone;
		String line = fileReader.nextLine();
		String[] temp = line.split(" ");

		String name = temp[0];
		String number = temp[1];
		int outgoingCalls = 0;
		if (ValidationPhoneNumber.outgoingCallValidation(temp[2])) {
			outgoingCalls = Integer.parseInt(temp[2]);

		}

		phone = new Phone(name, number, outgoingCalls);
		return phone;
	}

	public void addPhoneInFile(Phone phone) throws IOException {
		if (ValidationPhoneNumber.getValidPhoneNumber(phone.getPhone())) {
			phoneBook.put(phone.getPhone(), phone);

			File file = new File("phones.txt");
			FileWriter fw = new FileWriter(file, true);

			StringBuilder sb = new StringBuilder();
			sb.append(phone.getName() + " " + phone.getPhone() + " " + phone.getOutgoingCalls() + "\n");

			fw.write(sb.toString());
			fw.flush();
			fw.close();
		} else {
			System.err.println("Wrong number!");
		}
	}

}
