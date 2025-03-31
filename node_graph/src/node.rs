use egui::{Pos2, Rect, Vec2};
use rand::Rng;

#[derive(Clone, Debug)]
pub struct Node {
    pub id: usize,
    pub title: String,
    pub position: Pos2,
    pub size: Vec2,
    pub inputs: Vec<String>,
    pub outputs: Vec<String>,
}

impl Node {
    pub fn new(title: &str, inputs: Vec<&str>, outputs: Vec<&str>) -> Self {
        Self {
            id: rand::random_range(0..999999),
            title: title.to_string(),
            position: Pos2::new(100.0, 100.0),
            size: Vec2::new(140.0, 80.0),
            inputs: inputs.into_iter().map(String::from).collect(),
            outputs: outputs.into_iter().map(String::from).collect(),
        }
    }

    pub fn rect(&self) -> Rect {
        Rect::from_min_size(self.position, self.size)
    }
}
