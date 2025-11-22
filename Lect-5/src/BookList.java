import tester.*;

class Book {
    String name;
    String author;
    double price;
    int year;
    Book(String name, String author, double price, int year) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.year = year;
    }
    double salePrice(int discount){
        return this.price - (this.price * discount) / 100 ;
    }
}



/*
 * GOAL:
 * Represent a bunch of books, and be able to compute
 * - their total price
 * - how many books we have
 * - all the books published before the given year
 * - a sorted list of books
 */

/*
 * A list of books is one of
 * empty
 * (cons book list-of-books)
 */

// DYNAMIC DISPATCH: deciding which method definition to invoke (in which class)
// based on the information available at runtime of the object that's invoking
// the method

interface ILoBook {
    double totalPrice(int discount);
    int count();
    ILoBook allBooksBefore(int year);
    ILoBook sortByPrice();
    ILoBook insert(Book book);
}
class MtLoBook implements ILoBook {

    MtLoBook(){
    }
    public double totalPrice(int discount) {
        return 0;
    }
    public int count() {
        return 0;
    }
    public ILoBook allBooksBefore(int year) {
        return this;
    }
    public ILoBook sortByPrice() {
        return this;
    }
    public ILoBook insert(Book b) {
        return new ConsLoBook(b, this);
    }
}
class ConsLoBook implements ILoBook {
    Book first;
    ILoBook rest;
    ConsLoBook(Book first, ILoBook rest) {
        this.first = first;
        this.rest = rest;
    }
    public double totalPrice(int discount) {
        return  this.first.salePrice(discount) + this.rest.totalPrice(discount);
    }
    public int count() {
        return 1+ this.rest.count();
    }
    public ILoBook allBooksBefore(int year) {
        if(this.first.year < year){
            return new ConsLoBook(this.first, this.rest.allBooksBefore(year));
        }
        return this.rest.allBooksBefore(year);
    }
    public ILoBook sortByPrice() {
        return this.rest.sortByPrice().insert(first);
    }
    public ILoBook insert(Book book){
        if(this.first.price < book.price ){
            return new ConsLoBook(this.first, this.rest.insert(book));
        }
        return new ConsLoBook(book, this);
    }

}




class ExamplesBooks {
    Book htdp = new Book("HtDP", "MF", 10.0, 2014);
    Book hp = new Book("HP & the search for more money", "JKR", 90.00, 2015);
    Book gatsby = new Book("The Great Gatsby", "FSF", 15.99, 1930);
    ILoBook mtList = new MtLoBook();
    ILoBook twoBooks = new ConsLoBook(this.htdp,
            new ConsLoBook(this.hp,
                    this.mtList));
    ILoBook threeBooks = new ConsLoBook(this.gatsby, this.twoBooks);
    // tests for the method allBooksBefore
    boolean testAllBefore(Tester t) {
        return
                t.checkExpect(this.mtList.allBooksBefore(2001), this.mtList) &&
                        t.checkExpect(this.twoBooks.allBooksBefore(2001), mtList) &&
                        t.checkExpect(this.threeBooks.allBooksBefore(2001), new ConsLoBook(gatsby,this.mtList));
    }}






