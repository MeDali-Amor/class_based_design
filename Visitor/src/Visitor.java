import tester.Tester;

interface IShape {
    <U> U accept(IShapeVisitor<U> func);
}
class Circle implements IShape {
    int x, y;
    int radius;
    String color;
    Circle(int x, int y, int r, String color) {
        this.x = x;
        this.y = y;
        this.radius = r;
        this.color = color;
    }

   public <U> U accept(IShapeVisitor<U> visitor){
        return visitor.visitCircle(this);
   }
}
class Rect implements IShape {
    int x, y, w, h;
    String color;
    Rect(int x, int y, int w, int h, String color) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.color = color;
    }

    public <U> U accept(IShapeVisitor<U> visitor){
        return visitor.visitRect(this);
    }
}

class Square implements IShape {
    int x, y, size;
    String color;
    Square(int x, int y, int size, String color) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.color = color;
    }

    public <U> U accept(IShapeVisitor<U> visitor){
        return visitor.visitSquare(this);
    }
}



interface IShapeVisitor<U>{
    U visitCircle(Circle c);
    U visitRect(Rect r);
    U visitSquare(Square s);
}



class ShapeArea implements IShapeVisitor<Double>{
    public Double visitCircle(Circle c){
        return c.radius * c.radius * Math.PI;
    }
    public Double visitRect(Rect r){
        return r.h * r.w * 1.0;
    }public Double visitSquare(Square s){
        return s.size * s.size * 1.0;
    }
}

class ShapePrimeter implements IShapeVisitor<Double>{

    public Double visitCircle(Circle circle) {
        return 2 * Math.PI * circle.radius;
    }


    public Double visitRect(Rect rect) {
        return 2.0 * (rect.w + rect.h);
    }


    public Double visitSquare(Square square) {
        return 4.0 * square.size;
    }
}



class ShapeListArea implements IFunc<IShape, Double>{
    public Double apply(IShape shape){
        return shape.accept(new ShapeArea());
    }
}

class Examples {

    Circle C1 = new Circle(0,0,2,"red");
    Circle C2 = new Circle(2,3,5,"blue");
    Rect R1 = new Rect(4,3,5,2,"black");
    Square S1 = new Square(1,3,3,"yellow");

    IList<IShape> listOfShapes = new ConsList<IShape>(R1,new ConsList<IShape>(S1, new MtList<IShape>()));

    boolean testSort(Tester t) {
        return
                t.checkExpect(this.R1.accept(new ShapeArea()), 10.0)&&
                t.checkExpect(this.R1.accept(new ShapePrimeter()), 14.0)&&
                t.checkExpect(this.S1.accept(new ShapeArea()), 9.0)&&
                t.checkExpect(this.S1.accept(new ShapePrimeter()), 12.0)&&
                t.checkExpect(this.listOfShapes.map(new ShapeListArea()), new ConsList<Double>(10.0, new ConsList<Double>(9.0, new MtList<Double>())));
    }

}