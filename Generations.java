import java.util.ArrayList;
import java.util.LinkedList;

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

    public int generationZero(LinkedList<Pedigree> pedigreeList) {
        System.out.println("Construindo geracao 0");
        for (int i = 0; i < pedigreeList.size(); i++) {
            if (pedigreeList.get(i).getIdPai().equals("0") && pedigreeList.get(i).getIdMae().equals("0")) {
                this.addInGeneration(pedigreeList.get(i));
                pedigreeList.remove(i);
                i--;
            }
        }
        System.out.println("Numero de animais da geracao 0: " + this.getAnimals().size());
        System.out.println("Restante " + pedigreeList.size());
        return 0;
    }

    public int followingGenerations(Generations geracao, LinkedList<Pedigree> pedigreeList) {
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
                System.out.println("\nTodos os animais inseridos.\n");
                return 0;
            }
            return 1;

        }
    }

    public void followingZeroFamilies(LinkedList<Pedigree> pedigreeList) {
        System.out.println("\nConstruindo geracao 0");
        Pedigree primeiro = pedigreeList.getFirst();
        Pedigree aux;
        if (!primeiro.getIdPai().equals("0")) {
            if (!primeiro.getIdMae().equals("0")) {
                aux = new Pedigree(primeiro.getIdPai());
                this.addInGeneration(aux);
                aux = new Pedigree(primeiro.getIdMae());
                this.addInGeneration(aux);
            } else {
                aux = new Pedigree(primeiro.getIdPai());
                this.addInGeneration(aux);
            }
        } else {
            aux = new Pedigree(primeiro.getIdMae());
            this.addInGeneration(aux);
        }
    }

}
