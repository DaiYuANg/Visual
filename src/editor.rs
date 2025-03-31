use crate::state::AppState;
use crate::table::{Node, Table};
use crate::ui::DesignerApp;

/// 📌 2.4 编辑器界面
impl DesignerApp {
    fn top_panel(&mut self, ctx: &egui::Context) {
        // 顶部菜单栏
        egui::TopBottomPanel::top("top_panel")
            .show(ctx, |ui| {
            ui.add_space(5.0);
            ui.horizontal(|ui| {
                if ui.button("🏠 Return Home").clicked() {
                    self.state = AppState::Welcome;
                }
                if ui.button("➕ Create Table").clicked() {
                    self.tables.push(Table {
                        base: Node {
                            id: self.tables.len() as u32,
                            name: format!("Table{}", self.tables.len() + 1),
                            x: 100.0,
                            y: 100.0,
                        },
                        columns: vec!["id INT PRIMARY KEY".to_string()],
                    });
                }
            });
            ui.add_space(5.0);
        });
    }

    pub fn show_editor(&mut self, ctx: &egui::Context) {
        self.top_panel(ctx);
        if self.show_settings {
            egui::Window::new("Settings")
                .collapsible(false)
                .resizable(true)
                .show(ctx, |ui| {
                    ui.horizontal(|ui| {
                        // 左侧菜单栏
                        ui.group(|ui| {
                            ui.vertical(|ui| {
                                ui.radio_value(&mut self.selected_tab, 0, "General");
                                ui.radio_value(&mut self.selected_tab, 1, "Appearance");
                                ui.radio_value(&mut self.selected_tab, 2, "Shortcuts");
                            });
                        });

                        // 右侧内容区
                        ui.add_space(10.0); // 左右间距
                        ui.group(|ui| {
                            ui.vertical(|ui| {
                                match self.selected_tab {
                                    0 => {
                                        ui.label("General Settings");
                                        // 其他一般设置项
                                    }
                                    1 => {
                                        ui.label("Appearance Settings");
                                        ui.checkbox(&mut self.dark_mode, "Dark Mode");
                                    }
                                    2 => {
                                        ui.label("Shortcuts Settings");
                                        // 快捷键设置
                                    }
                                    _ => {}
                                }
                            });
                        });
                    });

                    ui.horizontal(|ui| {
                        // 关闭按钮
                        if ui.button("Close").clicked() {
                            self.show_settings = false; // 关闭窗口
                        }
                        if ui.button("Apply").clicked() {
                            println!("Apply")
                        }
                    });
                });
        }
        // 左侧工具栏
        egui::SidePanel::left("left_panel")
            .show(ctx, |ui| {
            ui.heading(egui_material_icons::icons::ICON_TOOLBAR.to_owned() + "Tools");
            ui.separator();
            ui.label("Drag");
        });

        // 右侧属性面板
        egui::SidePanel::right("right_panel").show(ctx, |ui| {
            ui.heading("📌 Properties");
            ui.separator();
            if let Some(selected_table) = self.tables.first() {
                ui.label(format!("Table Name: {}", selected_table.base.name));
            }
        });

        // 底部状态栏
        egui::TopBottomPanel::bottom("bottom_panel")
            .show_separator_line(true)
            .show(ctx, |ui| {
            ui.horizontal(|ui| {
                ui.label("🖥 Status: Editing...");
            });
        });

        self.center(ctx);
    }

    fn center(&mut self, ctx: &egui::Context) {
        // 中心画布
        egui::CentralPanel::default().show(ctx, |ui| {
            let painter = ui.painter();

            for (i, table) in self.tables.iter_mut().enumerate() {
                let rect = egui::Rect::from_min_size(
                    egui::pos2(table.base.x, table.base.y),
                    egui::vec2(150.0, 100.0),
                );

                painter.rect_filled(rect, 5.0, egui::Color32::LIGHT_BLUE);
                painter.text(
                    rect.center(),
                    egui::Align2::CENTER_CENTER,
                    &table.base.name,
                    egui::FontId::default(),
                    egui::Color32::BLACK,
                );

                // 拖拽功能
                if ui
                    .interact(rect, egui::Id::new(i), egui::Sense::drag())
                    .dragged()
                {
                    if let Some(pointer) = ui.input(|i| i.pointer.interact_pos()) {
                        table.base.x = pointer.x - 75.0;
                        table.base.y = pointer.y - 50.0;
                    }
                }
            }
        });
    }
}
