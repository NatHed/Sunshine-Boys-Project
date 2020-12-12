
import android.content.Context;
import androidx.test.core.app.ApplicationProvider;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class LoginActivityTest {
    private static final String FAKE_NAME = "firstName";
    private static final String FAKE_GIVEN = "givenName";
    private static final String FAKE_FAMILY = "familyName";
    private static final String FAKE_EMAIL = "name@fake.ca";
    private static final String FAKE_ID = "?"; // google's docs on what goes here are lacking
    private static final Uri FAKE_PHOTO = "file://home/user/fake.png";
    private Context context = ApplicationProvider.getApplicationContext();

    @Test
    public void accountValidTest() {
        ClassUnderTest myObjectUnderTest = new ClassUnderTest(context);
        // attempted to write a unit test for logging in with a google account, but couldn't get google's libraries not to throw an exception in a unit test
    }
}
