package edu.princeton.cs.algs4.zh.cn.graph;

import edu.princeton.cs.algs4.In;

/**
 * 无向图(Undirected Graph) API
 *
 * @author FXYGR @date 2019-12-29
 */
public abstract class Graph {

	public Graph() {
	}

	/**
	 * 创建一个含有 v 个顶点但不含有边的图
	 *
	 * @param v
	 */
	public Graph(int v) {
	}

	/**
	 * 从标准输入流 in 读入一幅图
	 *
	 * @param in
	 */
	public Graph(In in) {
	}

	/**
	 * 顶点数
	 *
	 * @return
	 */
	public abstract int V();

	/**
	 * 边数
	 *
	 * @return
	 */
	public abstract int E();

	/**
	 * 向图中添加一条边 v-w
	 *
	 * @param v
	 * @param w
	 */
	public abstract void addEdge(int v, int w);

	/**
	 * 和 v 相邻的所有顶点
	 *
	 * @param v
	 * @return
	 */
	public abstract Iterable<Integer> adj(int v);

	/**
	 * 对象的字符串表示
	 *
	 * @return
	 */
	@Override
	public String toString() {
		int V = V();
		StringBuilder stringBuilder =
				new StringBuilder(V).append(" vertices, ")
						.append(V()).append(" edges\n");
		for (int vertice = 0; vertice < V; ++vertice) {
			stringBuilder.append(vertice + ": ");
			for (int w : this.adj(vertice)) {
				stringBuilder.append(w + " ");
			}
			stringBuilder.append("\n");
		}
		return stringBuilder.toString();
	}

	/**
	 * 计算 v 的度数
	 *
	 * @param v
	 * @return
	 */
	public int degree(int v) {
		return Graph.degree(this, v);
	}

	/**
	 * 计算所有顶点的最大度数
	 *
	 * @return
	 */
	public int maxDegree() {
		return Graph.maxDegree(this);
	}

	/**
	 * 计算所有顶点的平均度数
	 *
	 * @return
	 */
	public double avgDegree() {
		return Graph.avgDegree(this);
	}

	/**
	 * 计算自环的个数
	 *
	 * @return
	 */
	public int numberOfSelfLoops() {
		return Graph.numberOfSelfLoops(this);
	}

	/**
	 * 计算 v 的度数
	 *
	 * @param G
	 * @param v
	 * @return
	 */
	public static int degree(Graph G, int v) {
		int degree = 0;
		for (int w : G.adj(v)) {
			++degree;
		}
		return degree;
	}

	/**
	 * 计算所有顶点的最大度数
	 *
	 * @param G
	 * @return
	 */
	public static int maxDegree(Graph G) {
		int max = 0;
		for (int v = 0; v < G.V(); ++v) {
			int degree = degree(G, v);
			if (degree > max) {
				max = degree;
			}
		}
		return max;
	}

	/**
	 * 计算所有顶点的平均度数
	 *
	 * @param G
	 * @return
	 */
	public static double avgDegree(Graph G) {
		return 2.0 * G.E() / G.V();
	}

	/**
	 * 计算自环的个数
	 *
	 * @param G
	 * @return
	 */
	public static int numberOfSelfLoops(Graph G) {
		int count = 0;
		for (int v = 0; v < G.V(); ++v) {
			for (int w : G.adj(v)) {
				if (w == v) {
					++count;
				}
			}
		}
		//  每条边都被记过两次
		return count / 2;
	}

}
