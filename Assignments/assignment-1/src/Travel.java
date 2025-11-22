interface IHousing{

}

interface ITransportation{

}

class Hut implements IHousing{
  int  population;
  int capacity;
  Hut(int population, int capacity){
      this.population = population;
      this.capacity = capacity;
  }
}

class Inn implements IHousing{
    String name;
    int  population;
    int capacity;
    int stalls;
    Inn(String name,int population, int capacity, int stalls){
        this.name = name;
        this.population = population;
        this.capacity = capacity;
        this.stalls = stalls;
    }
}
class Castle implements IHousing{
    String name;
    String familyName;
    int  population;
    int carriages;
    Castle(String name,String familyName,int population, int carriages){
        this.name = name;
        this.familyName = familyName;
        this.population = population;
        this.carriages = carriages;
    }
}
class Horse implements ITransportation{
    IHousing from ,to;
    String name;
    String color;
    Horse(IHousing from, IHousing to, String name, String color){
        this.from = from;
        this.to = to;
        this.name = name;
        this.color = color;
    }
}
class Carriage implements ITransportation{
    IHousing from ,to;
    int tonnage;
    Carriage(IHousing from, IHousing to, int tonnage){
        this.from = from;
        this.to = to;
        this.tonnage = tonnage;
    }
}

class ExamplesTravel{
    IHousing winterfell = new Castle("Winterfell", "Stark", 500,6);
    IHousing crossroads = new Inn("Inn At The Crossroads", 40, 20, 12);
    IHousing hovel = new Hut(5,1);
    IHousing redKeep = new Castle("The red Keep", "Bratheon", 2000, 20);
    IHousing castleBlack = new Castle("Castle Black", "The night watch", 3000, 10);
    IHousing cheapInn = new Inn("The Inn", 20, 20, 4);

    ITransportation brego = new Horse(castleBlack,winterfell,"Brego","black" );
    ITransportation clove = new Horse(winterfell, hovel, "Clove", "red");
    ITransportation royaCarriage = new Carriage(redKeep, crossroads, 6);
    ITransportation regularCarriage = new Carriage(winterfell, cheapInn, 1);
}