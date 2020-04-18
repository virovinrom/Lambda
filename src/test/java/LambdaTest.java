import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LambdaTest {

    private Product credit2202 = new Product();
    private Product credit2207 = new Product();
    private Product credit2909 = new Product();
    private Product leasing2202 = new Product();
    private Product leasing2909 = new Product();

    @Before
    public void setUp() {
        credit2202.setType("credit");
        credit2202.setProductNumber("VI123");
        credit2202.setIban("PRTT02202");
        credit2202.setVisible(true);
        credit2202.setId(1L);

        credit2207.setType("credit");
        credit2207.setProductNumber("VI123");
        credit2207.setIban("PRTT02207");
        credit2207.setVisible(true);
        credit2207.setId(2L);

        credit2909.setType("credit");
        credit2909.setProductNumber("VI123");
        credit2909.setIban("PRTT02909");
        credit2909.setVisible(true);
        credit2909.setId(3L);

        leasing2202.setType("leasing");
        leasing2202.setProductNumber("VI1233");
        leasing2202.setIban("PRTT02202");
        leasing2202.setVisible(true);
        leasing2202.setId(4L);

        leasing2909.setType("leasing");
        leasing2909.setProductNumber("VI1233");
        leasing2909.setIban("PRTT02909");
        leasing2909.setVisible(true);
        leasing2909.setId(5L);
    }


    @Test
    public void lambdaTest() {
        List<Product> productList = new ArrayList<>();
        productList.add(credit2202);
        productList.add(credit2207);
        productList.add(credit2909);
        productList.add(leasing2202);
        productList.add(leasing2909);

        Map<String, List<Product>> groupedByNumber = productList.stream()
                .filter(product -> product.getType().equals("credit") || product.getType().equals("leasing"))
                .collect(Collectors.groupingBy(Product::getProductNumber));

        for (Map.Entry<String, List<Product>> entry : groupedByNumber.entrySet()) {
            if (oneOfProductsContainsWord(entry, "2909")) {
                entry.getValue().forEach(product -> {
                    if (!product.getIban().contains("2909")) {
                        product.setVisible(false);
                        System.out.println(product.getId().toString());
                    }
                });


            }
        }
    }

    private static boolean oneOfProductsContainsWord(Map.Entry<String, List<Product>> entry, String word) {
        return entry.getValue().stream().anyMatch(i -> i.getIban().contains(word));
    }
}
