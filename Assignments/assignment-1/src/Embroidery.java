interface IMotif{
    double difficulty();

}

interface ILoMotif {
    double averageDifficulty();
    int count();

}

class ConsLoMotif implements ILoMotif{
    IMotif first;
    ILoMotif rest;
    ConsLoMotif(IMotif motif, ILoMotif rest){
        this.first = motif;
        this.rest = rest;
    }
    public int count(){
     return 1 + rest.count();
    }

    public double averageDifficulty(){
        int count  = this.count();
        if (count == 0) {
            return 0;
        }
        return (this.first.difficulty() + this.rest.averageDifficulty()) / count();
    }

}

class MtLoMotif implements ILoMotif{
    MtLoMotif (){

    }
    public double averageDifficulty(){
        return 0;
    }
    public int count(){
        return 0;
    }
}

class CrossStitchMotif implements IMotif {
    String name;
    double difficulty;
    CrossStitchMotif(String name, double difficulty){
        this.name = name;
        this.difficulty = difficulty;
    }
    public double difficulty(){
        return this.difficulty;
    }
}
class ChainStitchMotif implements IMotif {
    String name;
    double difficulty;
    ChainStitchMotif(String name, double difficulty){
        this.name = name;
        this.difficulty = difficulty;
    }
    public double difficulty(){
        return this.difficulty;
    }
}

class GroupMotif implements IMotif {
    ILoMotif motifs;
    GroupMotif(ILoMotif motifs){
       this.motifs = motifs;
    }
    public double difficulty(){
        return this.motifs.averageDifficulty();
    }
}

class EmbroideryPiece {
    String name;
    IMotif motif;
    EmbroideryPiece(String name, IMotif motif){
        this.name = name;
        this.motif = motif;
    }
    double averageDifficulty(){
        return motif.difficulty();
    }
}