import java.io.*;
import java.util.*;

class Book {
    private String title;
    private String author;
    private String isbn;
    private String checkedOutBy;
    private Date dueDate;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.checkedOutBy = null;
        this.dueDate = null;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getCheckedOutBy() {
        return checkedOutBy;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public boolean checkOut(String patronId, int days) {
        if (checkedOutBy != null) {
            return false; // Book is already checked out
        }
        checkedOutBy = patronId;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, days);
        dueDate = calendar.getTime();
        return true;
    }

    public void returnBook() {
        checkedOutBy = null;
        dueDate = null;
    }

    public boolean isOverdue() {
        if (dueDate != null && dueDate.before(new Date())) {
            return true;
        }
        return false;
    }
    public static void main(String args[]){
        
    }
}

class Patron {
    private String name;
    private String patronId;

    public Patron(String name, String patronId) {
        this.name = name;
        this.patronId = patronId;
    }

    public String getName() {
        return name;
    }

    public String getPatronId() {
        return patronId;
    }
}

class LibraryTransaction {
    private List<Book> books;
    private List<Patron> patrons;

    public LibraryTransaction() {
        books = new ArrayList<>();
        patrons = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void addPatron(Patron patron) {
        patrons.add(patron);
    }

    public void saveData(String booksFile, String patronsFile) {
        try (PrintWriter bookWriter = new PrintWriter(new FileWriter(booksFile));
             PrintWriter patronWriter = new PrintWriter(new FileWriter(patronsFile))) {
            for (Book book : books) {
                bookWriter.println(book.getTitle() + "," + book.getAuthor() + "," + book.getIsbn() + "," +
                        book.getCheckedOutBy() + "," + (book.getDueDate() != null ? book.getDueDate().toString() : ""));
            }
            for (Patron patron : patrons) {
                patronWriter.println(patron.getName() + "," + patron.getPatronId());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void displayBookInfo() {
        System.out.println("Books:");
        for (Book book : books) {
            System.out.println("Title: " + book.getTitle());
            System.out.println("Author: " + book.getAuthor());
            System.out.println("ISBN: " + book.getIsbn());
            System.out.println("Checked out by: " + (book.getCheckedOutBy() != null ? book.getCheckedOutBy() : "Available"));
            System.out.println("Due date: " + (book.getDueDate() != null ? book.getDueDate().toString() : "Not checked out"));
            System.out.println();
        }
    }

    public void displayPatronInfo() {
        System.out.println("Patrons:");
        for (Patron patron : patrons) {
            System.out.println("Name: " + patron.getName());
            System.out.println("Patron ID: " + patron.getPatronId());
            System.out.println();
        }
    }
}

