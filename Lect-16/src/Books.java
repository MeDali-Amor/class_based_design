import tester.Tester;

class Author {
    String first;
    String last;
    int yob;
    Book book;
    Author(String fst, String lst, int yob, Book bk) {
        this.first = fst;
        this.last = lst;
        this.yob = yob;
        this.book = bk;
    }
}


class Book {
    String title;
    int price;
    int quantity;
    Author author;
    Book(String title, int price, int quantity, Author ath) {
        this.title = title;
        this.price = price;
        this.quantity = quantity;
        this.author = ath;
    }
}

class Examples{
    Author a1 = new Author("Donald","E. Knuth", 1938, this.b1);
    Book b1 = new Book("The Art of Computer Programming (volume 1)",100,10, this.a1);
    boolean test(Tester t) {
        return
                t.checkExpect(this.a1.first, "Donald")&&
                t.checkExpect(this.a1.book.title, "The Art of Computer Programming (volume 1)");
    }
}