import java.util.*;

public class Pedigree { // classe que define cada animal presente na entrada
                            // essa propriedade de indexPai e indexMae nao estao 100% funcionais, mas seria otimo terminar elas pra matriz pegar 100%
    String id = "NULL";         // o erro esta la na classe generations, como aquela classe pega um animal X e procura seus filhos (Y's) na lista de animais
    String idPai = "NULL";          // somente ta fazendo relacao desses Y's somente com a mae ou com o pai (normalmente, com a mae)
    String idMae = "NULL";              // teria que corrigir isso de alguma forma, pra conseguir setar o index do pai e da mae desse animal
    float parentsKin;                   // assim seria otimo pra inserir rapido na matriz, pois ja saberiamos a posicao pra criar o parentesco
    int index;
    int indexMae;
    int indexPai;
    LinkedList<Pedigree> halfBrothers;
    LinkedList<Pedigree> brothers;

    public Pedigree(String id, String pai, String mae, int index) {
        this.id = id;
        this.idPai = pai;
        this.idMae = mae;
        this.index = index + 1;
    }

    public int getIndexMae() {
        return this.indexMae;
    }

    public void setIndexMae(int indexMae) {
        this.indexMae = indexMae;
    }

    public int getIndexPai() {
        return this.indexPai;
    }

    public void setIndexPai(int indexPai) {
        this.indexPai = indexPai;
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
