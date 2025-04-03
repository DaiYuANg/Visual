use crate::state::AppState;
use crate::table::Table;
use derive_builder::Builder;
use egui_file::FileDialog;
use node_graph::node_graph::NodeGraph;
use std::path::PathBuf;

/// 设计器应用
#[derive(Default)]
pub struct Visual {
  pub(crate) state: AppState,
  pub(crate) tables: Vec<Table>,
  pub(crate) show_settings: bool,
  pub dark_mode: bool,
  pub(crate) selected_tab: usize,
  pub graph: NodeGraph,
  pub(crate) opened_file: Option<PathBuf>,
  open_file_dialog: Option<FileDialog>,
}
