import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.LinkedList;

public class Reader {
	File file;
	String fileName;
	Scanner scanner = null;

	public Reader(String filePath) {
		fileName = filePath;
		try {
			file = new File(fileName);
			scanner = new Scanner(file);
		} catch (FileNotFoundException fe) {
			System.out.println("Exception: " + fe);
		}
	}

	public LinkedList<Pedigree> lerArquivo() {
		LinkedList<Pedigree> pedigreeList = new LinkedList<Pedigree>();
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
}