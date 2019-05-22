import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VaruLagerTest {

    @Test
    void addVaror() {
        Varor varorTest = new Varor("S80", 10000, "Sedan", "Bil", 15,2 );

        VaruLager varuLagerTest = new VaruLager();

        varuLagerTest.addVaror(varorTest);

        assertEquals(1, varuLagerTest.getProducts().size() );

    }
}