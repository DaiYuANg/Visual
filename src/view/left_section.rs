use crate::core::Visual;
use crate::view::tree_view::{TreeNode, tree_view};

impl Visual {
  pub fn left_section(&mut self, ctx: &egui::Context) {
    // 左侧工具栏
    egui::SidePanel::left("left_panel").show(ctx, |ui| {
      ui.heading(egui_material_icons::icons::ICON_TOOLBAR.to_owned() + "Tools");
      ui.separator();
      ui.label("Drag");
      let mut tree = TreeNode::new("Root").with_children(vec![
        TreeNode::new("Folder 1").with_children(vec![
          TreeNode::new("File 1.txt"),
          TreeNode::new("File 2.txt"),
        ]),
        TreeNode::new("Folder 2"),
      ]);
      egui::ScrollArea::vertical().show(ui, |ui| {
        tree_view(ui, &mut tree);
      });
    });
  }
}
