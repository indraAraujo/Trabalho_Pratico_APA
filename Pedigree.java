public class Pedigree {
    
    String id="NULL";
    String idPai="NULL";
    String idMae="NULL";

    public Pedigree(String id, String pai, String mae){
    this.id = id;
    this.idPai = pai;
    this.idMae = mae;
    }
    @Override
    public String toString() {
        return this.id+" "+this.idPai+" "+this.idMae+" ";
    }
}
