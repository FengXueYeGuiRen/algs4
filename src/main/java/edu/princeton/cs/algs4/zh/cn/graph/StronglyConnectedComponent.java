package edu.princeton.cs.algs4.zh.cn.graph;

/**
 * 强连通分量(SCC) API(4.2.5.1)
 * {@link ConnectedComponent}
 *
 * @author FXYGR @date 2020-01-09
 */
public abstract class StronglyConnectedComponent {

	public StronglyConnectedComponent() {
	}

	/**
	 * 预处理构造函数
	 *
	 * @param G
	 */
	public StronglyConnectedComponent(Graph G) {
	}

	/**
	 * v 和 w 是强连通的吗？
	 *
	 * @param v
	 * @param w
	 * @return
	 */
	public abstract boolean stronglyConnected(int v, int w);

	/**
	 * 图中的强连通分量的总数
	 *
	 * @return
	 */
	public abstract int count();

	/**
	 * v 所在的强连通分量的标识符(0 至 count()-1 之间)
	 *
	 * @param v
	 * @return
	 */
	public abstract int id(int v);

}
