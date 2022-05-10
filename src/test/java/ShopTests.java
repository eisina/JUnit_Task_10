import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.coherent.training.web.isina.shop.Cart;
import com.coherent.training.web.isina.shop.RealItem;
import com.coherent.training.web.isina.shop.VirtualItem;
import static org.junit.jupiter.api.Assertions.*;

class ShopTests {

    private RealItem realItem;
    private VirtualItem virtualItem;
    private Cart cart;
    private String name = "TestCartName";

    @BeforeEach
    public void initializeTheCart() {
        cart = new Cart(name);
        realItem = new RealItem();
        realItem.setPrice(3);
        virtualItem = new VirtualItem();
        virtualItem.setPrice(4);
    }

    @Test
    @DisplayName("RealItem class test")
    public void testRealItemWeight() {
        realItem.setWeight(1);
        assertEquals(1, realItem.getWeight(), "Weight doesn't equal expected value.");
    }

    @Test
    @DisplayName("VirtualItem class test")
    public void testVirtualItemSizeOnDisk() {
        virtualItem.setSizeOnDisk(2);
        assertEquals(2, virtualItem.getSizeOnDisk(),"Size on disk doesn't equal expected value.");
    }

    @Test
    @DisplayName("Cart class test for adding RealItem and VirtualItem")
    public void testClassAddItems() {
        cart.addRealItem(realItem);
        cart.addVirtualItem(virtualItem);
        assertEquals(8.4, cart.getTotalPrice(),"Total price of Real Item and Virtual Item doesn't equal expected value.");
    }

    @Test
    @DisplayName("Cart class test for deleting Real Item")
    public void testClassDeleteRealItems() {
        cart.addRealItem(realItem);
        cart.addVirtualItem(virtualItem);
        cart.deleteRealItem(realItem);
        assertEquals(4.8, cart.getTotalPrice(),"Total price after deleting Real Item doesn't equal expected value.");
    }

    @Test
    @DisplayName("Cart class test for deleting Virtual Item")
    public void testClassDeleteVirtualItems() {
        cart.addRealItem(realItem);
        cart.addVirtualItem(virtualItem);
        cart.deleteVirtualItem(virtualItem);
        assertEquals(3.6, cart.getTotalPrice(),"Total price after deleting Virtual Item doesn't equal expected value.");
    }
}