package org.visual.graph.editor.core.view.impl;

import java.util.*;
import javafx.geometry.Point2D;
import lombok.extern.slf4j.Slf4j;
import org.visual.graph.editor.api.GConnectionSkin;
import org.visual.graph.editor.api.SkinLookup;
import org.visual.graph.editor.api.VirtualSkin;
import org.visual.graph.editor.core.view.ConnectionLayouter;
import org.visual.graph.editor.model.GConnection;
import org.visual.graph.editor.model.GModel;

/** Default implementation of {@link ConnectionLayouter} */
@Slf4j
public class DefaultConnectionLayouter implements ConnectionLayouter {

  private final Set<GConnection> mDirty = new HashSet<>();
  private boolean mRedrawAll = false;
  private final Map<GConnectionSkin, Point2D[]> mConnectionPoints = new HashMap<>();
  private final SkinLookup mSkinLookup;
  private GModel mModel;

  /**
   * Creates a new {@link DefaultConnectionLayouter} instance. Only one instance should exist per
   * {@link DefaultGraphEditor} instance.
   *
   * @param pSkinLookup the {@link SkinLookup} used to look up skins
   */
  public DefaultConnectionLayouter(final SkinLookup pSkinLookup) {
    mSkinLookup = pSkinLookup;
  }

  @Override
  public void initialize(final GModel pModel) {
    mModel = pModel;
  }

  @Override
  public void redraw(final Collection<GConnection> pConnections) {
    mDirty.addAll(pConnections);
  }

  @Override
  public void redraw(final GConnection pConnection) {
    mDirty.add(pConnection);
  }

  @Override
  public void redrawAll() {
    mRedrawAll = true;
  }

  @Override
  public void draw() {
    if (mModel == null || mModel.getConnections().isEmpty()) {
      return;
    }

    try {
      if (mRedrawAll) {
        mConnectionPoints.clear();
        if (!mModel.getConnections().isEmpty()) {
          redrawAllConnections();
        }
        mRedrawAll = false;
      } else if (!mDirty.isEmpty()) {
        final List<GConnectionSkin> repaint = new ArrayList<>(mDirty.size());
        mDirty.stream()
            .map(mSkinLookup::lookupConnection)
            .filter(
                connectionSkin ->
                    connectionSkin != null && !(connectionSkin instanceof VirtualSkin))
            .forEach(
                connectionSkin -> {
                  final Point2D[] points = connectionSkin.update();
                  if (points != null) {
                    mConnectionPoints.put(connectionSkin, points);
                  }
                  repaint.add(connectionSkin);
                });

        repaint.forEach(skin -> skin.draw(mConnectionPoints));
        mDirty.clear();
      }

    } catch (Exception e) {
      log.debug("Could not redraw Connections: ", e);
    }
  }

  private void redrawAllConnections() {
    mModel.getConnections().stream()
        .map(mSkinLookup::lookupConnection)
        .filter(Objects::nonNull)
        .forEach(
            connectionSkin -> {
              final Point2D[] points = connectionSkin.update();
              if (points != null) {
                mConnectionPoints.put(connectionSkin, points);
              }
            });

    mConnectionPoints.keySet().forEach(skin -> skin.draw(mConnectionPoints));
  }
}
