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

class BookByAuthor implements IPredicate<Book>{
    public boolean apply(Book b) {
        return (b.author).equals("JKR");
    }

}


class SumPrices implements IReduce<Book, Double>{
    public Double apply(Book curr, Double acc) {
        return curr.price + acc;
    }
}





interface ILoBook extends IList<Book> {
    double totalPrice();

}

//interface IBookPredicate extends IPredicate<Book>{
//    boolean apply(Book book);
//}
//interface IBookComparator extends IComparator<Book> {
//    int compare(Book b1, Book b2);
//}

class CompareBookPrice implements IComparator<Book>{
    public int compare(Book b1, Book b2){
        return b1.price - b2.price < 0 ? -1  :b1.price - b2.price > 0 ? 1 :0;
    }
}

class AuthorName implements IFunc<Book, String>{
    public String apply(Book b){
        return b.author;
    }
}

class MtLoBook extends MtList<Book> implements ILoBook {

    MtLoBook(){
    }

    @Override
    public double totalPrice() {
        return 0;
    }
}
class ConsLoBook extends ConsList<Book> implements ILoBook {
    Book first;
    ILoBook rest;

    ConsLoBook(Book first, ILoBook rest){
       super(first, rest);
    }


    @Override
    public double totalPrice() {
        return this.first.price + this.rest.totalPrice();
    }
}




class Examples {
    Book htdp = new Book("HtDP", "MF", 10.0, 2014);
    Book hp = new Book("HP & goblet of fire", "JKR", 90.00, 2004);Book hp1 = new Book("HP & prisoner of Azkaban", "JKR", 100.00, 2003);Book hp2 = new Book("HP & the chamber of secrets", "JKR", 70.00, 2002);
    Book gatsby = new Book("The Great Gatsby", "FSF", 15.99, 1930);
    IList<Book> mtList = new MtList<Book>();
    IList<Book> twoBooks = new ConsList<Book>(this.htdp,
            new ConsList<Book>(this.hp,
                    this.mtList));
    IList<Book> threeBooks = new ConsList<Book>(this.gatsby, this.twoBooks);
    IList<Book> allbooks = new ConsList<Book>(hp1, new ConsList<Book>(hp2, threeBooks));
    IList<String> listOfAuther1 = threeBooks.map(new AuthorName());
//     tests for the method allBooksBefore
    boolean testSort(Tester t) {
        return
                t.checkExpect(this.mtList.sort(new CompareBookPrice()), this.mtList) &&
                        t.checkExpect(this.twoBooks.sort(new CompareBookPrice()),this.twoBooks ) &&
                        t.checkExpect(this.threeBooks.sort(new CompareBookPrice()), new ConsList<Book>(this.gatsby,new ConsList<Book>(this.htdp, new ConsList<Book>(this.hp, this.mtList))));
    }
    boolean testBookByJKR(Tester t) {
        return
                t.checkExpect(this.mtList.filter(new BookByAuthor()), this.mtList) &&
                        t.checkExpect(this.twoBooks.filter(new BookByAuthor()),new ConsList<Book>(this.hp,mtList) ) &&
                        t.checkExpect(this.allbooks.filter(new BookByAuthor()), new ConsList<Book>(this.hp1,new ConsList<Book>(this.hp2, new ConsList<Book>(this.hp, this.mtList))));
    }
    boolean testAuthorList(Tester t) {
        return
                t.checkExpect(listOfAuther1, new ConsList<String>("FSF", new ConsList<String>("MF", new ConsList<String>("JKR", new MtList<String>()))));
    }
    boolean testSumPrice(Tester t) {
        return
                t.checkExpect(this.mtList.foldr(new SumPrices(),0.0), 0.0) &&
                        t.checkExpect(this.allbooks.foldr(new SumPrices(),0.0),285.99);
    }
    }






