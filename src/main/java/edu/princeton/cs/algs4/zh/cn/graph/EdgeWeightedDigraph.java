package edu.princeton.cs.algs4.zh.cn.graph;

import edu.princeton.cs.algs4.zh.cn.graph.directed.WeightedDirectedEdge;

/**
 * 加权有向图 API(加权有向图的数据结构 4.4.2)
 *
 * @author FXYGR @date 2020-02-10
 */
public interface EdgeWeightedDigraph {

	/**
	 * 顶点总数
	 *
	 * @return
	 */
	int V();

	/**
	 * 边的总数
	 *
	 * @return
	 */
	int E();

	/**
	 * 将 e 添加到该有向图中
	 *
	 * @param e
	 */
	void addEdge(WeightedDirectedEdge e);

	/**
	 * 从 from 指出的边
	 *
	 * @param from
	 * @return
	 */
	Iterable<WeightedDirectedEdge> adj(int from);

	/**
	 * 该有向图中的所有边
	 *
	 * @return
	 */
	Iterable<WeightedDirectedEdge> edges();

	/**
	 * 对象的字符串表示
	 *
	 * @return
	 */
	@Override
	String toString();

}
