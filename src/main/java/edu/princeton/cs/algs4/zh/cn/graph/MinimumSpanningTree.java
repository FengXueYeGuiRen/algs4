package edu.princeton.cs.algs4.zh.cn.graph;

/**
 * 最小生成树 API(4.3.3)
 * {@link edu.princeton.cs.algs4.LazyPrimMST}
 *
 * @author FXYGR @date 2020-01-13
 */
public interface MinimumSpanningTree {

	/**
	 * 最小生成树的所有边
	 *
	 * @return
	 */
	Iterable<Edge> edges();

	/**
	 * 最小生成树的权重
	 *
	 * @return
	 */
	double weight();

}
