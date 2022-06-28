public class Pedigree {
    
    String id="NULL";
    String idPai="NULL";
    String idMae="NULL";
    String safra="NULL";
    String sexo="NULL";
    String iecc="NULL";
    String selecionado="NULL";

    public Pedigree(String id, String pai, String mae, String safra, String sexo, String iecc, String selecionado){
    this.id = id;
    this.idPai = pai;
    this.idMae = mae;
    this.safra = safra;
    this.sexo = sexo;
    this.iecc = iecc;
    this.selecionado = selecionado;
    }
    @Override
    public String toString() {
        return "ID: "+this.id+" Pai: "+this.idPai+" Mae: "+this.idMae+" Safra: "+this.safra+" Sexo: "+this.sexo+" IECC: "+this.iecc+" Selecionado: "+this.selecionado;
    }
}
