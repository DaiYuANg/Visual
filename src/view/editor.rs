use crate::ui::Visual;

impl Visual {
  pub fn editor(&mut self, ctx: &egui::Context) {
    // 中心画布
    egui::CentralPanel::default().show(ctx, |ui| {
      self.graph.add_node(node_graph::node::Node::new(
        "Add",
        vec!["A", "B"],
        vec!["Sum"],
      ));
      self.graph.add_node(node_graph::node::Node::new(
        "Multiply",
        vec!["X", "Y"],
        vec!["Product"],
      ));
      self.graph.show(ui);
    });
  }
}
