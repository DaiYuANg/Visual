use crate::connection::Connection;
use crate::node::Node;
use egui::{Color32, Pos2, Sense, Stroke, Ui};

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
            let response = ui.allocate_rect(rect, Sense::drag());

            if response.dragged() {
                node.position += response.drag_delta();
            }

            // 这里不提前获取 `painter`
            ui.painter().rect_filled(rect, 5.0, Color32::DARK_GRAY);
            ui.painter().text(
                rect.center(),
                egui::Align2::CENTER_CENTER,
                &node.title,
                egui::TextStyle::Body.resolve(ui.style()),
                Color32::WHITE,
            );

            // 端口绘制
            for (i, _) in node.inputs.iter().enumerate() {
                let pos = Pos2::new(node.position.x, node.position.y + 10.0 + i as f32 * 20.0);
                ui.painter().circle_filled(pos, 5.0, Color32::BLUE);
            }

            for (i, _) in node.outputs.iter().enumerate() {
                let pos = Pos2::new(
                    node.position.x + node.size.x,
                    node.position.y + 10.0 + i as f32 * 20.0,
                );
                ui.painter().circle_filled(pos, 5.0, Color32::GREEN);
            }
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
