import java.util.*;

public class Kinships {
    Pedigree animal;
    Generations generation;
    Parents parent;
    LinkedList <Pedigree> brothers;
    LinkedList <Pedigree> halfBrothers;


    public Kinships(Generations generation,Pedigree animal) {
        this.animal = animal;
        this.generation = generation;
        this.brothers = new LinkedList<Pedigree>();
        this.halfBrothers = new LinkedList<Pedigree>();
    }

    public void searchForBrothers(){
        for(int i=0;i<generation.getAnimals().size();i++){
            if((generation.getAnimals().get(i).getIdPai().equals(this.animal.getIdPai()) && generation.getAnimals().get(i).getIdMae().equals(this.animal.getIdMae()) && !generation.getAnimals().get(i).getId().equals(this.animal.getId())) && !generation.getAnimals().get(i).getIdMae().equals("0")&& !generation.getAnimals().get(i).getIdPai().equals("0")){
                this.brothers.add(generation.getAnimals().get(i));
                //System.out.println(this.animal.getId()+" e "+generation.getAnimals().get(i).getId()+" sao irmaos");
                
            }else if((generation.getAnimals().get(i).getIdPai().equals(this.animal.getIdPai()) && !generation.getAnimals().get(i).getIdMae().equals(this.animal.getIdMae())) || (generation.getAnimals().get(i).getIdMae().equals(this.animal.getIdMae()) && !generation.getAnimals().get(i).getIdPai().equals(this.animal.getIdPai()))&& !generation.getAnimals().get(i).getIdMae().equals("0")&& !generation.getAnimals().get(i).getIdPai().equals("0")){
                this.halfBrothers.add(generation.getAnimals().get(i));
                //System.out.println(this.animal.getId()+" e "+generation.getAnimals().get(i).getId()+" sao meio irmaos");
            }
        }
    }

/*    public void searchForParents(){
        if(this.generation.getGen()==1){
            this.parent = new Parents((float)0.5,(float)0.5);
        }else if(this.generation.getGen()==0){
            this.parent = new Parents();
        }else{
            float x=0;
            this.parent = new Parents((float)0.5+x,(float)0.5+x);
        }
    }

    public float kinshipBetweenAnimals(Pedigree joao, String maria){


        return (float) 0.1;
    }
*/
    public Pedigree getAnimal() {
        return this.animal;
    }

    public void setAnimal(Pedigree animal) {
        this.animal = animal;
    }

    public Generations getGeneration() {
        return this.generation;
    }

    public void setGeneration(Generations generation) {
        this.generation = generation;
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
