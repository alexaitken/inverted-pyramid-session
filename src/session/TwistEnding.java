package session;

public class TwistEnding {

	
	public void createUser(String name, String emailAddress, String phoneNumber) {
		if (invalidUserContent(name, emailAddress, phoneNumber)) 
			throw new RuntimeException("invalid users");
		saveUser(name, emailAddress, phoneNumber);
	}

	private void saveUser(String name, String emailAddress, String phoneNumber) {
		User user = new User(name, emailAddress, phoneNumber);
		
		Database.save(user);
	}

	private boolean invalidUserContent(String name, String emailAddress, String phoneNumber) {
		if (!User.validUserContent(name, emailAddress, phoneNumber)) {
			fixSystemFromFailure(name, emailAddress, phoneNumber);
			return false;
		}
		return true;
		
	}

	private void fixSystemFromFailure(String name, String emailAddress, String phoneNumber) {
		AdminSystem.restartSystem();
		AdminSystem.sendToAdmin("Could not create user " + name + emailAddress + phoneNumber);
		
	}
	
}
