import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.Metadata;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static com.framework.utilities.Constants.TEST_FILE_PATH;

public class DropboxUtils {

    private static DbxClientV2 client;

    public DropboxUtils(DbxClientV2 client) {
        this.client = client;
    }

    public void deleteFileandFolder(String file_name) throws DbxException {
        List<Metadata> allData = client.files().listFolder("").getEntries();

        for (Metadata eachData : allData) {
            if (eachData.getName().contains(file_name)) {
                client.files().deleteV2("/" + file_name);
                break;
            }
        }
    }

    public static void uploadFile(String fileName) throws IOException, DbxException {
        try (InputStream in = new FileInputStream(TEST_FILE_PATH)) {
            client.files().uploadBuilder("/" + fileName).uploadAndFinish(in);
        }
    }
}
