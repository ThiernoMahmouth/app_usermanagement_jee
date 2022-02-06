package utils;

import java.security.SecureRandom;
import java.util.Base64;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
  
public class PasswordUtils 
{
	  private static final SecureRandom RAND = new SecureRandom();
	  private static final int ITERATIONS = 65536;
	  public static final int KEY_LENGTH = 256;
	  private static final String ALGORITHM = "PBKDF2WithHmacSHA512";

	  public static String generateSalt (final int length) 
	  {
		    if (length < 1) 
		    {
		    	System.err.println("error in generateSalt: length must be > 0");
		    	return null;
		    }
		    byte[] salt = new byte[length];
		    RAND.nextBytes(salt);
		    return Base64.getEncoder().encodeToString(salt);
	  }
	  
	  public static String hashPassword (String password, String salt) 
	  {
		    char[] chars = password.toCharArray();
		    byte[] bytes = salt.getBytes();

		    PBEKeySpec spec = new PBEKeySpec(chars, bytes, ITERATIONS, KEY_LENGTH);

		    Arrays.fill(chars, Character.MIN_VALUE);

		    try 
		    {
		    	SecretKeyFactory fac = SecretKeyFactory.getInstance(ALGORITHM);
		    	byte[] securePassword = fac.generateSecret(spec).getEncoded();
		    	return Base64.getEncoder().encodeToString(securePassword);
		    } 
		    catch (NoSuchAlgorithmException | InvalidKeySpecException ex) 
		    {
		    	System.err.println("Exception encountered in hashPassword()");
		    	return null;
		    }
		    finally 
		    {
		      spec.clearPassword();
		    }
	  }
	  
	  public static boolean verifyPassword(String password, String key, String salt)
	  {
		    String mdpEncrypted = hashPassword(password, salt);
		    if (mdpEncrypted == null) 
		    	return false;
		    return mdpEncrypted.equals(key);
	  }
	  
	  public static void main(String[] args) 
	  {
		  /*Secure secure = new Secure();
		  String password = "THIERNO";
		  System.out.println(secure.toString());*/
		  /*
		  System.out.println("Password: " + password);
		  String passCrypt = hashPassword(password, secure.getSalt());
		  System.out.println("Password Encrypted: " + passCrypt);
		  */
		  System.out.println("/********************************************************************/");
		  /*
		  System.out.println("Test 1: " + verifyPassword("NADA", passCrypt, secure.getSalt()));
		  System.out.println("Test 2: " + verifyPassword("THIERNO", passCrypt, secure.getSalt()));
		  */

	  }
}
