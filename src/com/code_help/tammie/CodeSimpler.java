package com.code_help.tammie;

import java.util.ArrayList;
import java.util.List;

public class CodeSimpler {

    public List<Integer> visited = new ArrayList<>();

    public boolean isConnectedWithChargingStations(Vertex startVertex, Vertex endVertex) {
        // Sanity check
        if (startVertex.getIndex() == endVertex.getIndex()) {
            return true;
        }

        ArrayList<Edge> neighbour = startVertex.getIncidentRoads();
        ArrayList<Vertex> paths = new ArrayList<Vertex>();
        ArrayList<Boolean> overall = new ArrayList<Boolean>();
        Vertex added;
        visited.add(startVertex.getIndex());

        for (int j = 0; j < neighbour.size(); j++) {
            if (neighbour.get(j).getFirstVertex().getIndex() != startVertex.getIndex()) {
                added = neighbour.get(j).getFirstVertex();
            } else {
                added = neighbour.get(j).getSecondVertex();
            }

            if (added.getIndex() == endVertex.getIndex()) {
                return true;
            } else if (added.hasChargingStation() && !visited.contains(added.getIndex())) {
                paths.add(added);
            }
            visited.add(added.getIndex());
        }

        if (paths.size() != 0) {
            for (int m1 = 0; m1 < paths.size(); m1++) {
                overall.add(DFS(paths.get(m1), endVertex));
            }
            return (overall.contains(true));
        } else {
            return false;
        }
    }

    public boolean DFS(Vertex startVertex, Vertex endVertex) {
        ArrayList<Edge> neighbour = startVertex.getIncidentRoads();
        ArrayList<Vertex> paths = new ArrayList<Vertex>();
        ArrayList<Boolean> overall = new ArrayList<Boolean>();
        Vertex added;

        for (int j = 0; j < neighbour.size(); j++) {
            if (neighbour.get(j).getFirstVertex().getIndex() != startVertex.getIndex()) {
                added = neighbour.get(j).getFirstVertex();
            } else {
                added = neighbour.get(j).getSecondVertex();
            }

            if (added.getIndex() == endVertex.getIndex()) {
                return true;
            } else if (added.hasChargingStation() && !visited.contains(added.getIndex())) {
                paths.add(added);
            }
            visited.add(added.getIndex());
        }

        if (paths.size() != 0) {
            for (int m2 = 0; m2 < paths.size(); m2++) {
                overall.add(DFS(paths.get(m2), endVertex));
            }
            return (overall.contains(true));
        } else {
            return false;
        }
    }

}

class Vertex {
    private ArrayList<Edge> incidentRoads;

    public int getIndex() {
        return 0;
    }

    public ArrayList<Edge> getIncidentRoads() {
        return incidentRoads;
    }

    public boolean hasChargingStation() {
        return false;
    }
}

class Edge {
    Vertex first;
    Vertex second;

    public Vertex getFirstVertex() {
        return first;
    }

    public Vertex getSecondVertex() {
        return second;
    }
}
