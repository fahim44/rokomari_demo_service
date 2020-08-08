package com.fahim.route;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.lang.NonNull;

@Route
@CssImport("./styles/shared-styles.css")
public class MainView extends VerticalLayout {

    private Paragraph paragraph;

    public MainView() {
        addClassName("centered-content");
        initContent();
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
        });
        add(startButton);
    }

    private void setupStopButton() {
        Button stopButton = new Button("Stop");
        stopButton.addClickListener(buttonClickEvent -> {
            //TODO
            addToLog("stop button clicked");
        });
        add(stopButton);
    }

    private void addToLog(@NonNull String message) {
        paragraph.setText(message + " ======================= " + paragraph.getText());
    }
}