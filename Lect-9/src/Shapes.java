import tester.Tester;

interface IShape {
    public double area();
    public double distTo0();
    public IShape grow(int inc);
    public boolean biggerThan(IShape that);
    public boolean contains(CartPt pt);
}



class CartPt {
    int x, y;
    CartPt(int x, int y ){
    this.x = x;
    this.y = y;

}
    public double distTo0(){
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }
    public double distTo(CartPt otherPt){
        return Math.sqrt((this.x - otherPt.x) * (this.x
        - otherPt.x) + (this.y - otherPt.y) * (this.y - otherPt.y));
    }
}

abstract class AShape implements IShape{
    String color;
    CartPt loc;

    AShape( CartPt loc, String color){
        this.color = color;
        this.loc = loc;
    }

    public abstract double area();
    public abstract boolean contains(CartPt pt);
    public abstract IShape grow(int inc);

    public double distTo0() {
        return this.loc.distTo0();
    }

    public boolean biggerThan(IShape that) {
        return this.area() >= that.area();
    }


}


class Circle extends AShape{
    int radius;
    Circle(CartPt center, int radius, String color ){
        super(center, color);
        this.radius = radius;
    }
    public double area(){
        return Math.PI * this.radius * this.radius;
    }
    public double distTo0(){
        return this.loc.distTo0() - this.radius;
    }

    public IShape grow(int inc) {
        return new Circle(this.loc,this.radius + inc, this.color);
    }

    public boolean contains(CartPt pt) {
        return this.loc.distTo(pt)<= this.radius;
    }
}

class Rect extends AShape{
    int width, height;
    Rect(CartPt nw, int width, int height, String color ){
        super(nw, color);
        this.width = width;
        this.height = height;
    }
    public double area(){
        return this.width * this.height;
    }
    
    public IShape grow(int inc) {
        return new Rect(this.loc,this.width + inc,  this.height + inc, this.color);
    }

    public boolean contains(CartPt pt) {
        return this.loc.x <= pt.x && (this.loc.x + this.width >= pt.x) && this.loc.y <= pt.y && (this.loc.y + this.height >= pt.y);
    }
}

class Square extends Rect{
    int size;
    Square(CartPt nw, int size, String color ){
        super(nw, size, size,  color);
        this.size = size;
    }


    public IShape grow(int inc) {
        return new Square(this.loc,this.size + inc, this.color);
    }


}


class Examples{
    Examples() {}

    CartPt pt1 = new CartPt(0, 0);
    CartPt pt2 = new CartPt(3, 4);
    CartPt pt3 = new CartPt(7, 1);

    IShape c1 = new Circle(new CartPt(50, 50), 10, "red");
    IShape c2 = new Circle(new CartPt(50, 50), 30, "red");
    IShape c3 = new Circle(new CartPt(30, 100), 30, "blue");

    IShape s1 = new Square(new CartPt(50, 50), 30, "red");
    IShape s2 = new Square(new CartPt(50, 50), 50, "red");
    IShape s3 = new Square(new CartPt(20, 40), 10, "green");

    IShape r1 = new Rect(new CartPt(50, 50), 30, 20, "red");
    IShape r2 = new Rect(new CartPt(50, 50), 50, 40, "red");
    IShape r3 = new Rect(new CartPt(20, 40), 10, 20, "green");

    // test the method distTo0 in the class CartPt
    boolean testDistTo0(Tester t) {
        return
                t.checkInexact(this.pt1.distTo0(), 0.0, 0.001) &&
                        t.checkInexact(this.pt2.distTo0(), 5.0, 0.001);
    }

    // test the method distTo in the class CartPt
    boolean testDistTo(Tester t) {
        return
                t.checkInexact(this.pt1.distTo(this.pt2), 5.0, 0.001) &&
                        t.checkInexact(this.pt2.distTo(this.pt3), 5.0, 0.001);
    }

    // test the method area in the class Circle
    boolean testCircleArea(Tester t) {
        return
                t.checkInexact(this.c1.area(), 314.15, 0.01);
    }

    // test the method area in the class Square
    boolean testSquareArea(Tester t) {
        return
                t.checkInexact(this.s1.area(), 900.0, 0.01);
    }

    // test the method area in the class Rect
    boolean testRectArea(Tester t) {
        return
                t.checkInexact(this.r1.area(), 600.0, 0.01);
    }

    // test the method distTo0 in the class Circle
    boolean testCircleDistTo0(Tester t) {
        return
                t.checkInexact(this.c1.distTo0(), 60.71, 0.01) &&
                        t.checkInexact(this.c3.distTo0(), 74.40, 0.01);
    }

    // test the method distTo0 in the class Square
    boolean testSquareDistTo0(Tester t) {
        return
                t.checkInexact(this.s1.distTo0(), 70.71, 0.01) &&
                        t.checkInexact(this.s3.distTo0(), 44.72, 0.01);
    }

    // test the method distTo0 in the class Rect
    boolean testRectDistTo0(Tester t) {
        return
                t.checkInexact(this.r1.distTo0(), 70.71, 0.01) &&
                        t.checkInexact(this.r3.distTo0(), 44.72, 0.01);
    }

    // test the method grow in the class Circle
    boolean testCircleGrow(Tester t) {
        return
                t.checkExpect(this.c1.grow(20), this.c2);
    }

    // test the method grow in the class Square
    boolean testSquareGrow(Tester t) {
        return
                t.checkExpect(this.s1.grow(20), this.s2);
    }

    // test the method grow in the class Rect
    boolean testRectGrow(Tester t) {
        return
                t.checkExpect(this.r1.grow(20), this.r2);
    }

    // test the method biggerThan in the class Circle
    boolean testCircleBiggerThan(Tester t) {
        return
                t.checkExpect(this.c1.biggerThan(this.c2), false) &&
                        t.checkExpect(this.c2.biggerThan(this.c1), true) &&
                        t.checkExpect(this.c1.biggerThan(this.s1), false) &&
                        t.checkExpect(this.c1.biggerThan(this.s3), true);
    }

    // test the method biggerThan in the class Square
    boolean testSquareBiggerThan(Tester t) {
        return
                t.checkExpect(this.s1.biggerThan(this.s2), false) &&
                        t.checkExpect(this.s2.biggerThan(this.s1), true) &&
                        t.checkExpect(this.s1.biggerThan(this.c1), true) &&
                        t.checkExpect(this.s3.biggerThan(this.c1), false);
    }

    // test the method biggerThan in the class Rect
    boolean testRectBiggerThan(Tester t) {
        return
                t.checkExpect(this.r1.biggerThan(this.r2), false) &&
                        t.checkExpect(this.r2.biggerThan(this.r1), true) &&
                        t.checkExpect(this.r1.biggerThan(this.c1), true) &&
                        t.checkExpect(this.r3.biggerThan(this.s1), false);
    }

    // test the method contains in the class Circle
    boolean testCircleContains(Tester t) {
        return
                t.checkExpect(this.c1.contains(new CartPt(100, 100)), false) &&
                        t.checkExpect(this.c2.contains(new CartPt(40, 60)), true);
    }

    // test the method contains in the class Square
    boolean testSquareContains(Tester t) {
        return
                t.checkExpect(this.s1.contains(new CartPt(100, 100)), false) &&
                        t.checkExpect(this.s2.contains(new CartPt(55, 60)), true);
    }

    // test the method contains in the class Rect
    boolean testRectContains(Tester t) {
        return
                t.checkExpect(this.r1.contains(new CartPt(100, 100)), false) &&
                        t.checkExpect(this.r2.contains(new CartPt(55, 60)), true);
    }
}