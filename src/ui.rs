use crate::state::AppState;
use crate::table::Table;
use egui_file::FileDialog;
use node_graph::node_graph::NodeGraph;
use std::ffi::OsStr;
use std::path::{Path, PathBuf};

/// 设计器应用
#[derive(Default)]
pub struct Visual {
  pub(crate) state: AppState,
  pub(crate) tables: Vec<Table>,
  pub(crate) show_settings: bool,
  pub dark_mode: bool,
  pub(crate) selected_tab: usize,
  pub graph: NodeGraph,
  opened_file: Option<PathBuf>,
  open_file_dialog: Option<FileDialog>,
}

impl Visual {
  pub fn menu_bar(&mut self, ctx: &egui::Context) {
    // 创建菜单栏
    egui::TopBottomPanel::top("menubar").show(ctx, |ui| {
      egui::menu::bar(ui, |ui| {
        ui.menu_button("File", |ui| {
          if ui.button("New Project").clicked() {
            self.state = AppState::Editor;
            ui.close_menu();
          }
          if ui.button("Open Project").clicked() {
            let filter = Box::new({
              let ext = Some(OsStr::new("txt"));
              move |path: &Path| -> bool { path.extension() == ext }
            });
            let mut dialog =
              FileDialog::open_file(self.opened_file.clone()).show_files_filter(filter);
            dialog.show(ctx);
            dialog.open();
            // self.open_file_dialog = Some(dialog);
            // if let Some(dialog) = &mut self.open_file_dialog {
            //   // if dialog.show(ctx).selected() {
            //   //   if let Some(file) = dialog.path() {
            //   //     self.opened_file = Some(file.to_path_buf());
            //   //   }
            //   // }
            // }
            // ui.close_menu();
          }
          if ui.button("Save").clicked() {
            // TODO: 实现保存功能
            ui.close_menu();
          }
          if ui.button("Settings").clicked() {
            // TODO: 实现保存功能
            self.show_settings = true; // 显示设置窗口
          }
          ui.separator();
          if ui.button("Exit").clicked() {
            std::process::exit(0);
          }
        });

        ui.menu_button("Edit", |ui| {
          if ui.button("Undo").clicked() {
            // TODO: 撤销操作
          }
          if ui.button("Redo").clicked() {
            // TODO: 恢复操作
          }
        });

        ui.menu_button("View", |ui| {
          if ui.button("Toggle Dark Mode").clicked() {
            let visuals = ctx.style().visuals.dark_mode;
            ctx.set_visuals(if visuals {
              egui::Visuals::light()
            } else {
              egui::Visuals::dark()
            });
          }
        });

        ui.menu_button("Help", |ui| {
          if ui.button("About").clicked() {
            println!("PowerDesigner Rust - v0.1");
          }
        });
      });
    });
  }
}
