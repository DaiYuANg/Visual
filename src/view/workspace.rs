use crate::state::AppState;
use crate::table::{Node, Table};
use crate::ui::Visual;

impl Visual {
  pub fn workspace(&mut self, ctx: &egui::Context) {
    self.toolbar(ctx);
    self.left_section(ctx);
    self.editor(ctx);
    self.right_section(ctx);
    self.bottom_section(ctx);
  }
}
