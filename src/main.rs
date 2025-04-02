mod eui;
mod setting;
mod state;
mod table;
mod ui;
mod view;

use crate::ui::Visual;
use serde::{Deserialize, Serialize};

fn main() {
  let options = eframe::NativeOptions {
    renderer: eframe::Renderer::Wgpu,
    persist_window: true,
    viewport: egui::ViewportBuilder::default().with_inner_size([1000.0, 8000.0]),
    centered: true,
    ..Default::default()
  };

  let mut app = Visual::default();
  app.graph.add_node(node_graph::node::Node::new("Add"));
  app.graph.add_node(node_graph::node::Node::new("Multiply"));
  eframe::run_native("Visual", options, Box::new(|_cc| Ok(Box::new(app)))).unwrap();
}
