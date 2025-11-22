import java.util.ArrayList;

class BookShelf{



    public static void main(String[] args){
        class  Author{
            String name;
            int yob;
            Author(String name, int yob){
                this.name = name;
                this.yob = yob;
            }
            boolean sameAuthor(Author other){
                return this.name.equals(other.name) && this.yob == other.yob;
            }
        }
        class Book {
            String title;
            Author author;
            int price;
            Book(String title, Author author, int price){
                this.title = title;
                this.author= author;
                this.price = price;
            }
            boolean sameAuthor(Book other){
                return this.author.sameAuthor(other.author);
            }
            Book reducePrice(int reduction){
                int newPrice =this.price - ((this.price / 100) * reduction);
                return new Book(this.title, this.author, newPrice);
            }
        }

        Author author1 = new Author("Jeorge Martin", 1950);
        Author jrr = new Author("J.R.R Tolkein", 1900);

        Book got= new Book("Game of thrones", author1, 100
        );
        Book got2 = got.reducePrice(20);
        Book dance = new Book("Dance of dragons", author1, 100);
        Book tlotr = new Book("The lord of the rings", jrr, 160);
        System.out.println(got2.title + " " +got2.author.name+" "+got2.price);
        System.out.println(got.sameAuthor(dance));
        System.out.println(tlotr.sameAuthor(got));


    }

}