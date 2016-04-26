package phone;



public class Phone implements Comparable<Phone>{

	private String name;
	private String phone;
	private int outgoingCalls;
	

	public Phone(String name, String phone, int outgoingCalls) throws PhoneException {
		setName(name);
		setPhone(phone);
		setOutgoingCalls(outgoingCalls);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name != null && !name.equals("")) {
			this.name = name;
		}
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		if (phone != null && !phone.equals("")) {
			this.phone = phone;
		}
	}

	public int getOutgoingCalls() {
		return outgoingCalls;
	}

	public void setOutgoingCalls(int outgoingCalls) throws PhoneException {
		if(outgoingCalls >= 0) {
		this.outgoingCalls = outgoingCalls;
		}
		else {
			throw new PhoneException("Wrong input for call!");
		}
	}

	@Override
	public int compareTo(Phone o) {
		if(this.getName().compareTo(o.getName()) != 0) {
			return this.getName().compareTo(o.getName());
		}
		else {
			return this.getPhone().compareTo(o.getPhone());
		}
	}

	
	
}
