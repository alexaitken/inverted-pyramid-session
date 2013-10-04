import java.util.ArrayList;
import java.util.List;

public class SlapExamples {

  public void addPeople(List<Person> people) {
    for (Person person : people) {
      placeInNameRegistry(person);
      updatePhoneSystem();
      sendWelcomeEmail(person);
    }
  }

  public List<Person> addPeopleWithFailures(List<Person> people) {
    List<Person> failedPeople = new ArrayList<>();
    for (Person person : people) {
      try {
        placeInNameRegistry(person);
        notifyPhoneSystem();
        sendWelcomeEmail(person);
      } catch (Exception e) {
        failedPeople.add(person);
      }
    }
    return failedPeople;
  }
  
  public boolean addPersonWithFailures(Person person) {
    try {
      placeInNameRegistry(person);
      notifyPhoneSystem();
      sendWelcomeEmail(person);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
  
  
  public boolean addPersonWithFailures2(Person person) {
    try {
      addPerson(person);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
  
  public void addPerson(Person person) {
    placeInNameRegistry(person);
    notifyPhoneSystem();
    sendWelcomeEmail(person);
  }

  public Person parseName(String rawName) {
    String[] namePieces = rawName.split(" ");
    String firstName, lastName;

    if (namePieces.length < 2)
      throw new IllegalArgumentException("We must have 2 names");

    firstName = namePieces[0];
    lastName = namePieces[namePieces.length - 1];

    return new Person(firstName, lastName);
  }

  public void removePerson(Person person) {
    removeFromNameRegistry(person);
    notifyPhoneSystem();
    sendFarewellEmail(person);
  }

  private void sendFarewellEmail(Person person) {
  }

  private void removeFromNameRegistry(Person person) {
  }

  private void sendWelcomeEmail(Person person) {
  }

  private void updatePhoneSystem() {
  }

  private void placeInNameRegistry(Person person) {
  }

  private void notifyPhoneSystem() {
  }
}
