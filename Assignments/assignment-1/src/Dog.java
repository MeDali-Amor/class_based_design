

class Dog{

    String name;
    String breed;
    int yob;
    String state;
    boolean hypoallergenic;
    Dog(String name, String breed, String state, int yob, boolean hypoallergenic){
        this.name = name;
        this.breed=breed;
        this.yob = yob;
        this.hypoallergenic=hypoallergenic;
        this.state=state;

    }
}

class ExamplesDog{
    Dog Hufflepuff = new Dog("Hufflepuff", "Wheaten Terrier", "TX", 2012, true);
    Dog Pearl = new Dog("Pearl", "Labrador Retriever", "MA",2016, false);
}