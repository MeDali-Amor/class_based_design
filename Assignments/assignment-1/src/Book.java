class Book {
    String title;
    String author;
    int price;
    Book(String title, String author, int price){
        this.title = title;
        this.author= author;
        this.price = price;
    }
    boolean sameAuthor(Book other){
        return this.author.equals(other.author);
    }
    Book reducePrice(int reduction){
        int newPrice =this.price - ((this.price / 100) * reduction);
        return new Book(this.title, this.author, newPrice);
    }
}
interface IBookList{
    int count();
    int totalPrice();
    IBookList sortByPrice();
    IBookList insert(Book book);
}
class ConsBookList implements IBookList{
    Book first;
    IBookList rest;
    ConsBookList(Book first, IBookList rest){
        this.first=first;
        this.rest=rest;
    }
    public int count(){
        return this.rest.count() + 1;
    }
    public int totalPrice(){
        return this.first.price + this.rest.totalPrice();
    }
    public IBookList insert(Book book){
        if(this.first.price < book.price){
            return new ConsBookList(this.first, this.rest.insert(book));
        }
        else{
            return new ConsBookList(book, this);
        }
    }
    public  IBookList sortByPrice(){
        return this.rest.sortByPrice().insert(this.first);
    }
}
class EmptyList implements  IBookList{
    EmptyList(){

    }
    public int count(){
        return 0;
    }
    public int totalPrice(){
        return 0;
    }
    public  IBookList sortByPrice(){
        return this;
    }
    public  IBookList insert(Book book){
        return new ConsBookList(book, this);
    }
}
class BookListExpm{


    public static void main(String[] args){
        Book fotr = new Book("The fellowship of the ring","JRR Tolkein", 100);
        Book ttt = new Book("The Two Towers","JRR Tolkein", 100);
        Book trotk = new Book("The return of the king","JRR Tolkein", 100);
        IBookList tlotr = new ConsBookList(fotr, new ConsBookList(ttt, new ConsBookList(trotk, new EmptyList())));
        System.out.println(tlotr.count());
        System.out.println(tlotr.totalPrice());

    }
}