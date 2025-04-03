use serde::{Deserialize, Serialize};

/// 基础节点结构
#[derive(Debug, Serialize, Deserialize, Default, Clone)]
pub struct Node {
  pub(crate) id: u32, // 唯一标识
  pub(crate) name: String,
  pub(crate) x: f32,
  pub(crate) y: f32,
}

/// 数据库表，继承 Node
#[derive(Debug, Serialize, Deserialize, Default, Clone)]
pub struct Table {
  pub(crate) base: Node, // 复用 Node 结构
  pub(crate) columns: Vec<String>,
}

/// 关系连接 (示例扩展)
#[derive(Debug, Serialize, Deserialize, Default)]
pub struct Relation {
  pub(crate) from: u32, // 起点 Table ID
  pub(crate) to: u32,   // 终点 Table ID
  pub(crate) relation_type: String,
}
