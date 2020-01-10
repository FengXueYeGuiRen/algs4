package edu.princeton.cs.algs4.zh.cn.graph.undirected;

import edu.princeton.cs.algs4.zh.cn.graph.Edge;

/**
 * 带权重的边的数据类型(加权无向图的数据类型 4.3.2)
 * {@link edu.princeton.cs.algs4.Edge}
 *
 * @author FXYGR @date 2020-01-10
 */
public class WeightedEdge extends Edge {

	/**
	 * 顶点之一
	 */
	private int v;
	/**
	 * 另一个顶点
	 */
	private int w;
	/**
	 * 边的权重
	 */
	private double weight;

	/**
	 * 用于初始化的构造函数
	 *
	 * @param v
	 * @param w
	 * @param weight
	 */
	public WeightedEdge(int v, int w, double weight) {
		if (v < 0 || w < 0) {
			throw new IllegalArgumentException(
					"vertex index must be a nonnegative integer");
		}
		if (Double.isNaN(weight)) {
			throw new IllegalArgumentException(
					"Weight is NaN");
		}
		this.v = v;
		this.w = w;
		this.weight = weight;
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
	 * 边两端的顶点之一
	 *
	 * @return
	 */
	@Override
	public int either() {
		return v;
	}

	/**
	 * 另一个顶点
	 *
	 * @param v
	 * @return
	 */
	@Override
	public int other(int v) {
		if (v == this.v) {
			return w;
		}
		if (v == this.w) {
			return v;
		}
		throw new IllegalArgumentException("Inconsistent edge");
	}

	/**
	 * 将这条边与 that 比较
	 *
	 * @param that
	 * @return
	 */
	@Override
	public int compareTo(Edge that) {
		if (that == null) {
			throw new NullPointerException();
		}
		return Double.compare(weight, that.weight());
	}

	/**
	 * 对象的字符串表示
	 *
	 * @return
	 */
	@Override
	public String toString() {
		return v + "-" + w + " " + weight;
	}

}
