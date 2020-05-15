package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

/**
 * Event fired when a user adds a Neighbour to favorites
 */
public class FavoriteNeighbourEvent {

    /**
     * Neighbour to see favorite status updated
     */
    public Neighbour neighbour;

    /**
     * Constructor.
     * @param neighbour
     */
    public FavoriteNeighbourEvent(Neighbour neighbour) {
        this.neighbour = neighbour;
        DI.getNeighbourApiService().changeFavoriteStatus(neighbour);
    }
}
