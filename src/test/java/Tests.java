import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import parser.JsonParser;
import parser.NoSuchFileException;
import parser.Parser;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class Tests {

    private RealItem realItem;
    private VirtualItem virtualItem;
    private Cart cart;
    private String name = "TestCartName";
    private Cart testCart;

    @Test
    void testParserReadFromFileException(){
        Parser parser = new JsonParser();
        NoSuchFileException thrown = Assertions.assertThrows(NoSuchFileException.class, () -> {
            parser.readFromFile(new File("src/main/resources/noexistsing.json"));
        });
        assertEquals("File src/main/resources/noexistsing.json.json not found!", thrown.getMessage());
    }

    @Test
    void testParserWriteToFile(){
        Parser parser = new JsonParser();
        Cart comparedCart = new Cart("eugen-cartik");
        RealItem car = new RealItem();
        car.setName("BMW");
        car.setPrice(22103.9);
        car.setWeight(1400.0);

        VirtualItem disk = new VirtualItem();
        disk.setName("Microsoft office");
        disk.setPrice(30.0);
        disk.setSizeOnDisk(8500.0);

        comparedCart.addRealItem(car);
        comparedCart.addVirtualItem(disk);
        comparedCart.showItems();
        parser.writeToFile(comparedCart);
        Cart testikCart = parser.readFromFile(new File("src/main/resources/eugen-cartik.json"));
        assertThat(comparedCart).usingRecursiveComparison().isEqualTo(testikCart);
    }

    @Test
    void testParserReadFromFile(){
        Parser parser = new JsonParser();
        Cart comparedCart = new Cart("eugen-cart");
        RealItem car = new RealItem();
        car.setName("BMW");
        car.setPrice(22103.9);
        car.setWeight(1400.0);

        VirtualItem disk = new VirtualItem();
        disk.setName("Microsoft office");
        disk.setPrice(30.0);
        disk.setSizeOnDisk(8500.0);

        comparedCart.addRealItem(car);
        comparedCart.addVirtualItem(disk);
        comparedCart.showItems();
        Cart testCart = parser.readFromFile(new File("src/main/resources/eugen-cart.json"));
        testCart.showItems();
        assertThat(comparedCart).usingRecursiveComparison().isEqualTo(testCart);
    }

  /*  @BeforeEach
    public void init(){
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
        assertEquals(1,realItem.getWeight());
    }


    @Test
    @DisplayName("VirtualItem class test")
    void testVirtualItemSizeOnDisk() {
        virtualItem.setSizeOnDisk(2);
        assertEquals(2,virtualItem.getSizeOnDisk());
    }


    //2 tests for class
    @Test
    @DisplayName("Cart class test for adding")
    void testClassAddItems() {
        cart.addRealItem(realItem);
        cart.addVirtualItem(virtualItem);
        assertEquals(8.4,cart.getTotalPrice());
    }

    // after removing the item total sum doesn't count
    @Test
    @DisplayName("Cart class test for deleting")
    void testClassDeleteItems() {
        cart.addRealItem(realItem);
        cart.addVirtualItem(virtualItem);
        cart.deleteRealItem(realItem);
        assertEquals(4.8,cart.getTotalPrice());
    }
*/
}