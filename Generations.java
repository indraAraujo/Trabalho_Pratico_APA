import java.util.*;

public class Generations {
    int gen;
    ArrayList<Pedigree> animals = new ArrayList<Pedigree>();

    public Generations(int gen) {
        this.gen = gen;
    }

    void addInGeneration(Pedigree animal) {
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

    public void generationZero(LinkedList<Pedigree> pedigreeList) {
        System.out.println("Total de animais: "+pedigreeList.size());
        System.out.println("----------------------------------------------\nConstruindo a geracao "+this.gen);
        for (int i = 0; i < pedigreeList.size(); i++) {
            if (pedigreeList.get(i).getIdPai().equals("0") && pedigreeList.get(i).getIdMae().equals("0")) {
                this.addInGeneration(pedigreeList.get(i));
                pedigreeList.remove(i);
                i--;
            }
        }
        System.out.println("Numero de animais da geracao 0: " + this.getAnimals().size());
        System.out.println("Restante " + pedigreeList.size());
    }

    public int followingGenerations(Generations geracao, LinkedList<Pedigree> pedigreeList, Kinships parentesco) {
        System.out.println("\nConstruindo geracao " + this.gen);
        for (int i = 0; i < geracao.getAnimals().size(); i++) {
            for (int j = 0; j < pedigreeList.size(); j++) {
                if (pedigreeList.get(j).getIdPai().equals(geracao.getAnimals().get(i).id)
                        || pedigreeList.get(j).getIdMae().equals(geracao.getAnimals().get(i).id)) {
                    this.addInGeneration(pedigreeList.get(j));
                    pedigreeList.remove(j);
                    j--;
                }
            }
        }
        if (this.getAnimals().size() == 0) {
            if (pedigreeList.size() == 0) {
                // igp
            } else {
                System.out.println("Sem animais na geracao - final da familia");
            }
            return 0;
        } else {
            System.out.println("Numero de animais da geracao " + this.gen + ": " + this.getAnimals().size());
            System.out.println("Restante " + pedigreeList.size());
            if (pedigreeList.size() == 0) {
                System.out.println("----------------------------------------------\nTodos os animais inseridos.");
                return 0;
            }
            return 1;

        }
    }

}
