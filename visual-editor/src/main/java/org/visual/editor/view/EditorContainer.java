package org.visual.editor.view;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.visual.data.structure.graph.Model;
import org.visual.editor.core.VisualEditor;

import static java.util.Optional.ofNullable;

@Slf4j
public class EditorContainer extends AutoScrollingWindow {

  private final ObjectProperty<VisualEditor> visualEditor = new SimpleObjectProperty<>();

  public void setVisualEditor(@NonNull VisualEditor editor) {
    visualEditor.set(editor);
  }

  private final ChangeListener<Model> modelChangeListener = (observable, oldValue, newValue) -> modelChanged(newValue);

  public EditorContainer() {
    visualEditor.addListener((observable, oldValue, newValue) -> {
      clearEditorState(oldValue);
      setupEditorState(newValue);
    });
  }

  private void modelChanged(Model newValue) {
//      graphEditor.getView().resize(newValue.getContentWidth(), newValue.getContentHeight());
//    checkWindowBounds();
//    minimap.setModel(newValue);
  }

  private void clearEditorState(@NotNull VisualEditor editor) {
    editor.getModelProperty().removeListener(modelChangeListener);
    setEditorProperties(null);
  }

  private void setupEditorState(@NotNull VisualEditor editor) {
    editor.getModelProperty().addListener(modelChangeListener);

    val view = editor.getView();
    val model = editor.getModel();

    ofNullable(model)
      .ifPresent(existsModel -> view.resize(model.getContentWidth(), model.getContentHeight()));

    setContent(view);
//    minimap.setContent(view);
//    minimap.setModel(model);
//    minimap.setSelectionManager(pGraphEditor.getSelectionManager());

    view.toBack();

    setEditorProperties(editor.getProperties());
  }

  @Override
  protected void layoutChildren() {
    super.layoutChildren();

//    if (getChildren().contains(minimap))
//    {
//      minimap.relocate(getWidth() - (minimap.getWidth() + MINIMAP_INDENT), MINIMAP_INDENT);
//    }
  }
}
