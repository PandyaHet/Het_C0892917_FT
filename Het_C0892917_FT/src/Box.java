import java.util.ArrayList;
import java.util.List;

public class Box {
    private List<Object> box_items;
    private int boxNo;
    private int box_capacity;

    //constructor to initialize capacity and box number of Box
    public Box(int capacity, int boxNumber) {
        this.box_capacity = capacity;
        this.boxNo = boxNumber;
        this.box_items = new ArrayList<>();
    }

    //function to add items in box
    public void addItem(Object item) {
        if (box_items.size() < box_capacity) {
            box_items.add(item);
        } else {
            System.out.println("Box is full, no more items are added.");
        }
    }

    // to get items from the box
    public List<Object> getItems() {
        return box_items;
    }

    // to get box number
    public int getBoxNumber() {
        return boxNo;
    }
}
