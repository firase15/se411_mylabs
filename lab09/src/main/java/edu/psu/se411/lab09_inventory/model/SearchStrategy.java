package edu.psu.se411.lab09_inventory.model;

@FunctionalInterface
public interface SearchStrategy<T> {
    boolean matches(T item);
}