package GUI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class UserStorage {

    private static final String USER_FILE = "src/Resources/users.txt";

    // Register a new user. Returns true if success, false if username already exists.
    public static boolean registerUser(String username, String password, String email, String userCurrency) {
        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            return false;
        }

            // Check if username already exists	
            try (BufferedReader br = new BufferedReader(new FileReader(USER_FILE))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",", 4);
                    if (parts.length == 4) {
                        String existingUser = parts[0].trim();
                        if (existingUser.equalsIgnoreCase(username.trim())) {
                            return false; // user already exists
                        }
                    }
                } // generate error exceptions for files
            } catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

            // Add new user to file
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(USER_FILE, true))) {
                bw.write(username.trim() + "," + password + "," + email.trim() + "," + userCurrency); // write each username and password in file
                bw.newLine();
            } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

          return true;
    }

    // Check login. Returns true if username+password match
    public static boolean validateLogin(String username, String password) {
    	
        if (username == null || password == null) {
            return false;
        }

            try (BufferedReader br = new BufferedReader(new FileReader(USER_FILE))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",", 4);
                    if (parts.length == 4) {
                        String fileUsername = parts[0].trim();
                        String filePassword = parts[1];
                        if (fileUsername.equalsIgnoreCase(username.trim()) && filePassword.equals(password)) {
                            return true;
                        }
                    }
                }
            } catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        return false;
    }
    
    
    
    
    public static boolean updateUser(String oldUsername, String newUsername, String newPassword, 
            String newEmail, String newCurrency) {

    		if (oldUsername == null || oldUsername.isBlank()) {
    			return false; }

    			File tempFile = new File("src/Resources/users_temp.txt");
    			File originalFile = new File("src/Resources/users.txt");

    		boolean userFound = false;

    		try (BufferedReader br = new BufferedReader(new FileReader(originalFile));
    				
    		BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {
    			String line;

    			while ((line = br.readLine()) != null) {
    					String[] parts = line.split(",", 4);
    					
    					if (parts.length == 4) {
    						String existingUser = parts[0].trim();

    						if (existingUser.equalsIgnoreCase(oldUsername.trim())) {
    						
    							userFound = true;
    							String updatedLine = newUsername.trim() + "," + newPassword + "," + newEmail.trim() + "," + newCurrency;
    							bw.write(updatedLine);
    							bw.newLine();
    							} else {
    								bw.write(line);
    								bw.newLine();
    							}
    					}
    			}

    		} catch (IOException e) {
    			e.printStackTrace();
    			return false;
    		}

    		// Replace old file with updated file
    		if (userFound) {
    			originalFile.delete();
    			tempFile.renameTo(originalFile);
    		} else {
    			tempFile.delete();
    		}

    		return userFound;
    }
    
    
    
    public static String getUserCurrency(String username) {
        if (username == null || username.isBlank()) {
            return "USD"; // default
        }

        try (BufferedReader br = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 4);
                if (parts.length == 4) {
                    String fileUsername = parts[0].trim();
                    String currency = parts[3].trim(); // get currency, last thing stored in user data
                    if (fileUsername.equalsIgnoreCase(username.trim())) {
                        return currency;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "USD"; // if errors in getting currency display USD
    }


}


