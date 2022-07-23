public class Pedigree {
    
    String id="NULL";
    String idPai="NULL";
    String idMae="NULL";

    public Pedigree(String id, String pai, String mae){
    this.id = id;
    this.idPai = pai;
    this.idMae = mae;
    }

    public Pedigree(String id){
        this.id = id;
        this.idMae = "";
        this.idPai = "";
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


    @Override
    public String toString() {
        return this.id+" "+this.idPai+" "+this.idMae+" ";
    }
}
