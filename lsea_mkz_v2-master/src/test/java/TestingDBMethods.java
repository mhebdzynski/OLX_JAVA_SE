import database.DB_Bike;
import database.DB_Frame;
import database.DB_Handling;
import database.DB_Profile;
import lsea.Car;
import lsea.LSEA;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TestingDBMethods {

    @Mock
    EntityManager eM = Mockito.mock(EntityManager.class);
    EntityTransaction eT = Mockito.mock(EntityTransaction.class);
    Query query = Mockito.mock(Query.class);
    DB_Handling db_H = new DB_Handling();
    LSEA prof = new LSEA("Blazej", "Csdafasdfa", "Blaze", "CVwikfdalfds", 21, 3000000.0f);
    Car car = new Car("BlazeCar", "Lambo", 1, 1000000.78f, 3122.5f,prof,1, 2000, 300, 5, "great", "Poland");
    DB_Profile db_prof = new DB_Profile(prof.getNick(), prof.getPassword(), prof.getName(), prof.getSurname(), prof.getAge(), prof.getMoney());
    DB_Frame db_frame = new DB_Frame(1, "material");
    DB_Bike db_bike = new DB_Bike("nameOI","brand",1,1,1,db_prof,1,1,db_frame,1,1,1);

    @Test
    public void loadingListOfFramesToDBTest() {
        Mockito.when(eM.getTransaction()).thenReturn(eT);
        assertEquals(true,db_H.loadFrames("data/frames", eM));
    }

    @Test
    public void loadingListOfFramesToDBWithNonExistingPathTest() {
        Mockito.when(eM.getTransaction()).thenReturn(eT);
        assertEquals(false,db_H.loadFrames("data/fram", eM));
    }

    @Test
    public void loadingListOfProfilesToDBTest() {
        Mockito.when(eM.getTransaction()).thenReturn(eT);
        assertEquals(true, db_H.loadProfiles("data/profiles", eM));
    }

    @Test
    public void loadingListOfProfilesToDBWithNonExistingPathTest() {
        Mockito.when(eM.getTransaction()).thenReturn(eT);
        assertEquals(false, db_H.loadProfiles("data/prof", eM));
    }

    @Test
    public void loadingListOfBikesToDBTest() {
        Mockito.when(eM.getTransaction()).thenReturn(eT);
        assertEquals(true,db_H.loadBikes("data/bikes", eM));
    }

    @Test
    public void loadingListOfBikesToDBWithNonExistingPathTest() {
        Mockito.when(eM.getTransaction()).thenReturn(eT);
        assertEquals(false,db_H.loadBikes("data/bike", eM));
    }

    @Test
    public void loadingListOfCarsToDBTest() throws FileNotFoundException {
        Mockito.when(eM.getTransaction()).thenReturn(eT);
        assertEquals(true,db_H.loadCars("data/cars", eM));
    }

    @Test
    public void loadingListOfCarsToDBWithNonExistingPathTest() throws FileNotFoundException {
        Mockito.when(eM.getTransaction()).thenReturn(eT);
        assertEquals(false,db_H.loadCars("data/car", eM));
    }

    @Test
    public void updatingPasswordTest() {
        db_H.getDbP().add(db_prof);
        Mockito.when(eM.find(DB_Profile.class, db_H.getDbP().size())).thenReturn(db_prof);
        Mockito.when(eM.getTransaction()).thenReturn(eT);
        String pass = "Kamil_Test";
        assertEquals(true, db_H.updatePassword(pass, eM));
    }

    @Test
    public void updatingPasswordWithNoProfilesInTheArrayTest() {
        Mockito.when(eM.find(DB_Profile.class, db_H.getDbP().size())).thenReturn(db_prof);
        Mockito.when(eM.getTransaction()).thenReturn(eT);
        String pass = "Kamil_Test";
        assertEquals(false, db_H.updatePassword(pass, eM));
    }

    @Test
    public void deletingBikeByBrandTest() {
        db_H.getDbB().add(db_bike);
        Mockito.when(eM.find(DB_Bike.class, db_H.getDbB().size())).thenReturn(db_bike);
        Mockito.when(eM.getTransaction()).thenReturn(eT);
        String brand = "brand";
        assertEquals(true, db_H.deleteBikeBrand(brand, eM));
    }

    @Test
    public void deletingBikeByBrandWithNonExistingBrandTest() {
        db_H.getDbB().add(db_bike);
        Mockito.when(eM.find(DB_Bike.class, db_H.getDbB().size())).thenReturn(db_bike);
        Mockito.when(eM.getTransaction()).thenReturn(eT);
        String brand = "non_existing_brand";
        assertEquals(false, db_H.deleteBikeBrand(brand, eM));
    }

    @Test
    public void deletingBikeByBrandWithoutFirstlyAddingItToArrayListTest() {
        Mockito.when(eM.find(DB_Bike.class, db_H.getDbB().size())).thenReturn(db_bike);
        Mockito.when(eM.getTransaction()).thenReturn(eT);
        String brand = "brand";
        assertEquals(false, db_H.deleteBikeBrand(brand, eM));
    }

    @Test
    public void queryingTest() {
        int param1 = 22;
        float param2 = 5000.1f;
        String queryString = "SELECT p FROM DB_Profile p WHERE p.age > :age AND p.money < :money";
        Mockito.when(eM.createQuery(queryString)).thenReturn(query);
        db_H.getDbP().add(db_prof);
        Mockito.when(query.getResultList()).thenReturn(db_H.getDbP());
        assertEquals(true, db_H.query(eM, param1, param2));
    }
}
