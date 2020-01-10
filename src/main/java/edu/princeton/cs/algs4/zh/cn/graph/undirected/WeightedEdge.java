package edu.princeton.cs.algs4.zh.cn.graph.undirected;

import edu.princeton.cs.algs4.zh.cn.graph.Edge;

/**
 * 加权边 API(4.3.2)
 * {@link edu.princeton.cs.algs4.Edge}
 *
 * @author FXYGR @date 2020-01-10
 */
public class WeightedEdge extends Edge {

	/**
	 * 边的权重
	 *
	 * @return
	 */
	@Override
	public double weight() {
		return 0;
	}

	/**
	 * 边两端的顶点之一
	 *
	 * @return
	 */
	@Override
	public int either() {
		return 0;
	}

	/**
	 * 另一个顶点
	 *
	 * @return
	 */
	@Override
	public int other() {
		return 0;
	}

	/**
	 * 将这条边与 that 比较
	 *
	 * @param that
	 * @return
	 */
	@Override
	public int compareTo(Edge that) {
		return 0;
	}

	/**
	 * 对象的字符串表示
	 *
	 * @return
	 */
	@Override
	public String toString() {
		return null;
	}

}
