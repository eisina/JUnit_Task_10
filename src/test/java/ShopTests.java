import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;
import static org.junit.jupiter.api.Assertions.*;

class ShopTests {

    private RealItem realItem;
    private VirtualItem virtualItem;
    private Cart cart;
    private String name = "TestCartName";

    @BeforeEach
    public void init() {
        cart = new Cart(name);
        realItem = new RealItem();
        realItem.setPrice(3);
        virtualItem = new VirtualItem();
        virtualItem.setPrice(4);
        System.out.println("BEFORE METHOD");
    }

    @Test
    @DisplayName("RealItem class test")
    public void testRealItemWeight() {
        realItem.setWeight(1);
        assertEquals(1, realItem.getWeight());
    }

    @Test
    @DisplayName("VirtualItem class test")
    void testVirtualItemSizeOnDisk() {
        virtualItem.setSizeOnDisk(2);
        assertEquals(2, virtualItem.getSizeOnDisk());
    }

    @Test
    @DisplayName("Cart class test for adding")
    void testClassAddItems() {
        cart.addRealItem(realItem);
        cart.addVirtualItem(virtualItem);
        assertEquals(8.4, cart.getTotalPrice());
    }

    @Test
    @DisplayName("Cart class test for deleting")
    void testClassDeleteItems() {
        cart.addRealItem(realItem);
        cart.addVirtualItem(virtualItem);
        cart.deleteRealItem(realItem);
        assertEquals(4.8, cart.getTotalPrice());
    }
}