package py.com.ci.presentation.console;

import py.com.ci.person.boundary.PersonManager;
import py.com.ci.person.entities.Person;

import java.util.List;
import java.util.Scanner;

public class PersonUI {
    Scanner sc = new Scanner(System.in);
    Person person = new Person();
    PersonManager manager = new PersonManager();

    public static void main(String[] args) {
        PersonUI ui = new PersonUI();
        ui.mainMenu();
    }

    private void mainMenu() {
        System.out.println("-------------------------------");
        System.out.println("Choose an option: ");
        System.out.println("1- List all People");
        System.out.println("2- Add a Person");
        System.out.println("3- Update a Person");
        System.out.println("4- Delete a Person");
        System.out.println("5- Exit");
        int option = sc.nextInt();
        try {
            switch (option) {
                case 1:
                    listAllPeople();
                    break;
                case 2:
                    addPerson();
                    break;
                case 3:
                    updatePerson();
                    break;
                case 4:
                    deletePerson();
                    break;
                case 5:
                    return;
            }
            mainMenu();
        } catch (Exception ex) {
            this.mainMenu();
        }
    }

    private void listAllPeople() {
        List<Person> personList = manager.getAllPeople();
        if (!personList.isEmpty()) {
            for (Person person : personList) {
                System.out.println(person);
            }
        } else {
            System.out.println("No person registered");
        }
    }

    private void addPerson() {
        sc.nextLine();
        System.out.println("Insert Person Name");
        person.setPersonName(sc.nextLine());
        System.out.println("Insert Person Lastname");
        person.setPersonLastname(sc.nextLine());
        System.out.println("Insert Person Phone");
        person.setPersonPhone(sc.nextLine());
        System.out.println("Insert Person Email");
        person.setPersonEmail(sc.nextLine());
        System.out.println("Insert Person Address");
        person.setPersonAddress(sc.nextLine());

        if (manager.addPerson(person)) {
            System.out.println("Insert Successful");
        } else {
            System.out.println("Japiro");
        }
    }

    private void updatePerson() {
        this.listAllPeople();
        System.out.println("Insert Person Id");
        person.setPersonId(sc.nextInt());
        sc.nextLine();
        System.out.println("Insert new Person Name");
        person.setPersonName(sc.nextLine());
        System.out.println("Insert new Person Lastname");
        person.setPersonLastname(sc.nextLine());
        System.out.println("Insert new Person Phone");
        person.setPersonPhone(sc.nextLine());
        System.out.println("Insert new Person Email");
        person.setPersonEmail(sc.nextLine());
        System.out.println("Insert new Person Address");
        person.setPersonAddress(sc.nextLine());

        if (manager.updatePerson(person)) {
            System.out.println("Update Successful");
        } else {
            System.out.println("Japiro");
        }
    }

    private void deletePerson(){
        this.listAllPeople();
        sc.nextLine();
        System.out.println("Insert person id");
        person.setPersonId(sc.nextInt());

        if (manager.deletePerson(person)){
            System.out.println("Delete successful");
        }else{
            System.out.println("Japiro");
        }

    }
}
