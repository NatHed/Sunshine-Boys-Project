package weather.app.simpleweather;

import org.junit.Test;
import android.content.Context;
import androidx.test.core.app.ApplicationProvider;
import java.util.regex.Pattern;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class weatherUnitTest {
    private Context context = ApplicationProvider.getApplicationContext();
    private static final TEST_PASSWORD = "1aA$fgh";
    private static final TEST_EMAIL = "haha@yes.ca"
    private static final Pattern PASSWORD_PATTERN=
         Pattern.compile("^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                "(?=.*[A-Z])" +         //at least 1 upper case letter
                //"(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{7,}" +               //at least 7 characters
                         "$");
    @Mock
    Context mockContext;

    @Test
    public void password_pattern_valid() {
        assertThat(PASSWORD_PATTERN.matcher(checkpassword).matches()).isTrue();
    }

    @Test
    public void email_login_valid() {
        when(mockContext.getString(R.string.email))
            .thenReturn(TEST_EMAIL);
        ClassUnderTest myObjectUnderTest = new ClassUnderTest(mockContext);
        assertThat(Patterns.EMAIL_ADDRESS.matcher(checkemail).matches());
    }
}
