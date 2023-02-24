package com.example.wesfritzsample.SQLqueries;



import com.example.wesfritzsample.JDBC.JDBC;
import com.example.wesfritzsample.classes.Parcel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.SQLException;
import java.sql.*;


/** This class holds the needed SQL methods I need to pull parcel data.
 * The bridge between data and my application.
 */
public class parcelQueries {
    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String ipAdd = "//127.0.0.1:3306/parcel_data";
    private static final String driverurl = protocol + vendor + ipAdd + "?connectionTimeZone=SERVER";

    private static final String driver = "com.mysql.cj.jdbc.Driver";


    public static Connection connection;
    private static final String un = "outsideUser";
    private static final String pw = "outsideUser!14";

    /** This method connects the application to the database.
     * Used when I need to make a query.
     * @return Returns the connection to the database.
     */
    public static Connection connectToDB()  {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(driverurl, un, pw);
        } catch (ClassNotFoundException | SQLException e) {
        }
        System.out.println("DB Connection Established!");
        return connection;
    }

    /**
     * This method disconnects the application from the database.
     * Not used currently, but kept in for testing/troubleshooting. purposes.
     */
    public static void disconnectDB() {
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("DB Connection Closed!");
    }


    /** This method creates a list of all parcel data from the database.
     * If the application expands, this could prove more useful, as it is a full pull of all data.
     * @param connection Needs a connection to the database to function properly.
     * @return Returns a list of all data needed.
     * @throws SQLException
     */
    public static ObservableList<Parcel> selectToFillParcelTable(Connection connection) throws SQLException {
        ObservableList<Parcel> parcelInfo = FXCollections.observableArrayList();

        // The following query retrieves all data from parcels, to establish database connection.

        String sql = "SELECT * FROM parcel_data.parcels";

        //PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        PreparedStatement ps = JDBC.createConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            long pin = rs.getLong("PIN");
            String address = rs.getString("ADDRESS");
            String owner = rs.getString("OWNER");
            double marketValue = rs.getDouble("MARKET_VALUE");
            String saleDate = rs.getString("SALE_DATE");
            double salePrice = rs.getDouble("SALE_PRICE");
            String link = rs.getString("LINK");

            Parcel parcel = new Parcel(pin, address, owner, marketValue, saleDate, salePrice, link);
            parcelInfo.add(parcel);
        }
        return parcelInfo;
    }



    /** This method makes an observable for the Street Name Table.
     * Lists data ordered by street name first, then street number, effectively grouping the streets together.
     * Also uses 'DISTINCT' in query, to avoid including duplicate entries.
     * @param connection needs a connection to the database to function properly.
     * @return Returns the list of parcels from the queried database.
     * @throws SQLException
     */
    public static ObservableList<Parcel> selectToFillStreetNameTable(Connection connection) throws SQLException {
        ObservableList<Parcel> streetNameParcels = FXCollections.observableArrayList();

        // The following query retrieves the data from the parcels, placing street name first
        // and number second in hierarchy.
        // The select distinct scrubs the data for multiple entries that are the same.

        String sql = "SELECT DISTINCT * FROM parcel_data.parcels ORDER BY\n" +
                "  substring_index(address,' ',char_length(replace(address,' ',''))-char_length(address)),\n" +
                "  0+address,\n" +
                "  address";

        PreparedStatement ps = JDBC.createConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            long pin = rs.getLong("PIN");
            String address = rs.getString("ADDRESS");
            String owner = rs.getString("OWNER");
            double marketValue = rs.getDouble("MARKET_VALUE");
            String saleDate = rs.getString("SALE_DATE");
            double salePrice = rs.getDouble("SALE_PRICE");
            String link = rs.getString("LINK");

            Parcel parcel = new Parcel(pin, address, owner, marketValue, saleDate, salePrice, link);
            streetNameParcels.add(parcel);
        }
        return streetNameParcels;
    }



    /** This method makes an observable for the First Name Table.
     * Lists data ordered by owners' first names alphabetically, which also groups LLCs at the bottom.
     * Also uses 'DISTINCT' in query, to avoid including duplicate entries.
     * @param connection needs a connection to the database to function properly.
     * @return Returns the list of parcels from the queried database.
     * @throws SQLException
     */
    public static ObservableList<Parcel> selectToFillFirstNameTable(Connection connection) throws SQLException {
        ObservableList<Parcel> firstNameParcels = FXCollections.observableArrayList();

        // The following query retrieves the data from the parcels, ordering the data by the owner's first name
        // The select distinct scrubs the data for multiple entries that are the same.

        String sql = "SELECT DISTINCT `PIN`,`ADDRESS`,\n" +
                "   CONCAT(SUBSTRING_INDEX(`OWNER`,',', -1),' ', SUBSTRING_INDEX(`OWNER`,',', 1)) OWNER  \n" +
                "  ,`MARKET_VALUE`,`SALE_DATE`, `SALE_PRICE`, `LINK`\n" +
                "  FROM parcel_data.parcels ORDER BY owner";

        PreparedStatement ps = JDBC.createConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            long pin = rs.getLong("PIN");
            String address = rs.getString("ADDRESS");
            String owner = rs.getString("OWNER");
            double marketValue = rs.getDouble("MARKET_VALUE");
            String saleDate = rs.getString("SALE_DATE");
            double salePrice = rs.getDouble("SALE_PRICE");
            String link = rs.getString("LINK");

            Parcel parcel = new Parcel(pin, address, owner, marketValue, saleDate, salePrice, link);
            firstNameParcels.add(parcel);
        }
        return firstNameParcels;
    }


    /** This method opens the connection to the database.
     * Gives a line of output to signify a successful connection.
     * @return connection -- provides connection to the database when fired off. */
    public static Connection createConnection()   {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(driverurl, un, pw);
        } catch (ClassNotFoundException | SQLException e) {
        }
        System.out.println("DB Connection Established!");
        return connection;
    }

}
