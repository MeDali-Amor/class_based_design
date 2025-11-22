
class Util{

    int checkRange(int val, int min, int max, String msg) {
        if (val >= min && val <= max) {
            return val;
        } else {
            throw new IllegalArgumentException(msg);
        }

    }
}
class Date {
    int day, month, year;
    Date(int year, int month, int day){
        this.year = new Util().checkRange(year, 1500,2100,"Invalid year: "+Integer.toString((year)));
        this.month = new Util().checkRange(month, 1,12,"Invalid month: "+Integer.toString((month)));
        this.day = new Util().checkRange(day, 1,31,"Invalid day: "+Integer.toString((day)));
    }
}

class Examples{
    Date d20100228 = new Date(2010, 2, 28);   // Feb 28, 2010
    Date d20091012 = new Date(2009, 10, 12);  // Oct 12, 2009

    // Bad date
    Date dn303323 = new Date(-30, 33, 23);

    t.checkConstructorException(
            // the expected exception
            new IllegalArgumentException("Invalid year: 53000"),

    // the *name* of the class (as a String) whose constructor we invoke
     "Date",

             // the arguments for the constructor
             53000, 12, 30);

}