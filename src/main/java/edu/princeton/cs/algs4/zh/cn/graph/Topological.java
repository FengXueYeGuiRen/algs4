package edu.princeton.cs.algs4.zh.cn.graph;

/**
 * 拓扑排序 API(4.2.4.3)
 *
 * @author FXYGR @date 2020-01-08
 */
public abstract class Topological {

	public Topological() {
	}

	/**
	 * 拓扑排序的构造函数
	 *
	 * @param G
	 */
	public Topological(DirectedGraph G) {
	}

	/**
	 * G 是有向无环图吗
	 *
	 * @return
	 */
	public abstract boolean isDAG();

	/**
	 * 拓扑有序的所有顶点
	 *
	 * @return
	 */
	public abstract Iterable<Integer> order();

}
