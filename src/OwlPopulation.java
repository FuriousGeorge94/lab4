import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.util.concurrent.ForkJoinWorkerThread;

public class OwlPopulation {
    private String fileName;
    private Owl[] data;
    public OwlPopulation(String name) throws FileNotFoundException {

        fileName = name;
        populateData();
    }

    public int populateData() throws FileNotFoundException {
        File f = new File(fileName);
        Scanner scanner = new Scanner(f);

        int numLines = 0;
        while(scanner.hasNextLine()){
            numLines++;
            String s = scanner.nextLine();
        }
        scanner.close();

        data = new Owl[numLines];   //data is is allocated the exact amount of space it needs
        scanner = new Scanner(f);

        //TODO: Populate the data with owls constructed from the lines of the file given
        for (int i = 0; i < numLines ;i++){
            String s = scanner.nextLine();
            String[] owlLine = s.split(",");
            data[i] = new Owl(owlLine[0], Integer.parseInt(owlLine[1]), Double.parseDouble(owlLine[2]));
        }

        return numLines;
    }


    public double averageAge(){
        double totalYrs = 0;
        double numOwls  = 0;

        for (int i = 0; i < data.length; i++) {
            totalYrs += data[i].getAge();
            numOwls  += 1;
        }
        return totalYrs/numOwls;

    }

    public Owl getYoungest() {
        Owl youngest= data[0];

        for (int i = 0; i < data.length; i++) {
            if (data[i].getAge() < youngest.getAge()){
                youngest = data[i];
            }
        }
        return youngest;
    }
    public Owl getHeaviest(){
        Owl heaviest = data[0];
        for (int i = 0; i < data.length; i++) {
            if (data[i].getWeight() > heaviest.getWeight()){
                heaviest = data[i];
            }
        }
        return heaviest;
    }


    public boolean containsOwl(Owl other){
        //TODO: method you can implement as a helper function to your merge method
        return false;
    }
    public void merge(OwlPopulation other){
        //TODO: a brief overview of what you can do to implement this method is given below:

        //1) determine (and store) the distinct owls in the other population.
        int numUnique = 0;
        boolean distinct;
        int index = 0;
        boolean[] distinctArray = new boolean[other.data.length];
        for (int i = 0; i<other.data.length; i++) {
            distinct = true;
            for (int j = 0; j<data.length; j++) {
                    if ( other.data[i].equals(data[j])){
                      distinct= false;

                }
            }
            distinctArray[i] = distinct;
            if (distinct)
              numUnique++;
        }
        Owl[] uniqueOwls= new Owl[data.length + numUnique];
        for (int i = 0; i < uniqueOwls.length; i++) {
          if (i < data.length){
            uniqueOwls[i] = data[i];
       } else { 
           while (!distinctArray[index]) {
             index++;
           }
           uniqueOwls[i] = other.data[index];
           index++;
           
         }
        }
        data = uniqueOwls;
    }

    public int popSize(){
        return data.length;
    }

    public String toString(){
      return (  "The youngest owl is " + getYoungest().getName() + ", who is "     + getYoungest().getAge())  +
              "\nThe Heaviest owl is " + getHeaviest().getName() + ", who weighs " + getHeaviest().getWeight()+
              "\nThe Average age of the population is " + averageAge();
    }

    public static void main(String[] args) {
        try {

            //The following should run when you are complete. Feel free to comment out as you see fit while you work.
            OwlPopulation pop1 = new OwlPopulation("owlPopulation1.csv");
            System.out.println(pop1 == null);
            System.out.println(pop1.popSize());

            OwlPopulation pop2 = new OwlPopulation("owlPopulation2.csv");
            System.out.println(pop1);
            System.out.println(pop2);
            System.out.println(pop2.popSize());

            pop1.merge(pop2);

            System.out.println(pop1);
            System.out.println(pop1.popSize());

        }
        catch (FileNotFoundException f){
            System.out.println("File not found.");

        }
    }
}
