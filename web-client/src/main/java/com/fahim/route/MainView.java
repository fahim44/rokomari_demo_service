package com.fahim.route;

import com.fahim.service.SocketService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;

@Slf4j
@Route
@CssImport("./styles/shared-styles.css")
public class MainView extends VerticalLayout {

    private Paragraph paragraph;
    private SocketService socketService;

    @Autowired
    public MainView(SocketService socketService) {
        addClassName("centered-content");
        initContent();

        this.socketService = socketService;
    }

    private void initContent() {
        setupStartButton();
        setupStopButton();

        paragraph = new Paragraph("");
        add(paragraph);
    }

    private void setupStartButton() {
        Button startButton = new Button("Start");
        startButton.addClickListener(buttonClickEvent -> {
            //TODO;
            addToLog("start button clicked");
            socketService.connect();
        });
        add(startButton);
    }

    private void setupStopButton() {
        Button stopButton = new Button("Stop");
        stopButton.addClickListener(buttonClickEvent -> {
            //TODO
            addToLog("stop button clicked");
            socketService.disconnect();
        });
        add(stopButton);
    }

    private void addToLog(@NonNull String message) {
        paragraph.setText(message + " ======================= " + paragraph.getText());
    }
}