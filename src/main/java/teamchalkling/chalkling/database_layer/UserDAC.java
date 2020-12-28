//package database_layer;
//
//import business_logic_layer.UserService;
//
//public class UserDAC extends DAC{
//    private final UserService user_service;
//    private final UserRepository user_repository;
//    private final String table_name = "User";
//    private final String sql_table = "CREATE TABLE IF NOT EXISTS " + table_name + " (\n"
//            + "	UserID INTEGER UNIQUE,\n"
//            + "	Username TEXT UNIQUE,\n"
//            + "	Password TEXT,\n"
//            + ");";
//
//    public UserDAC(String sql_url, UserService userService, UserRepository userRepository){
////        super(sql_url);
//        super();
//        user_service = userService;
//        user_repository = userRepository;
//    }
//
//    public void saveUsers(){
//        user_repository.saveAll(user_service.getAllUsers());
//    }
//
//
////    /**
////     * Read DB
////     *
////     * @return boolean true or false if reading successful
////     */
////    @Override
////    public boolean read() {
////        Connection connection = makeConnection();   //establish connection to DB
////        PreparedStatement entry = null;     //preparedStatement object initialized - stores sql query executed
////        ResultSet resultEntry = null;   //ResultSet used to execute prepared statement for SQL
////
////        if (!tableExists(table_name)) createNewTable(sql_table);
////
////        String sql = "SELECT * FROM " + table_name;  //Selects all rows from the table named 'User'. this is our sql query
////
////        try {
////            entry = connection.prepareStatement(sql);   //DB connection to be queried
////            resultEntry = entry.executeQuery();     //executes the SQL query
////            while (resultEntry.next()) {    //iterating through all rows of the table
////
////                String username = resultEntry.getString("Username");    //storing value of col 1 for the row
////                String password = resultEntry.getString("Password");    //storing value of col 2 for the row
////
////                try {
////
////                    //Verifies the inputted values from the DB are valid, before calling use case to store locally
////                    if (username.matches("[A-Za-z0-9]+") && password.matches("[A-Za-z0-9]+"))
////                        user_service.addUser(username, password);
////                    else throw new Exception("Incorrect format of row");
////
////                } catch (Exception e) {
////                    System.out.println("Incorrect format of row " + resultEntry.getRow());
////                }
////            }
////            return true;
////        } catch (SQLException e) {
////            System.out.println(e.toString());
////            return false;
////        } finally {
////            try {
////                assert resultEntry != null;     //closes all connections and queries before returning boolean
////                resultEntry.close();
////                entry.close();
////                connection.close();
////            } catch (SQLException e) {
////                System.out.println(e.toString());
////            }
////        }
////    }
////
////    /**
////     * Write updated info to DB
////     *
////     * @return boolean true or false if writing successful
////     */
////    @Override
////    public boolean write() {
////        if (!tableExists(table_name)) createNewTable(sql_table);
////
////        // Values(?,?,?) Indicates we are entering data into each field - we can also replace '?' with static string references
////        // REPLACE keyword overwrites all data in table, avoids duplicates iff field is set as unique
////        // INSERT can be used to simply insert data to table
////        String sql = "REPLACE INTO User(UserID, Username, Password) VALUES(?,?,?) ";
////        Connection connection = makeConnection();
////
////        try {
////            for (User user : user_service.getAllUsers()) {
////                PreparedStatement entry;    //Prepared Statement object for non static entries
////
////                //store the data we are entering into a preparedStatement
////                entry = connection.prepareStatement(sql);   //creates the preparedStatement Object
////                entry.setInt(1, user.getUserID());   //stores userID to be entered for col 1
////                entry.setString(2, user.getUsername());   //stores username to be entered for col 2
////                entry.setString(3, user.getPassword());    //stores password type for col 3
////                entry.execute();    //preparedStatement is entered into table, executes the query
////            }
////            return true;
////        } catch (SQLException e) {
////            System.out.println(e.toString());
////            return false;
////        } finally {
////            try {
////                connection.close();
////            } catch (SQLException e) {
////                System.out.println(e.toString());
////            }
////        }
////    }
//}
