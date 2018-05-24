/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ciphervideoexample;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


public class CipherVideoExample {
    
    private static byte[] encryptionKey = "jkafADFASDfq34572987985d".getBytes(StandardCharsets.UTF_8);

    private static final String ALGORITHM = "AES";

    public static void main(String[] args) {
        try {
            byte[] data = Files.readAllBytes(new File("src/in/film1mp4v.mp4").toPath());
            
            SecretKeySpec secretKey = new SecretKeySpec(encryptionKey, ALGORITHM);
            
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            
            byte[] dataEncripted = cipher.doFinal(data);
            
            FileOutputStream fos = new FileOutputStream("src/out/film1mp4v.crypt");
            fos.write(dataEncripted);
            fos.close();
            
            
            //desencriptar
            
            byte[] datainputEncrypted = Files.readAllBytes(new File("src/out/film1mp4v.crypt").toPath());
            
            Cipher cipher2 = Cipher.getInstance(ALGORITHM);
            cipher2.init(Cipher.DECRYPT_MODE, secretKey);
            
            byte[] dataDecripted = cipher2.doFinal(datainputEncrypted);
            
            FileOutputStream fos2 = new FileOutputStream("src/out/film1mp4v_decripted.mp4");
            fos2.write(dataDecripted);
            fos2.close();
        } catch (Exception ex) {
            Logger.getLogger(CipherVideoExample.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    
}
