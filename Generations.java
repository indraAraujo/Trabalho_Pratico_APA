import java.util.ArrayList;

public class Generations {
    int gen;
    ArrayList<Pedigree> animals = new ArrayList<Pedigree>();

    public Generations(int gen){
        this.gen = gen;
    }

    void addInGeneration(Pedigree animal){
    animals.add(animal);
    }

    public int getGen() {
        return this.gen;
    }

    public void setGen(int gen) {
        this.gen = gen;
    }

    public ArrayList<Pedigree> getAnimals() {
        return this.animals;
    }

    public void setAnimals(ArrayList<Pedigree> animals) {
        this.animals = animals;
    }

}
