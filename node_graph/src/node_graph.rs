use crate::connection::Connection;
use crate::node::Node;
use egui::{Color32, CursorIcon, Pos2, Rect, Sense, Stroke, Ui, Vec2};

#[derive(Default)]
pub struct NodeGraph {
  nodes: Vec<Node>,
  connections: Vec<Connection>,
  selected_node: Option<usize>,
  dragging_connection: Option<(usize, String)>,
}

impl NodeGraph {
  pub fn new() -> Self {
    Self {
      nodes: Vec::new(),
      connections: Vec::new(),
      selected_node: None,
      dragging_connection: None,
    }
  }

  pub fn add_node(&mut self, node: Node) {
    self.nodes.push(node);
  }
  // egui::TextStyle::Body.resolve(ui.style()),

  fn draw_nodes(&mut self, ui: &mut Ui) {
    for node in &mut self.nodes {
      let rect = node.rect();
      let handle_size = Vec2::splat(10.0);
      let handle_rect = Rect::from_min_size(rect.right_bottom() - handle_size, handle_size);

      // 调整手柄交互（优先处理）
      let handle_response = ui.allocate_rect(handle_rect, Sense::drag());
      let is_resizing = handle_response.dragged();

      // 设置调整光标
      if handle_response.hovered() || is_resizing {
        ui.ctx().set_cursor_icon(CursorIcon::ResizeSouthEast);
      }

      // 处理调整大小
      if is_resizing {
        let delta = handle_response.drag_delta();
        node.size += delta;
        node.size = node.size.max(Vec2::splat(20.0)); // 最小20x20
      }

      // 节点主体交互
      let node_response = ui.allocate_rect(rect, Sense::drag());
      let is_dragging = node_response.dragged() && !is_resizing;

      // 设置拖动光标（仅在未调整大小时）
      if is_dragging {
        ui.ctx().set_cursor_icon(CursorIcon::Grab);
      } else if node_response.hovered() && !is_resizing {
        ui.ctx().set_cursor_icon(CursorIcon::PointingHand);
      }

      // 处理节点移动
      if is_dragging {
        node.position += node_response.drag_delta();
      }

      ui.painter().text(
        rect.center(),
        egui::Align2::CENTER_CENTER,
        &node.title,
        egui::TextStyle::Body.resolve(ui.style()),
        Color32::BLACK,
      );

      // 绘制手柄（带交互状态反馈）
      let handle_color = if is_resizing {
        Color32::LIGHT_BLUE
      } else if handle_response.hovered() {
        Color32::BLUE
      } else {
        Color32::BLUE.linear_multiply(0.3)
      };
      ui.painter().rect_filled(handle_rect, 2.0, handle_color);
    }
  }

  fn draw_connections(&mut self, ui: &mut Ui) {
    let painter = ui.painter();

    for conn in &self.connections {
      let from = self
        .nodes
        .iter()
        .find(|n| n.id == conn.from_node)
        .map(|n| n.position);
      let to = self
        .nodes
        .iter()
        .find(|n| n.id == conn.to_node)
        .map(|n| n.position);

      if let (Some(start), Some(end)) = (from, to) {
        painter.line_segment([start, end], Stroke::new(2.0, Color32::WHITE));
      }
    }
  }

  pub fn show(&mut self, ui: &mut Ui) {
    self.draw_nodes(ui);
    self.draw_connections(ui);
  }
}
