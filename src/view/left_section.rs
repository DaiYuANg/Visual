use crate::core::Visual;

impl Visual {
  pub fn left_section(&mut self, ctx: &egui::Context) {
    // 左侧工具栏
    egui::SidePanel::left("left_panel").show(ctx, |ui| {
      ui.heading(egui_material_icons::icons::ICON_TOOLBAR.to_owned() + "Tools");
      ui.separator();
      ui.label("Drag");
    });
  }
}
