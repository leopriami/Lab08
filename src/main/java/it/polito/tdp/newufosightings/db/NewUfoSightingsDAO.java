package it.polito.tdp.newufosightings.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.newufosightings.model.Edge;
import it.polito.tdp.newufosightings.model.Event;
import it.polito.tdp.newufosightings.model.Event.EventType;
import it.polito.tdp.newufosightings.model.Sighting;
import it.polito.tdp.newufosightings.model.State;

public class NewUfoSightingsDAO {

	public List<Sighting> loadAllSightings(int anno, int giorni) {
		String sql = "select upper(s1.state) as st1, upper(s2.state) as st2, count(*) as peso " + 
				"from sighting as s1, sighting as s2, neighbor " + 
				"where s1.state = neighbor.state1 and s2.state = neighbor.state2 and s1.id < s2.id and s1.state < s2.state " + 
				"and year(s1.datetime) = ? and year(s2.datetime) = ? and ABS(DATEDIFF(s1.datetime, s2.datetime)) <= ? " + 
				"group by s1.state, s2.state " + 
				"order by s1.state, s2.state asc";
		List<Sighting> list = new ArrayList<>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);	
			st.setInt(1, anno);
			st.setInt(2, anno);
			st.setInt(3, giorni);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				list.add(new Sighting(res.getString("st1"), res.getString("st2"), res.getInt("peso")));
			}

			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}

		return list;
	}

	public List<String> loadAllStates() {
		String sql = "select distinct id from state order by id asc";
		List<String> result = new ArrayList<String>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				String state = rs.getString("id");
				result.add(state);
			}

			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}
	
	public List<Edge> loadAllEdges() {
		String sql = "SELECT state1, state2 FROM new_ufo_sightings.neighbor where state1 < state2 order by state1, state2 asc";
		List<Edge> result = new ArrayList<Edge>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Edge state = new Edge(rs.getString("state1"), rs.getString("state2"), 0);
				result.add(state);
			}

			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}
	
	public List<Event> loadAllAvvistamenti(int anno, int giorni) {
		String sql = "select distinct s1.id, s2.id, s1.datetime, s2.datetime, upper(s1.state) as st1, upper(s2.state) as st2 " + 
				"from sighting as s1, sighting as s2, neighbor " + 
				"where s1.state = neighbor.state1 and s2.state = neighbor.state2 and s1.id < s2.id and s1.state < s2.state " + 
				"and year(s1.datetime) = ? and year(s2.datetime) = ? and ABS(DATEDIFF(s1.datetime, s2.datetime)) <= ?";
		List<Event> list = new ArrayList<>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);	
			st.setInt(1, anno);
			st.setInt(2, anno);
			st.setInt(3, giorni);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				list.add(new Event(res.getDate("s1.datetime").toLocalDate(), EventType.AVVISTAMENTO, res.getString("st1")));
				list.add(new Event(res.getDate("s2.datetime").toLocalDate(), EventType.AVVISTAMENTO, res.getString("st2")));
			}

			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}

		return list;
	}
	
}
