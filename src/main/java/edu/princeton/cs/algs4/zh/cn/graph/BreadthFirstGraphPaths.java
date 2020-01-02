package edu.princeton.cs.algs4.zh.cn.graph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.zh.cn.graph.undirected.AdjacencyListsGraph;
import edu.princeton.cs.algs4.zh.cn.graph.undirected.Graph;

import java.util.Collections;
import java.util.Iterator;
import java.util.Stack;

/**
 * 使用广度优先搜索查找图中的路径(广度优先搜索 4.1.5 算法4.2)
 * {@link edu.princeton.cs.algs4.BreadthFirstPaths}
 *
 * @author FXYGR @date 2020-01-02
 */
public class BreadthFirstGraphPaths extends GraphPaths {

	private static final int INFINITY = Integer.MAX_VALUE;
	/**
	 * 起点
	 */
	private int s;
	/**
	 * 到达该顶点的最短路径已知吗？
	 */
	private boolean[] markeds;
	/**
	 * 到达该顶点的已知路径伤的最后一个顶点
	 */
	private int[] edgeTos;

	/**
	 * 在 G 中找出所有起点为 s 的路径
	 *
	 * @param G
	 * @param s
	 */
	public BreadthFirstGraphPaths(Graph G, int s) {
		if (s < 0 || s >= G.V()) {
			throw new IllegalArgumentException(
					"vertex " + s + " is not between 0 and " + (G.V() - 1));
		}
		this.s = s;
		markeds = new boolean[G.V()];
		edgeTos = new int[G.V()];
		for (int v = 0; v < edgeTos.length; ++v) {
			edgeTos[v] = INFINITY;
		}
		bfs(G, s);
	}

	/**
	 * 广度优先搜索
	 *
	 * @param G
	 * @param s
	 */
	private void bfs(Graph G, int s) {
		Queue<Integer> queue = new Queue();
		//  标记起点
		markeds[s] = true;
		//  将它(起点)加入队列
		queue.enqueue(s);

		while (!queue.isEmpty()) {
			//  从队列中删去下一个顶点
			int v = queue.dequeue();
			for (int w : G.adj(v)) {
				//  对于每个未被标记的相邻顶点
				if (!markeds[w]) {
					//  保存最短路径的最后一条边
					edgeTos[w] = v;
					//  标记它，因为最短路径已知
					markeds[w] = true;
					//  并加它添加到队列中
					queue.enqueue(w);
				}
			}
		}
	}

	/**
	 * 是否存在从 s 到 v 的路径
	 *
	 * @param v
	 * @return
	 */
	@Override
	public boolean hasPathTo(int v) {
		if (v < 0 || v >= markeds.length) {
			return false;
		}
		return markeds[v];
	}

	/**
	 * s 到 v 的路径
	 *
	 * @param v
	 * @return
	 */
	@Override
	public Iterable<Integer> pathTo(int v) {
		if (!hasPathTo(v)) {
			return emptyIterable();
		}
		Stack<Integer> stack = new Stack();
		for (int w = v; w != s; w = edgeTos[w]) {
			stack.push(w);
		}
		stack.push(s);
		return stack;
	}

	private static Iterable<Integer> emptyIterable() {
		return new Iterable<Integer>() {
			@Override
			public Iterator<Integer> iterator() {
				return Collections.emptyIterator();
			}
		};
	}

	public static void main(String[] args) {
		Graph graph = new AdjacencyListsGraph(new In(args[0]));
		int s = Integer.parseInt(args[1]);

		GraphPaths paths = new BreadthFirstGraphPaths(graph, s);
		for (int v = 0; v < graph.V(); ++v) {
			System.out.print(s + " to " + v + ": ");
			if (!paths.hasPathTo(v)) {
				continue;
			}
			for (int w : paths.pathTo(s)) {
				if (w != s) {
					StdOut.print("-");
				}
				StdOut.print(w);
			}
		}
	}

}
