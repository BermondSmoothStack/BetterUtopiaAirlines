package apr.ss.utopia.cli.employee;

import apr.ss.utopia.entity.Flight;
import apr.ss.utopia.entity.Seat;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

public class EmployeeAddSeatMenuTest extends TestCase {

    @Mock
    EmployeeAddSeatMenu menu;

    @Before
    public void setUp(){
        Flight testFlight = new Flight();
        Seat firstClass = new Seat();
        Seat businessClass = new Seat();
        Seat economyClass = new Seat();

        firstClass.setCapacity(50);
        businessClass.setCapacity(100);
        economyClass.setCapacity(200);

        testFlight.setFirstClass(firstClass);
        testFlight.setBusinessClass(businessClass);
        testFlight.setEconomyClass(economyClass);

        menu = new EmployeeAddSeatMenu(testFlight);
    }

    @Test
    public void testFirstClassDisplay() {
        EmployeeAddSeatMenu mockedMenu = spy(menu);
        doReturn(1).when(mockedMenu).getIntSeatInput();
        doReturn(100).when(mockedMenu).getIntNewCapacityInput();
        doNothing().when(mockedMenu).updateSeat(any());

        boolean actual = mockedMenu.display();
        assertFalse(actual);
    }

    @Test
    public void testBusinessClassDisplay() {
        EmployeeAddSeatMenu mockedMenu = spy(menu);
        doReturn(2).when(mockedMenu).getIntSeatInput();
        doReturn(100).when(mockedMenu).getIntNewCapacityInput();
        doNothing().when(mockedMenu).updateSeat(any());

        boolean actual = mockedMenu.display();
        assertFalse(actual);
    }

    @Test
    public void testEconomyClassDisplay() {
        EmployeeAddSeatMenu mockedMenu = spy(menu);
        doReturn(3).when(mockedMenu).getIntSeatInput();
        doReturn(100).when(mockedMenu).getIntNewCapacityInput();
        doNothing().when(mockedMenu).updateSeat(any());

        boolean actual = mockedMenu.display();
        assertFalse(actual);
    }

    @Test
    public void testQuitDisplay() {
        EmployeeAddSeatMenu mockedMenu = spy(menu);
        doReturn(4).when(mockedMenu).getIntSeatInput();
        doReturn(100).when(mockedMenu).getIntNewCapacityInput();
        doNothing().when(mockedMenu).updateSeat(any());

        boolean actual = mockedMenu.display();
        assertFalse(actual);
    }
}