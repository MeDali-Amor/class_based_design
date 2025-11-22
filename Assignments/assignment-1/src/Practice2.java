class Image {
    int width; // in pixels
    int height; // in pixels
    String source;
    Image(int width, int height, String source) {
        this.width = width;
        this.height = height;
        this.source = source;
    }
    String sizeString(){
        int size = this.width * this.height;
        if(size <= 10000){
            return "small";
        }
        else if(size <= 1000000) {
            return "medium";
        }
        return "large";
    }
}