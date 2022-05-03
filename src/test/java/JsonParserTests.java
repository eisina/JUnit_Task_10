import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import parser.JsonParser;
import parser.NoSuchFileException;
import parser.Parser;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonParserTests {

    Parser parser;
    Cart comparedWriteCart;
    Cart comparedReadCart;

    @BeforeEach
    void init() {
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

    @Test
    void testParserReadFromFileException() {
        NoSuchFileException thrown = Assertions.assertThrows(NoSuchFileException.class, () -> {
            parser.readFromFile(new File("src/main/resources/noexistsing.json"));
        });
        assertEquals("File src/main/resources/noexistsing.json.json not found!", thrown.getMessage());
    }

    @Disabled
    @Test
    void testParserWriteToFile() throws IOException {
        parser.writeToFile(comparedWriteCart);
        Path filepath = Paths.get("src/main/resources/kate-cart.json");
        Path filepath2 = Paths.get("src/main/resources/kate-cart-compare.json");
        File fileFromPath = filepath.toFile();
        File file2FromPath = filepath2.toFile();
        assertEquals(FileUtils.readFileToString(fileFromPath, "utf-8"),
                FileUtils.readFileToString(file2FromPath, "utf-8"));
    }

    @Test
    void testParserReadFromFile() {
        Cart testCart = parser.readFromFile(new File("src/main/resources/eugen-cart.json"));
        assertThat(comparedReadCart).usingRecursiveComparison().isEqualTo(testCart);
    }

    @AfterEach
    void cleanUpEach() throws IOException {
        Path filepath = Paths.get("src/main/resources/kate-cart.json");
        Files.deleteIfExists(filepath);
    }
}
