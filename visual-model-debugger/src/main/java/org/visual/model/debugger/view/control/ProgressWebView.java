/*
 * Scenic View,
 * Copyright (C) 2012 Jonathan Giles, Ander Ruiz, Amy Fowler
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.visual.model.debugger.view.control;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.scene.Node;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;
import javafx.util.Duration;

public class ProgressWebView extends StackPane {

    WebView webView = new WebView();
    String loadedPage;

    public ProgressWebView() {
        final ProgressIndicator progressIndicator = new ProgressIndicator();
        progressIndicator.setMaxSize(300, 300);
        webView.getEngine().getLoadWorker().stateProperty().addListener(new ChangeListener<>() {
            Animation anim;

            @Override
            public void changed(final ObservableValue<? extends State> arg0, final State old, final State newValue) {
                if (newValue == State.READY) {
                    anim = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
                        if (webView.getEngine().getLoadWorker().getProgress() == -1) {
                            doLoad(loadedPage);
                        }
                    }));
                    anim.play();
                } else if (anim != null) {
                    anim.stop();
                    anim = null;
                }
            }
        });
        webView.getEngine().getLoadWorker().progressProperty().addListener(new ChangeListener<Number>() {
            private final double DURATION = 500;
            private final FadeTransition fadeIn = new FadeTransition(Duration.millis(DURATION));
            private final FadeTransition fadeOut = new FadeTransition(Duration.millis(DURATION));
            private final ParallelTransition fader = new ParallelTransition(fadeIn, fadeOut);

            {
                fadeOut.setFromValue(1.0);
                fadeOut.setToValue(0.0);

                fadeIn.setFromValue(0.0);
                fadeIn.setToValue(1.0);
            }

            @Override
            public void changed(final ObservableValue<? extends Number> arg0, final Number arg1, final Number progress) {
                final double progressValue = progress.doubleValue();

                if (progressValue == 0) {
                    getChildren().setAll(progressIndicator);
                    doFade(webView, progressIndicator);
                } else if (progressValue == 1.0) {
                    getChildren().setAll(webView);
                    doFade(progressIndicator, webView);
                }
                progressIndicator.setProgress(progressValue);
            }

            private void doFade(final Node n1, final Node n2) {
                fader.stop();
                fadeOut.setNode(n1);
                fadeIn.setNode(n2);
                fader.play();
            }
        });

    }

    public void doLoad(final String page) {
        String location = webView.getEngine().getLocation();
        if (location == null || !location.equals(page)) {
            loadedPage = page;
            webView.getEngine().load(page);
        }
    }
}
