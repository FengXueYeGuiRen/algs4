package edu.princeton.cs.algs4.zh.cn.graph.directed;

import edu.princeton.cs.algs4.zh.cn.graph.DirectedEdge;

/**
 * 加权有向边的数据类型(加权有向图的数据结构 4.4.2)
 * {@link edu.princeton.cs.algs4.DirectedEdge}
 *
 * @author FXYGR @date 2020-02-10
 */
public class WeightedDirectedEdge implements DirectedEdge {

	/**
	 * 边的起点
	 */
	private final int from;
	/**
	 * 边的终点
	 */
	private final int to;
	/**
	 * 边的权重
	 */
	private final double weight;

	public WeightedDirectedEdge(int from, int to, double weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}

	/**
	 * 指出这条边的顶点
	 *
	 * @return
	 */
	@Override
	public int from() {
		return from;
	}

	/**
	 * 这条边指向的顶点
	 *
	 * @return
	 */
	@Override
	public int to() {
		return to;
	}

	/**
	 * 边的权重
	 *
	 * @return
	 */
	@Override
	public double weight() {
		return weight;
	}

	/**
	 * 对象的字符串表示
	 *
	 * @return
	 */
	@Override
	public String toString() {
		return String.format("%d->%d %.2f", from, to, weight);
	}

}
