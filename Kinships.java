import java.util.*;

public class Kinships { // classe que gera a matriz de coanscestralidade
    float[][] mat;      // vai criando lista de irmaos e meio irmaos, e assim calcula o parentesco de um animal com seus irmaos/meio irmaos
    LinkedList <Pedigree> brothers;
    LinkedList <Pedigree> halfBrothers;

    public Kinships(int matSize, Generations geracao){
        this.mat = new float[matSize][matSize];
        for(int i=0;i<geracao.getAnimals().size();i++){
            mat[geracao.getAnimals().get(i).getIndex()][geracao.getAnimals().get(i).getIndex()] = (float)1;
        }
    }
    
    public void addKinship(Generations generation){ // preenche a matriz de coanscestralidade
        for(int i=0;i<generation.getAnimals().size();i++){          // vai pegando os animais da geracao
            this.mat[generation.getAnimals().get(i).getIndex()][generation.getAnimals().get(i).getIndex()] = (float)1;
            searchForBrothers(generation.getAnimals().get(i), generation);
            if(generation.getGen() == 1){
                generation.getAnimals().get(i).setParentsKin((float)0);
                if(generation.getAnimals().get(i).getMae()!=null){
                this.mat[generation.getAnimals().get(i).getIndex()][generation.getAnimals().get(i).getMae().getIndex()] = (float)0.5;
                }
                if(generation.getAnimals().get(i).getPai()!=null){
                this.mat[generation.getAnimals().get(i).getIndex()][generation.getAnimals().get(i).getPai().getIndex()] = (float)0.5;
                }
                if(!this.brothers.isEmpty()){
                    for(int j=0;j<generation.getAnimals().get(i).getBrothers().size();j++){
                        this.mat[generation.getAnimals().get(i).getIndex()][generation.getAnimals().get(i).getBrothers().get(j).getIndex()] = (float)0.25;
                    }
                } if (!this.halfBrothers.isEmpty()){
                    for(int j=0;j<generation.getAnimals().get(i).getHalfBrothers().size();j++){
                    this.mat[generation.getAnimals().get(i).getIndex()][generation.getAnimals().get(i).getHalfBrothers().get(j).getIndex()] = (float)0.125;
                }
            }
        }else{ // caso seja de uma geracao >= 2
            if(generation.getAnimals().get(i).getMae()!=null && generation.getAnimals().get(i).getPai()!=null){
                if(generation.getAnimals().get(i).getPai().getBrothers().contains(generation.getAnimals().get(i).getMae())){ // se entrar eh pq os pais sao irmaos completos
                    generation.getAnimals().get(i).setParentsKin((float)0.25);
                }else if(generation.getAnimals().get(i).getPai().getHalfBrothers().contains(generation.getAnimals().get(i).getMae())){ // se entrar eh pq os pais sao meio irmaos
                    generation.getAnimals().get(i).setParentsKin((float)0.125);
                }}else{
                    generation.getAnimals().get(i).setParentsKin((float)0);
                }
                if(generation.getAnimals().get(i).getMae()!=null){
                this.mat[generation.getAnimals().get(i).getIndex()][generation.getAnimals().get(i).getMae().getIndex()] = (float)0.5+((generation.getAnimals().get(i).getParentsKin()/2));
                } if(generation.getAnimals().get(i).getPai()!=null){
                this.mat[generation.getAnimals().get(i).getIndex()][generation.getAnimals().get(i).getPai().getIndex()] = (float)0.5+((generation.getAnimals().get(i).getParentsKin()/2));
                }
                if(!this.halfBrothers.isEmpty()){
                    for(int j=0;j<generation.getAnimals().get(i).getBrothers().size();j++){
                        this.mat[generation.getAnimals().get(i).getIndex()][generation.getAnimals().get(i).getBrothers().get(j).getIndex()] = (float)0.25+(generation.getAnimals().get(i).getParentsKin()/4);
                    }
                } if (!this.halfBrothers.isEmpty()){
                    for(int j=0;j<generation.getAnimals().get(i).getHalfBrothers().size();j++){
                    this.mat[generation.getAnimals().get(i).getIndex()][generation.getAnimals().get(i).getHalfBrothers().get(j).getIndex()] = (float)0.125;
                }
            }
         }
    }
}

    public void searchForBrothers(Pedigree animal, Generations generation){ // funcao que procura pelos irmaos e meio irmaos do animal
        this.brothers = new LinkedList<Pedigree>(); // cria novas listas pra cada animal
        this.halfBrothers = new LinkedList<Pedigree>();
        for(int i=0;i<generation.getAnimals().size();i++){
            if((generation.getAnimals().get(i).getIdPai().equals(animal.getIdPai()) && generation.getAnimals().get(i).getIdMae().equals(animal.getIdMae()) && !generation.getAnimals().get(i).getId().equals(animal.getId())) && !generation.getAnimals().get(i).getIdMae().equals("0")&& !generation.getAnimals().get(i).getIdPai().equals("0")){
                brothers.add(generation.getAnimals().get(i)); // caso tiver mesmo pai e mesma mae eh um irmao completo
                //System.out.println(this.animal.getId()+" e "+generation.getAnimals().get(i).getId()+" sao irmaos");
                
            }else if((generation.getAnimals().get(i).getIdPai().equals(animal.getIdPai()) && !generation.getAnimals().get(i).getIdMae().equals(animal.getIdMae())) || (generation.getAnimals().get(i).getIdMae().equals(animal.getIdMae()) && !generation.getAnimals().get(i).getIdPai().equals(animal.getIdPai()))&& !generation.getAnimals().get(i).getIdMae().equals("0")&& !generation.getAnimals().get(i).getIdPai().equals("0")){
                halfBrothers.add(generation.getAnimals().get(i)); // caso for irmao somente de pai ou somente de mae eh meio irmao
                //System.out.println(this.animal.getId()+" e "+generation.getAnimals().get(i).getId()+" sao meio irmaos");
            }
        }
        animal.setBrothers(brothers);
        animal.setHalfBrothers(halfBrothers);
    }

    public LinkedList<Pedigree> getBrothers() {
        return this.brothers;
    }

    public void setBrothers(LinkedList<Pedigree> brothers) {
        this.brothers = brothers;
    }

    public LinkedList<Pedigree> getHalfBrothers() {
        return this.halfBrothers;
    }

    public void setHalfBrothers(LinkedList<Pedigree> halfBrothers) {
        this.halfBrothers = halfBrothers;
    }

}
