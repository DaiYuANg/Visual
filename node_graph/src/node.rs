use egui::{Pos2, Rect, Vec2};
use rand::Rng;

#[derive(Clone, Debug,Eq,PartialEq)]
pub struct Node {
    pub id: usize,
    pub title: String,
    pub position: Pos2,
    pub size: Vec2,
}

impl Node {
    pub fn new(title: &str) -> Self {
        Self {
            id: rand::random_range(0..999999),
            title: title.to_string(),
            position: Pos2::new(100.0, 100.0),
            size: Vec2::new(140.0, 80.0),
        }
    }

    pub fn rect(&self) -> Rect {
        Rect::from_min_size(self.position, self.size)
    }
}
