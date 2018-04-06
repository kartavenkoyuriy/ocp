package chapter2.designPattern.singleton.staticBlock;

public class HayStorage {
    private int quantity = 0;

    //or instantiate in staticBlock initializer block for instantiate/process additional data
    private final static HayStorage instance = new HayStorage();

    private HayStorage() {
    }

    public static HayStorage getInstance(){
        return instance;
    }

    public synchronized void addHeyQuantity(int amount){
        quantity += amount;
    }

    public synchronized boolean removeHey(int amount){
        if(quantity < amount){
            return false;
        }
        quantity -= amount;
        return true;
    }

    public synchronized int getHey() {
        return quantity;
    }
}