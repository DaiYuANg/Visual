#[derive(Clone, Debug)]
pub struct Connection {
    pub from_node: usize,
    pub from_port: String,
    pub to_node: usize,
    pub to_port: String,
}