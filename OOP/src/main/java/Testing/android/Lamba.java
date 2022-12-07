package Testing.android;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import Testing.Objects.Book.Book;

public class Lamba implements Comparable<Book> {

    // In this example we're not going to follow the MVC rules just for the porpuses
    // of this tutorial.

    public static void main(String[] args) {
        Book b1 = new Book("Head First", 20);
        Book b2 = new Book("Head First 2", 30);
        Book b3 = new Book("Head First 3", 10);

        List<Book> bookList = Arrays.asList(b1, b2, b3);

        sortBooks(bookList);
        sortBooksLambda(bookList);

    }

    public static void sortBooks(List<Book> bookList) {
        Comparator<Book> titleComparator = new Comparator<Book>() {

            @Override
            public int compare(Book b1, Book b2) {
                return b2.getNombre().compareTo(b1.getNombre());
            }

        };
        Collections.sort(bookList, titleComparator);
        System.out.println(bookList);
    }

    public static  void sortBooksLambda(List<Book> listBook) {

        Collections.sort((List<Book>) listBook, (b1, b2) -> (int) (((Book) b1).getPrecio() - ((Book) b2).getPrecio()));
        System.out.println(listBook);
    }


    @Override
    public int compareTo(Book b1) {

        return 0;
    }

}
