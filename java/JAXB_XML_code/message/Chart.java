package com.asiainfo.biframe.dam.common.message;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @description chart标签
 * @author qihy
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "nodes", "edges" })
@XmlRootElement(name = "chart")
public class Chart{
	@XmlAttribute(name = "type")
	private String type;
	@XmlAttribute(name = "entranceIds")
	private String entranceIds;
//	@XmlElements({ @XmlElement(name="Node",type = Node.class)})
	@XmlElementWrapper(name="nodes")
    @XmlElement(name = "node")
	private List<Node> nodes = new ArrayList<Node>();
//	@XmlElements({ @XmlElement(name ="Edge",type = Edge.class) })
	@XmlElementWrapper(name="edges")
    @XmlElement(name = "edge")
	private List<Edge> edges = new ArrayList<Edge>();
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getEntranceIds() {
		return entranceIds;
	}
	public void setEntranceIds(String entranceIds) {
		this.entranceIds = entranceIds;
	}
	public List<Node> getNodes() {
		return nodes;
	}
	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}
	public List<Edge> getEdges() {
		return edges;
	}
	public void setEdges(List<Edge> edges) {
		this.edges = edges;
	}
	
	
}