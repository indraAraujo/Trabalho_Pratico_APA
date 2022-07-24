import java.io.IOException;
import java.util.LinkedList;

public class Main {
    public static void main(String args[]) {
        Reader reader = new Reader(args[0]);
        LinkedList<Generations> geracoes = new LinkedList<Generations>();
        LinkedList<Pedigree> pedigreeList = reader.lerArquivo();
        Generations nextGeneration;
        int moreChildren = 1, genNumber = 0, start = 1;
        Generations geracao;
        while (!pedigreeList.isEmpty()) {
            genNumber = 0;
            moreChildren = 1;
            geracao = new Generations(0);
            geracoes.add(geracao);
            if (start == 1) {
                start = geracao.generationZero(pedigreeList);
            } else {
                geracao.followingZeroFamilies(pedigreeList);
            }
            while (moreChildren == 1) {
                genNumber++;
                nextGeneration = new Generations(genNumber);
                moreChildren = nextGeneration.followingGenerations(geracao, pedigreeList);
                geracoes.add(nextGeneration);
                geracao = nextGeneration;
            }
        }

          try { 
          reader.writeAll("saida.txt",geracoes);
          } catch (IOException e) {
          e.printStackTrace();
          }
         

    }
}