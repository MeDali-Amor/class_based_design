interface IList<T>{
    IList<T> filter(IPredicate<T> pred);
    IList<T> sort(IComparator<T> comparator);
    IList<T> insert(IComparator<T> comparator, T el);
    int length();
    <K> IList<K> map(IFunc<T, K> func);
    <K> K foldr(IReduce<T, K> reduce, K init);
}

interface IFunc<A,B>{
    B apply(A arg);
}

interface IReduce<A,B>{
    B apply(A curr, B acc);
}

class MtList<T> implements IList<T>{

    public IList<T> sort(IComparator<T> comparator){
        return this;
    }
    public IList<T>insert(IComparator<T> comparator, T el){
        return new ConsList<T>(el, this);
    }
    public IList<T> filter(IPredicate<T> pred){
        return this;
    }
    public int length(){
        return 0;
    }
    public <K> IList<K> map(IFunc<T,K> func){
        return new MtList<K>();
    }

    public <K> K foldr(IReduce<T, K> func, K init) {
        return init;
    }
}
class ConsList<T> implements IList<T>{
    T first;
    IList<T> rest;
    ConsList(T first, IList<T> rest ){
        this.first = first;
        this.rest = rest;
    }

    public IList<T> sort(IComparator<T> comparator){
        return this;
    }
    public IList<T>insert(IComparator<T> comparator, T el){
        if(comparator.compare(this.first, el) < 0){
            return new ConsList<T>(first, rest.insert(comparator,el));
        }
        else{
            return new ConsList<T>(el, this);
        }
    }
    public IList<T>  filter(IPredicate<T> pred){
        if(pred.apply(this.first)){
            return new ConsList<T>(this.first, this.rest.filter(pred));
        }
        else {
            return this.rest.filter(pred);
        }
    }
    public int length(){
        return 1 + this.rest.length();
    }
    public <K> IList<K> map(IFunc<T,K> func){
        return new ConsList<K>(func.apply(this.first), this.rest.map(func));
    }
    public <K> K foldr(IReduce<T,K> reduce, K init){
        return reduce.apply(this.first, this.rest.foldr(reduce, init));
    }
}

interface IComparator<T>{
    int compare(T t1, T t2);
}

interface IPredicate<T>{
    boolean apply(T t);
}