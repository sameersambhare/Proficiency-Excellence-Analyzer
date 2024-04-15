package Backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseManager {
    
    private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String DB_USERNAME = "system";
    private static final String DB_PASSWORD = "krushna123";

    // SQL query to insert a new user into the database
    private static final String INSERT_USER_QUERY = "INSERT INTO user_credentials (username, password) VALUES (?, ?)";

    // SQL query to retrieve password from the database based on username
    private static final String RETRIEVE_PASSWORD_QUERY = "SELECT password FROM user_credentials WHERE username = ?";

    // Register function to insert user into the database
    public static boolean insertStudentCredentials(String username, String password) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // Load the Oracle JDBC driver
            

            // Connect to Oracle Database
            connection = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);

            // Prepare SQL statement for insertion
            String sql = "INSERT INTO StudCred (sr_no, username, password) VALUES (StudCount.nextval, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);

            // Execute the statement
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            // Close the connection and statement in a finally block to ensure resources are released
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public static boolean insertHRCredentials(String username, String password) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // Load the Oracle JDBC driver
            

            // Connect to Oracle Database
            connection = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);

            // Prepare SQL statement for insertion
            String sql = "INSERT INTO HrCred (sr_no, username, password) VALUES (StudCount.nextval, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);

            // Execute the statement
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            // Close the connection and statement in a finally block to ensure resources are released
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
   
    public static boolean insertClgCredentials(String username, String password) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // Load the Oracle JDBC driver
            

            // Connect to Oracle Database
            connection = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);

            // Prepare SQL statement for insertion
            String sql = "INSERT INTO ClgCred (sr_no, username, password) VALUES (StudCount.nextval, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);

            // Execute the statement
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            // Close the connection and statement in a finally block to ensure resources are released
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public static boolean isValidStudent(String username, String password) {
        String sql = "SELECT username FROM StudCred WHERE username = ? AND password = ?";
        return isValidUser(username, password, sql);
    }

    public static boolean isValidHR(String username, String password) {
        String sql = "SELECT username FROM HrCred WHERE username = ? AND password = ?";
        return isValidUser(username, password, sql);
    }

    public static boolean isValidCollege(String username, String password) {
        String sql = "SELECT username FROM ClgCred WHERE username = ? AND password = ?";
        return isValidUser(username, password, sql);
    }

    private static boolean isValidUser(String username, String password, String query) {
        boolean isValid = false;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);
            statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            resultSet = statement.executeQuery();

            isValid = resultSet.next(); // If result set has a row, credentials are valid
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return isValid;
    }
   
    
        public boolean addStudentProfile(String username, String fullName, String email, String contactNumber,
                                         String qualification, String links, String profileDescription) {
            boolean success = false;
            Connection connection = null;
            PreparedStatement preparedStatement = null;

            try {
            	connection = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);
                connection.setAutoCommit(false); // Start transaction
                
                // Insert into StudProfile table
                String insertStudProfileQuery = "INSERT INTO StudProfile (username, name, contact,  email) VALUES (?, ?, ?, ?)";
                preparedStatement = connection.prepareStatement(insertStudProfileQuery);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, fullName);
                preparedStatement.setString(3, contactNumber);
                 preparedStatement.setString(4, email);
                preparedStatement.executeUpdate();

                // Insert into ProfileDesc table
                String insertProfileDescQuery = "INSERT INTO ProfileDesc (profile_id, profile_desc) VALUES (?, ?)";
                preparedStatement = connection.prepareStatement(insertProfileDescQuery);
                preparedStatement.setString(1, username); // Replace with appropriate profile_id
                preparedStatement.setString(2, profileDescription);
                preparedStatement.executeUpdate();

                // Insert into Qualification table
                String insertQualificationQuery = "INSERT INTO Qualification (q_id, q_description) VALUES (?, ?)";
                preparedStatement = connection.prepareStatement(insertQualificationQuery);
                preparedStatement.setString(1, username); // Replace with appropriate q_id
                preparedStatement.setString(2, qualification);
                preparedStatement.executeUpdate();

                // Insert into Links table
                String insertLinksQuery = "INSERT INTO Links (url_id, urls) VALUES (?, ?)";
                preparedStatement = connection.prepareStatement(insertLinksQuery);
                preparedStatement.setString(1, username); // Replace with appropriate url_id
                preparedStatement.setString(2, links);
                preparedStatement.executeUpdate();

                connection.commit(); // Commit transaction
                success = true; // Set success flag to true
            } catch (SQLException e) {
                try {
                    connection.rollback(); // Rollback transaction if an error occurs
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                e.printStackTrace();
            } finally {
                try {
                    if (preparedStatement != null) {
                        preparedStatement.close();
                    }
                    connection.setAutoCommit(true); // Restore auto-commit mode
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            return success;
            
        }


            public static boolean insertCollegeProfile(String collegeName, String instituteCode,
                                                       String collegeAddress, String collegeContact,
              
                                                       String collegeEmail, String username) {
            	String sql;
            	System.out.println(username);
              
                try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);
                     ) {
                	String checkSql = "SELECT COUNT(*) FROM ClgProfile WHERE username = ?";
                    PreparedStatement checkStmt = conn.prepareStatement(checkSql);
                    checkStmt.setString(1, username);
                    ResultSet rs = checkStmt.executeQuery();
                    rs.next(); 
                    int rowCount = rs.getInt(1); 
                    
                    if (rowCount == 0) 
                	sql = "INSERT INTO ClgProfile (collegeName, instituteCode, collegeAddress, collegeContact, collegeEmail, username) " +
                            "VALUES (?, ?, ?, ?, ?, ?)";
                	else
                    sql = "UPDATE ClgProfile SET collegeName = ?, instituteCode = ?, collegeAddress = ?, collegeContact = ?, collegeEmail = ? WHERE username = ?";

                	PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, collegeName);
                    pstmt.setString(2, instituteCode);
                    pstmt.setString(3, collegeAddress);
                    pstmt.setString(4, collegeContact);
                    pstmt.setString(5, collegeEmail);
                    pstmt.setString(6, username);

                    int rowsAffected = pstmt.executeUpdate();

                   
                    return rowsAffected > 0; // Return true if at least one row was affected (insertion successful)
                } catch (SQLException e) {
                    System.err.println("Error inserting college profile: " + e.getMessage());
                    return false; // Return false if an exception occurred or no rows were affected
                }
            }
        
    // Function to check if the user login is valid
   
    
    private static final String INSERT_PROFILE_QUERY = "INSERT INTO UserProfile (username, full_name, email, contact_number, qualification, address, gender, profile_description) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    // Method to save profile data to the database
    public static void saveProfile(String username, String fullName, String email, String contactNumber, String qualification, String address, String gender, String profileDescription) {
        try (
            // Load the Oracle JDBC driver
            Connection connection = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);
            // Create a PreparedStatement with the INSERT query
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PROFILE_QUERY)
        ) {
            // Set the parameters for the PreparedStatement
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, fullName);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, contactNumber);
            preparedStatement.setString(5, qualification);
            preparedStatement.setString(6, address);
            preparedStatement.setString(7, gender);
            preparedStatement.setString(8, profileDescription);

            // Execute the INSERT statement
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Profile saved successfully!");
            } else {
                System.out.println("Failed to save profile.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
   
       
    

    
    static boolean applyForJob(String username,String postId){
    try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);){
             PreparedStatement pre = connection.prepareStatement("INSERT INTO Applications (post_id,username) VALUES (?, ?)"); 
             pre.setString(1, postId);		
         pre.setString(2, username);
         int result = pre.executeUpdate();
       
        	 return result >0;
        
    }
    catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
    
    }      
    
    

    public static void saveJobPost(String postId,String positionName, String company, String qualification, String experience, String location, String jobDescription, String requiredSkills) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);){
             PreparedStatement preparedStatement1 = connection.prepareStatement("INSERT INTO JobPost (post_id,position_name, company, experience, location,salary) VALUES (?, ?, ?, ?, ?, ?)"); 
        	PreparedStatement preparedStatement2 = connection.prepareStatement("INSERT INTO Qualification (q_id,q_description) VALUES (?, ?)"); 
        	PreparedStatement preparedStatement3 = connection.prepareStatement("INSERT INTO Skills (s_id,skills_set) VALUES (?, ?)"); 
        	PreparedStatement preparedStatement4 = connection.prepareStatement("INSERT INTO ProfileDesc (profile_id,profile_desc) VALUES (?, ?)"); 
     	                      
        		
        	preparedStatement1.setString(1, postId);		
        	preparedStatement1.setString(2, positionName);
            preparedStatement1.setString(3, company);
            preparedStatement1.setString(4, experience);
            preparedStatement1.setString(5, location);
            preparedStatement1.setString(6, "8 LPA");
            
            preparedStatement2.setString(1, postId);
            preparedStatement2.setString(2, qualification);
            
            preparedStatement3.setString(1,postId);
            preparedStatement3.setString(2,requiredSkills);
            
            preparedStatement4.setString(1,postId);
            preparedStatement4.setString(2, jobDescription);
            
            int rowsInserted1 = preparedStatement1.executeUpdate();
            System.out.print("Executed");
            int rowsInserted2 = preparedStatement2.executeUpdate();
            int rowsInserted3 = preparedStatement3.executeUpdate();
            int rowsInserted4= preparedStatement4.executeUpdate();
            if (rowsInserted1 > 0 && rowsInserted2 > 0 && rowsInserted3 > 0 && rowsInserted4 > 0) {
                System.out.println("Job posted successfully!");
            } else {
                System.out.println("Failed to post job.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public static void main(String[] args) {
       
    }
}

