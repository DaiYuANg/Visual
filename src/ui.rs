use crate::state::AppState;
use crate::table::Table;
use node_graph::node_graph::NodeGraph;

/// 设计器应用
#[derive(Default)]
pub struct DesignerApp {
  pub(crate) state: AppState,
  pub(crate) tables: Vec<Table>,
  pub(crate) show_settings: bool,
  pub dark_mode: bool,
  pub(crate) selected_tab: usize,
  pub graph: NodeGraph,
}

impl DesignerApp {
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
            // TODO: 实现打开文件
            ui.close_menu();
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
