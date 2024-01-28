package org.visual.debugger.node;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.kordamp.ikonli.Ikon;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;

@Getter
@RequiredArgsConstructor
public enum NodeIconMapping {
    ANCHOR_PANE("AnchorPane", FontAwesomeSolid.ANCHOR),

    VBOX("VBox", FontAwesomeSolid.BOX),

    MENU_BAR("MenuBar", FontAwesomeSolid.COMMENT);

    private final String name;

    private final Ikon icon;

    private static final Map<String, Ikon> map = Arrays.stream(NodeIconMapping.values()).collect(Collectors.toUnmodifiableMap(NodeIconMapping::getName, NodeIconMapping::getIcon));

    public static @NotNull Ikon findNodeIcon(String name) {
        return Optional.ofNullable(map.get(name)).orElse(FontAwesomeSolid.BOX);
    }
}
