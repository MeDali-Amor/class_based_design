interface IShape{
    double area();
    double distanceFromOrigin();
    IShape grow(int inc);
    boolean isBigger(IShape other);
}
interface IPoint{
    double distanceFromOrigin();
    double distanceTo();
}
class PolarPt implements IPoint {
    int r, theta;
    PolarPt(int r, int theta){
        this.r=r;
        this.theta=theta;
    }
    public double distanceFromOrigin(){
        return this.r;
    }

    public double distanceTo() {
        return 0;
    }
}

class CartPt implements IPoint {
    int x, y;
    CartPt(int x, int y){
        this.x = x;
        this.y = y;
    }
    public double distanceFromOrigin(){
        return Math.sqrt(this.x * this.x + this.y*this.y);
    }
    public double distanceTo() {
        return 0;
    }
}
class Circle implements IShape{
    IPoint center;
    int radius;
    String color;
    Circle(IPoint center, int radius, String color){
        this.center = center;
        this.radius = radius;
        this.color = color;
    }
    public double area(){
        return Math.PI *this.radius *this.radius;
    }
    public double distanceFromOrigin(){
        return this.center.distanceFromOrigin() - this.radius;
    }
    public IShape grow(int inc) {
        return new Circle(this.center, this.radius * inc,this.color );
    }
    public boolean isBigger(IShape other){
        return this.area() > other.area();
    }
}
class Square implements IShape{
    IPoint topLeft;
    int size;
    String color;
    Square(IPoint topLeft, int size, String color){
        this.topLeft= topLeft;
        this.size = size;
        this.color = color;

    }
    public double area(){
        return this.size * this.size;
    }
    public double distanceFromOrigin(){
        return this.topLeft.distanceFromOrigin();
    }
    public IShape grow(int inc) {
        return new Square(this.topLeft, this.size * inc, this.color);
    }
    public boolean isBigger(IShape other){
        return this.area() > other.area();
    }
}