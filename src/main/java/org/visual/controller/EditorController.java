package org.visual.controller;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import jakarta.inject.Singleton;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.SetChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.control.*;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.javafx.FontIcon;
import org.visual.core.GraphEditorPersistence;
import org.visual.customskins.DefaultSkinController;
import org.visual.customskins.SkinController;
import org.visual.customskins.TitledSkinController;
import org.visual.customskins.TreeSkinController;
import org.visual.customskins.titled.TitledSkinConstants;
import org.visual.customskins.tree.TreeConnectorValidator;
import org.visual.customskins.tree.TreeSkinConstants;
import org.visual.graph.editor.api.Commands;
import org.visual.graph.editor.api.EditorElement;
import org.visual.graph.editor.api.GraphEditor;
import org.visual.graph.editor.core.DefaultGraphEditor;
import org.visual.graph.editor.core.skins.defaults.connection.SimpleConnectionSkin;
import org.visual.graph.editor.core.view.GraphEditorContainer;
import org.visual.graph.editor.model.GNode;
import org.visual.graph.editor.model.GraphFactory;
import org.visual.selections.SelectionCopier;

@Singleton
@Slf4j
public class EditorController implements Initializable {
  private static final String STYLE_CLASS_TITLED_SKINS = "titled-skins";

  @FXML private Button addConnectorButton;

  @FXML private Button clearConnectorsButton;

  @FXML private RadioButton inputConnectorTypeButton;

  @FXML private RadioButton outputConnectorTypeButton;

  @FXML private RadioButton leftConnectorPositionButton;

  @FXML private RadioButton rightConnectorPositionButton;

  @FXML private RadioButton topConnectorPositionButton;

  @FXML private RadioButton bottomConnectorPositionButton;

  @FXML private RadioButton showGridButton;

  @FXML private RadioButton snapToGridButton;

  //    @FXML
  //    private Menu readOnlyMenu;

  @FXML private RadioButton defaultSkinButton;

  @FXML private RadioButton treeSkinButton;

  @FXML private RadioButton titledSkinButton;

  //    @FXML
  //    private Menu intersectionStyle;

  @FXML private RadioButton gappedStyleButton;

  @FXML private RadioButton detouredStyleButton;

  @FXML private ToggleButton minimapButton;

  @FXML private GraphEditorContainer graphEditorContainer;

  private final GraphEditor graphEditor = new DefaultGraphEditor();
  private final SelectionCopier selectionCopier =
      new SelectionCopier(graphEditor.getSkinLookup(), graphEditor.getSelectionManager());
  private final GraphEditorPersistence graphEditorPersistence = new GraphEditorPersistence();

  private DefaultSkinController defaultSkinController;
  private TreeSkinController treeSkinController;
  private TitledSkinController titledSkinController;

  private final SimpleObjectProperty<SkinController> activeSkinController =
      new SimpleObjectProperty<>() {

        @Override
        protected void invalidated() {
          super.invalidated();
          if (get() != null) {
            get().activate();
          }
        }
      };

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    val model = GraphFactory.eINSTANCE.createGModel();

    graphEditor.setModel(model);
    graphEditorContainer.setGraphEditor(graphEditor);

    setDetouredStyle();

    defaultSkinController = new DefaultSkinController(graphEditor, graphEditorContainer);
    treeSkinController = new TreeSkinController(graphEditor, graphEditorContainer);
    titledSkinController = new TitledSkinController(graphEditor, graphEditorContainer);

    activeSkinController.set(defaultSkinController);

    graphEditor.modelProperty().addListener((w, o, n) -> selectionCopier.initialize(n));
    selectionCopier.initialize(model);

    initializeMenuBar();
    addActiveSkinControllerListener();
  }

  public void panToCenter() {
    graphEditorContainer.panTo(Pos.CENTER);
  }

  @FXML
  public void load() {
    graphEditorPersistence.loadFromFile(graphEditor);
    checkSkinType();
  }

  @FXML
  public void loadSample() {
    defaultSkinButton.setSelected(true);
    setDefaultSkin();
    graphEditorPersistence.loadSample(graphEditor);
  }

  @FXML
  public void loadSampleLarge() {
    defaultSkinButton.setSelected(true);
    setDefaultSkin();
    graphEditorPersistence.loadSampleLarge(graphEditor);
  }

  @FXML
  public void loadTree() {
    treeSkinButton.setSelected(true);
    setTreeSkin();
    graphEditorPersistence.loadTree(graphEditor);
  }

  @FXML
  public void loadTitled() {
    titledSkinButton.setSelected(true);
    setTitledSkin();
    graphEditorPersistence.loadTitled(graphEditor);
  }

  @FXML
  public void save() {
    graphEditorPersistence.saveToFile(graphEditor);
  }

  @FXML
  public void clearAll() {
    Commands.clear(graphEditor.getModel());
  }

  @FXML
  public void exit() {
    Platform.exit();
  }

  @FXML
  public void undo() {
    Commands.undo(graphEditor.getModel());
  }

  @FXML
  public void redo() {
    Commands.redo(graphEditor.getModel());
  }

  @FXML
  public void copy() {
    selectionCopier.copy();
  }

  @FXML
  public void paste() {
    activeSkinController.get().handlePaste(selectionCopier);
  }

  @FXML
  public void selectAll() {
    activeSkinController.get().handleSelectAll();
  }

  @FXML
  public void deleteSelection() {

    final List<EObject> selection =
        new ObjectArrayList<>(graphEditor.getSelectionManager().getSelectedItems());
    graphEditor.delete(selection);
  }

  @FXML
  public void addNode() {
    activeSkinController.get().addNode(graphEditor.getView().getLocalToSceneTransform().getMxx());
  }

  @FXML
  public void addConnector() {
    activeSkinController
        .get()
        .addConnector(getSelectedConnectorPosition(), inputConnectorTypeButton.isSelected());
  }

  @FXML
  public void clearConnectors() {
    activeSkinController.get().clearConnectors();
  }

  @FXML
  public void setDefaultSkin() {
    activeSkinController.set(defaultSkinController);
  }

  @FXML
  public void setTreeSkin() {
    activeSkinController.set(treeSkinController);
  }

  @FXML
  public void setTitledSkin() {
    activeSkinController.set(titledSkinController);
  }

  @FXML
  public void setGappedStyle() {
    graphEditor.getProperties().getCustomProperties().remove(SimpleConnectionSkin.SHOW_DETOURS_KEY);
    graphEditor.reload();
  }

  @FXML
  public void setDetouredStyle() {
    final Map<String, String> customProperties = graphEditor.getProperties().getCustomProperties();
    customProperties.put(SimpleConnectionSkin.SHOW_DETOURS_KEY, Boolean.toString(true));
    graphEditor.reload();
  }

  @FXML
  public void toggleMinimap() {
    graphEditorContainer.getMinimap().visibleProperty().bind(minimapButton.selectedProperty());
  }

  /** Initializes the menu bar. */
  private void initializeMenuBar() {

    final ToggleGroup skinGroup = new ToggleGroup();
    skinGroup.getToggles().addAll(defaultSkinButton, treeSkinButton, titledSkinButton);

    final ToggleGroup connectionStyleGroup = new ToggleGroup();
    connectionStyleGroup.getToggles().addAll(gappedStyleButton, detouredStyleButton);

    final ToggleGroup connectorTypeGroup = new ToggleGroup();
    connectorTypeGroup.getToggles().addAll(inputConnectorTypeButton, outputConnectorTypeButton);

    final ToggleGroup positionGroup = new ToggleGroup();
    positionGroup.getToggles().addAll(leftConnectorPositionButton, rightConnectorPositionButton);
    positionGroup.getToggles().addAll(topConnectorPositionButton, bottomConnectorPositionButton);

    graphEditor.getProperties().gridVisibleProperty().bind(showGridButton.selectedProperty());
    graphEditor.getProperties().snapToGridProperty().bind(snapToGridButton.selectedProperty());

    //            readOnlyMenu.getItems().add(readOnly);
    Arrays.stream(EditorElement.values())
        .forEach(
            type -> {
              final CheckMenuItem readOnly = new CheckMenuItem(type.name());
              graphEditor.getProperties().readOnlyProperty(type).bind(readOnly.selectedProperty());
            });
    minimapButton.setGraphic(new FontIcon(FontAwesomeSolid.MAP));

    final SetChangeListener<? super EObject> selectedNodesListener =
        change -> checkConnectorButtonsToDisable();
    graphEditor.getSelectionManager().getSelectedItems().addListener(selectedNodesListener);
    checkConnectorButtonsToDisable();
  }

  /** Adds a listener to make changes to available menu options when the skin type changes. */
  private void addActiveSkinControllerListener() {
    activeSkinController.addListener((_, _, _) -> handleActiveSkinControllerChange());
  }

  /**
   * Enables & disables certain menu options and sets CSS classes based on the new skin type that
   * was set active.
   */
  private void handleActiveSkinControllerChange() {

    if (treeSkinController.equals(activeSkinController.get())) {

      graphEditor.setConnectorValidator(new TreeConnectorValidator());
      graphEditor.getView().getStyleClass().remove(STYLE_CLASS_TITLED_SKINS);
      treeSkinButton.setSelected(true);

    } else if (titledSkinController.equals(activeSkinController.get())) {

      graphEditor.setConnectorValidator(null);
      if (!graphEditor.getView().getStyleClass().contains(STYLE_CLASS_TITLED_SKINS)) {
        graphEditor.getView().getStyleClass().add(STYLE_CLASS_TITLED_SKINS);
      }
      titledSkinButton.setSelected(true);

    } else {

      graphEditor.setConnectorValidator(null);
      graphEditor.getView().getStyleClass().remove(STYLE_CLASS_TITLED_SKINS);
      defaultSkinButton.setSelected(true);
    }

    // Demo does not currently support mixing of skin types. Skins don't know how to cope with
    // it.
    clearAll();
    flushCommandStack();
    checkConnectorButtonsToDisable();
    selectionCopier.clearMemory();
  }

  /** Crudely inspects the model's first node and sets the new skin type accordingly. */
  private void checkSkinType() {

    if (graphEditor.getModel().getNodes().isEmpty()) {
      return;
    }

    val firstNode = graphEditor.getModel().getNodes().getFirst();
    val type = firstNode.getType();

    if (TreeSkinConstants.TREE_NODE.equals(type)) {
      activeSkinController.set(treeSkinController);
    } else if (TitledSkinConstants.TITLED_NODE.equals(type)) {
      activeSkinController.set(titledSkinController);
    } else {
      activeSkinController.set(defaultSkinController);
    }
  }

  /** Checks if the connector buttons need disabling (e.g. because no nodes are selected). */
  private void checkConnectorButtonsToDisable() {

    final boolean nothingSelected =
        graphEditor.getSelectionManager().getSelectedItems().stream()
            .noneMatch(e -> e instanceof GNode);

    final boolean treeSkinActive = treeSkinController.equals(activeSkinController.get());
    final boolean titledSkinActive = titledSkinController.equals(activeSkinController.get());

    if (titledSkinActive || treeSkinActive) {
      addConnectorButton.setDisable(true);
      clearConnectorsButton.setDisable(true);
      //            connectorTypeMenu.setDisable(true);
      //            connectorPositionMenu.setDisable(true);
    } else if (nothingSelected) {
      addConnectorButton.setDisable(true);
      clearConnectorsButton.setDisable(true);
      //            connectorTypeMenu.setDisable(false);
      //            connectorPositionMenu.setDisable(false);
    } else {
      addConnectorButton.setDisable(false);
      clearConnectorsButton.setDisable(false);
      //            connectorTypeMenu.setDisable(false);
      //            connectorPositionMenu.setDisable(false);
    }

    //        intersectionStyle.setDisable(treeSkinActive);
  }

  /** Flushes the command stack, so that the undo/redo history is cleared. */
  private void flushCommandStack() {

    val editingDomain = AdapterFactoryEditingDomain.getEditingDomainFor(graphEditor.getModel());
    if (editingDomain != null) {
      editingDomain.getCommandStack().flush();
    }
  }

  /**
   * Gets the side corresponding to the currently selected connector position in the menu.
   *
   * @return the {@link Side} corresponding to the currently selected connector position
   */
  private Side getSelectedConnectorPosition() {

    if (leftConnectorPositionButton.isSelected()) {
      return Side.LEFT;
    }
    if (rightConnectorPositionButton.isSelected()) {
      return Side.RIGHT;
    }
    if (topConnectorPositionButton.isSelected()) {
      return Side.TOP;
    }
    return Side.BOTTOM;
  }
}
