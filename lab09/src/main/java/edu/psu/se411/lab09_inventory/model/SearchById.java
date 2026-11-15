package edu.psu.se411.lab09_inventory.model;

public class SearchById implements SearchStrategy<Item> {

    private int id;

    public SearchById(int id) {
        this.id = id;
    }

    @Override
    public boolean matches(Item item) {
        return item.getId() == id;
    }
}