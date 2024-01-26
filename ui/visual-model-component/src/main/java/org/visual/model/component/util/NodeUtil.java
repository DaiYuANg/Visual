package org.visual.model.component.util;

import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.stage.Window;
import lombok.experimental.UtilityClass;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@UtilityClass
public class NodeUtil {

    public static ObservableList<Node> getChildren(final Node node) {
        if (node == null) return FXCollections.emptyObservableList();

        if (node instanceof Parent n) {
            return n.getChildrenUnmodifiable();
        }

        if (node instanceof SubScene n) {
            return n.getRoot().getChildrenUnmodifiable();
        }

        return FXCollections.emptyObservableList();
    }

    public static Optional<ObservableList<Node>> getChildrenOptional(final Node node) {
        return Optional.ofNullable(node)
                .map(n -> (n instanceof Parent) ? (Parent) n : (n instanceof SubScene) ? ((SubScene) n).getRoot() : null)
                .map(Parent::getChildrenUnmodifiable)
                .filter(children -> !children.isEmpty());
    }

    /**
     * Retrieves the parent of the given node
     *
     * @param n the node for which the parent is to be found
     * @return the found parent or null
     */
    public static Parent parentOf(final Node n) {
        if (n == null) {
            return null;
        }
        val p = n.getParent();
        if (p != null) {
            return parentOf(p);
        }
        if (n instanceof Parent) {
            return (Parent) n;
        }

        return null;
    }

    public static <T extends Node> @Nullable T getContainer(final @NotNull Node node, final Class<T> containerType) {
        val parent = node.getParent();
        if (parent == null) {
            return null;
        }
        if (containerType.isInstance(parent)) {
            return containerType.cast(parent);
        }
        return getContainer(parent, containerType);
    }

    public static Optional<Parent> parentOfOptional(final Node n) {
        return Optional.ofNullable(n)
                .map(Node::getParent)
                .flatMap(NodeUtil::parentOfRecursive)
                .map(Parent.class::cast);
    }

    /**
     * Retrieves the root window containing the given node
     *
     * @param n the node to look window for
     * @return the window the node belongs to, or null if it cannot be found
     */
    public static Window windowOf(final Node n) {
        if (n == null) {
            return null;
        }

        val p = n.getParent();
        if (p != null) {
            return windowOf(p);
        }

        if (n instanceof Parent container) {
            val windows = Window.getWindows();
            return windows.stream().filter(w -> w.getScene() != null)
                    .filter(w -> container == w.getScene().getRoot()).findFirst().orElse(null);
        }

        return null;
    }

    private static Optional<Parent> parentOfRecursive(final @NotNull Node node) {
        return Optional.ofNullable(node.getParent())
                .flatMap(NodeUtil::parentOfRecursive);
    }

    /**
     * Retrieves the root window containing the given scene
     *
     * @param s the scene to look window for
     * @return the window the scene belongs to, or null if it cannot be found
     */
    public static Window windowOf(final Scene s) {
        return Optional.ofNullable(s)
                .map(scene -> {
                    val windows = Window.getWindows();
                    return windows.stream()
                            .filter(w -> s == w.getScene())
                            .findFirst()
                            .orElse(null);
                })
                .orElse(null);
    }
}
