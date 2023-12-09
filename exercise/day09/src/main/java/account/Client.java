package account;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Client {
    private final List<OrderLine> lines;

    public Client(Map<String, Double> orderLines) {
        this(orderLines.entrySet().stream()
            .map(set -> new OrderLine(set.getKey(), set.getValue()))
            .toList());
    }

     public Client(List<OrderLine> list) {
        this.lines = list;
    }

    public String toStatement() {
        return lines.stream()
            .map(this::formatLine)
            .collect(Collectors.joining(System.lineSeparator()))
            .concat(System.lineSeparator() + "Total : " + getTotalAmount() + "€");
    }

    public double getTotalAmount() {
        return lines.stream()
            .map(OrderLine::cost)
            .reduce(Double::sum).orElse(0D);
    }

    private String formatLine(OrderLine entry) {
        return entry.description() + " for " + entry.cost() + "€";
    }

}

