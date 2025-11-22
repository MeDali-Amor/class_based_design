import tester.Tester;

interface IAT{
   int count();
   int countHelper();
    int femaleAncOver40();
    int femaleAncOver40Helper();
    int numTotalGens();
    int numPartialGens();
    boolean wellFormed();
    boolean isOlder(Person child);
    boolean wellFormedHelp(Person child);
    IAT youngerIAT(IAT other);
    IAT youngerIATHelp(IAT other, int otherYob);
}

class Unknown implements IAT{
    Unknown(){

    }

    public int count(){
        return 0;
    }
    public int countHelper(){
        return 0;
    }

    public int femaleAncOver40() {
        return 0;
    }

    public int femaleAncOver40Helper() {
        return 0;
    }


    public int numPartialGens() {
        return 0;
    }

    public int numTotalGens() {
        return 0;
    }
    public boolean wellFormed() { return true; }


    public boolean isOlder(Person child) {
        return true;
    }
    public boolean wellFormedHelp(Person child){
        return true;
    }
    public IAT youngerIAT(IAT other){
        return other;
    }
    public IAT youngerIATHelp(IAT other, int otherYob){
        return other;
    }




}
class Person implements IAT{
    String name;
    int yob;
    boolean isMAle;
    IAT mom, dad;
    Person(String name,
    int yob,
    boolean isMAle,
    IAT mom, IAT dad){
        this.name = name;
        this.yob = yob;
        this.isMAle= isMAle;
        this.mom= mom;
        this.dad = dad;
    }
    public int count(){
        return  this.mom.countHelper() + this.dad.countHelper();
    }
    public int countHelper(){
        return 1  + this.mom.countHelper() + this.dad.countHelper();
    }
    public int femaleAncOver40() {
        return this.mom.femaleAncOver40Helper() + this.dad.femaleAncOver40Helper();
    }

    public int femaleAncOver40Helper() {
        int femaleOver40Count = !this.isMAle && (2015-this.yob)>40 ? 1 : 0;
        return femaleOver40Count+ this.mom.femaleAncOver40Helper() + this.dad.femaleAncOver40Helper();
    }


    public int numTotalGens() {
        return 1 + Math.min(this.dad.numTotalGens(), this.mom.numTotalGens());
    }


    public int numPartialGens() {
        return 1 + Math.max(this.dad.numPartialGens(), this.mom.numPartialGens());
    }

//    public boolean wellFormed() { return this.dad.wellFormed() && this.mom.wellFormed() && this.mom.isOlder(this) && this.dad.isOlder(this); }
 public boolean wellFormed(){
    return this.mom.wellFormedHelp(this) && this.dad.wellFormedHelp(this);
    }

    public boolean isOlder(Person child) {
        return this.yob < child.yob;
    }
    public boolean wellFormedHelp(Person child){
        return this.yob < child.yob && this.dad.wellFormedHelp(this) && this.mom.wellFormedHelp(this)  ;
    }
    public IAT youngerIAT(IAT other){
        return other.youngerIATHelp(this, this.yob);
//        return other.isOlder(this) ? this: other;
    }
    public IAT youngerIATHelp(IAT other, int otherYob){
        return this.yob > otherYob ? this: other;
    }
}

class Examples {
    IAT enid = new Person("Enid", 1904, false, new Unknown(), new Unknown());
    IAT edward = new Person("Edward", 1902, true, new Unknown(), new Unknown());
    IAT emma = new Person("Emma", 1906, false, new Unknown(), new Unknown());
    IAT eustace = new Person("Eustace", 1907, true, new Unknown(), new Unknown());

    IAT david = new Person("David", 1925, true, new Unknown(), this.edward);
    IAT daisy = new Person("Daisy", 1927, false, new Unknown(), new Unknown());
    IAT dana = new Person("Dana", 1933, false, new Unknown(), new Unknown());
    IAT darcy = new Person("Darcy", 1930, false, this.emma, this.eustace);
    IAT darren = new Person("Darren", 1935, true, this.enid, new Unknown());
    IAT dixon = new Person("Dixon", 1936, true, new Unknown(), new Unknown());

    IAT clyde = new Person("Clyde", 1955, true, this.daisy, this.david);
    IAT candace = new Person("Candace", 1960, false, this.dana, this.darren);
    IAT cameron = new Person("Cameron", 1959, true, new Unknown(), this.dixon);
    IAT claire = new Person("Claire", 1956, false, this.darcy, new Unknown());

    IAT bill = new Person("Bill", 1980, true, this.candace, this.clyde);
    IAT bree = new Person("Bree", 1981, false, this.claire, this.cameron);

    IAT andrew = new Person("Andrew", 2001, true, this.bree, this.bill);

    boolean testCount(Tester t) {
        return
                t.checkExpect(this.andrew.count(), 16) &&
                        t.checkExpect(this.david.count(), 1) &&
                        t.checkExpect(this.enid.count(), 0) &&
                        t.checkExpect(new Unknown().count(), 0);
    }
    boolean testFemaleAncOver40(Tester t) {
        return
                t.checkExpect(this.andrew.femaleAncOver40(), 7) &&
                        t.checkExpect(this.bree.femaleAncOver40(), 3) &&
                        t.checkExpect(this.darcy.femaleAncOver40(), 1) &&
                        t.checkExpect(this.enid.femaleAncOver40(), 0) &&
                        t.checkExpect(new Unknown().femaleAncOver40(), 0);
    }

    boolean testWellFormed(Tester t) {
        return
                t.checkExpect(this.andrew.wellFormed(), true) &&
                        t.checkExpect(new Unknown().wellFormed(), true) &&
                        t.checkExpect(
                                new Person("Zane", 2000, true, this.andrew, this.bree).wellFormed(),
                                false);
    }
    boolean testIsYounger(Tester t) {
        return
                t.checkExpect(this.andrew.youngerIAT(this.enid), this.andrew) &&
                        t.checkExpect(new Unknown().youngerIAT(this.bree), this.bree) &&
                        t.checkExpect(
                                this.darcy.youngerIAT(this.andrew),
                                this.andrew);
    }
//    boolean testAncNames(Tester t) {
//        return
//                t.checkExpect(this.david.ancNames(),
//                        new ConsLoString("David",
//                                new ConsLoString("Edward", new MtLoString()))) &&
//                        t.checkExpect(this.eustace.ancNames(),
//                                new ConsLoString("Eustace", new MtLoString())) &&
//                        t.checkExpect(new Unknown().ancNames(), new MtLoString());
//    }
//    boolean testYoungestGrandparent(Tester t) {
//        return
//                t.checkExpect(this.emma.youngestGrandparent(), new Unknown()) &&
//                        t.checkExpect(this.david.youngestGrandparent(), new Unknown()) &&
//                        t.checkExpect(this.claire.youngestGrandparent(), this.eustace) &&
//                        t.checkExpect(this.bree.youngestGrandparent(), this.dixon) &&
//                        t.checkExpect(this.andrew.youngestGrandparent(), this.candace) &&
//                        t.checkExpect(new Unknown().youngestGrandparent(), new Unknown());
//    }
boolean testNumGens(Tester t) {
    return
            t.checkExpect(this.andrew.numTotalGens(), 3) &&
                    t.checkExpect(this.andrew.numPartialGens(), 5) &&
                    t.checkExpect(this.enid.numTotalGens(), 1) &&
                    t.checkExpect(this.enid.numPartialGens(), 1) &&
                    t.checkExpect(new Unknown().numTotalGens(), 0) &&
                    t.checkExpect(new Unknown().numPartialGens(), 0);
}
}