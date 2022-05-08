import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;

import static org.testng.Assert.assertEquals;

public class ShopsTest {

    private RealItem realItem;
    private VirtualItem virtualItem;
    private Cart cart;
    private String name = "TestCartName";

    @BeforeClass
    public void initializeTheCart() {
        cart = new Cart(name);
        realItem = new RealItem();
        realItem.setPrice(3);
        virtualItem = new VirtualItem();
        virtualItem.setPrice(4);
    }

    @Test
    public void testRealItemWeight() {
        realItem.setWeight(1);
        assertEquals(1.0, realItem.getWeight(), "Weight doesn't equal expected value.");
    }

    @Test
    public void testVirtualItemSizeOnDisk() {
        virtualItem.setSizeOnDisk(2);
        assertEquals(2.0, virtualItem.getSizeOnDisk(), "Size on disk doesn't equal expected value.");
    }

    @Test
    public void testClassAddItems() {
        cart.addRealItem(realItem);
        cart.addVirtualItem(virtualItem);
        assertEquals(8.4, cart.getTotalPrice(), "Total price of Real Item and Virtual Item doesn't equal expected value.");
    }

    @Test
    public void testClassDeleteRealItems() {
        cart.addRealItem(realItem);
        cart.addVirtualItem(virtualItem);
        cart.deleteRealItem(realItem);
        assertEquals(4.8, cart.getTotalPrice(), "Total price after deleting Real Item doesn't equal expected value.");
    }

    @Test
    public void testClassDeleteVirtualItems() {
        cart.addRealItem(realItem);
        cart.addVirtualItem(virtualItem);
        cart.deleteVirtualItem(virtualItem);
        assertEquals(3.6, cart.getTotalPrice(), "Total price after deleting Virtual Item doesn't equal expected value.");
    }
}
