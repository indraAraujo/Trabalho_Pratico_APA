import java.util.LinkedList;

public class Main {
    public static void main(String args[]) {
        Reader reader = new Reader(args[0]);
        LinkedList<Generations> geracoes = new LinkedList<Generations>();
        LinkedList<Pedigree> pedigreeList = reader.lerArquivo();
        //System.out.println("Total de animais listados: " + pedigreeList.size());
        int[][] familyMat = new int[pedigreeList.size()][pedigreeList.size()];
        System.out.println("Construindo geracao 0");
        Generations geracao = new Generations(0);
        for (int i = 0; i < pedigreeList.size(); i++) {
            if(pedigreeList.get(i).getIdPai().equals("0") && pedigreeList.get(i).getIdMae().equals("0")){
            geracao.addInGeneration(pedigreeList.get(i));
            pedigreeList.remove(i);
            }
        }
        System.out.println("Numero de animais da geracao 0: "+geracao.getAnimals().size());
        geracoes.add(geracao);
        System.out.println("restante "+pedigreeList.size());
        for(int i=0;i<geracao.getAnimals().size();i++){
            for(int j=0;j<pedigreeList.size();j++){
                if(pedigreeList.get(j).getIdPai().equals(geracao.getAnimals().get(i).id) || pedigreeList.get(j).getIdMae().equals(geracao.getAnimals().get(i).id)){
                   // System.out.println("achei parentesco"); // demora demais // USAR MATRIZ PRA VERIFICAR O FILHOS
                }
            }   
        }
    }
}