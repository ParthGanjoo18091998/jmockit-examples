package com.payement.jmockito;

import com.user.poc.Inventory.Inventory;
import com.user.poc.Inventory.InventoryRepository;
import com.user.poc.Inventory.InventoryService;
import com.util.MiscUtil;
import mockit.*;
import org.junit.Test;

import java.util.logging.Logger;

import static org.testng.AssertJUnit.assertTrue;

public class InventoryServiceTest {
    @Tested
    private InventoryService inventoryService;

    @Mocked
    private InventoryRepository inventoryRepository;

    @Test
    public void testUpdateInventory() {
        new MockUp<MiscUtil>() {
            public  String getInstance() {
                return "Instance";
            }
        };

        new Expectations() {{
            MiscUtil.getContext();
            result = "context";
        }};

        new Expectations() {{
            inventoryRepository.findByProductId(1);
            result = new Inventory(1, 10);

            inventoryRepository.save((Inventory) any);
        }};

        boolean result = inventoryService.updateInventory(1, 5);
        assertTrue(result);

        new Verifications() {{
            inventoryRepository.save((Inventory) any);
            minTimes = 1;
            maxTimes = 1;
        }};
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateInventoryWithNegativeQuantity() {
        inventoryService.updateInventory(1, -5);
    }

    @Test
    public void testUpdateInventoryWithMockUp() {

        new MockUp<InventoryRepository>() {
            @mockit.Mock
            public Inventory findByProductId(int productId) {
                return new Inventory(productId, 10);
            }

            @mockit.Mock
            public void save(Inventory inventory) {
                // Mocked save method
            }
        };

        new Expectations() {{
            MiscUtil.getContext();
            result = "context";
        }};

        boolean result = inventoryService.updateInventory(1, 10);
        assertTrue(result);

        new Verifications() {{
            inventoryRepository.save((Inventory) any);
            minTimes = 1;
            maxTimes = 1;
        }};
    }
}


