package weather.app.simpleweather;

import org.junit.Test;

import static org.junit.Assert.*;


public class RegisterActivityTest {
    // Test the password matching pattern against a known good string
    @Test
    public void passwordTest_isCorrect() {
        private final String FAKE_PASSWORD = "1eA!tyui";
        assertThat(PASSWORD_PATTERN.matcher(FAKE_PASSWORD).matches()).isTrue();
    }
    // Do the same for email
    @Test
    public void emailTest_isCorrect() {
        private final String FAKE_EMAIL = "user@host.ca";
        assertThat(Patterns.EMAIL_ADDRESS.matcher(FAKE_EMAIL).matches()).isTrue();
    }
}
