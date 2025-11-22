import tester.Tester;

interface ILoRunner{
    ILoRunner find(IRunnerPredicate predicate);

    ILoRunner insertBy(IRunnerComparator compare, Runner r);
    ILoRunner sortBy(IRunnerComparator compare);
//    Runner winnerEasy();
//    Runner getFirst();
    Runner winner();
//    Runner winnerHelper(Runner other);
    Runner findMin(IRunnerComparator comparator);
    Runner findMinHelper(IRunnerComparator comparator, Runner other);
    Runner last();
    Runner findMax(IRunnerComparator comparator);
}

interface ICompare{
    boolean compare(Runner r1, Runner r2);
}

interface IRunnerComparator{
    int compare(Runner r1, Runner r2);
}

class CompareByTime implements IRunnerComparator{
    public int compare(Runner r1, Runner r2){
        return r1.time - r2.time;
    }
}
class CompareByName implements IRunnerComparator{
    public int compare(Runner r1, Runner r2){
        return r1.name.compareTo(r2.name);
    }
}
class CompareByPos implements IRunnerComparator{
    public int compare(Runner r1, Runner r2){
        return r1.pos - r2.pos;
    }
}

class ReverseComparator implements IRunnerComparator{
    IRunnerComparator original;
    ReverseComparator(IRunnerComparator original){
        this.original = original;
    }
    public int compare(Runner r1, Runner r2){
       return original.compare(r2, r1);
    }
}

interface IRunnerPredicate {
    boolean apply(Runner r);
}

class AndPredicate implements IRunnerPredicate{
    IRunnerPredicate right, left;
    AndPredicate(IRunnerPredicate right, IRunnerPredicate left){
        this.left = left;
        this.right = right;
    }
    public boolean apply(Runner r){
        return this.right.apply(r) && this.left.apply(r);
    }
}
class OrPredicate implements IRunnerPredicate{
    IRunnerPredicate right, left;
    OrPredicate(IRunnerPredicate right, IRunnerPredicate left){
        this.left = left;
        this.right = right;
    }
    public boolean apply(Runner r){
        return this.right.apply(r) || this.left.apply(r);
    }
}

class RunnerIsMale implements IRunnerPredicate {
    public boolean apply(Runner r) { return r.isMale; }
}
class RunnerIsFemale implements IRunnerPredicate{
   public boolean apply(Runner r) { return !r.isMale; }
}
class RunnerIsInFirst50 implements IRunnerPredicate {
   public boolean apply(Runner r) { return r.pos <= 50; }
}
class FinishIn4Hours implements IRunnerPredicate {
    public boolean apply(Runner r) { return r.time <= (60 * 4); }
}
class RunnerIsYounger40 implements IRunnerPredicate {
    public boolean apply(Runner r) { return r.age < 40; }
}


class Runner{
    String name;
    int age;
    int bib;
    boolean isMale;
    int pos;
    int time;
    Runner(String name,int age,
    int bib,
    boolean isMale,
    int pos,
    int time){
        this.name = name;
        this.age = age;
        this.bib = bib;
        this.isMale = isMale;
        this.pos = pos;
        this.time = time;
    }
    public boolean isMAleRunner(){
        return this.isMale;
    }
    public boolean isInFirst50(){
        return this.pos <= 50;
    }
    public boolean finishedBefore(Runner other){
        return this.time < other.time;
    }
    public boolean posBefore(Runner other){
        return this.pos < other.pos;
    }


}

class MTLoRunner implements ILoRunner{
    MTLoRunner(){}

    public ILoRunner find(IRunnerPredicate predicate){
        return this;
    }


    public ILoRunner sortBy(IRunnerComparator compare){
        return this;
    }

    public ILoRunner insertBy(IRunnerComparator compare, Runner other) {
        return new ConsLoRunner(other, this);
    }

//    public Runner winnerEasy() {
//        throw new RuntimeException("No winner of an empty list of Runners");
//    }
//    public Runner getFirst() {
//        throw new RuntimeException("No winner of an empty list of Runners");
//    }
    public Runner winner() {
        throw new RuntimeException("No winner of an empty list of Runners");
    }

//    public Runner winnerHelper(Runner other) {
//        return other;
//    }
    public Runner findMin(IRunnerComparator comparator){
        throw new RuntimeException("No winner of an empty list of Runners");
    }
    public Runner findMinHelper(IRunnerComparator comparator, Runner other){
        return other;
    }
    public Runner findMax(IRunnerComparator comparator){
        throw new RuntimeException("No winner of an empty list of Runners");
    }
    public Runner last(){
        throw new RuntimeException("No winner of an empty list of Runners");
    }
}

class ConsLoRunner implements ILoRunner{
    Runner first;
     ILoRunner rest;
    ConsLoRunner(Runner first, ILoRunner rest){
        this.first = first;
        this.rest = rest;

}
    public ILoRunner find(IRunnerPredicate predicate){
        if(predicate.apply(this.first)){
            return new ConsLoRunner(first, rest.find(predicate));
        }
        else {
            return rest.find(predicate);
        }
    }
    
    public ILoRunner sortBy(IRunnerComparator compare){
        return this.rest.sortBy(compare).insertBy(compare, this.first);
    }

    public ILoRunner insertBy(IRunnerComparator compare, Runner other) {
        if(compare.compare(this.first, other) < 0){
            return new ConsLoRunner(this.first, this.rest.insertBy(compare,other));
        }
        else{
            return new ConsLoRunner(other, this);
        }
    }
//    public Runner winnerEasy() {
//        return this.sortBy(new CompareByTime()).getFirst();
//    }
//    public Runner getFirst() {
//        return this.first;
//    }
    public Runner winner() {
//        return this.rest.winnerHelper(this.first);
        return findMin(new CompareByTime());
    }
//    public Runner winnerHelper(Runner other) {
//        if(this.first.finishedBefore(other)){
//            return this.rest.winnerHelper(this.first);
//        }
//        else  {
//            return this.rest.winnerHelper(other);
//        }
//    }

    public Runner findMin(IRunnerComparator comparator){
        return this.rest.findMinHelper(comparator, this.first);
    }
    public Runner findMinHelper(IRunnerComparator comparator, Runner acc){
        if(comparator.compare(this.first,acc) < 0){
            return this.rest.findMinHelper(comparator,this.first);
        }
        else  {
            return this.rest.findMinHelper(comparator,acc);
        }
    }
    public Runner findMax(IRunnerComparator comparator){
        return findMin(new ReverseComparator(comparator));
    }
    public Runner last(){
        return this.findMax(new CompareByTime());
    }
}

class Examples {
    Runner johnny = new Runner("Kelly", 97, 999, true, 30, 360);
    Runner frank  = new Runner("Shorter", 32, 888, true, 245, 130);
    Runner bill = new Runner("Rogers", 36, 777, true, 119, 129);
    Runner joan = new Runner("Benoit", 29, 444, false, 18, 155);

    ILoRunner mtlist = new MTLoRunner();
    ILoRunner list1 = new ConsLoRunner(johnny, new ConsLoRunner(joan, mtlist));
    ILoRunner list2 = new ConsLoRunner(frank, new ConsLoRunner(bill, list1));
    ILoRunner list3 = new ConsLoRunner(frank, new ConsLoRunner(bill, mtlist));

//    boolean testFindMethods(Tester t) {
//        return
//                t.checkExpect(this.list1.find(new RunnerIsFemale()),
//                        new ConsLoRunner(this.joan, new MTLoRunner())) &&
//                        t.checkExpect(this.list2.find(new RunnerIsMale()),
//                                new ConsLoRunner(this.frank,
//                                        new ConsLoRunner(this.bill,
//                                                new ConsLoRunner(this.johnny, new MTLoRunner()))));
//    }
//    boolean testFindUnder4Hours(Tester t) {
//        return
//                t.checkExpect(this.list2.find(new FinishIn4Hours()),
//                        new ConsLoRunner(this.frank,
//                                new ConsLoRunner(this.bill,
//                                        new ConsLoRunner(this.joan, new MTLoRunner()))));
//    }
    boolean testSortByTime(Tester t) {
        return
                t.checkExpect(this.list2.sortBy(new CompareByTime()),
                        new ConsLoRunner(this.bill,
                                new ConsLoRunner(this.frank,
                                        new ConsLoRunner(this.joan, new ConsLoRunner(this.johnny, new MTLoRunner())))));
    }
    boolean testWinner(Tester t) {
        return
                t.checkExpect(this.list2.winner(),
                        this.bill)
         &&t.checkExpect(this.list1.winner(),
                this.joan);
    }
    boolean testlast(Tester t) {
        return
                t.checkExpect(this.list2.last(),
                        this.johnny)
         &&t.checkExpect(this.list3.last(),
                this.frank);
    }
    boolean testSortByname(Tester t) {
        return
                t.checkExpect(this.list2.sortBy(new CompareByName()),
                        new ConsLoRunner(this.joan,
                                new ConsLoRunner(this.johnny,
                                        new ConsLoRunner(this.bill, new ConsLoRunner(this.frank, new MTLoRunner())))));
    }
    // In Examples class
//    boolean testCombinedQuestions(Tester t) {
//        return
//                t.checkExpect(this.list2.find(
//                                new AndPredicate(new RunnerIsMale(), new FinishIn4Hours())),
//                        new ConsLoRunner(this.frank,
//                                new ConsLoRunner(this.bill, new MTLoRunner()))) &&
//                        t.checkExpect(this.list2.find(
//                                        new AndPredicate(new RunnerIsFemale(),
//                                                new AndPredicate(new RunnerIsYounger40(),
//                                                        new RunnerIsInFirst50()))),
//                                new ConsLoRunner(this.joan, new MTLoRunner()));
//    }
//    boolean testOrQuestions(Tester t) {
//        return
//                t.checkExpect(this.list2.find(
//                                new OrPredicate(new RunnerIsFemale(), new FinishIn4Hours())),
//                        new ConsLoRunner(this.frank,
//                                new ConsLoRunner(this.bill, new ConsLoRunner(this.joan, new MTLoRunner())))) &&
//                        t.checkExpect(this.list1.find(
//                                        new OrPredicate(new RunnerIsFemale(),
//                                                new RunnerIsYounger40())),
//                                new ConsLoRunner(this.joan, new MTLoRunner()));
//    }
}