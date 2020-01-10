package edu.princeton.cs.algs4.zh.cn.graph;

/**
 * 有向环 API(4.2.4.2)
 *
 * @author FXYGR @date 2020-01-07
 */
public abstract class DirectedCycle {

	public DirectedCycle() {
	}

	/**
	 * 寻找有向环的构造函数
	 *
	 * @param G
	 */
	public DirectedCycle(DirectedGraph G) {
	}

	/**
	 * G 是否含有有向环
	 *
	 * @return
	 */
	public abstract boolean hasCycle();

	/**
	 * 有向环中的所有顶点(如果存在的话)
	 *
	 * @return
	 */
	public abstract Iterable<Integer> cycle();

}
