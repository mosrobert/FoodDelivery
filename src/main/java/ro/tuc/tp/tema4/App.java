package ro.tuc.tp.tema4;

import ro.tuc.tp.tema4.BusinessLayer.DeliveryService;
import ro.tuc.tp.tema4.BusinessLayer.MenuItem;
import ro.tuc.tp.tema4.DataLayer.Serializator;
import ro.tuc.tp.tema4.Presentation.Login;

import java.awt.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Serializator.deserializareProduse();
        Serializator.deserializareClienti();
        Serializator.deserializareComenzi();
        Login window = new Login();
    }
}
