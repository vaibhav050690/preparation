package behavioral.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Menu implements Iterable<Item>{

    List<Item> menuItems;

    public Menu(){
        menuItems = new ArrayList<>();
    }

    public void addItem(Item item){
        menuItems.add(item);
    }

    public Iterator<Item> createIterator(){
        return new MenuIterator();
    }

    @Override
    public Iterator<Item> iterator() {
        return new MenuIterator();
    }

    class MenuIterator implements Iterator<Item>{

        int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < menuItems.size();
        }

        @Override
        public Item next() {
            return menuItems.get(currentIndex++);
        }

        @Override
        public void remove() {
            menuItems.remove(menuItems.size()-1);
        }

        public Item first(){
            return menuItems.get(0);
        }

        public Item currentItem(){
            return menuItems.get(currentIndex);
        }
    }
}