package tokosepeda;

import java.util.EnumSet;
import java.util.Random;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Helpers {
  public enum BikeSize {
    Kecil,
    Sedang,
    Besar;
  }

  boolean isValidBikeSize(String bikeSize) {
    for (BikeSize size : EnumSet.allOf(BikeSize.class)) {
      if (size.toString().equals(bikeSize)) return true;
    }

    return false;
  }

  HashMap<String, String> bikePrice = new HashMap<String, String>(){{
    put(BikeSize.Kecil.toString(),  "500.000");
    put(BikeSize.Sedang.toString(), "900.000");
    put(BikeSize.Besar.toString(),  "1.500.000");
  }};

  String getBikePrice(String bikeSize) {
    switch (bikeSize) {
      case "Kecil":   return bikePrice.get(BikeSize.Kecil.toString());
      case "Sedang":  return bikePrice.get(BikeSize.Sedang.toString());
      case "Besar":   return bikePrice.get(BikeSize.Besar.toString());
      default:        return "";
    }
  }
  
  String generateID(String bikeSize) {
    char code = bikeSize.toUpperCase().charAt(0);
    int randNum = 100 + new Random().nextInt(999);
    return "B" + String.valueOf(code) + String.valueOf(randNum);
  }

  void alert(String message) {
    JOptionPane.showMessageDialog(null, message, "Information", JOptionPane.INFORMATION_MESSAGE);
  }

  void showFrame(JFrame frame) {
    frame.setVisible(true);
    frame.pack();
    frame.setLocationRelativeTo(null);
  }
}
