package cse41321.containers;

import java.util.Iterator;

import cse41321.exceptions.DuplicateElementException;

import static com.google.common.base.Preconditions.*;
public class Graph<V,E> {
	public class Vertex{
		private V data;
		private HashSet<Edge> edgesIncidentFrom = new HashSet<Edge>();
		private HashSet<Edge> edgesIncidentTo = new HashSet<Edge>();
		
		private Vertex(V data) {
			this.data = data;
		}
		
		public V getData() {
			return data;
		}
		
		public Iterable<Edge> getEdgesIncidentTo(){
			return new Iterable<Edge>() {
				public Iterator<Edge> iterator() {
					return edgesIncidentTo.iterator();
				}
			};
		}		public Iterable<Edge> getEdgesIncidentFrom(){
			return new Iterable<Edge>() {
				public Iterator<Edge> iterator() {
					return edgesIncidentFrom.iterator();
				}
			};
		}
		
		@Override
		@SuppressWarnings("unchecked")
		public boolean equals(Object o) {
			if(this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			
			Vertex vertex = (Vertex) o;
			return data.equals(vertex.data);
		}
		
		@Override
		public int hashCode() {
			return data.hashCode();
		}
	}
	
	public class Edge {
		private Vertex from;
		private Vertex to;
		private E data;
		
		private Edge(Vertex from, Vertex to) {
			this(from, to, null);
		}
		
		private Edge(Vertex from, Vertex to, E data) {
			this.from = from;
			this.to = to;
			this.data = data;
		}
		
		public Vertex getFrom() {
			return from;
		}
		
		public Vertex getTo() {
			return to;
		}
		
		public E getData() {
			return data;
		}
		
		@Override
		@SuppressWarnings("unchecked")
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			
			Edge edge = (Edge) o;
			
			return from.equals(edge.from) && to.equals(edge.to);
		}
		
		@Override
		public int hashCode() {
			int result = from.hashCode();
			result = 31 * result + to.hashCode();
			return result;
		}
	}
	
	private HashSet<Vertex> vertices = new HashSet<Vertex>();
	private HashSet<Edge> edges = new HashSet<Edge>();
	
	public boolean containsVertex(V data) {
		return  vertices.isMember(new Vertex(data));
	}
	
	public int getNumVertices() {
		return vertices.getSize();
	}
	
	public Iterable<Vertex> getVertices() {
		return new Iterable<Vertex>() {
			public Iterator<Vertex> iterator() {
				return vertices.iterator();
			}
		};
	}
	
	public Vertex getVertex(V data) throws
			NullPointerException,
			IllegalStateException {
		checkVertexPreconditions(data);
		checkState(containsVertex(data), "Vertex does not exist");
		return vertices.getMember(new Vertex(data));
	}
	
	public void insertVertex(V data) throws
			NullPointerException,
			IllegalStateException, IllegalArgumentException, DuplicateElementException {
		checkVertexPreconditions(data);
		checkState(!containsVertex(data), "Vertex does not exist");
		vertices.insert(new Vertex(data));

	}
	
	public V removeVertex(V data) throws
			NullPointerException,
			IllegalStateException {
		checkVertexPreconditions(data);
		checkState(containsVertex(data), "Vertex does not exist");
		Vertex vertex = vertices.getMember(new Vertex(data));
		checkState(vertex.edgesIncidentFrom.isEmpty(), "Vertex has Edges incident from it");
		checkState(vertex.edgesIncidentTo.isEmpty(), "Vertex has edges  incident to it");
		return data;
	}
	
	private void checkVertexPreconditions(V data) {
		checkNotNull(data, "data must not be null");
	}
	
	public boolean containsEdge(V from, V to) {
		return from != null
				&& to != null
				&& containsVertex(from)
				&& containsVertex(to)
				&& edges.isMember(new Edge(getVertex(from), getVertex(to)));
	}
	
	public int getNumEdges() {
		return edges.getSize();
	}
	
	public Iterable<Edge> getEdges() {
		return new Iterable<Edge>() {
			public Iterator<Edge> iterator() {
				return edges.iterator();
			}
		};
	}
	
	public Edge getEdge(V from, V to) throws
			NullPointerException,
			IllegalStateException{
		checkEdgePreconditions(from, to);
		checkState(containsEdge(from, to), "Edge does not exist");
		
		return edges.getMember(new Edge(getVertex(from), getVertex(to)));
	}
	
	public void insertEdge(V from, V to, E data) throws
			NullPointerException,
			IllegalStateException, IllegalArgumentException, DuplicateElementException {
		checkEdgePreconditions(from, to);
		checkState(!containsEdge(from, to), "Edge does not exist");

		Vertex fromVertex = getVertex(from);
		Vertex toVertex = getVertex(to);
		Edge edge = new Edge(fromVertex, toVertex, data);
		edges.insert(edge);
		
		fromVertex.edgesIncidentFrom.insert(edge);
		toVertex.edgesIncidentTo.insert(edge);
	}
	
	public E removeEdge(V from, V to) throws
			NullPointerException,
			IllegalStateException{
		checkEdgePreconditions(from, to);
		checkState(containsEdge(from, to), "Edge does not exist");
		
		Vertex fromVertex = getVertex(from);
		Vertex toVertex = getVertex(to);
		Edge edge = new Edge(fromVertex, toVertex);
		E data = edges.remove(edge).data;
		
		fromVertex.edgesIncidentFrom.remove(edge);
		toVertex.edgesIncidentTo.remove(edge);
		
		return data;
	}
	
	private void checkEdgePreconditions (V from, V to) throws
			NullPointerException,
			IllegalStateException {
		checkNotNull(from, "from must not be null");
		checkNotNull(to, "to must not be null");
		checkState(containsVertex(from), "from vertex does not exist");
		checkState(containsVertex(to), "to vertex does not exist");
	}
}
