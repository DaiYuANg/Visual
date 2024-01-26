package org.visual.model.graph.editor.core.skins;


import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import javafx.util.Callback;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.visual.model.graph.editor.api.*;
import org.visual.model.graph.editor.core.skins.defaults.*;
import org.visual.model.graph.editor.core.view.ConnectionLayouter;
import org.visual.model.graph.editor.core.view.GraphEditorView;
import org.visual.model.graph.editor.model.GConnection;
import org.visual.model.graph.editor.model.GConnector;
import org.visual.model.graph.editor.model.GJoint;
import org.visual.model.graph.editor.model.GNode;


/**
 * Default {@link SkinManager} implementation
 *
 * @since 09.02.2016
 */
public class GraphEditorSkinManager implements SkinManager {

    private final GraphEditor mGraphEditor;
    private final GraphEditorView mView;

    private Callback<GNode, GNodeSkin> mNodeSkinFactory;
    private Callback<GConnector, GConnectorSkin> mConnectorSkinFactory;
    private Callback<GConnection, GConnectionSkin> mConnectionSkinFactory;
    private Callback<GJoint, GJointSkin> mJointSkinFactory;
    private Callback<GConnector, GTailSkin> mTailSkinFactory;

    private final Map<GNode, GNodeSkin> mNodeSkins = new HashMap<>();
    private final Map<GConnector, GConnectorSkin> mConnectorSkins = new HashMap<>();
    private final Map<GConnection, GConnectionSkin> mConnectionSkins = new HashMap<>();
    private final Map<GJoint, GJointSkin> mJointSkins = new HashMap<>();
    private final Map<GConnector, GTailSkin> mTailSkins = new HashMap<>();

    private ConnectionLayouter mConnectionLayouter;
    private final Consumer<GSkin<?>> mOnPositionMoved = this::positionMoved;

    /**
     * Creates a new skin manager instance. Only one instance should exist per
     * {@link DefaultGraphEditor} instance.
     *
     * @param pGraphEditor {@link GraphEditor}
     * @param pView        {@link GraphEditorView}
     */
    public GraphEditorSkinManager(final GraphEditor pGraphEditor, final GraphEditorView pView) {
        mView = pView;
        mGraphEditor = pGraphEditor;
    }

    @Override
    public void setConnectionLayouter(final ConnectionLayouter pConnectionLayouter) {
        mConnectionLayouter = pConnectionLayouter;
    }

    @Override
    public void setNodeSkinFactory(final Callback<GNode, GNodeSkin> pSkinFactory) {
        mNodeSkinFactory = pSkinFactory;
    }

    @Override
    public void setConnectorSkinFactory(final Callback<GConnector, GConnectorSkin> pConnectorSkinFactory) {
        mConnectorSkinFactory = pConnectorSkinFactory;
    }

    @Override
    public void setConnectionSkinFactory(final Callback<GConnection, GConnectionSkin> pConnectionSkinFactory) {
        mConnectionSkinFactory = pConnectionSkinFactory;
    }

    @Override
    public void setJointSkinFactory(final Callback<GJoint, GJointSkin> pJointSkinFactory) {
        mJointSkinFactory = pJointSkinFactory;
    }

    @Override
    public void setTailSkinFactory(final Callback<GConnector, GTailSkin> pTailSkinFactory) {
        mTailSkinFactory = pTailSkinFactory;
    }

    @Override
    public void clear() {
        if (!mNodeSkins.isEmpty()) {
            val nodes = mNodeSkins.keySet().toArray(new GNode[0]);
            Arrays.stream(nodes).forEach(this::removeNode);
        }

        if (!mConnectorSkins.isEmpty()) {
            val connectors = mConnectorSkins.keySet().toArray(new GConnector[0]);
            Arrays.stream(connectors).forEach(this::removeConnector);
        }

        if (!mConnectionSkins.isEmpty()) {
            val connections = mConnectionSkins.keySet().toArray(new GConnection[0]);
            Arrays.stream(connections).forEach(this::removeConnection);
        }

        if (!mJointSkins.isEmpty()) {
            val joints = mJointSkins.keySet().toArray(new GJoint[0]);
            Arrays.stream(joints).forEach(this::removeJoint);
        }

        if (!mTailSkins.isEmpty()) {
            final GTailSkin[] tails = mTailSkins.values().toArray(new GTailSkin[0]);
            for (final GTailSkin tail : tails) {
                mView.remove(tail);
                tail.dispose();
            }
        }

        // remove any remainders that might have been left over:
        mView.clear();
    }

    @Override
    public void removeNode(final GNode pNodeToRemove) {
        if (pNodeToRemove == null) {
            return;
        }
        final GNodeSkin removedSkin = mNodeSkins.remove(pNodeToRemove);
        if (removedSkin != null) {
            mView.remove(removedSkin);
            removedSkin.dispose();
        }

        pNodeToRemove.getConnectors().forEach(this::removeConnector);
    }

    @Override
    public void removeConnector(final GConnector pConnectorToRemove) {
        if (Objects.isNull(pConnectorToRemove)) {
            return;
        }
        val removedSkin = mConnectorSkins.remove(pConnectorToRemove);
        if (removedSkin != null) {
            removedSkin.dispose();
        }
        val removedTailSkin = mTailSkins.remove(pConnectorToRemove);
        if (removedTailSkin != null) {
            removedTailSkin.dispose();
        }
    }

    @Override
    public void removeConnection(final GConnection pConnectionToRemove) {
        if (pConnectionToRemove != null) {
            final GConnectionSkin removedSkin = mConnectionSkins.remove(pConnectionToRemove);
            if (removedSkin != null) {
                mView.remove(removedSkin);
                removedSkin.dispose();
            }
        }
    }

    @Override
    public void removeJoint(final GJoint pJointToRemove) {
        if (pJointToRemove != null) {
            final GJointSkin removedSkin = mJointSkins.remove(pJointToRemove);
            if (removedSkin != null) {
                mView.remove(removedSkin);
                removedSkin.dispose();
            }
        }
    }

    @Override
    public void updateConnectors(final GNode pNode) {
        final GNodeSkin nodeSkin = mNodeSkins.get(pNode);
        if (nodeSkin != null) {
            final List<GConnectorSkin> nodeConnectorSkins = pNode.getConnectors().stream().map(this::lookupConnector)
                    .collect(Collectors.toList());
            nodeSkin.setConnectorSkins(nodeConnectorSkins);
        }
    }

    @Override
    public void updateJoints(final GConnection pConnection) {
        final GConnectionSkin connectionSkin = lookupConnection(pConnection);
        if (connectionSkin != null) {
            final List<GJointSkin> connectionJointSkins = pConnection.getJoints().stream().map(this::lookupJoint)
                    .collect(Collectors.toList());
            connectionSkin.setJointSkins(connectionJointSkins);
        }
    }

    @Override
    public GNodeSkin lookupOrCreateNode(final GNode pNode) {
        return mNodeSkins.computeIfAbsent(pNode, this::createNodeSkin);
    }

    @Override
    public GConnectorSkin lookupOrCreateConnector(final GConnector pConnector) {
        return mConnectorSkins.computeIfAbsent(pConnector, this::createConnectorSkin);
    }

    @Override
    public GConnectionSkin lookupOrCreateConnection(final GConnection pConnection) {
        return mConnectionSkins.computeIfAbsent(pConnection, this::createConnectionSkin);
    }

    @Override
    public GJointSkin lookupOrCreateJoint(final GJoint pJoint) {
        return mJointSkins.computeIfAbsent(pJoint, this::createJointSkin);
    }

    @Override
    public GNodeSkin lookupNode(final GNode pNode) {
        return mNodeSkins.get(pNode);
    }

    @Override
    public GConnectorSkin lookupConnector(final GConnector pConnector) {
        return mConnectorSkins.get(pConnector);
    }

    @Override
    public GConnectionSkin lookupConnection(final GConnection pConnection) {
        return mConnectionSkins.get(pConnection);
    }

    @Override
    public GJointSkin lookupJoint(final GJoint pJoint) {
        return mJointSkins.get(pJoint);
    }

    @Override
    public GTailSkin lookupTail(final GConnector pConnector) {
        // GTailSkin is always/only created on demand
        return mTailSkins.computeIfAbsent(pConnector, this::createTailSkin);
    }

    private @NotNull GConnectorSkin createConnectorSkin(final GConnector pConnector) {
        GConnectorSkin skin = mConnectorSkinFactory == null ? null : mConnectorSkinFactory.call(pConnector);
        if (skin == null) {
            skin = new DefaultConnectorSkin(pConnector);
        }
        skin.setGraphEditor(mGraphEditor);
        return skin;
    }

    private @NotNull GTailSkin createTailSkin(final GConnector pConnector) {
        GTailSkin skin = mTailSkinFactory == null ? null : mTailSkinFactory.call(pConnector);
        if (skin == null) {
            skin = new DefaultTailSkin(pConnector);
        }
        skin.setGraphEditor(mGraphEditor);
        return skin;
    }

    private @NotNull GConnectionSkin createConnectionSkin(final GConnection pConnection) {
        GConnectionSkin skin = mConnectionSkinFactory == null ? null : mConnectionSkinFactory.call(pConnection);
        if (skin == null) {
            skin = new DefaultConnectionSkin(pConnection);
        }
        skin.setGraphEditor(mGraphEditor);
        if (!(skin instanceof VirtualSkin)) {
            mView.add(skin);
        }
        return skin;
    }

    private @NotNull GJointSkin createJointSkin(final GJoint pJoint) {
        GJointSkin skin = mJointSkinFactory == null ? null : mJointSkinFactory.call(pJoint);
        if (skin == null) {
            skin = new DefaultJointSkin(pJoint);
        }
        skin.setGraphEditor(mGraphEditor);
        skin.getRoot().setEditorProperties(mGraphEditor.getProperties());
        skin.impl_setOnPositionMoved(mOnPositionMoved);
        skin.initialize();
        if (!(skin instanceof VirtualSkin)) {
            mView.add(skin);
        }
        return skin;
    }

    private @NotNull GNodeSkin createNodeSkin(final GNode pNode) {
        GNodeSkin skin = mNodeSkinFactory == null ? null : mNodeSkinFactory.call(pNode);
        if (skin == null) {
            skin = new DefaultNodeSkin(pNode);
        }
        skin.setGraphEditor(mGraphEditor);
        skin.getRoot().setEditorProperties(mGraphEditor.getProperties());
        skin.impl_setOnPositionMoved(mOnPositionMoved);
        skin.initialize();
        if (!(skin instanceof VirtualSkin)) {
            mView.add(skin);
        }
        return skin;
    }

    private void positionMoved(final GSkin<?> pMovedSkin) {
        final ConnectionLayouter layouter = mConnectionLayouter;
        if (layouter == null) {
            return;
        }
        if (pMovedSkin instanceof GNodeSkin gns) {
            // redraw all connections attached to each connector of the GNode:
            gns.getItem().getConnectors().stream().map(GConnector::getConnections).forEach(layouter::redraw);
            return;
        }
        if (pMovedSkin instanceof GJointSkin gjs) {
            // redraw the GConnection of the GJoint:
            layouter.redraw(gjs.getItem().getConnection());
        }
    }
}
