import java.util.*;

/*
    Trata a árvore genialógica
*/
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

    /*
        Cria a geração 0
    */
    public void generationZero(LinkedList<Pedigree> pedigreeList) { 
        System.out.println("Total de animais: " + pedigreeList.size());
        for (int i = 0; i < pedigreeList.size(); i++) {
            if (pedigreeList.get(i).getIdPai().equals("0") && pedigreeList.get(i).getIdMae().equals("0")) {
                pedigreeList.get(i).brothers = new LinkedList<Pedigree>();
                pedigreeList.get(i).halfBrothers = new LinkedList<Pedigree>();
                this.addInGeneration(pedigreeList.get(i));
                pedigreeList.remove(i);
                i--;
            }
        }
        System.out.println("\nNumero de animais da geracao 0: " + this.getAnimals().size());
        System.out.println("Restante " + pedigreeList.size());
    }

    /*
        Cria as outras gerações depois da geração 0
    */
    public int followingGenerations(Generations geracao, LinkedList<Pedigree> pedigreeList, Kinships parentesco,
            LinkedList<Generations> geracoes) { 
        Pedigree zero = new Pedigree("0");
        System.out.println("\nConstruindo geracao " + this.gen);
        for (int i = 0; i < geracao.getAnimals().size(); i++) {
            for (int j = 0; j < pedigreeList.size(); j++) {
                if(pedigreeList.get(j).idPai.equals("0")){
                    pedigreeList.get(j).setPai(zero);
                }else if(pedigreeList.get(j).idMae.equals("0")){
                    pedigreeList.get(j).setMae(zero);
                }
                if (pedigreeList.get(j).getIdMae().equals(geracao.getAnimals().get(i).id)) {
                    pedigreeList.get(j).setMae(geracao.getAnimals().get(i));
                    pedigreeList.get(j).Mae.children.add(pedigreeList.get(j));
                    for(int k=0;k<geracao.getAnimals().size();k++){
                        if(geracao.getAnimals().get(k).id.equals(pedigreeList.get(j).idPai)){
                            pedigreeList.get(j).setPai(geracao.getAnimals().get(k));
                            pedigreeList.get(j).Pai.children.add(pedigreeList.get(j));
                            break;
                        }
                    }
                }
                if (pedigreeList.get(j).getIdPai().equals(geracao.getAnimals().get(i).id)) { 
                    pedigreeList.get(j).setPai(geracao.getAnimals().get(i));
                    pedigreeList.get(j).Pai.children.add(pedigreeList.get(j));
                    for(int k=0;k<geracao.getAnimals().size();k++){
                        if(geracao.getAnimals().get(k).id.equals(pedigreeList.get(j).idMae)){
                            pedigreeList.get(j).setMae(geracao.getAnimals().get(k));
                            pedigreeList.get(j).Mae.children.add(pedigreeList.get(j));
                            break;
                        }
                    }
                }
                if(pedigreeList.get(j).getMae() != null && pedigreeList.get(j).getPai() != null){
                    this.addInGeneration(pedigreeList.get(j));
                    pedigreeList.remove(j);
                    j--;
                }
                }
        }


    if(this.getAnimals().size()==0) {
        if (pedigreeList.size() == 0) {
            // igp
        } else {
            System.out.println("Sem animais na geracao - final da familia");
        }
        return 0;
    }else{
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
