import java.io.IOException;
import java.util.LinkedList;

public class Main {
    public static void main(String args[]) throws IOException {
        FileHandler arq = new FileHandler("teste.txt");
        LinkedList<Pedigree> pedigreeList;
        LinkedList<Generations> geracoes = new LinkedList<Generations>();
        Generations nextGeneration;
        Generations geracao;
        int moreChildren = 1, genNumber = 0;
        pedigreeList = arq.dataFix(); // PREPROCESSAMENTO, explicacao em filehandler
        arq = new FileHandler(args[0]);
        pedigreeList = arq.lerArquivo(pedigreeList); // le a entrada
        int matSize = pedigreeList.size()+1; // define o tamanho da matriz de saida (total de animais x total de animais, pois a relacao de parentesco tem que ser de 1 pra 1)
        arq.escritor("new.csv", pedigreeList); // cria new.csv, o banco de dados corrigido, com todos os animais

            genNumber = 0;
            moreChildren = 1;
            geracao = new Generations(0);
            geracoes.add(geracao);
            geracao.generationZero(pedigreeList); // cria a geracao 0
            Kinships parentesco = new Kinships(matSize,geracao); // cria a matriz de parentesco
            
            while (moreChildren == 1) { // enquanto existam animais que nao estao adicionados na arvore genealogica
                genNumber++;
                nextGeneration = new Generations(genNumber);  // cria uma nova geracao
                moreChildren = nextGeneration.followingGenerations(geracao, pedigreeList,parentesco,geracoes); // essa funcao existe pra encontrar a relacao entre a geracao passada e seus filhos
                geracoes.add(nextGeneration); // adiciona a nova geracao a lista de geracoes
                geracao = nextGeneration; // seta a geracao passada pra ser igual a geracao atual, para assim dar continuidade as proximas geracoes
                parentesco.addKinship(nextGeneration); // adiciona os parentescos da geracao 
            }
        arq.createKinships("parentesco.txt", parentesco.mat,matSize); // quando nao existam mais animais para serem inseridos, cria um .txt com a matriz de parentesco
          try {                                                           // para entender essa matriz de parentesco, olhem para o new.csv que seria o banco de dados correto
            System.out.println("**********************************************\nGerando arquivo de saida...\n"); 
          arq.writeAll("saida.txt",geracoes); // gera uma saida com a lista de geracoes e os correspondentes animais 
          } catch (IOException e) {
          e.printStackTrace();
          }
         

    }
}