use serde::{Deserialize, Serialize};

/// 应用状态：决定当前显示启动界面还是编辑界面
enum AppState {
    Welcome, // 启动界面
    Editor,  // 进入编辑器
}

/// 数据库表结构
#[derive(Debug, Serialize, Deserialize, Default)]
struct Table {
    name: String,
    x: f32,
    y: f32,
    columns: Vec<String>,
}

/// 设计器应用
struct DesignerApp {
    state: AppState, // 当前状态
    tables: Vec<Table>,
}

impl DesignerApp {
    fn new() -> Self {
        Self {
            state: AppState::Welcome,
            tables: vec![],
        }
    }
}

impl eframe::App for DesignerApp {
    fn update(&mut self, ctx: &egui::Context, _frame: &mut eframe::Frame) {
        match self.state {
            AppState::Welcome => self.show_welcome_screen(ctx),
            AppState::Editor => self.show_editor(ctx),
        }
    }
}

impl DesignerApp {
    fn show_welcome_screen(&mut self, ctx: &egui::Context) {
        egui::CentralPanel::default().show(ctx, |ui| {
            ui.heading("Welcome PowerDesigner Rust");

            if ui.button("Create new project").clicked() {
                self.state = AppState::Editor;
            }

            if ui.button("Open exists project").clicked() {
                // TODO: 实现文件选择器
                self.state = AppState::Editor;
            }
        });
    }
}

/// 📌 2.4 编辑器界面
impl DesignerApp {
    fn show_editor(&mut self, ctx: &egui::Context) {
        egui::CentralPanel::default().show(ctx, |ui| {
            ui.horizontal(|ui| {
                if ui.button("return index").clicked() {
                    self.state = AppState::Welcome;
                }
                if ui.button("create table").clicked() {
                    self.tables.push(Table {
                        name: format!("Table{}", self.tables.len() + 1),
                        x: 100.0,
                        y: 100.0,
                        columns: vec!["id INT PRIMARY KEY".to_string()],
                    });
                }
            });

            let painter = ui.painter();
            for (i, table) in self.tables.iter_mut().enumerate() {
                let rect = egui::Rect::from_min_size(
                    egui::pos2(table.x, table.y),
                    egui::vec2(150.0, 100.0),
                );

                painter.rect_filled(rect, 5.0, egui::Color32::LIGHT_BLUE);
                painter.text(
                    rect.center(),
                    egui::Align2::CENTER_CENTER,
                    &table.name,
                    egui::FontId::default(),
                    egui::Color32::BLACK,
                );

                if ui
                    .interact(rect, egui::Id::new(i), egui::Sense::drag())
                    .dragged()
                {
                    if let Some(pointer) = ui.input(|i| i.pointer.interact_pos()) {
                        table.x = pointer.x - 75.0;
                        table.y = pointer.y - 50.0;
                    }
                }
            }
        });
    }
}

fn main() {
    let options = eframe::NativeOptions::default();
    eframe::run_native(
        "PowerDesigner Rust",
        options,
        Box::new(|_cc| Ok(Box::new(DesignerApp::new()))),
    )
    .unwrap();
}
