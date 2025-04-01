use crate::ui::Visual;

impl Visual {
  pub fn right_section(&mut self, ctx: &egui::Context) {
    // 右侧属性面板
    egui::SidePanel::right("right_panel").show(ctx, |ui| {
      ui.heading("📌 Properties");
      ui.separator();
      if let Some(selected_table) = self.tables.first() {
        ui.label(format!("Table Name: {}", selected_table.base.name));
      }
    });
  }
}
