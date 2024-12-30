//package com.Cbic_Aaklan_Project.Controller;
//
//import com.Cbic_Aaklan_Project.dao.pool.JDBCConnection;
//import com.Cbic_Aaklan_Project.payload.LoginRequestDTO;
//import com.Cbic_Aaklan_Project.payload.UserRegistrationDTO;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//@Controller
//@RequestMapping("/api/admin")
//public class AdminController {
//    private org.slf4j.Logger logger =  LoggerFactory.getLogger(AdminController.class);
//// http://localhost:8080/cbicApi/api/admin/onboardUser
//    @PostMapping("/onboardUser")
//    @ResponseBody
//    public ResponseEntity<String> onboardUser(@RequestBody UserRegistrationDTO userRegistrationDTO) {
//        Connection con = null;
//        PreparedStatement pstmt = null;
//
//        // Debug log to verify input
//        logger.info("Received user data: Email = {}", userRegistrationDTO.getUserEmail());
//
//        // Validate input
//        if (userRegistrationDTO.getUserEmail() == null || userRegistrationDTO.getUserEmail().isEmpty()) {
//            return ResponseEntity.badRequest().body("Email cannot be null or empty.");
//        }
//
//        if (userRegistrationDTO.getPassword() == null || userRegistrationDTO.getPassword().isEmpty()) {
//            return ResponseEntity.badRequest().body("Password cannot be null or empty.");
//        }
//
//        // Check if the user is eligible for onboarding
//        if (!isEligibleForOnboarding(userRegistrationDTO.getUserEmail())) {
//            return ResponseEntity.status(403).body("User is not eligible for onboarding.");
//        }
//
//        String query = "INSERT INTO LoginRequest (email, password) VALUES (?, ?)";
//        try {
//            con = JDBCConnection.getTNConnection();
//            pstmt = con.prepareStatement(query);
//
//            // Hash the password before storing it
//            String hashedPassword = BCrypt.hashpw(userRegistrationDTO.getPassword(), BCrypt.gensalt());
//            pstmt.setString(1, userRegistrationDTO.getUserEmail());
//            pstmt.setString(2, hashedPassword);
//
//            int rowsInserted = pstmt.executeUpdate();
//            if (rowsInserted > 0) {
//                return ResponseEntity.ok("User onboarded successfully!");
//            } else {
//                return ResponseEntity.status(500).body("Failed to onboard user.");
//            }
//        } catch (SQLException e) {
//            logger.error("SQL Exception occurred: {}", e.getMessage());
//            return ResponseEntity.status(500).body("An error occurred while processing the request.");
//        } finally {
//            try {
//                if (pstmt != null) pstmt.close();
//                if (con != null) con.close();
//            } catch (SQLException e) {
//                logger.error("Error closing resources: {}", e.getMessage());
//            }
//        }
//    }
//
//    /**
//     * Check if a user is eligible for onboarding based on their email.
//     *
//     * @param email The user's email address.
//     * @return true if the user is eligible, false otherwise.
//     */
//    private boolean isEligibleForOnboarding(String email) {
//        Connection con = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//
//        try {
//            con = JDBCConnection.getTNConnection();
//            String query = "SELECT email FROM ApprovedUsers WHERE email = ?";
//            pstmt = con.prepareStatement(query);
//            pstmt.setString(1, email);
//
//            rs = pstmt.executeQuery();
//            return rs.next(); // If a record is found, the user is eligible
//        } catch (SQLException e) {
//            logger.error("Error checking user eligibility: {}", e.getMessage());
//            return false;
//        } finally {
//            try {
//                if (rs != null) rs.close();
//                if (pstmt != null) pstmt.close();
//                if (con != null) con.close();
//            } catch (SQLException e) {
//                logger.error("Error closing resources: {}", e.getMessage());
//            }
//        }
//    }
//
//
//
//
//    // Offboarding API
//    // http://localhost:8080/cbicApi/api/admin/onboardUser
//    @DeleteMapping("/offboardUser")
//    @ResponseBody
//    public ResponseEntity<String> offboardUser(@RequestParam("email") String userEmail) {
//        Connection con = null;
//        PreparedStatement pstmt = null;
//
//        String query = "DELETE FROM LoginRequest WHERE TRIM(LOWER(email)) = TRIM(LOWER(?))";
//        try {
//            con = JDBCConnection.getTNConnection();
//            pstmt = con.prepareStatement(query);
//            pstmt.setString(1, userEmail);
//
//            int rowsDeleted = pstmt.executeUpdate();
//            logger.info("Rows deleted: {}", rowsDeleted); // Log the result
//            if (rowsDeleted > 0) {
//                return ResponseEntity.ok("User offboarded successfully!");
//            } else {
//                return ResponseEntity.status(404).body("User not found.");
//            }
//        } catch (SQLException e) {
//            logger.error("SQL Exception occurred: {}", e.getMessage());
//            return ResponseEntity.status(500).body("An error occurred while processing the request.");
//        } finally {
//            try {
//                if (pstmt != null) pstmt.close();
//                if (con != null) con.close();
//            } catch (SQLException e) {
//                logger.error("Error closing resources: {}", e.getMessage());
//            }
//        }
//    }
//
//
//    // Login API
//    @PostMapping("/login")
//    @ResponseBody
//    // http://localhost:8080/cbicApi/api/admin/login
//    public ResponseEntity<String> login(@RequestBody LoginRequestDTO loginRequestDTO) {
//        Connection con = null;
//        PreparedStatement pstmt = null;
//        ResultSet resultSet = null;
//
//        String query = "SELECT password FROM LoginRequest WHERE email = ?";
//
//        try {
//            con = JDBCConnection.getTNConnection();
//            pstmt = con.prepareStatement(query);
//            pstmt.setString(1, loginRequestDTO.getUserEmail());
//
//            resultSet = pstmt.executeQuery();
//            if (resultSet.next()) {
//                String hashedPassword = resultSet.getString("password");
//                // Verify password
//                if (BCrypt.checkpw(loginRequestDTO.getPassword(), hashedPassword)) {
//                    return ResponseEntity.ok("Login successful!");
//                } else {
//                    return ResponseEntity.status(401).body("Invalid email or password.");
//                }
//            } else {
//                return ResponseEntity.status(401).body("Invalid email or password.");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return ResponseEntity.status(500).body("An error occurred while processing the request.");
//        } finally {
//            try {
//                if (resultSet != null) resultSet.close();
//                if (pstmt != null) pstmt.close();
//                if (con != null) con.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    // Forgot Password API
//    // http://localhost:8080/cbicApi/api/admin/forgotPassword?email=rks@example.com
//    @PostMapping("/forgotPassword")
//    @ResponseBody
//    public ResponseEntity<String> forgotPassword(@RequestParam("email") String userEmail) {
//        Connection con = null;
//        PreparedStatement pstmt = null;
//        ResultSet resultSet = null;
//
//        // Query to check if the user exists in the LoginRequest table
//        String query = "SELECT email FROM LoginRequest WHERE email = ?";
//        try {
//            con = JDBCConnection.getTNConnection();
//            pstmt = con.prepareStatement(query);
//            pstmt.setString(1, userEmail);
//
//            resultSet = pstmt.executeQuery();
//            if (resultSet.next()) {
//                // User found, generate a reset token
//                String resetToken = java.util.UUID.randomUUID().toString();
//
//                // Check if reset_token column exists in LoginRequest table before updating
//                String updateQuery = "UPDATE LoginRequest SET reset_token = ? WHERE email = ?";
//                pstmt.close(); // Close previous prepared statement before reusing
//                pstmt = con.prepareStatement(updateQuery);
//                pstmt.setString(1, resetToken);
//                pstmt.setString(2, userEmail);
//
//                int rowsUpdated = pstmt.executeUpdate();
//                if (rowsUpdated > 0) {
//                    // Log the reset token for debugging (in a real scenario, you'd send the token via email)
//                    logger.info("Reset token generated for user {}: {}", userEmail, resetToken);
//                    return ResponseEntity.ok("Password reset token sent to your email.");
//                } else {
//                    return ResponseEntity.status(500).body("Error updating reset token.");
//                }
//            } else {
//                return ResponseEntity.status(404).body("User not found.");
//            }
//        } catch (SQLException e) {
//            logger.error("SQL Exception occurred: {}", e.getMessage());
//            return ResponseEntity.status(500).body("An error occurred while processing the request.");
//        } finally {
//            try {
//                if (resultSet != null) resultSet.close();
//                if (pstmt != null) pstmt.close();
//                if (con != null) con.close();
//            } catch (SQLException e) {
//                logger.error("Error closing resources: {}", e.getMessage());
//            }
//        }
//    }
//}
//
