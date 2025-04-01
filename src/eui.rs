use crate::state::AppState;
use crate::ui::DesignerApp;

impl eframe::App for DesignerApp {
  fn update(&mut self, ctx: &egui::Context, _frame: &mut eframe::Frame) {
    self.menu_bar(ctx);

    match self.state {
      AppState::Welcome => self.show_welcome_screen(ctx),
      AppState::Editor => self.show_editor(ctx),
    }
    // 启用 egui Debug 面板
    egui::Window::new("Egui Debugger").show(ctx, |ui| {
      ctx.inspection_ui(ui);
    });
  }
}
