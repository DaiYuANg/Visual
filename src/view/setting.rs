use crate::core::Visual;

impl Visual {
  pub fn setting(&mut self, ctx: &egui::Context) {
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
  }
}
