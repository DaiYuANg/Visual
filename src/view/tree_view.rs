#[derive(Clone)]
pub struct TreeNode {
  pub label: String,
  pub children: Vec<TreeNode>,
  pub selected: bool,
  pub open: bool,
}

impl TreeNode {
  pub fn new(label: impl Into<String>) -> Self {
    Self {
      label: label.into(),
      children: Vec::new(),
      selected: false,
      open: false,
    }
  }

  pub fn with_children(mut self, children: Vec<TreeNode>) -> Self {
    self.children = children;
    self
  }
}

pub fn tree_view(ui: &mut egui::Ui, tree: &mut TreeNode) -> egui::Response {
  let id = ui.make_persistent_id(&tree.label);
  let indent = 16.0;

  if tree.children.is_empty() {
    // 叶子节点 - 只有选择功能
    ui.horizontal(|ui| {
      ui.add_space(indent);
      ui.label("   "); // 占位图标空间
      selectable_label(ui, tree)
    }).response
  } else {
    // 可折叠节点
    let mut state = egui::collapsing_header::CollapsingState::load_with_default_open(
      ui.ctx(),
      id,
      tree.open,
    );

    // 创建自定义header
    let header_response = ui.horizontal(|ui| {
      // 可点击的折叠图标
      let icon = if state.is_open() { "▼" } else { "▶" };
      let icon_response = ui.add(
        egui::Label::new(icon)
            .sense(egui::Sense::click())
      );

      // 可选择的标签
      let label_response = selectable_label(ui, tree);

      // 合并响应区域
      let response = label_response.union(icon_response.clone());

      // 处理图标点击
      if icon_response.clicked() {
        state.toggle(ui);
        tree.open = state.is_open();
      }

      response
    });

    // 处理整个行的点击
    if header_response.response.clicked() {
      tree.selected = !tree.selected;
    }

    // 显示子节点
    if state.is_open() {
      state.show_body_indented(&header_response.response, ui, |ui| {
        for child in &mut tree.children {
          tree_view(ui, child);
        }
      });
    }

    header_response.response
  }
}

fn selectable_label(ui: &mut egui::Ui, tree: &mut TreeNode) -> egui::Response {
  let text_color = if tree.selected {
    ui.style().visuals.selection.stroke.color
  } else {
    ui.style().visuals.text_color()
  };

  ui.add(
    egui::Label::new(
      egui::RichText::new(&tree.label)
          .color(text_color)
    )
        .sense(egui::Sense::click())
  )
}