import java.util.*;

public class Kinships {
    float[][] mat;
    LinkedList <Pedigree> brothers;
    LinkedList <Pedigree> halfBrothers;

    public Kinships(int matSize){
        this.mat = new float[matSize][matSize];
    }

    public void addKinship(Generations generation,LinkedList<Generations> generations){
        String grabberPai;
        for(int i=0;i<generation.getAnimals().size();i++){
            this.searchForBrothers(generation.getAnimals().get(i),generation);
            if(!this.getBrothers().isEmpty()){
             for(int j=0;j<this.getBrothers().size();j++){
             //System.out.println("Animal: "+generation.getAnimals().get(i).getIndex()+" Irmaos completos: "+this.getBrothers());
             if(generation.getGen() == 1){
                generation.getAnimals().get(i).setParentsKin((float)0);
                this.getBrothers().get(j).setParentsKin((float)0);
                this.mat[generation.getAnimals().get(i).getIndex()][this.getBrothers().get(j).getIndex()] = (float)0.25+generation.getAnimals().get(i).getParentsKin(); 
             }else{
                grabberPai = generation.getAnimals().get(i).getIdPai();
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
         if(!this.getHalfBrothers().isEmpty()){
             for(int j=0;j<this.getHalfBrothers().size();j++){
                 this.mat[generation.getAnimals().get(i).getIndex()][this.getHalfBrothers().get(j).getIndex()] = (float)0.125;
             }
         }
         }
    }

    public void searchForBrothers(Pedigree animal, Generations generation){
        this.brothers = new LinkedList<Pedigree>();
        this.halfBrothers = new LinkedList<Pedigree>();
        for(int i=0;i<generation.getAnimals().size();i++){
            if((generation.getAnimals().get(i).getIdPai().equals(animal.getIdPai()) && generation.getAnimals().get(i).getIdMae().equals(animal.getIdMae()) && !generation.getAnimals().get(i).getId().equals(animal.getId())) && !generation.getAnimals().get(i).getIdMae().equals("0")&& !generation.getAnimals().get(i).getIdPai().equals("0")){
                brothers.add(generation.getAnimals().get(i));
                //System.out.println(this.animal.getId()+" e "+generation.getAnimals().get(i).getId()+" sao irmaos");
                
            }else if((generation.getAnimals().get(i).getIdPai().equals(animal.getIdPai()) && !generation.getAnimals().get(i).getIdMae().equals(animal.getIdMae())) || (generation.getAnimals().get(i).getIdMae().equals(animal.getIdMae()) && !generation.getAnimals().get(i).getIdPai().equals(animal.getIdPai()))&& !generation.getAnimals().get(i).getIdMae().equals("0")&& !generation.getAnimals().get(i).getIdPai().equals("0")){
                halfBrothers.add(generation.getAnimals().get(i));
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
