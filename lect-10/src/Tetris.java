interface ITetrisPiece{
    int SCREEN_HEIGHT = 30;
}

abstract class ATetrisPiece implements ITetrisPiece{
    int x,y;

    ATetrisPiece(int x, int y){
        this.x = x;
        this.y = y;
    }
    ATetrisPiece(int x){
        this(x,SCREEN_HEIGHT);
    }
}

class Square extends ATetrisPiece{

    Square(int topLeftX, int topLeftY){
        super(topLeftX, topLeftY);
    }

    Square(int topLeftX){
        super(topLeftX);
    }
}

class LShape extends ATetrisPiece{

    LShape(int cornerX, int cornerY){
        super(cornerX, cornerY);
    }
    LShape(int cornerX){
        super(cornerX);
    }
}