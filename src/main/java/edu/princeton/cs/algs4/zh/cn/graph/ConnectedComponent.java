package edu.princeton.cs.algs4.zh.cn.graph;

import edu.princeton.cs.algs4.zh.cn.graph.undirected.Graph;

/**
 * 连通分量 API
 *
 * @author FXYGR @date 2020-01-02
 */
public abstract class ConnectedComponent {

	public ConnectedComponent() {
	}

	/**
	 * 预处理构造函数
	 *
	 * @param G
	 */
	public ConnectedComponent(Graph G) {
	}

	/**
	 * v 和 w 连通吗？
	 *
	 * @param v
	 * @param w
	 * @return
	 */
	public abstract boolean connected(int v, int w);

	/**
	 * 连通分量数
	 *
	 * @return
	 */
	public abstract int count();

	/**
	 * v 所在的连通分量的标识符(0 ~ count()-1)
	 *
	 * @param v
	 * @return
	 */
	public abstract int id(int v);

}
