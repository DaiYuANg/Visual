use crate::core::Visual;

impl Visual {
  pub fn bottom_section(&mut self, ctx: &egui::Context) {
    // 底部状态栏
    egui::TopBottomPanel::bottom("bottom_panel")
      .show_separator_line(true)
      .show(ctx, |ui| {
        ui.horizontal(|ui| {
          ui.label("🖥 Status: Editing...");
        });
      });
  }
}
