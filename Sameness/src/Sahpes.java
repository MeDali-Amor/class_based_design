import tester.Tester;

interface IShape {
    boolean sameShape(IShape that);
    boolean sameCircle(Circle that);
    boolean sameRect(Rect that);
    boolean sameSquare(Square that);
}
class Circle implements IShape {
    int x, y;
    int radius;
    Circle(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }
    public boolean sameShape(IShape that){

        return that.sameCircle(this);
    }


    public boolean isCircle() {
        return true;
    }


    public boolean isSquare() {
        return false;
    }


    public boolean isRect() {
        return false;
    }
    public boolean sameCircle(Circle that){
        return this.x == that.x &&
                this.y == that.y &&
                this.radius == that.radius;
    }
    public boolean sameRect(Rect that){
        return false;
    }
    public boolean sameSquare(Square that){
        return false;
    }


}
class Rect implements IShape {
    int x, y;
    int w, h;
    Rect(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
    public boolean sameShape(IShape that){
        return that.sameRect(this);
    }

    public boolean isCircle() {
        return false;
    }


    public boolean isSquare() {
        return false;
    }


    public boolean isRect() {
        return true;
    }
    public boolean sameRect(Rect that){
        return this.x == that.x &&
                this.y == that.y &&
                this.w == that.w &&
                this.h == that.h;
    }
    public boolean sameSquare(Square that){
        return false;
    }
    public boolean sameCircle(Circle that){
        return false;
    }

}

class Square extends Rect {

    Square(int x, int y, int size) {
        super(x,y, size, size);
    }
    public boolean sameShape(IShape that){
        return that.sameSquare(this);
    }



    public boolean isSquare() {
        return true;
    }


    public boolean isRect() {
        return false;
    }
    public boolean sameSquare(Square that){
        return this.x == that.x &&
                this.y == that.y &&
                this.w == that.w &&
                this.h == that.h;
    }
    public boolean sameRect(Rect that){
        return false;
    }

}

class CartPt{
    int posX, posY;

    CartPt(int x, int y){
        this.posX = x;
        this.posY = y;
    }
    boolean samePoint(CartPt other){
        return this.posX == other.posX && this.posY ==other.posY;
    }
}

//class Examples{
//    Circle c1 = new Circle(3, 4, 5);
//    Circle c2 = new Circle(4, 5, 6);
//    Circle c3 = new Circle(3, 4, 5);
//    Rect r1 = new Rect(3, 4, 5, 5);
//    Rect r2 = new Rect(4, 5, 6, 7);
//    Rect r3 = new Rect(3, 4, 5, 5);
//    // In test method in an Examples class
//    Square s1 = new Square(3, 4, 5);
//    Square s2 = new Square(4, 5, 6);
//    Square s3 = new Square(3, 4, 5);
//
//    boolean testSameShape(Tester t) {
//        // Comparing squares
//        t.checkExpect(s1.sameShape(s2), false);
//        t.checkExpect(s2.sameShape(s1), false);
//        t.checkExpect(s1.sameShape(s3), true);
//        t.checkExpect(s3.sameShape(s1), true);
//
//        // Comparing a Square with a Rect of a different size
//        t.checkExpect(s1.sameShape(r2), false); // Good
//        t.checkExpect(r2.sameShape(s1), false); // Good
//
//        // Comparing a Square with a Rect of the same size
//        t.checkExpect(s1.sameShape(r1), false); // Good
//        t.checkExpect(r1.sameShape(s1), false);  // Not so good (test expected result)
//
//        return true; }
//
//}