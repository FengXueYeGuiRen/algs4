package edu.princeton.cs.algs4.zh.cn.graph;

/**
 * 加权边 API(4.3.2)
 * {@link edu.princeton.cs.algs4.Edge}
 *
 * @author FXYGR @date 2020-01-10
 */
public abstract class Edge implements Comparable<Edge> {

	public Edge() {
	}

	/**
	 * 用于初始化的构造函数
	 *
	 * @param v
	 * @param w
	 * @param weight
	 */
	public Edge(int v, int w, double weight) {
	}

	/**
	 * 边的权重
	 *
	 * @return
	 */
	public abstract double weight();

	/**
	 * 边两端的顶点之一
	 *
	 * @return
	 */
	public abstract int either();

	/**
	 * 另一个顶点
	 *
	 * @param v
	 * @return
	 */
	public abstract int other(int v);

	/**
	 * 将这条边与 that 比较
	 *
	 * @param that
	 * @return
	 */
	@Override
	public abstract int compareTo(Edge that);

	/**
	 * 对象的字符串表示
	 *
	 * @return
	 */
	@Override
	public abstract String toString();

}
