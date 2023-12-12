package greeting;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class GreeterTest {
    @Test
    void saysHello() {
        Greeter greeter = GreeterFactory.create("default");

        assertThat(greeter.greet())
                .isEqualTo("Hello.");
    }

    @Test
    void saysHelloFormally() {
        Greeter greeter = GreeterFactory.create("formal");

        assertThat(greeter.greet())
                .isEqualTo("Good evening, sir.");
    }

    @Test
    void saysHelloCasually() {
        Greeter greeter = GreeterFactory.create("casual");

        assertThat(greeter.greet())
                .isEqualTo("Sup bro?");
    }

    @Test
    void saysHelloIntimately() {
        Greeter greeter = GreeterFactory.create("intimate");

        assertThat(greeter.greet())
                .isEqualTo("Hello Darling!");
    }
}
