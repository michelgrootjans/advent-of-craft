package greeting;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GreeterTest {
    @Test
    void saysHello() {
        Greeter greeter = new DefaultGreeter();

        assertThat(greeter.greet())
                .isEqualTo("Hello.");
    }

    @Test
    void saysHelloFormally() {
        Greeter greeter = new FormalGreeter();

        assertThat(greeter.greet())
                .isEqualTo("Good evening, sir.");
    }

    @Test
    void saysHelloCasually() {
        Greeter greeter = new CasualGreeter();

        assertThat(greeter.greet())
                .isEqualTo("Sup bro?");
    }

    @Test
    void saysHelloIntimately() {
        Greeter greeter = new IntimateGreeter();

        assertThat(greeter.greet())
                .isEqualTo("Hello Darling!");
    }
}
