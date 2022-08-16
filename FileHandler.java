import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.LinkedList;

public class FileHandler {
	File file;
	String fileName;
	Scanner scanner = null;
	String output[][];
	int lines = 0;

	public FileHandler(String filePath) {
		fileName = filePath;
		try {
			file = new File(fileName);
			scanner = new Scanner(file);
		} catch (FileNotFoundException fe) {
			System.out.println("Exception: " + fe);
		}
	}

	public void escritor(String path, LinkedList<Pedigree> pedigreeList) throws IOException { // funcao pra criar o
																								// new.csv (explicacao
																								// abaixo)
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
		this.output = new String[pedigreeList.size() * pedigreeList.size()][3];
		this.lines = pedigreeList.size() * pedigreeList.size();
		for (int i = 0; i < pedigreeList.size(); i++) {
			buffWrite.append("\"" + pedigreeList.get(i).getId() + "\",\"" + pedigreeList.get(i).getIdPai() + "\",\""
					+ pedigreeList.get(i).getIdMae() + "\"\n");
			this.output[i][0] = "\"" + pedigreeList.get(i).getId() + "\",";
		}
		buffWrite.close();
	}

	public void createKinships(String path, float[][] mat, int matSize) throws IOException { // funcao que cria a matriz
																								// de saida
																								// (parentesco.txt)
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
		for (int i = 1; i < matSize; i++) {
			for (int j = 1; j < matSize; j++) {
				buffWrite.append("[" + mat[i][j] + "]");
			}
			buffWrite.append("\n");
		}
		buffWrite.close();
	}

	public void writeAll(String path, LinkedList<Generations> generations) throws IOException { // funcao que cria a
																								// lista geracoes com
																								// seus respectivos
																								// animais
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
		for (int i = 0; i < generations.size(); i++) {
			buffWrite.append(generations.get(i).gen + "<");
			if (generations.get(i).getGen() == 0) {
				for (int j = 0; j < generations.get(i).getAnimals().size(); j++) {
					buffWrite.append(generations.get(i).getAnimals().get(j).getId() + ",");
				}
			} else {
				for (int j = 0; j < generations.get(i).getAnimals().size(); j++) {
					buffWrite.append(generations.get(i).getAnimals().get(j).toString() + ",");
				}
			}
			buffWrite.append(">\n");
		}
		buffWrite.close();
	}

	public void outputGenerator(String path, LinkedList<Generations> geracoes, float[][] mat) throws IOException {
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
		String idAnimal, idMae, idPai;
		for (int g = 0; g < geracoes.size(); g++) {
			for (int i = 0; i < geracoes.get(g).getAnimals().size(); i++) {
				// PARENTESCO COM OS PAIS
				idAnimal = geracoes.get(g).getAnimals().get(i).id;
				if (!geracoes.get(g).getAnimals().get(i).idMae.equals("0")) {
					idMae = geracoes.get(g).getAnimals().get(i).getMae().id;
					buffWrite.append("\"" + idAnimal + "\",\"" + idMae + "\",\""
					+ mat[geracoes.get(g).getAnimals().get(i).index][geracoes.get(g).getAnimals().get(i).getMae()
							.getIndex()]
					+ "\"\n");
				}
				if (!geracoes.get(g).getAnimals().get(i).idPai.equals("0")) {
					idPai = geracoes.get(g).getAnimals().get(i).getPai().id;
					buffWrite.append("\"" + idAnimal + "\",\"" + idPai + "\",\""
						+ mat[geracoes.get(g).getAnimals().get(i).index][geracoes.get(g).getAnimals().get(i).getPai()
								.getIndex()]
						+ "\"\n");
				}
				buffWrite.append("\"" + idAnimal + "\",\"" + idAnimal + "\",\""
						+ mat[geracoes.get(g).getAnimals().get(i).index][geracoes.get(g).getAnimals().get(i).index]
						+ "\"\n");
				// PARENTESCO COM OS IRMAOS E MEIO IRMAOS
				for(int j=0;j<geracoes.get(g).getAnimals().get(i).brothers.size();j++){
					buffWrite.append("\"" + idAnimal + "\",\"" + geracoes.get(g).getAnimals().get(i).getBrothers().get(j).id + "\",\""
					+ mat[geracoes.get(g).getAnimals().get(i).index][geracoes.get(g).getAnimals().get(i).getBrothers().get(j).getIndex()]
					+ "\"\n");
				}
				for(int j=0;j<geracoes.get(g).getAnimals().get(i).halfBrothers.size();j++){
					buffWrite.append("\"" + idAnimal + "\",\"" + geracoes.get(g).getAnimals().get(i).getHalfBrothers().get(j).id + "\",\""
					+ mat[geracoes.get(g).getAnimals().get(i).index][geracoes.get(g).getAnimals().get(i).getHalfBrothers().get(j).getIndex()]
					+ "\"\n");
				}
				// PARENTESCO COM OS FILHOS
				for(int j=0;j<geracoes.get(g).getAnimals().get(i).children.size();j++){
					buffWrite.append("\"" + idAnimal + "\",\"" + geracoes.get(g).getAnimals().get(i).getChildren().get(j).id + "\",\""
					+ mat[geracoes.get(g).getAnimals().get(i).index][geracoes.get(g).getAnimals().get(i).getChildren().get(j).getIndex()]
					+ "\"\n");
				}
			}
		}
		buffWrite.close();
	}

	public LinkedList<Pedigree> lerArquivo(LinkedList<Pedigree> fixData) { // funcao que le a entrada
		LinkedList<Pedigree> pedigreeList = fixData;
		LinkedList<String> ids = new LinkedList<String>();
		for (int i = 0; i < pedigreeList.size(); i++) {
			ids.add(pedigreeList.get(i).getId());
		}
		int shift = pedigreeList.size();
		int cont = 0;
		scanner.nextLine();
		while (scanner.hasNext()) {
			String line = scanner.nextLine();
			String[] animal;
			animal = line.split(",");
			/*
			 * for(int i=0;i<pedigreeList.size();i++){
			 * if(pedigreeList.get(i).id.equals(pai.id)){
			 * pai.setIndex(pedigreeList.get(i).index);
			 * }else if(pedigreeList.get(i).id.equals(mae.id)){
			 * mae.setIndex(pedigreeList.get(i).index);
			 * }
			 * }
			 */
			Pedigree exemplo = new Pedigree(animal[0].replace("\"", ""), animal[1].replace("\"", ""),
					animal[2].replace("\"", ""), (cont + shift));
			// System.out.println(exemplo.toString());
			pedigreeList.add(exemplo);
			cont++;
		}
		return pedigreeList;
	}

	public LinkedList<Pedigree> dataFix() { // essa funcao eh a funcao que faz o preprocessamente que eu estava falando,
											// ela eh MUITO importante porque nesse .csv
		LinkedList<String> filhos = new LinkedList<String>(); // de entrada existem alguns pais e maes que sao
																// referenciados, que nem ao menos fazem parte do banco
		LinkedList<String> pais = new LinkedList<String>(); // isto eh, imaginem que o animal X esta dizendo que seus
															// pais sao Y e Z, porem Y e Z nao fazem parte
		LinkedList<Pedigree> complement = new LinkedList<Pedigree>(); // do banco de dados. Isso acontece direto nesse
																		// banco, entao esta funcao eh essencial.
		String id; // Se nao usar essa funcao a arvore genealogica fica quebrada em diversas
					// arvores
		Pedigree emptyAncestor; // pois ela nao consegue criar uma relacao entre todos os animais, em outras
								// palavras
		scanner.nextLine(); // a geracao 0 nao fica completa, e isso quebra toda a arvore.
		while (scanner.hasNext()) {
			String line = scanner.nextLine(); // basicamente esta funcao rola antes de ler de vez o .csv, ele procura
												// por esses pais que
			String[] animal; // nao estao presentes no banco de dados, e adiciona eles no COMECO do new.csv,
								// com seus pais.
			String[] words = line.split("\n"); // sendo 0 e 0 (assim eles tambem pertencem a geracao 0). Caso essa
												// funcao nao existisse e a gente
			for (int i = 0; i < words.length; i++) { // simplesmente assumisse que esses pais e maes que nao estao no
														// banco fossem igual a 0, nao seriam
				animal = line.split(","); // considerados parentescos entre irmaos desses pais e maes, entao, mais uma
											// vez, essa funcao eh
				if (!filhos.contains(animal[0])) { // essencial pro programa.
					filhos.add(animal[0]);
				}
				if (!pais.contains(animal[1])) {
					pais.add(animal[1]);
				}
				if (!pais.contains(animal[2])) {
					pais.add(animal[2]);
				}
			}
		}
		// System.out.println("Pais: "+pais.size());
		// System.out.println("Iniciando preprocessamento...");
		for (int i = 0; i < pais.size(); i++) {
			if (filhos.contains(pais.get(i))) {
				pais.remove(i);
				i--;
			}
		}
		// System.out.println("** Fim **");
		// System.out.println("Pais: "+pais.size());
		for (int i = 1; i < pais.size(); i++) {
			id = pais.get(i);
			emptyAncestor = new Pedigree(id.replace("\"", ""), "0", i);
			complement.add(emptyAncestor);
			// System.out.println(emptyAncestor.toString());
		}

		return complement;
	}
}