import java.util.*;

public class Generations { // classe que monta a arvore genialogica baseado nas geracoes.
    int gen; // primeiro a geracao 0, dps a geracao 1 (filhos da geracao 0), dps geracao 2
             // (filhos da geracao 1) ... etc
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

    public void generationZero(LinkedList<Pedigree> pedigreeList) { // funcao para criar a geracao 0 (geracao com pai &&
                                                                    // mae == 0)
        System.out.println("Total de animais: " + pedigreeList.size());
        System.out.println("----------------------------------------------\nConstruindo a geracao " + this.gen);
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

    public int followingGenerations(Generations geracao, LinkedList<Pedigree> pedigreeList, Kinships parentesco,
            LinkedList<Generations> geracoes) { // funcao para criar as geracoes seguintes (geracoes != 0)
        Pedigree mae, pai;
        String idmae, idpai;
        Generations searchingGen;
        System.out.println("\nConstruindo geracao " + this.gen);
        for (int i = 0; i < geracao.getAnimals().size(); i++) {
            for (int j = 0; j < pedigreeList.size(); j++) {
                if (pedigreeList.get(j).getIdMae().equals(geracao.getAnimals().get(i).id)
                        || pedigreeList.get(j).getIdPai().equals(geracao.getAnimals().get(i).id)) { // erro de
                                                                                                    // logica,
                    if (pedigreeList.get(j).getIdMae().equals(geracao.getAnimals().get(i).id)) {
                        pedigreeList.get(j).setMae(geracao.getAnimals().get(i));
                    }
                    if (pedigreeList.get(j).getIdPai().equals(geracao.getAnimals().get(i).id)) {
                        pedigreeList.get(j).setPai(geracao.getAnimals().get(i));
                    }
                    this.addInGeneration(pedigreeList.get(j));
                    pedigreeList.remove(j);
                    j--;
                }
            }
        }

        for (int i = 0; i < this.animals.size(); i++) { // procura pelo pai/mae faltante dentro da geracao
            pai = this.animals.get(i).getPai();
            mae = this.animals.get(i).getMae();

            if (mae == null) {
                idmae = this.animals.get(i).getIdMae();
                for (int j = 0; j < geracao.getAnimals().size(); j++) {
                    if (this.animals.get(i).getIdMae().equals(geracao.getAnimals().get(j).getId())) {
                        this.animals.get(i).setMae(geracao.getAnimals().get(j));
                    }
                }
            } else if (pai == null) {
                for (int j = 0; j < geracao.getAnimals().size(); j++) {
                    if (this.animals.get(i).getIdPai().equals(geracao.getAnimals().get(j).getId())) {
                        this.animals.get(i).setPai(geracao.getAnimals().get(j));
                    }
                }
            }

        } // eh preciso procurar pelo pai faltante nas outras geracoes tambem,
          // infelizmente (faz o tempo crescer d+)

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
