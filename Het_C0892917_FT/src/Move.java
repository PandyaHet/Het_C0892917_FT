import java.util.ArrayList;
import java.util.List;

public class Move {

    private List<Box> boxes;

    //constructor for creating class type arraylist and length of arraylist
    public Move(int numberOfBoxes) {
        this.boxes = new ArrayList<>(numberOfBoxes);
    }

    //to class object to arraylist.
    public void addBox(Box box) {
        boxes.add(box);
    }

    //display the items of boxes one by one.
    public void print() {
        System.out.println("Contents of the move:");
        for (Box box : boxes) {
            printBoxContents(box, 0);
        }
    }

    //display the items in the box
    private void printBoxContents(Box box, int depth) {

        for (Object item : box.getItems()) {
            if (item instanceof SingleObject) {
                System.out.println("  " + ((SingleObject) item).getName());
            } else if (item instanceof Box) {
                printBoxContents((Box) item, depth + 1);
            }
        }
    }
    //find item from added boxes
    public int find(String itemName) {
        for (Box box : boxes) {
            int result = findItemInBox(box, itemName);
            if (result >= 0) {
                return result;
            }
        }
        return -1; // Item not found in any box
    }

    //find the item from the current box.
    private int findItemInBox(Box box, String itemName) {
        for (Object item : box.getItems()) {
            if (item instanceof SingleObject && ((SingleObject) item).getName().equals(itemName)) {
                return box.getBoxNumber();
            } else if (item instanceof Box) {
                int result = findItemInBox((Box) item, itemName);
                if (result >= 0) {
                    return result;
                }
            }
        }
        return -1; // Item not found in the current box
    }

    public static void main(String[] args) {

        Move move = new Move(2);

        /*
         * We create and then fill 3 boxes
         * Arguments of the constructor of Box:
         * argument 1: number of items (simple items/objects or box) that the box can hold
         * argument 2: box number
         */

        // box 1 contains scissors
        Box box1 = new Box(1, 1);
        box1.addItem(new SingleObject("scissors"));

        // box 2 contains one book
        Box box2 = new Box(1, 2);
        box2.addItem(new SingleObject("book"));

        // box 3 contains one compass
        // and one box containing one scarf
        Box box3 = new Box(2, 3);
        box3.addItem(new SingleObject("compass"));
        Box box4 = new Box(1, 4);
        box4.addItem(new SingleObject("scarf"));
        box3.addItem(box4);

        // We add the three boxes to the first box of move - see below
        Box box5 = new Box(3, 5);
        box5.addItem(box1);
        box5.addItem(box2);
        box5.addItem(box3);

        // We add one box containing 3 objects to move
        Box box6 = new Box(3, 6);
        box6.addItem(new SingleObject("pencils"));
        box6.addItem(new SingleObject("pens"));
        box6.addItem(new SingleObject("rubber"));

        // We add the two most external boxes to the move
        move.addBox(box5);
        move.addBox(box6);

        // We print all the contents of the move
        move.print();

        // We print the number of the outermost cardboard containing the item "scarf"
        System.out.println("The sarf is in the cardboard number " + move.find("scarf"));
    }


}