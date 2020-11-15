import helpers.NumberGenerator;

public class Client {
    private int id;

    public Client () {
        this.id = NumberGenerator.generateId();
    }

    public int getId(){
        return id;
    }
}