mod editor;
mod eui;
mod state;
mod table;
mod ui;
mod welcome;

use crate::ui::DesignerApp;
use serde::{Deserialize, Serialize};

fn main() {
    let options = eframe::NativeOptions {
        renderer: eframe::Renderer::Wgpu,
        persist_window: true,
        viewport: egui::ViewportBuilder::default().with_inner_size([1000.0, 8000.0]),
        centered: true,
        ..Default::default()
    };

    eframe::run_native(
        "Visual",
        options,
        Box::new(|_cc| Ok(Box::new(DesignerApp::new()))),
    )
    .unwrap();
}
