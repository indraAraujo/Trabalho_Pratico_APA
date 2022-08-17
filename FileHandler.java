import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.LinkedList;

/*
	Lida com os tratamentos dos arquivos
*/
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

	/*
		Cria um novo arquivo com o banco de dados corrigidos
	*/
	public void escritor(String path, LinkedList<Pedigree> pedigreeList) throws IOException { 
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

	/*
		Cria a matriz de saída e salva no arquivo parentesco.txt
	*/
	public void createKinships(String path, float[][] mat, int matSize) throws IOException { 
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
		for (int i = 1; i < matSize; i++) {
			for (int j = 1; j < matSize; j++) {
				buffWrite.append("[" + mat[i][j] + "]");
			}
			buffWrite.append("\n");
		}
		buffWrite.close();
	}

	/*
		Cria a lista de gerações com seus respectivos animais
	*/
	public void writeAll(String path, LinkedList<Generations> generations) throws IOException { 
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

	/*
		Salva os parentescos calculados no arquivo de saída
	*/
	public void outputGenerator(String path, String[] ids, float[][] mat,int matSize) throws IOException {
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
		for (int i = 1; i < matSize; i++) {
			for(int j = 1; j < matSize; j++){
				buffWrite.append("\"" + ids[i] + "\",\"" + ids[j] + "\",\""
					+ mat[i][j] + "\"\n");
			}
		}
		buffWrite.close();
	}

	/*
		Lê o arquivo de entrada com os bancos de dados corrigidos
	*/
	public LinkedList<Pedigree> lerArquivo(LinkedList<Pedigree> fixData) { 
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
			Pedigree exemplo = new Pedigree(animal[0].replace("\"", ""), animal[1].replace("\"", ""),
					animal[2].replace("\"", ""), (cont + shift));
			pedigreeList.add(exemplo);
			cont++;
		}
		return pedigreeList;
	}

	/*
		Descobre quais os animais que são referenciados e não estão no arquivo de entrada original
	*/
	public LinkedList<Pedigree> dataFix() { 
		LinkedList<String> filhos = new LinkedList<String>(); 
		LinkedList<String> pais = new LinkedList<String>(); 
		LinkedList<Pedigree> complement = new LinkedList<Pedigree>();
		String id; 
		Pedigree emptyAncestor; 
		scanner.nextLine(); 
		while (scanner.hasNext()) {
			String line = scanner.nextLine(); 
			String[] animal; 
			String[] words = line.split("\n"); 
			for (int i = 0; i < words.length; i++) { 
				animal = line.split(","); 
				if (!filhos.contains(animal[0])) { 
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
		for (int i = 0; i < pais.size(); i++) {
			if (filhos.contains(pais.get(i))) {
				pais.remove(i);
				i--;
			}
		}
		for (int i = 1; i < pais.size(); i++) {
			id = pais.get(i);
			emptyAncestor = new Pedigree(id.replace("\"", ""), "0", i);
			complement.add(emptyAncestor);
		}

		return complement;
	}
}