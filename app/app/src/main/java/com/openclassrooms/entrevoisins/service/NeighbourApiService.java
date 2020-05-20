package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;


/**
 * Neighbour API client
 */
public interface NeighbourApiService {

    /**
     * Get all my Neighbours
     * @return {@link List}
     */
    List<Neighbour> getNeighbours();

    /**
     * Get all my Neighbours
     * @return {@link List}
     */
    List<Neighbour> getFavorites();

    /**
     * Deletes a neighbour
     * @param neighbour
     */
    void deleteNeighbour(Neighbour neighbour);

    /**
     * Create a neighbour
     * @param neighbour
     */
    void createNeighbour(Neighbour neighbour);

    /**
     * Get a Neighbour via its ID
     * @return {@link Neighbour}
     */
    Neighbour getNeighbourById(long id);

    /**
     * Changes favorite status of a neighbour
     * if status is true, sets it to false
     * if status is false, sets it to true
     * @param neighbour
     */
    void changeFavoriteStatus(Neighbour neighbour);
}
