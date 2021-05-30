package tokosepeda;

import java.sql.*;

public class Service {
  // "jdbc:mysql://localhost/mydatabase"
  private String DB_URL  = "jdbc:postgresql://ec2-54-196-33-23.compute-1.amazonaws.com:5432/dcmlkeusklq0hb";
  private String DB_USER = "jthivvocqriibm";
  private String DB_PASS = "ac6274125c484e6ca9cb7c9a95e3f32bd1b3057e4159fdbd2f5b5ec81dcbab4f";

  ResultSet getAllBike() throws SQLException {
    Connection db = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    PreparedStatement query = db.prepareStatement("SELECT * FROM bikes ORDER BY name ASC");
    ResultSet rs = query.executeQuery();

    if (!rs.isBeforeFirst() && rs.getRow() == 0) {
      new Helpers().alert("Data sepeda kosong, silahkan masukkan data terlebih dahulu");
    }

    return rs;
  }

  ResultSet getBikeByID(String id) throws SQLException {
    Connection db = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    PreparedStatement query = db.prepareStatement("SELECT * FROM bikes WHERE id = ?");

    query.setString(1, id);

    ResultSet rs = query.executeQuery();

    if (!rs.isBeforeFirst() && rs.getRow() == 0) {
      new Helpers().alert("Data sepeda tidak di temukan, silahkan masukkan id kembali");
    }

    return rs;
  }
  
  void addBike(String name, String size, String stock) throws SQLException {
    try (
      Connection db = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
      PreparedStatement query = db.prepareStatement("INSERT INTO bikes (id, name, size, price, stock) VALUES (?, ?, ?, ?, ?)");
    ) {
      Helpers helper = new Helpers();

      query.setString(1, helper.generateID(size));
      query.setString(2, name);
      query.setString(3, size);
      query.setString(4, helper.getBikePrice(size));
      query.setInt(5, Integer.parseInt(stock));

      query.execute();
    }
  }
  
  void decreaseStock(String id) throws SQLException {
    try (
      Connection db = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
      PreparedStatement getQuery = db.prepareStatement("SELECT * FROM bikes WHERE id = ?");
      PreparedStatement updateQuery = db.prepareStatement("UPDATE bikes SET stock = ? WHERE id = ?");
    ) {
      getQuery.setString(1, id);

      ResultSet bike = getQuery.executeQuery();
      
      if (bike.next()) {
        Integer stock = bike.getInt("stock") - 1;

        updateQuery.setInt(1, stock);
        updateQuery.setString(2, id);

        updateQuery.execute();
      }
    }
  }
}
