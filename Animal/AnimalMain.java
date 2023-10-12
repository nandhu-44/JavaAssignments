interface Animal {
    void eat();
    void print();
}

class Birds implements Animal {
    public void eat() {
        System.out.println("Birds are eating.");
    }

    public void print() {
        System.out.println("I am a bird.");
    }
}

class Insects implements Animal {
    public void eat() {
        System.out.println("Insects are eating.");
    }

    public void print() {
        System.out.println("I am a insect.");
    }
}

class Mammals implements Animal {
    public void eat() {
        System.out.println("Mammals are eating.");
    }

    public void print() {
        System.out.println("I am a mammal.");
    }
}

public class AnimalMain {
    public static void main(String[] args) {
        Animal[] animals = new Animal[3];
        animals[0] = new Birds();
        animals[1] = new Insects();
        animals[2] = new Mammals();

        for (Animal animal : animals) {
            System.out.println();
            animal.print();
            animal.eat();
        }
    }
}
