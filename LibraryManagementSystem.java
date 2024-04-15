public class LibraryManagementSystem {
    public static void main(String[] args) {
        // Sample usage
        LibraryTransaction transaction = new LibraryTransaction();

        Book book1 = new Book("The Great Gatsby", "F. Scott Fitzgerald", "9780743273565");
        Book book2 = new Book("To Kill a Mockingbird", "Harper Lee", "9780061120084");
        transaction.addBook(book1);
        transaction.addBook(book2);

        Patron patron1 = new Patron("Alice", "P001");
        Patron patron2 = new Patron("Bob", "P002");
        transaction.addPatron(patron1);
        transaction.addPatron(patron2);

        // Check out a book
        book1.checkOut(patron1.getPatronId(), 14);

        // Return a book
        book1.returnBook();

        // Display book and patron information
        transaction.displayBookInfo();
        transaction.displayPatronInfo();

        // Save data to files
        transaction.saveData("books.txt", "patrons.txt");
    }
}
