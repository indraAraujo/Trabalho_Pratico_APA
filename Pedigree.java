import java.util.*;

public class Pedigree {

    String id = "NULL";
    String idPai = "NULL";
    String idMae = "NULL";
    float parentsKin;
    int index;
    LinkedList<Pedigree> halfBrothers;
    LinkedList<Pedigree> brothers;

    public Pedigree(String id, String pai, String mae, int index) {
        this.id = id;
        this.idPai = pai;
        this.idMae = mae;
        this.index = index + 1;
    }

    public Pedigree(String id, int index) {
        this.id = id;
        this.idMae = "0";
        this.idPai = "0";
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
