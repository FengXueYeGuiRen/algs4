package edu.princeton.cs.algs4.zh.cn.graph.undirected;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

import java.util.Collections;
import java.util.Iterator;

/**
 * (邻接表数组)无向图(Undirected Graph) 数据类型
 * {@link edu.princeton.cs.algs4.Graph}
 *
 * @author FXYGR @date 2019-12-29
 */
public class AdjacencyListsGraph extends Graph {

	/**
	 * 顶点数目
	 */
	private final int V;
	/**
	 * 边的数目
	 */
	private int E;
	/**
	 * 邻接表
	 */
	private Bag<Integer>[] adj;

	/**
	 * 创建一个含有 V 个顶点但不含有边的图
	 *
	 * @param V
	 */
	public AdjacencyListsGraph(int V) {
		this.V = V;
		this.E = 0;
		//  创建邻接表
		adj = (Bag<Integer>[]) new Bag[V];
		//  将所有链表初始化为空
		for (int v = 0; v < V; ++v) {
			adj[v] = new Bag();
		}
	}

	/**
	 * 从标准输入流 in 读入一幅图
	 *
	 * @param in
	 */
	public AdjacencyListsGraph(In in) {
		//  读取 V 并将图初始化
		this(in.readInt());
		//  读取 E
		int E = in.readInt();
		for (int edge = 0; edge < E; ++edge) {
			//  添加一条边
			//  读取一个顶点
			int v = in.readInt();
			//  读取另一个顶点
			int w = in.readInt();
			//  添加一条连接它们的边
			this.addEdge(v, w);
		}
	}

	/**
	 * 顶点数
	 *
	 * @return
	 */
	@Override
	public int V() {
		return V;
	}

	/**
	 * 边数
	 *
	 * @return
	 */
	@Override
	public int E() {
		return E;
	}

	/**
	 * 向图中添加一条边 v-w
	 *
	 * @param v
	 * @param w
	 */
	@Override
	public void addEdge(int v, int w) {
		if (v < 0 || v > this.V || w < 0 || w > this.V) {
			return;
		}
		this.adj[v].add(v);
		this.adj[w].add(v);

		++this.E;
	}

	/**
	 * 和 v 相邻的所有顶点
	 *
	 * @param v
	 * @return
	 */
	@Override
	public Iterable<Integer> adj(int v) {
		if (v < 0 || v > V) {
			return emptyIterable();
		}
		return adj[v];
	}

	private static Iterable<Integer> emptyIterable() {
		return new Iterable<Integer>() {
			@Override
			public Iterator<Integer> iterator() {
				return Collections.emptyIterator();
			}
		};
	}

}
