package com.payement.mockito;

import com.user.poc.Inventory.Inventory;
import com.user.poc.Inventory.InventoryRepository;
import com.user.poc.Inventory.InventoryService;
import com.util.MiscUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class InventoryServiceTest {

    @InjectMocks
    private InventoryService inventoryService;

    @Mock
    private InventoryRepository inventoryRepository;

    @Before
    public void setUp() {
        inventoryService = new InventoryService(inventoryRepository);
    }

    @Test
    public void testUpdateInventory() {

        try(final MockedStatic<MiscUtil> miscUtil = Mockito.mockStatic(MiscUtil.class);) {
            miscUtil.when(() -> MiscUtil.getInstance()).thenReturn("Instance");
            miscUtil.when(() -> MiscUtil.getContext()).thenReturn("context");
            // Given
            Inventory inventory = new Inventory(1, 10);
            when(inventoryRepository.findByProductId(1)).thenReturn(inventory);

            // When
            boolean result = inventoryService.updateInventory(1, 5);

            // Then
            assertTrue(result);
            verify(inventoryRepository, times(1)).save(any(Inventory.class));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateInventoryWithNegativeQuantity() {
        //wrongly converted
        Mockito.when(MiscUtil.getInstance()).thenReturn("Instance");
        inventoryService.updateInventory(1, -5);
    }

    @Test
    public void testUpdateInventoryWithMockUp() {
        try(final MockedStatic<MiscUtil> miscUtil = Mockito.mockStatic(MiscUtil.class)) {
            miscUtil.when(() -> MiscUtil.getInstance()).thenReturn("Instance");
           Mockito.when(MiscUtil.getContext()).thenReturn("context");
            // Given
            Inventory inventory = new Inventory(1, 10);
            when(inventoryRepository.findByProductId(1)).thenReturn(inventory);

            // When
            boolean result = inventoryService.updateInventory(1, 10);

            // Then
            assertTrue(result);
            verify(inventoryRepository, times(1)).save(any(Inventory.class));
        }
    }
}
