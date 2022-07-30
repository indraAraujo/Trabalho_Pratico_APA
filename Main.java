import java.io.IOException;
import java.util.LinkedList;

public class Main {
    public static void main(String args[]) throws IOException {
        FileHandler arq = new FileHandler(args[0]);
        LinkedList<Pedigree> pedigreeList;
        LinkedList<Generations> geracoes = new LinkedList<Generations>();
        Generations nextGeneration;
        Generations geracao;
        int moreChildren = 1, genNumber = 0;
        pedigreeList = arq.dataFix();
        arq = new FileHandler(args[0]);
        pedigreeList = arq.lerArquivo(pedigreeList);
        int matSize = pedigreeList.size()+1;
        Kinships parentesco = new Kinships(matSize);
        arq.escritor("matriz.csv", pedigreeList);

        while (!pedigreeList.isEmpty()) {
            genNumber = 0;
            moreChildren = 1;
            geracao = new Generations(0);
            geracoes.add(geracao);
            geracao.generationZero(pedigreeList);
            while (moreChildren == 1) {
                genNumber++;
                nextGeneration = new Generations(genNumber);
                moreChildren = nextGeneration.followingGenerations(geracao, pedigreeList,parentesco);
                geracoes.add(nextGeneration);
                geracao = nextGeneration;
                parentesco.addKinship(nextGeneration,geracoes);
            }
        }
        arq.createKinships("parentesco.txt", parentesco.mat,matSize);
          try {
            System.out.println("**********************************************\nGerando arquivo de saida...\n"); 
          arq.writeAll("saida.txt",geracoes);
          } catch (IOException e) {
          e.printStackTrace();
          }
         

    }
}