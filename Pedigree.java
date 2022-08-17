import java.util.*;

/*
    Define as características dos animais da entrada
*/
public class Pedigree {
    String id = "NULL";        
    float parentsKin;            
    int index;
    Pedigree Mae;
    Pedigree Pai;
    String idPai;
    String idMae;
    LinkedList<Pedigree> halfBrothers;
    LinkedList<Pedigree> brothers;
    LinkedList<Pedigree> children = new LinkedList<Pedigree>();

    public Pedigree(String id, String idPai, String idMae, int index) {
        this.id = id;
        this.idPai = idPai;
        this.idMae = idMae;
        this.index = index + 1;
    }

    public Pedigree(String id) {
        this.id = id;
    }

    public Pedigree(String id, String gen0, int index) {
        this.id = id;
        this.idMae = gen0;
        this.idPai = gen0;
        this.index=index;
    }

    public Pedigree getMae() {
        return this.Mae;
    }

    public void setMae(Pedigree Mae) {
        this.Mae = Mae;
    }

    public Pedigree getPai() {
        return this.Pai;
    }

    public void setPai(Pedigree Pai) {
        this.Pai = Pai;
    }
  
    public Pedigree(String id, int index) {
        this.id = id;
        this.Mae.id = "0";
        this.Pai.id = "0";
        this.index = index + 1;
    }
    public void setParentsKin(float x) {
        this.parentsKin = x;
    }

    public LinkedList<Pedigree> getHalfBrothers() {
        return this.halfBrothers;
    }

    public void setHalfBrothers(LinkedList<Pedigree> halfBrothers) {
        this.halfBrothers = halfBrothers;
    }

    public LinkedList<Pedigree> getBrothers() {
        return this.brothers;
    }

    public void setBrothers(LinkedList<Pedigree> brothers) {
        this.brothers = brothers;
    }

    public LinkedList<Pedigree> getChildren() {
        return this.children;
    }

    public void setChildren(LinkedList<Pedigree> children) {
        this.children = children;
    }

    public float getParentsKin() {
        return this.parentsKin;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdPai() {
        return this.idPai;
    }

    public void setIdPai(String idPai) {
        this.idPai = idPai;
    }

    public String getIdMae() {
        return this.idMae;
    }

    public void setIdMae(String idMae) {
        this.idMae = idMae;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return this.id + " " + this.idPai + " " + this.idMae;
    }
}
