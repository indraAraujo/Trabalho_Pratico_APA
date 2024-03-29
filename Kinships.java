import java.util.*;

/*
    Trata a matriz de coancestralidade
*/
public class Kinships { 
    float[][] mat;      
    String[] ids;
    LinkedList <Pedigree> brothers;
    LinkedList <Pedigree> halfBrothers;

    public Kinships(int matSize, Generations geracao,String[][] output){
        this.mat = new float[matSize][matSize];
        this.ids = new String[matSize];
        for(int i=0;i<geracao.getAnimals().size();i++){
            mat[geracao.getAnimals().get(i).getIndex()][geracao.getAnimals().get(i).getIndex()] = (float)1;
            ids[geracao.getAnimals().get(i).getIndex()] = geracao.getAnimals().get(i).id;
        }
    }

    /*
        Preenche a matriz de coancestralidade
    */
    public void addKinship(Generations generation, String[][] output){ 
        for(int i=0;i<generation.getAnimals().size();i++){         
            this.mat[generation.getAnimals().get(i).getIndex()][generation.getAnimals().get(i).getIndex()] = (float)1;
            ids[generation.getAnimals().get(i).getIndex()] = generation.getAnimals().get(i).id;
            searchForBrothers(generation.getAnimals().get(i), generation);
            if(generation.getGen() == 1){
                generation.getAnimals().get(i).setParentsKin((float)0);
                if(generation.getAnimals().get(i).getMae()!=null){
                this.mat[generation.getAnimals().get(i).getIndex()][generation.getAnimals().get(i).getMae().getIndex()] = (float)0.5;
                this.mat[generation.getAnimals().get(i).getMae().getIndex()][generation.getAnimals().get(i).getIndex()] = this.mat[generation.getAnimals().get(i).getIndex()][generation.getAnimals().get(i).getMae().getIndex()];
                }
                if(generation.getAnimals().get(i).getPai()!=null){
                this.mat[generation.getAnimals().get(i).getIndex()][generation.getAnimals().get(i).getPai().getIndex()] = (float)0.5;
                this.mat[generation.getAnimals().get(i).getPai().getIndex()][generation.getAnimals().get(i).getIndex()] = this.mat[generation.getAnimals().get(i).getIndex()][generation.getAnimals().get(i).getPai().getIndex()];
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
            if(!generation.getAnimals().get(i).getMae().id.equals("0") && !generation.getAnimals().get(i).getPai().id.equals("0")){
                if(generation.getAnimals().get(i).getPai().getBrothers().contains(generation.getAnimals().get(i).getMae())){ // se entrar eh pq os pais sao irmaos completos
                    generation.getAnimals().get(i).setParentsKin((float)0.25);
                }else if(generation.getAnimals().get(i).getPai().getHalfBrothers().contains(generation.getAnimals().get(i).getMae())){ // se entrar eh pq os pais sao meio irmaos
                    generation.getAnimals().get(i).setParentsKin((float)0.125);
                }}else{
                    generation.getAnimals().get(i).setParentsKin((float)0);
                }
                if(generation.getAnimals().get(i).getMae()!=null){
                this.mat[generation.getAnimals().get(i).getIndex()][generation.getAnimals().get(i).getMae().getIndex()] = (float)0.5+((generation.getAnimals().get(i).getParentsKin()/2));
                this.mat[generation.getAnimals().get(i).getMae().getIndex()][generation.getAnimals().get(i).getIndex()] = this.mat[generation.getAnimals().get(i).getIndex()][generation.getAnimals().get(i).getMae().getIndex()];
                if(generation.getAnimals().get(i).Mae.brothers!=null){
                for(int j=0;j<generation.getAnimals().get(i).Mae.brothers.size();j++){
                    this.mat[generation.getAnimals().get(i).getIndex()][generation.getAnimals().get(i).Mae.brothers.get(j).getIndex()] = this.mat[generation.getAnimals().get(i).getIndex()][generation.getAnimals().get(i).Mae.index]*this.mat[generation.getAnimals().get(i).Mae.index][generation.getAnimals().get(i).Mae.brothers.get(j).getIndex()];
                    this.mat[generation.getAnimals().get(i).Mae.brothers.get(j).getIndex()][generation.getAnimals().get(i).getIndex()] = this.mat[generation.getAnimals().get(i).getIndex()][generation.getAnimals().get(i).Mae.brothers.get(j).getIndex()];
                }}if(generation.getAnimals().get(i).Mae.brothers!=null){
                    for(int j=0;j<generation.getAnimals().get(i).Mae.halfBrothers.size();j++){
                    this.mat[generation.getAnimals().get(i).getIndex()][generation.getAnimals().get(i).Mae.halfBrothers.get(j).getIndex()] = this.mat[generation.getAnimals().get(i).getIndex()][generation.getAnimals().get(i).Mae.index]*this.mat[generation.getAnimals().get(i).Mae.index][generation.getAnimals().get(i).Mae.halfBrothers.get(j).getIndex()];
                    this.mat[generation.getAnimals().get(i).Mae.halfBrothers.get(j).getIndex()][generation.getAnimals().get(i).getIndex()] = this.mat[generation.getAnimals().get(i).getIndex()][generation.getAnimals().get(i).Mae.halfBrothers.get(j).getIndex()];
                }}
                } if(generation.getAnimals().get(i).getPai()!=null){
                this.mat[generation.getAnimals().get(i).getIndex()][generation.getAnimals().get(i).getPai().getIndex()] = (float)0.5+((generation.getAnimals().get(i).getParentsKin()/2));
                this.mat[generation.getAnimals().get(i).getPai().getIndex()][generation.getAnimals().get(i).getIndex()] = this.mat[generation.getAnimals().get(i).getIndex()][generation.getAnimals().get(i).getPai().getIndex()];
                if(generation.getAnimals().get(i).Pai.brothers!=null){
                for(int j=0;j<generation.getAnimals().get(i).Pai.brothers.size();j++){
                    this.mat[generation.getAnimals().get(i).getIndex()][generation.getAnimals().get(i).Pai.brothers.get(j).getIndex()] = this.mat[generation.getAnimals().get(i).getIndex()][generation.getAnimals().get(i).Pai.index]*this.mat[generation.getAnimals().get(i).Pai.index][generation.getAnimals().get(i).Pai.brothers.get(j).getIndex()];
                    this.mat[generation.getAnimals().get(i).Pai.brothers.get(j).getIndex()][generation.getAnimals().get(i).getIndex()] = this.mat[generation.getAnimals().get(i).getIndex()][generation.getAnimals().get(i).Pai.brothers.get(j).getIndex()];
                }}if(generation.getAnimals().get(i).Pai.halfBrothers!=null){
                for(int j=0;j<generation.getAnimals().get(i).Pai.halfBrothers.size();j++){
                    this.mat[generation.getAnimals().get(i).getIndex()][generation.getAnimals().get(i).Pai.halfBrothers.get(j).getIndex()] = this.mat[generation.getAnimals().get(i).getIndex()][generation.getAnimals().get(i).Pai.index]*this.mat[generation.getAnimals().get(i).Pai.index][generation.getAnimals().get(i).Pai.halfBrothers.get(j).getIndex()];
                    this.mat[generation.getAnimals().get(i).Pai.halfBrothers.get(j).getIndex()][generation.getAnimals().get(i).getIndex()] = this.mat[generation.getAnimals().get(i).getIndex()][generation.getAnimals().get(i).Pai.halfBrothers.get(j).getIndex()];
                }}
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

    /*
        Procura pelos irmãos e meio-irmãos de um animal
    */
    public void searchForBrothers(Pedigree animal, Generations generation){ 
        this.brothers = new LinkedList<Pedigree>(); // cria novas listas pra cada animal
        this.halfBrothers = new LinkedList<Pedigree>();
        for(int i=0;i<generation.getAnimals().size();i++){
            if((generation.getAnimals().get(i).getIdPai().equals(animal.getIdPai()) && generation.getAnimals().get(i).getIdMae().equals(animal.getIdMae()) && !generation.getAnimals().get(i).getId().equals(animal.getId())) && !generation.getAnimals().get(i).getIdMae().equals("0")&& !generation.getAnimals().get(i).getIdPai().equals("0")){
                brothers.add(generation.getAnimals().get(i)); // caso tiver mesmo pai e mesma mãe é um irmão completo
                
            }else if((generation.getAnimals().get(i).getIdPai().equals(animal.getIdPai()) && !generation.getAnimals().get(i).getIdMae().equals(animal.getIdMae())&& (!generation.getAnimals().get(i).getIdPai().equals("0"))) || (generation.getAnimals().get(i).getIdMae().equals(animal.getIdMae()) && !generation.getAnimals().get(i).getIdPai().equals(animal.getIdPai()) && !generation.getAnimals().get(i).getIdMae().equals("0"))){
                halfBrothers.add(generation.getAnimals().get(i)); // caso for irmãoo somente de pai ou somente de mãe é meio irmão
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
