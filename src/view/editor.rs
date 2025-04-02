use crate::ui::Visual;

impl Visual {
  pub fn editor(&mut self, ctx: &egui::Context) {
    // 中心画布
    egui::CentralPanel::default().show(ctx, |ui| {

      self.graph.show(ui);
    });
  }
}
