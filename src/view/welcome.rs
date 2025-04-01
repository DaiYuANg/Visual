use crate::state::AppState;
use crate::ui::Visual;

impl Visual {
  pub fn show_welcome_screen(&mut self, ctx: &egui::Context) {
    egui::CentralPanel::default()
      .frame(egui::Frame {
        fill: egui::Color32::from_rgb(30, 30, 30), // 深色背景
        ..Default::default()
      })
      .show(ctx, |ui| {
        ui.with_layout(egui::Layout::top_down(egui::Align::Center), |ui| {
          ui.add_space(50.0); // 顶部间距

          // 🌟 大标题
          ui.label(
            egui::RichText::new("🚀 Welcome to PowerDesigner Rust")
              .heading()
              .color(egui::Color32::WHITE)
              .strong(),
          );

          ui.add_space(20.0); // 标题和按钮间距

          // 📌 创建新项目按钮
          if ui
            .add_sized([200.0, 50.0], egui::Button::new("➕ Create New Project"))
            .clicked()
          {
            self.state = AppState::Editor;
          }

          ui.add_space(10.0); // 按钮间距

          // 📂 打开已有项目按钮
          if ui
            .add_sized([200.0, 50.0], egui::Button::new("📂 Open Existing Project"))
            .clicked()
          {
            // TODO: 实现文件选择器
            self.state = AppState::Editor;
          }

          ui.add_space(30.0); // 底部间距

          // 🔗 版权信息
          ui.label(egui::RichText::new("© 2024 PowerDesigner Rust").color(egui::Color32::GRAY));
        });
      });
  }
}
