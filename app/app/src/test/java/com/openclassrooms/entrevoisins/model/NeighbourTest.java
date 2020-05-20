package com.openclassrooms.entrevoisins.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * Unit test on Neighbour class
 */
@RunWith(JUnit4.class)
public class NeighbourTest {

    private Neighbour mNeighbour = new Neighbour(123456789, "testName", "testAvatarUrl", "testAddress", "testPhoneNumber", "testAboutMe");

    @Test
    public void getIdWithSuccess() {
        assertEquals(123456789, mNeighbour.getId());
    }

    @Test
    public void setIdWithSuccess() {
        mNeighbour.setId(987654321);
        assertEquals(987654321, mNeighbour.getId());
    }

    @Test
    public void getNameWithSuccess() {
        assertEquals("testName", mNeighbour.getName());
    }

    @Test
    public void setNameWithSuccess() {
        mNeighbour.setName("testNameSet");
        assertEquals("testNameSet", mNeighbour.getName());
    }

    @Test
    public void getAvatarUrlWithSuccess() {
        assertEquals("testAvatarUrl", mNeighbour.getAvatarUrl());
    }

    @Test
    public void setAvatarUrlWithSuccess() {
        mNeighbour.setAvatarUrl("testAvatarUrlSet");
        assertEquals("testAvatarUrlSet", mNeighbour.getAvatarUrl());
    }

    @Test
    public void getAddressWithSuccess() {
        assertEquals("testAddress", mNeighbour.getAddress());
    }

    @Test
    public void setAddressWithSuccess() {
        mNeighbour.setAddress("testAddressSet");
        assertEquals("testAddressSet", mNeighbour.getAddress());
    }

    @Test
    public void getPhoneNumberWithSuccess() {
        assertEquals("testPhoneNumber", mNeighbour.getPhoneNumber());
    }

    @Test
    public void setPhoneNumberWithSuccess() {
        mNeighbour.setPhoneNumber("testPhoneNumberSet");
        assertEquals("testPhoneNumberSet", mNeighbour.getPhoneNumber());
    }

    @Test
    public void getAboutMeWithSuccess() {
        assertEquals("testAboutMe", mNeighbour.getAboutMe());
    }

    @Test
    public void setAboutMeWithSuccess() {
        mNeighbour.setAboutMe("testAboutMeSet");
        assertEquals("testAboutMeSet", mNeighbour.getAboutMe());
    }

    @Test
    public void isFavoriteTest() {
        assertFalse(mNeighbour.isFavorite());
    }

    @Test
    public void setFavoriteWithSuccess() {
        mNeighbour.setFavorite(true);
        assertTrue(mNeighbour.isFavorite());
    }
}
