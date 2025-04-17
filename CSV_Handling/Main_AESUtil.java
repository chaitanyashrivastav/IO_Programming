package org.example;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

class AESUtil{
    private static final String SECRET_KEY = "1234567890123456" ;

    public static String encrypt(String data) throws Exception {
        SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes() , "AES") ;   // uses the password as key
        Cipher cipher = Cipher.getInstance("AES") ;  // use AES type
        cipher.init(Cipher.ENCRYPT_MODE, key);                  // set to encryption mode
        byte[] encrypted = cipher.doFinal(data.getBytes());   // do the encryption

        return Base64.getEncoder().encodeToString(encrypted) ;   // make it readable
    }

    // Turn secret code back into normal text
    public static String decrypt(String encryptedData) throws Exception {
        SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes() , "AES") ;
        Cipher cipher = Cipher.getInstance("AES") ;
        cipher.init(Cipher.DECRYPT_MODE , key); // now into the decrypt mode
        byte[] decoded = Base64.getDecoder().decode(encryptedData);  // decode secret string
        byte[] decrypted = cipher.doFinal(decoded);      // decrypt it

        return new String(decrypted) ;  // back to normal
    }
}


class CSVWriter {
    public static void writeEncryptedCSV(String filename, List<String[]> data, int[] sensitiveIndexes) throws Exception {
        try (FileWriter writer = new FileWriter(filename)) {
            for (String[] row : data) {
                for (int i = 0; i < row.length; i++) {
                    if (isSensitive(i, sensitiveIndexes)) { //if it is  to be encrypted
                        row[i] = AESUtil.encrypt(row[i]);
                    }
                }
                writer.write(String.join(",",row) + "\n");
            }
        }
    }

    private static boolean isSensitive(int index, int[] sensitiveIndexes) {
        for (int i : sensitiveIndexes) {
            if (i == index) return true;
        }
        return false;
    }
}

class CSVReader {
    public static List<String[]> readDecryptedCSV(String filename, int[] sensitiveIndexes) throws Exception {
        List<String[]> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                for (int i = 0; i < row.length; i++) {
                    if (isSensitive(i, sensitiveIndexes)) {
                        row[i] = AESUtil.decrypt(row[i]);
                    }
                }
                result.add(new String[]{row[1],row[2]});
            }
        }
        return result;
    }

    private static boolean isSensitive(int index, int[] sensitiveIndexes) {
        for (int i : sensitiveIndexes) {
            if (i == index) return true;
        }
        return false;
    }
}

public class Main_AESUtil {
    public static void main(String[] args) throws Exception {
        List<String[]> data = Arrays.asList(
                new String[]{"Name", "Email", "Salary"},
                new String[]{"Alice", "alice@example.com", "70000"},
                new String[]{"Bob", "bob@example.com", "80000"}
        );

        int[] sensitiveIndexes = {1, 2}; // Email and Salary

        CSVWriter.writeEncryptedCSV("src/employees.csv", data, sensitiveIndexes);

        List<String[]> decrypted = CSVReader.readDecryptedCSV("src/employees.csv", sensitiveIndexes);
        for (String[] row : decrypted) {
            System.out.println(Arrays.toString(row));
        }
    }
}

