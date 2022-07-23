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
            if (start == 1) {
                geracao = new Generations(0);
                start = geracao.generationZero(pedigreeList);
                geracoes.add(geracao);
            } else {
                geracao = new Generations(-1); //
                geracoes.add(geracao);
                geracao = new Generations(0);
                geracao.followingZeroFamilies(pedigreeList);
                geracoes.add(geracao); //
            }
            while (moreChildren == 1) {
                genNumber++;
                nextGeneration = new Generations(genNumber);
                moreChildren = nextGeneration.followingGenerations(geracao, pedigreeList);
                geracoes.add(nextGeneration);
                geracao = nextGeneration;
            }
        }

        for(int i=0;i<geracoes.get(0).getAnimals().size();i++){
            System.out.println(geracoes.get(0).getAnimals().get(i).getId());
        }
        /*
         * try { // CRIA UMA SAIDA COM OS ANIMAIS RESTANTES
         * reader.escritor("saida.txt",pedigreeList);
         * } catch (IOException e) {
         * e.printStackTrace();
         * }
         */

    }
}