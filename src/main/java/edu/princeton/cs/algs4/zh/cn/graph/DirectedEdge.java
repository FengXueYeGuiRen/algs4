package edu.princeton.cs.algs4.zh.cn.graph;

/**
 * 有向边 API(加权有向图的数据结构 4.4.2)
 *
 * @author FXYGR @date 2020-02-10
 */
public interface DirectedEdge {

	/**
	 * 指出这条边的顶点
	 *
	 * @return
	 */
	int from();

	/**
	 * 这条边指向的顶点
	 *
	 * @return
	 */
	int to();

	/**
	 * 边的权重
	 *
	 * @return
	 */
	double weight();

	/**
	 * 对象的字符串表示
	 *
	 * @return
	 */
	@Override
	String toString();

}
