import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ShopRepositoryTest {
    Product product1 = new Product(11, "Сливки", 100);
    Product product2 = new Product(222, "Сыр", 200);
    Product product3 = new Product(3, "Макароны", 80);
    Product product4 = new Product(222, "Креветки", 1000);

    @Test
    public void shouldFindProductById() {
        ShopRepository repo = new ShopRepository();
        repo.add(product1);
        repo.add(product2);
        repo.add(product3);

        Assertions.assertEquals(product3, repo.findById(3));
    }

    @Test
    public void shouldNoFindProductById() {
        ShopRepository repo = new ShopRepository();
        repo.add(product1);
        repo.add(product2);
        repo.add(product3);

        Assertions.assertEquals(null, repo.findById(4));
    }

    @Test
    public void shouldRemoveById() {
        ShopRepository repo = new ShopRepository();
        repo.add(product1);
        repo.add(product2);
        repo.add(product3);

        repo.removeById(222);
        Product[] expected = {product1, product3};

        Assertions.assertArrayEquals(expected, repo.findAll());
    }

    @Test
    public void shouldGenerateExceptionNotFound() {
        ShopRepository repo = new ShopRepository();
        repo.add(product1);
        repo.add(product2);
        repo.add(product3);

        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.removeById(4);
        });
    }

    @Test
    public void shouldAddProductInToRepository() {
        ShopRepository repo = new ShopRepository();
        repo.add(product1);
        repo.add(product2);

        Product[] expected = {product1, product2};

        Assertions.assertArrayEquals(expected, repo.findAll());
    }

    @Test
    public void shouldGenerateExceptionAlreadyExists() {
        ShopRepository repo = new ShopRepository();
        repo.add(product1);
        repo.add(product2);
        repo.add(product3);

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repo.add(product4);
        });
    }
}
