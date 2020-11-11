package edu.depaul.se422.shoppingapp;

import static org.junit.jupiter.api.Assertions.*;
import edu.depaul.se433.shoppingapp.*;
import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.time.*;
import java.util.*;
import static org.mockito.Mockito.*;

public class PurchaseDBOTest {

    //Test saving of purchase

    @Test
    @DisplayName("Tests that purchase database saves items...")
    void saveItem(){

        PurchaseDBO db = mock(PurchaseDBO.class);
        Bill bill = new Bill(100.00, 25.00, 0.06, 115.00);
        PurchaseAgent agent = new PurchaseAgent(db);
        Purchase item = Purchase.make("Sal", LocalDate.now(), bill.total(), "IL", String.valueOf(ShippingType.NEXT_DAY));
        agent.save(item);
        verify(db).savePurchase(item);

    }

    //Test get of purchase

    @Test
    @DisplayName("Tests that database can retrieve a list of purchases")
    void retrievePurchases(){

        PurchaseDBO db = mock(PurchaseDBO.class);
        PurchaseAgent agent = new PurchaseAgent(db);

        for(int i = 0; i < 5; i++){

            Bill bill = new Bill( (25 * (i + 1)), (i % 2 == 0) ? 10.00 : 25.00, (i % 2 == 0) ? 0 : 0.06, (30 * (i + 1)) );
            Purchase item = Purchase.make("Sal", LocalDate.of(2020, 6, (2 + i)), bill.total(), (i % 2 == 0) ? "OH" : "IL", (i % 2 == 0) ? String.valueOf(ShippingType.STANDARD) : String.valueOf(ShippingType.NEXT_DAY));
            agent.save(item);

        }

        List<Purchase> purchases = agent.getPurchases();
        assertEquals(5, purchases.size());

    }

    //Test average calculation


}
