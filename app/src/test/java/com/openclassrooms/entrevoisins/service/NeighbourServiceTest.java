package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void getFavoritesWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        for (int i = 0; i < neighbours.size(); i++) {
            neighbours.get(i).setFavorite(true);
        }
        List<Neighbour> favorites = service.getFavorites();
        assertThat(favorites, IsIterableContainingInAnyOrder.containsInAnyOrder(neighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }

    @Test
    public void createNeighbourWithSuccess() {
        Neighbour neighbourToCreate = new Neighbour(System.currentTimeMillis(), "test", "test", "test", "test", "test");
        service.createNeighbour(neighbourToCreate);
        assertTrue(service.getNeighbours().contains(neighbourToCreate));
    }

    @Test
    public void getNeighbourByIdWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        Neighbour neighbour = neighbours.get(0);
        assertSame(neighbour, service.getNeighbourById(neighbour.getId()));
    }

    @Test
    public void changeFavoriteStatusWithSuccess() {
        Neighbour neighbourToChangeFavoriteStatus = service.getNeighbours().get(0);
        neighbourToChangeFavoriteStatus.setFavorite(false);
        service.changeFavoriteStatus(neighbourToChangeFavoriteStatus);
        assertTrue(service.getFavorites().contains(neighbourToChangeFavoriteStatus));
        service.changeFavoriteStatus(neighbourToChangeFavoriteStatus);
        assertFalse(service.getFavorites().contains(neighbourToChangeFavoriteStatus));
    }
}
