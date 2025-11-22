

interface  IIceCream{

}

class EmptyServing implements IIceCream{
    boolean cone;
    EmptyServing(boolean cone){
        this.cone = cone;
    }

}
class Scooped implements IIceCream{
    IIceCream more;
    String flavor;
    Scooped(String flavor,IIceCream more){
        this.flavor = flavor;
        this.more = more;
    }
}

class ExamplesIceCream{
    IIceCream cup = new Scooped("caramel swirl", new Scooped("black raspberry", new Scooped("coffee", new Scooped("mint chip", new EmptyServing(false)))));
    IIceCream cone = new Scooped("strawberry", new Scooped("vanilla", new Scooped("chocolate", new EmptyServing(true))));
}