import account.Client;
import account.OrderLine;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.lang.System.lineSeparator;
import static org.assertj.core.api.Assertions.assertThat;

class ClientTests {

    @Test
    void client_should_return_statement() {
        var client = new Client(List.of(
            new OrderLine("Tenet Deluxe Edition", 45.99),
            new OrderLine("Inception", 30.50),
            new OrderLine("The Dark Knight", 30.50),
            new OrderLine("Interstellar", 23.98))
        );

        assertThat(client.getTotalAmount()).isEqualTo(45.99 + 30.50 + 30.50 + 23.98);
        assertThat(client.toStatement()).isEqualTo(
                "Tenet Deluxe Edition for 45.99€" + lineSeparator() +
                        "Inception for 30.5€" + lineSeparator() +
                        "The Dark Knight for 30.5€" + lineSeparator() +
                        "Interstellar for 23.98€" + lineSeparator() +
                        "Total : 130.97€");
    }
}