// Import necessay classes for handling input and exceptions
import java.util.InputMismatchException;
import java.util.Scanner;
// Main class of the Address Book application
class AddressBook {

    // Inner class to represent a Contact with name, address, and a reference to the next contact
    static class Contact {
        String name;
        String address;
        Contact next;

        // Constructor to initialize a new contact
        public Contact(String name, String address) {
            this.name = name;
            this.address = address;
            this.next = null;
        }
    }
    // Inner class to represent a linked list of contacts
    static class LinkedList {
        Contact head;

        // Method to add a new contact at the beginning of the list
        public void addNode(String name, String address) {
            Contact newContact = new Contact(name, address);
            newContact.next = head;
            head = newContact;
            System.out.println("Added contact: Name: " + name + ", Address: " + address);
        }
        // Method to remove a contact by name
        public boolean removeNode(String name) {
            if (head == null) return false;
            if (head.name.equals(name)) {
                head = head.next;
                return true;
            }
            Contact current = head;
            while (current.next != null) {
                if (current.next.name.equals(name)) {
                    current.next = current.next.next;
                    return true;
                }
                current = current.next;
            }
            return false;
        }
       
        // Method to get a contact by its position in the list
        public Contact getNode(int index) {
            Contact current = head;
            int count = 0;
            while (current != null) {
                if (count == index) return current;
                count++;
                current = current.next;
            }
            return null;
        }
        // Method to print all contacts in the the list
        public void printList() {
            System.out.println("Printing contacts...");
            Contact current = head;
            while (current != null) {
                System.out.println("Name: " + current.name + ", Address: " + current.address);
                current = current.next;
            }
        }
    }
    // Main method - entry point of the application
    public static void main(String[] args) {
        LinkedList addressBook = new LinkedList();
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                try {
                    System.out.println("\nAddress Book Operations:");
                    System.out.println("1. Add Contact");
                    System.out.println("2. Remove Contact");
                    System.out.println("3. Get Contact");
                    System.out.println("4. Print All Contacts");
                    System.out.println("5. Exit");
                    System.out.print("Choose an operation: ");

                    int choice = scanner.nextInt();
                    scanner.nextLine(); // consume the newline character

                    String name, address;
                    int index;

                    switch (choice) {
                        case 1:
                            System.out.print("Enter name: ");
                            name = scanner.nextLine();
                            System.out.print("Enter address: ");
                            address = scanner.nextLine();
                            addressBook.addNode(name, address);
                            break;
                        case 2:
                            System.out.print("Enter name of the contact to remove: ");
                            name = scanner.nextLine();
                            if (addressBook.removeNode(name)) {
                                System.out.println("Contact removed.");
                            } else {
                                System.out.println("Contact not found.");
                            }
                            break;
                        case 3:
                            System.out.print("Enter index of the contact to get: ");
                            index = scanner.nextInt();
                            scanner.nextLine(); // consume the newline character after the number
                            Contact contact = addressBook.getNode(index);
                            if (contact != null) {
                                System.out.println("Name: " + contact.name + ", Address: " + contact.address);
                            } else {
                                System.out.println("No contact found at index " + index + ".");
                            }
                            break;
                        case 4:
                            addressBook.printList();
                            break;
                        case 5:
                            System.out.println("Exiting Address Book.");
                            return; // This will exit the main method
                        default:
                            System.out.println("Invalid option! Please enter a number between 1 and 5.");
                            break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("That's not a valid option. Please enter a number between 1 and 5.");
                    scanner.nextLine(); // consume the invalid input
                }
            }
            // Scanner will be closed automatically when the program exits or any exception is thrown
        }
    }
}
