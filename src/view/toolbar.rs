use crate::state::AppState;
use crate::table::{Node, Table};
use crate::ui::Visual;

impl Visual {
  pub fn toolbar(&mut self, ctx: &egui::Context) {
    egui::TopBottomPanel::top("top_panel").show(ctx, |ui| {
      ui.add_space(5.0);
      ui.horizontal(|ui| {
        if ui.button("🏠 Return Home").clicked() {
          self.state = AppState::Welcome;
        }
        if ui.button("➕ Create Table").clicked() {
          self.tables.push(Table {
            base: Node {
              id: self.tables.len() as u32,
              name: format!("Table{}", self.tables.len() + 1),
              x: 100.0,
              y: 100.0,
            },
            columns: vec!["id INT PRIMARY KEY".to_string()],
          });
        }
      });
      ui.add_space(5.0);
    });
  }
}
