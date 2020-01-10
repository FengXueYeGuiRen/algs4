package edu.princeton.cs.algs4.zh.cn.graph;

/**
 * 符号图(用符号做为顶点名的图) API(4.1.7)
 *
 * @author FXYGR @date 2020-01-03
 */
public abstract class SymbolGraph {

	public SymbolGraph() {
	}

	/**
	 * 根据 fileName 指定的文件构造图，使用 delim 来分割顶点名
	 *
	 * @param fileName 图文件
	 * @param delim    分割符
	 */
	public SymbolGraph(String fileName, String delim) {
	}

	/**
	 * key 是一个顶点吗
	 *
	 * @param key
	 * @return
	 */
	public abstract boolean contains(String key);

	/**
	 * key 的索引
	 *
	 * @param key
	 * @return
	 */
	public abstract int index(String key);

	/**
	 * 索引 v 的顶点名
	 *
	 * @param v
	 * @return
	 */
	public abstract String name(int v);

	/**
	 * 隐藏的 Graph 对象
	 *
	 * @return
	 */
	public abstract Graph G();

}
