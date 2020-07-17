import database.DB_Handling;
import lsea.Bike;
import lsea.Car;
import lsea.Frame;
import lsea.LSEA;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
@RunWith(MockitoJUnitRunner.class)
public class TestingNonDBMethods {

    @Mock
    EntityManager eM = Mockito.mock(EntityManager.class);
    EntityTransaction eT = Mockito.mock(EntityTransaction.class);
    DB_Handling db_H = new DB_Handling();
    LSEA prof = new LSEA("Blazej", "Csdafasdfa", "Blaze", "CVwikfdalfds", 21, 3000000.0f);
    Car car = new Car("BlazeCar", "Lambo", 1, 1000000.78f, 3122.5f, prof, 1, 2000, 300, 5, "great", "Poland");
    Frame fram = Frame.carbon;
    Bike bikeTest = new Bike("nameOI","brand",1,1,1,prof,1,1,fram,1,1,1);
    List<Bike> bikes = new ArrayList<>();


    @Test
    public void createProfileTest() {
        ArrayList<LSEA> profiles = new ArrayList<>();
        Mockito.when(eM.getTransaction()).thenReturn(eT);
        System.setIn(new ByteArrayInputStream("Blazej\nCsdafasdfa\nBlaze\nCVwikfdalfds\n21\n3000000.0f".getBytes()));
        assertEquals(prof.toString(), prof.createProfile(profiles, eM, db_H).toString());
    }

    @Test
    public void saveProfilesTestExistingPath() {
        ArrayList<LSEA> profiles = new ArrayList<>();
        profiles.add(prof);
        prof.save(profiles, "data/profilesTest");
        File file = new File("data/profilesTest.txt");
        assertEquals(true, file.exists());
    }

    @Test
    public void addCarOfferTest() {
        Mockito.when(eM.getTransaction()).thenReturn(eT);
        System.setIn(new ByteArrayInputStream("BlazeCar\nLambo\ngreat\nPoland\n1\n1000000.78f\n3122.5f\n300\n2000\n5".getBytes()));
        assertEquals(car.toString(), car.createOfferCar(prof, eM, db_H).toString());
    }

    @Test
    public void buyItemCarTestGood() throws CloneNotSupportedException {
        LSEA profTest = new LSEA("Zibex", "SDASDASDdsadas", "Patryk", "Dunajewski", 22, 111111111111111f);
        Mockito.when(eM.getTransaction()).thenReturn(eT);
        Car carTmp = car;
        carTmp.setAccount(profTest);
        assertEquals(carTmp.toString(), car.buyItem(profTest).toString());
    }

    @Test
    public void buyItemCarTestNotEnoughMoney() throws CloneNotSupportedException {
        LSEA profTest = new LSEA("Zibex", "SDASDASDdsadas", "Patryk", "Dunajewski", 22, 1);
        Mockito.when(eM.getTransaction()).thenReturn(eT);
        assertEquals(car.toString(), car.buyItem(profTest).toString());
    }

    @Test
    public void buyItemCarTestNotAvailable() throws CloneNotSupportedException {
        LSEA profTest = new LSEA("Zibex", "SDASDASDdsadas", "Patryk", "Dunajewski", 22, 1);
        Mockito.when(eM.getTransaction()).thenReturn(eT);
        car.setIs_available(0);
        assertEquals(car.toString(), car.buyItem(profTest).toString());
    }

    @Test
    public void saveCarTestExistingPath() {
        ArrayList<Car> cars = new ArrayList<>();
        String uPath = "data/carsTest";
        cars.add(car);
        car.save(db_H, cars, uPath);
        File file = new File(uPath + ".txt");
        assertEquals(true, file.exists());
    }

    @Test
    public void saveCarTestNotExistingPath() {
        ArrayList<Car> cars = new ArrayList<>();
        String uPath = "data/carsTest";
        cars.add(car);
        car.save(db_H, cars, uPath);
        File file = new File(uPath + "s.txt");
        assertEquals(false, file.exists());
    }

    @Test
    public void createBikeOffer(){
        Mockito.when(eM.getTransaction()).thenReturn(eT);

        System.setIn(new ByteArrayInputStream("bike\nbikos\ncarbon\n1\n124124\n2354345\n12\n1\n1\n1\n".getBytes()));

        assertEquals(new Bike("bike","bikos",1,124124,2354345,prof,1,12, Frame.carbon,1,1,1).toString(),
                bikeTest.createOfferBike(prof, eM, db_H).toString());
    }

    @Test
    public void saveBikesTest(){
        String uPath = "data/bikesTest";
        Mockito.when(eM.getTransaction()).thenReturn(eT);

        bikes.add(new Bike("Test1", "BikosTest1",1,(float) 123.11,(float)12.21, prof, 1,12, Frame.carbon, 1,1,1));
        bikes.add(new Bike("Test2", "BikosTest2",1,(float) 123.11,(float)12.21, prof, 1,12, Frame.carbon, 1,1,1));
        File fileTest = new File(uPath + ".txt");
        bikeTest.save(db_H, bikes, uPath);
        assertEquals(true, fileTest.exists());

    }
}