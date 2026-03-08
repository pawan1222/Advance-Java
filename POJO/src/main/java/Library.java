import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Library {

    private List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> findLatestBooks(int yearThreshold) {

        return books.stream()
                .filter(book -> book.getYear() > yearThreshold)
                .collect(Collectors.toList());
    }

    public List<Book> findBooks(
            int year,
            int minPages,
            String authorSubstring) {

        return books.stream()
                .filter(book ->
                        book.getYear() > year &&
                                book.getPages() >= minPages &&
                                book.getAuthor().toLowerCase()
                                        .contains(authorSubstring.toLowerCase())
                )
                .collect(Collectors.toList());
    }


    public static void main(String[] args) {

        Library library = new Library();
        Scanner sc = new Scanner(System.in);

        for(int i=1;i<=5;i++){
            System.out.println("Enter title:");
            String title = sc.nextLine();

            System.out.println("Enter author:");
            String author = sc.nextLine();

            System.out.println("Enter year:");
            int year = sc.nextInt();

            System.out.println("Enter pages:");
            int pages = sc.nextInt();
            sc.nextLine();

            library.addBook(new Book(title, author, year, pages));
        }


        List<Book> latestBooks = library.findLatestBooks(2016);
        for (Book b : latestBooks) {
            System.out.println(b.getTitle() + " - " + b.getAuthor());
        }


        List<Book> authorBooks = library.findBooks(0, 0, "martin");
        for (Book b : authorBooks) {
            System.out.println(b.getTitle() + " - " + b.getAuthor());
        }

        List<Book> complexBooks = library.findBooks(2015, 300, "java");
        for (Book b : complexBooks) {
            System.out.println(b.getTitle() + " - " + b.getAuthor());
        }
    }
}
