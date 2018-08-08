package behavioral.iterator;

import java.util.Iterator;

public class IteratorTest {

    public static void main(String[] args) {
        Menu menu = new Menu();
        Item item = new Item("A",1);
        Item item1 = new Item("B",1);
        Item item2 = new Item("C",1);
        Item item3 = new Item("D",1);
        Item item4 = new Item("E",1);
        menu.addItem(item);
        menu.addItem(item1);
        menu.addItem(item2);
        menu.addItem(item3);
        menu.addItem(item4);
        Iterator<Item> it = menu.createIterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }
        it = menu.createIterator();
        it.remove();
        while (it.hasNext()){
            System.out.println(it.next());
        }


    }
}