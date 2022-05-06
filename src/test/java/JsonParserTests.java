import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import com.coherent.training.web.isina.parser.JsonParser;
import com.coherent.training.web.isina.parser.NoSuchFileException;
import com.coherent.training.web.isina.parser.Parser;
import com.coherent.training.web.isina.shop.Cart;
import com.coherent.training.web.isina.shop.RealItem;
import com.coherent.training.web.isina.shop.VirtualItem;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonParserTests {

    private Parser parser;
    private Cart comparedWriteCart;
    private Cart comparedReadCart;

    @BeforeEach
    public void init() {
        parser = new JsonParser();
        comparedReadCart = new Cart("eugen-cart");
        comparedWriteCart = new Cart("kate-cart");

        RealItem car = new RealItem();
        car.setName("BMW");
        car.setPrice(22103.9);
        car.setWeight(1400.0);

        VirtualItem disk = new VirtualItem();
        disk.setName("Microsoft office");
        disk.setPrice(30.0);
        disk.setSizeOnDisk(8500.0);

        comparedReadCart.addRealItem(car);
        comparedReadCart.addVirtualItem(disk);
        comparedWriteCart.addRealItem(car);
        comparedWriteCart.addVirtualItem(disk);
    }

    @ParameterizedTest
    @ValueSource(strings = {"src/main/resources/noexistsing.json", "noexistsing.json", "", " "})
    public void testParserReadFromFileException(String path) {
        NoSuchFileException thrown = Assertions.assertThrows(NoSuchFileException.class, () -> {
            parser.readFromFile(new File(path));
        });
        assertEquals(String.format("File %s.json not found!", path), thrown.getMessage(), "Error message doesn't equal expected value.");
    }

    @Disabled
    @Test
    public void testParserWriteToFile() throws IOException {
        parser.writeToFile(comparedWriteCart);
        String actualFile = FileUtils.readFileToString(new File("src/main/resources/kate-cart.json"), "utf-8");
        String expectedFile = FileUtils.readFileToString(new File("src/main/resources/kate-cart-compare.json"), "utf-8");
        assertEquals(actualFile, expectedFile,"Object added to File incorrectly.");
    }

    @Test
    public void testParserReadFromFile() {
        Cart testCart = parser.readFromFile(new File("src/main/resources/eugen-cart.json"));
        assertAll("ValidateEmpCode",
                () -> assertEquals(testCart.getCartName(),comparedReadCart.getCartName(), "Cart name doesn't equal expected value."),
                () -> assertEquals(testCart.getTotalPrice(),comparedReadCart.getTotalPrice(), "Cart Total Price doesn't equal expected value.")
                );
    }

    @AfterAll
    public static void cleanUpEach() throws IOException {
        Path filepath = Paths.get("src/main/resources/kate-cart.json");
        Files.deleteIfExists(filepath);
    }
}
