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

	public FileHandler(String filePath) {
		fileName = filePath;
		try {
			file = new File(fileName);
			scanner = new Scanner(file);
		} catch (FileNotFoundException fe) {
			System.out.println("Exception: " + fe);
		}
	}

	public void escritor(String path, LinkedList<Pedigree> pedigreeList) throws IOException {
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
		for(int i=0;i<pedigreeList.size();i++){
			buffWrite.append(pedigreeList.get(i).toString() + "\n");
		}
		buffWrite.close();
	}

	public void writeAll(String path, LinkedList<Generations> generations) throws IOException {
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
		for(int i=0;i<generations.size();i++){
			buffWrite.append(generations.get(i).gen + "<");
			if(generations.get(i).getGen() == 0){
				for(int j=0;j<generations.get(i).getAnimals().size();j++){
					buffWrite.append(generations.get(i).getAnimals().get(j).getId() + ",");
				}
			}else{
			for(int j=0;j<generations.get(i).getAnimals().size();j++){
				buffWrite.append(generations.get(i).getAnimals().get(j).toString() + ",");
			}
		}
			buffWrite.append(">\n");
		}
		buffWrite.close();
	}

	public LinkedList<Pedigree> lerArquivo(LinkedList<Pedigree> fixData) {
		LinkedList<Pedigree> pedigreeList = fixData;
		scanner.nextLine();
		while (scanner.hasNext()) {
			String line = scanner.nextLine();
			String[] animal;
			String[] words = line.split("\n");
			for(int i=0;i<words.length;i++){
				animal = line.split(",");
				Pedigree exemplo = new Pedigree(animal[0].replace("\"", ""), animal[1].replace("\"",""), animal[2].replace("\"",""));
				//System.out.println(exemplo.toString());
				pedigreeList.add(exemplo);
			}
		}
		return pedigreeList;
	}
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
			for(int i=0;i<words.length;i++){
				animal = line.split(",");
                if(!filhos.contains(animal[0])){
                    filhos.add(animal[0]);
                }
                if(!pais.contains(animal[1])){
                    pais.add(animal[1]);
                }
                if(!pais.contains(animal[2])){
                    pais.add(animal[2]);
                }
			}
		}
       // System.out.println("Pais: "+pais.size());
      //  System.out.println("Iniciando preprocessamento...");
        for(int i=0;i<pais.size();i++){
            if(filhos.contains(pais.get(i))){
                pais.remove(i);
                i--;
            }
        }
       // System.out.println("** Fim **");
       // System.out.println("Pais: "+pais.size());
		for(int i=0;i<pais.size();i++){
			id = pais.get(i);
			emptyAncestor = new Pedigree(id.replace("\"", ""));
			complement.add(emptyAncestor);
		}
		return complement;
	}
}