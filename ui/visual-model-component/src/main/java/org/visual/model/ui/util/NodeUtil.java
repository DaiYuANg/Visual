package org.visual.model.ui.util;

import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

@UtilityClass
public class NodeUtil {

    public static ObservableList<Node> getChildren(Node node) {
        if (node == null) return FXCollections.emptyObservableList();

        if (node instanceof Parent n) {
            return n.getChildrenUnmodifiable();
        }

        if (node instanceof SubScene n) {
            return n.getRoot().getChildrenUnmodifiable();
        }

        return FXCollections.emptyObservableList();
    }

    public static Optional<ObservableList<Node>> getChildrenOptional(Node node) {
        return Optional.ofNullable(node)
                .map(n -> (n instanceof Parent) ? (Parent) n : (n instanceof SubScene) ? ((SubScene) n).getRoot() : null)
                .map(Parent::getChildrenUnmodifiable)
                .filter(children -> !children.isEmpty());
    }

    public static Optional<Parent> parentOf(Node n) {
        return Optional.ofNullable(n)
                .map(Node::getParent)
                .flatMap(NodeUtil::parentOfRecursive)
                .map(Parent.class::cast);
    }

    private static Optional<Parent> parentOfRecursive(@NotNull Node node) {
        return Optional.ofNullable(node.getParent())
                .flatMap(NodeUtil::parentOfRecursive);
    }
}
