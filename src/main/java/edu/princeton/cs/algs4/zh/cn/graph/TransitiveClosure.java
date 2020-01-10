package edu.princeton.cs.algs4.zh.cn.graph;

/**
 * 顶点对可达性 API(4.2.5.4)
 * {@link edu.princeton.cs.algs4.TransitiveClosure}
 *
 * @author FXYGR @date 2020-01-09
 */
public abstract class TransitiveClosure {

	public TransitiveClosure() {
	}

	/**
	 * 预处理的构造函数
	 *
	 * @param G
	 */
	public TransitiveClosure(DirectedGraph G) {
	}

	/**
	 * w 是从 v 可达的吗
	 *
	 * @param v
	 * @param w
	 * @return
	 */
	public abstract boolean reachable(int v, int w);

}
