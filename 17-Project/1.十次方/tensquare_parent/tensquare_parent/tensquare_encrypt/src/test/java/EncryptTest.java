import com.tensquare.encrypt.EncryptApplication;
import com.tensquare.encrypt.rsa.RsaKeys;
import com.tensquare.encrypt.service.RsaService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = EncryptApplication.class)
public class EncryptTest {

    @Autowired
    private RsaService rsaService;

    @Before
    public void before() throws Exception{
    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void genEncryptDataByPubKey() {
        String data = "{\"title\":\"java\"}";

        try {

            String encData = rsaService.RSAEncryptDataPEM(data, RsaKeys.getServerPubKey());

            System.out.println("data: " + data);
            System.out.println("encData: " + encData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test() throws Exception {
        String requestData = "nlfwL0U3RnWdLkfu8n1Nl7wdZAhAu29lRYSTWrGzZ/3PMLh2dNOe6MZ494kBHkE3IfPsYQB0vDTOQX2gx4Nn8ZXkdcekVmviSqqX2BUMhaHMUaV88+ZY3LBPEgpL+AGfDBRwb+gAR7HvAs3NJqutyQFH6yYkJu8XWOAbzPxWD0bZQvopeu7hyfsRqu+dLJI+imQ+dTxbGqQa09j9CtEPnYRJi3gxSqGD+RWGi9cVuxa7xiqVHji7u5q1h3cVvy+8FTmfiR6WMk42Y7ujdfxpFfXBY/qDXRHvq1WBWEnrKVVIN3dhJ7TAlpRgEjQxBQklSfEXwc1Pk7oXR0sSSsnjKA==";

        String decryptData = rsaService.RSADecryptDataPEM(requestData, RsaKeys.getServerPrvKeyPkcs8());

        System.out.println(decryptData);

    }
}
