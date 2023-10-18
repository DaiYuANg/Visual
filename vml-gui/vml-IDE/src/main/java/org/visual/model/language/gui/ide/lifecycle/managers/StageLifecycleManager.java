package org.visual.model.language.gui.ide.lifecycle.managers;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.github.eckig.grapheditor.Commands;
import io.github.eckig.grapheditor.GraphEditor;
import io.github.eckig.grapheditor.core.DefaultGraphEditor;
import io.github.eckig.grapheditor.model.GConnector;
import io.github.eckig.grapheditor.model.GModel;
import io.github.eckig.grapheditor.model.GNode;
import io.github.eckig.grapheditor.model.GraphFactory;
import io.vertx.core.eventbus.EventBus;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.extern.slf4j.Slf4j;
import org.visual.model.language.gui.ide.di.DIContainer;
import org.visual.model.language.gui.ide.event.fx.handlers.OnCloseRequestHandler;
import org.visual.model.language.gui.ide.lifecycle.LifecycleManager;
import org.visual.model.language.gui.ide.views.scene.WorkspaceScene;

@Slf4j
@Singleton
public class StageLifecycleManager implements LifecycleManager {

    @Inject
    private Stage mainStage;

    private final EventBus eventBus;

    private final Rectangle2D bounds = Screen.getPrimary().getVisualBounds();

    {
        eventBus = DIContainer.INSTANCE.getInjector().getInstance(EventBus.class);
    }

    @Override
    public void initialize() {
        log.atInfo().log("stage initializer executing");
        setOnClose();
        setView();
        listenResizeAndPosition();
    }

    @Override
    public void stop() {
    }

    private void setOnClose() {
        mainStage.setOnCloseRequest(new OnCloseRequestHandler(mainStage));
    }
    private GNode createNode()
    {
        GNode node = GraphFactory.eINSTANCE.createGNode();

        GConnector input = GraphFactory.eINSTANCE.createGConnector();
        GConnector output = GraphFactory.eINSTANCE.createGConnector();

        input.setType("left-input");
        output.setType("right-output");

        node.getConnectors().add(input);
        node.getConnectors().add(output);

        return node;
    }

    private void addNodes(GModel model)
    {
        GNode firstNode = createNode();
        GNode secondNode = createNode();

        firstNode.setX(150);
        firstNode.setY(150);

        secondNode.setX(400);
        secondNode.setY(200);
        secondNode.setWidth(200);
        secondNode.setHeight(150);

        Commands.addNode(model, firstNode);
        Commands.addNode(model, secondNode);
    }

    private void setView() {
        mainStage.initStyle(StageStyle.TRANSPARENT);
        mainStage.setResizable(true);
        mainStage.centerOnScreen();
        mainStage.setTitle(System.getProperty("application.name"));
        mainStage.setWidth(bounds.getWidth() * 0.5);
        mainStage.setHeight(bounds.getHeight() * 0.6);
		log.info("{}",eventBus);
        eventBus.consumer("changeScene", event -> {
			log.info("123");
            GraphEditor graphEditor = new DefaultGraphEditor();
            GModel model = GraphFactory.eINSTANCE.createGModel();
            graphEditor.setModel(model);
            addNodes(model);
            Scene scene = new Scene(graphEditor.getView(), 800, 600);
			Platform.runLater(()-> mainStage.setScene(scene));

        });
    }

    private void listenResizeAndPosition() {
        mainStage.widthProperty().addListener((observable, oldValue, newValue) -> log.info(newValue.toString()));

        mainStage.heightProperty().addListener((observable, oldValue, newValue) -> log.info(newValue.toString()));
        mainStage.xProperty().addListener((observable, oldValue, newValue) -> System.out.println("X 坐标位置变化：从 " + oldValue + " 到 " + newValue));
        mainStage.yProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Y 坐标位置变化：从 " + oldValue + " 到 " + newValue);
        });
    }
}
