package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getFavorites() {

        List<Neighbour> favorites = new ArrayList<>();
        for (Neighbour neighbour : neighbours)
            if (neighbour.isFavorite())
                favorites.add(neighbour);

        return favorites;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    /**
     * {@inheritDoc}
     * @param id
     */
    @Override
    public Neighbour getNeighbourById(long id) throws NullPointerException {

        for (Neighbour neighbour : neighbours)
            if (neighbour.getId() == id)
                return neighbour;

        throw new NullPointerException("No neighbour matching the id : " + id + ".");
    }

    /**
     * {@inheritDoc}
     * @param neighbour
     */
    @Override
    public void changeFavoriteStatus(Neighbour neighbour) {
        neighbour.setFavorite(!neighbour.isFavorite());
    }
}
