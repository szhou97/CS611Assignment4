import java.io.File;
import java.util.ArrayList;

public class MarketStock extends Inventory {
    public MarketStock(String[] files) {
        for (int i = 0; i < files.length; i++) {
            this.populateStock(files[i]);
        }
    }

    public void populateStock(String file) {
        
    }
}
