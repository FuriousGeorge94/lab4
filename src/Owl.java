public class Owl {
    private String owlName;
    private int owlAge;
    private double owlWeight;

    public Owl(String name, int age, double weight){
        owlName = name;
        owlAge = age;
        owlWeight = weight;
    }


   public String getName() {
        return owlName;
   }

    public void setName(String name) {
        owlName = name;
    }
    public int getAge() {
        return owlAge;
    }
    public void setAge(int age) {
        owlAge = age;
    }
    public double getWeight() {
        return owlWeight;
    }
    public void setWeight(double weight) {
        owlWeight = weight;
    }

    public boolean equals(Owl other) {
        return owlAge == other.owlAge && owlWeight == other.owlWeight && owlName.equals(other.owlName);
    }

    public static void main(String[] args){
        Owl owl1 = new Owl("owl1",5, 12.0);
        Owl owl2 = new Owl("owl2",5, 12.0);
        Owl owl3 = new Owl("owl1",5, 12.0);
        System.out.println(owl1.equals(owl2));
        System.out.println(owl1.equals(owl3));

    }
}

