package com.fahim.route;

import com.fahim.model.Order;
import com.fahim.service.OrderService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;

import java.util.Date;
import java.util.Objects;
import java.util.Random;

@Slf4j

@Route("client")
@CssImport("./styles/shared-styles.css")
public class MainView extends VerticalLayout {

    private Paragraph paragraph;
    private final OrderService orderService;
    private final Random random;

    @Autowired
    public MainView(OrderService orderService) {
        this.orderService = orderService;
        this.random = new Random();
        addClassName("centered-content");
        initContent();
    }

    private void initContent() {
        setupSendButton();

        paragraph = new Paragraph("");
        add(paragraph);
    }

    private void setupSendButton() {
        Button sendButton = new Button("Create new Order");
        sendButton.addClickListener(buttonClickEvent -> {

            Order order = new Order();
            order.setName(getRandomString());
            order.setPrice(random.nextDouble());
            order.setTime(new Date());
            addToLog(String.format("new Order: %s", order));
            ResponseEntity<Order> responseEntity = orderService.createNewOrder(order);
            log.info(responseEntity.getStatusCode().toString());
            log.info(Objects.requireNonNull(responseEntity.getBody()).toString());
            getUI().ifPresent(ui ->
                    addToLog("Response: " + Objects.requireNonNull(responseEntity.getBody()).toString()));
        });
        add(sendButton);
    }

    private String getRandomString() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    private void addToLog(@NonNull String message) {
        paragraph.setText(message + " ======================= " + paragraph.getText());
    }
}