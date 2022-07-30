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
    
    public void addKinship(Generations generation,LinkedList<Generations> generations){ // preenche a matriz de coanscestralidade
        String grabberPai;                                                              
        int grabberIndexPai;
        int grabberIndexMae;
        for(int i=0;i<generation.getAnimals().size();i++){          // vai pegando os animais da geracao
            this.mat[generation.getAnimals().get(i).getIndex()][generation.getAnimals().get(i).getIndex()] = (float)1; // seta o parentesco dele com ele mesmo pra 1
            this.searchForBrothers(generation.getAnimals().get(i),generation); // procura os irmaos e meio irmaos do animal
            if(!this.getBrothers().isEmpty()){ // caso tenha irmaos completos
             for(int j=0;j<this.getBrothers().size();j++){
             //System.out.println("Animal: "+generation.getAnimals().get(i).getIndex()+" Irmaos completos: "+this.getBrothers());
             if(generation.getGen() == 1){ // se for a geracao 1, o parentesco dos pais eh 0 (os animais da geracao 0 n tem parentesco nenhum, pois seus pais sao 0 e 0)
                grabberIndexMae = generation.getAnimals().get(i).getIndexMae();
                grabberIndexPai = generation.getAnimals().get(i).getIndexPai();
                generation.getAnimals().get(i).setParentsKin((float)0);
                this.mat[generation.getAnimals().get(i).getIndex()][grabberIndexMae] = (float)0.5; // seta o parentesco do animal com os pais pra 0.5, ja que os pais n tem parentesco
                this.mat[generation.getAnimals().get(i).getIndex()][grabberIndexPai] = (float)0.5;
                this.getBrothers().get(j).setParentsKin((float)0);
                this.mat[generation.getAnimals().get(i).getIndex()][this.getBrothers().get(j).getIndex()] = (float)0.25+generation.getAnimals().get(i).getParentsKin(); // parentesco com os irmaos 0.25 pois os mais nao tem parentesco
             }else{ // caso nao for a geracao 1, para definir o parentesco de dois irmaos completos eh necessario somar 0.25 + o parentesco dos pais / 4 (algo assim, tem que revisar e ajeitar essa parte)
                grabberPai = generation.getAnimals().get(i).getIdPai(); // escrevendo isso percebi que ta faltando setar o parentesco destes animais com seus pais, por isso somente a geracao 1 tem parentesco com seus pais no parentesco.txt
                for(int k=0;k<generations.get(generation.getGen()).getAnimals().size();k++){
                    if(generations.get(generation.getGen()).getAnimals().get(k).getId().equals(grabberPai)){
                        this.mat[generation.getAnimals().get(i).getIndex()][this.getBrothers().get(j).getIndex()] = (float)0.25+generation.getAnimals().get(i).getParentsKin();
                    }    
                }
                this.getBrothers().get(j).setParentsKin((float)0);
                this.mat[generation.getAnimals().get(i).getIndex()][this.getBrothers().get(j).getIndex()] = (float)0.25+generation.getAnimals().get(i).getParentsKin();
             }
             }
         }
         if(!this.getHalfBrothers().isEmpty()){ // caso tenha meio irmaos
             for(int j=0;j<this.getHalfBrothers().size();j++){
                 this.mat[generation.getAnimals().get(i).getIndex()][this.getHalfBrothers().get(j).getIndex()] = (float)0.125;
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
