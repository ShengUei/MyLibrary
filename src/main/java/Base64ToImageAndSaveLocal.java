/*
* Author: Sheng-Uei,Weng
* Creation Date: 2022/04/21
* Modified Date:
* Description:
*   Convert the Base64 String to image file, and save file to classpath directory in web server
*/

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.time.Instant;

public class Base64ToImageAndSaveLocal {

    public String bs64ToImgSave(String imgBase64) {
        String[] splitByComma = imgBase64.split(",");
        String fileExtension = splitByComma[0].split("/")[1].split(";")[0];
        String fileName = String.format("%s.%s", Instant.now().toEpochMilli(), fileExtension);
        byte[] byteArray = Base64.decodeBase64(splitByComma[1]);
        try {
            String pathname = ResourceUtils.getURL("classpath:").getPath() + "static/images/" + fileName;
            FileUtils.writeByteArrayToFile(new File(pathname), byteArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }

}
