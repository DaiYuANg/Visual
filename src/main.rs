mod core;
mod eui;
mod module;
mod state;
mod table;
mod ui;
mod view;

use crate::core::Visual;
use crate::module::MyModule;
use log::debug;
use notify::{Event, RecursiveMode, Watcher};
use redb::{Database, TableDefinition};
use serde::{Deserialize, Serialize};
use std::path::Path;
use std::sync::mpsc;
use std::thread;
use std::thread::Thread;

fn init() {
  let data_dir = dirs::data_dir().unwrap();
  env_logger::init();
  const TABLE: TableDefinition<&str, u64> = TableDefinition::new("my_data");
  let db_path = data_dir.join("my_data.db");
  debug!("{}", db_path.to_str().unwrap());
  Database::create(db_path).unwrap();
  tokio::task::spawn_blocking(move || {
    let (tx, rx) = mpsc::channel();

    // Use recommended_watcher() to automatically select the best implementation
    // for your platform. The `EventHandler` passed to this constructor can be a
    // closure, a `std::sync::mpsc::Sender`, a `crossbeam_channel::Sender`, or
    // another type the trait is implemented for.
    let mut watcher = notify::recommended_watcher(tx).unwrap();

    // Add a path to be watched. All files and directories at that path and
    // below will be monitored for changes.
    watcher
      .watch(Path::new("."), RecursiveMode::Recursive)
      .unwrap();
    // Block forever, printing out events as they come in
    for res in rx {
      match res {
        Ok(event) => println!("event: {:?}", event),
        Err(e) => println!("watch error: {:?}", e),
      }
    }
  });
}

#[tokio::main]
async fn main() {
  init();
  let module = MyModule::builder().build();
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
