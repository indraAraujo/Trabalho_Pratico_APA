import java.util.LinkedList;

public class Main
{
    public static void main(String args[])
    {
    Reader reader = new Reader(args[0]);
    LinkedList pedigreeList = reader.lerArquivo();
    System.out.println("Total de animais listados: "+pedigreeList.size());
    //for (int i=0;i<pedigreeList.size();i++){
    //    System.out.println(pedigreeList.get(i).toString());}         
    }
}