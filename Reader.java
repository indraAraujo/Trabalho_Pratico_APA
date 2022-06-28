import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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

	public void lerArquivo() {
		while (scanner.hasNext()) {
			String line = scanner.nextLine();
			String[] animal;
			String[] words = line.split(" ");
			for(int i=0;i<words.length;i++){
				animal = line.split(",");
				Pedigree exemplo = new Pedigree(animal[0], animal[1], animal[2], animal[3], animal[4], animal[5], animal[6]);
				System.out.println(exemplo.toString());
			}
		}
	}
}