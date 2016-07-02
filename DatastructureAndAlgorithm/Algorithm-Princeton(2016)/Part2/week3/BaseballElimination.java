package week3;

import java.util.ArrayList;
import edu.princeton.cs.algs4.FlowEdge;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.In;

public class BaseballElimination {

    private final TeamTable tt;

    public BaseballElimination(String filename) {
        // create a baseball division from given filename in format specified below
        In file = new In(filename);
        String info = file.readLine();
        int teamNum = Integer.parseInt(info);
        tt = new TeamTable(teamNum);
        info = file.readLine();
        while (info != null) {
            tt.addTeam(info);
            info = file.readLine();
        }
        
        
    }
    
    private class TeamTable {
        
        private int[][] recordTable;
        private int teamNum;
        private ArrayList<String> team;
        TeamTable(int size) {
            recordTable = new int[size][];  // each row stores one team information
            team = new ArrayList<String>(); // match an index to team name
            teamNum = 0;
        }
        
        void addTeam(String str) {
            
            if (teamNum >= recordTable.length)
                throw new IndexOutOfBoundsException();
            String[] info = str.split(" ");
            info = removeSpaces(info);
            recordTable[teamNum] = new int[info.length - 1];    // the index of row implies a team
            for (int idx = 0; idx < info.length - 1; idx++) {
                recordTable[teamNum][idx] = Integer.parseInt(info[idx + 1]);
            }
            team.add(info[0]);  // convert team name to an integer
            teamNum++;
        }
        
        String[] removeSpaces(String[] arr) {
            StringBuffer teamInfo = new StringBuffer();
            for (int idx = 0; idx < arr.length; idx++) {
                if (!arr[idx].equals("")) teamInfo.append(arr[idx] + " ");
            }
            
            return teamInfo.toString().split(" ");
        }
        
        // return the row of the team name
        int[] getTeamInfo(String name) {
            if (!team.contains(name))
                throw new IllegalArgumentException();
            int idx = team.indexOf(name);
            return recordTable[idx];
        }
        
        // return the integer represented the team in 2d-array recordTable
        int getTeamIdx(String name) {
            if (!team.contains(name))
                throw new IllegalArgumentException();
            return team.indexOf(name);
        }
        
        // return the number of win which is on the column 0 in 2d-array recordTable
        // by name
        int getWin(String name) {
            return getTeamInfo(name)[0];
        }
        
        // return the number of win which is on the column 0 in 2d-array recordTable
        // by index
        int getWin(int idx) {
            return recordTable[idx][0];
        }
        
        // return the number of losses which is on the column 1 in 2d-array recordTable
        int getLosses(String name) {
            return getTeamInfo(name)[1];
        }
        
        // return the number of remaining which is on the column 2 in 2d-array recordTable
        int getRemaining(String name) {
            return getTeamInfo(name)[2];
        }
        
        // return the number of games to play between two team
        // by name
        int getAgainst(String t1, String t2) {
            if (!team.contains(t1) || !team.contains(t2))
                throw new IllegalArgumentException();
            int other = team.indexOf(t2);
            String mainTeam = t1;
            if (other < team.indexOf(t1)) {
                mainTeam = t2;
                other = team.indexOf(t1);
            }
            
            return getTeamInfo(mainTeam)[3 + other];
            
        }
        
        // return the number of games to play between two team
        // by index
        int getAgainst(int t1, int t2) {
            if (t1 > t2) {
                int temp = t1;
                t1 = t2;
                t2 = temp;
            }
            
            return recordTable[t1][3 + t2];
        }
    }
    
    public int numberOfTeams() {
        // number of teams
        return tt.teamNum;
    }
    
    public Iterable<String> teams() {
        // all teams
        return tt.team;
    }
    
    public int wins(String team) {
        // number of wins for given team
        return tt.getWin(team);
    }
    
    public int losses(String team) {
        // number of losses for given team
        return tt.getLosses(team);
    }
    
    public int remaining(String team) {
        // number of remaining games for given team
        return tt.getRemaining(team);
    }
    
    public int against(String team1, String team2) {
        // number of remaining games between team1 and team2
        return tt.getAgainst(team1, team2);
    }
    
    public boolean isEliminated(String team) {
        // is given team eliminated?

        System.out.println(team);
        Elimination elimitor = new Elimination(team);
        return elimitor.isEliminated();
    }
    
    
    private class Elimination {
        
        private ArrayList<String> mt = new ArrayList<String>();     // match each vertex to integer
        private FlowNetwork graph;
        private boolean isEliminated = false;
        private ArrayList<String> removedByTeams = new ArrayList<String>();
        private int fullflow = 0;   // outflow from source
        
        Elimination(String team) { 
            graph = buildFNW(team);
        }
        
        private FlowNetwork buildFNW(String name) {
            int targetTeam = tt.getTeamIdx(name);
            
            // the number of pairs between left teams
            int pairAmount = (tt.teamNum - 1) * (tt.teamNum - 2) / 2;   
            
            // the number of total vertices equals sum of start , end, the number of left teams
            // the number of possible pairs between left teams
            int vertexAmount = 2 + pairAmount + tt.teamNum - 1;          
                                                                            
            FlowNetwork fnw = new FlowNetwork(vertexAmount);
            // allocates a new index of each vertex on FlowNetwork graph
            final int start = 0;            
            mt.add("start");
            final int end = vertexAmount - 1;
            for (int idx = 0; idx < tt.teamNum; idx++) {
                if (idx == targetTeam) continue;
                mt.add(idx + "");
            }
            
            for (int t1 = 0; t1 < tt.teamNum; t1++) {
                if (t1 == targetTeam) continue;
                for (int t2 = t1 + 1; t2 < tt.teamNum; t2++) {
                    if (t2 == targetTeam) continue;
                    mt.add(t1 + " " + t2);
                    // path from start
                    FlowEdge edge = new FlowEdge(start, mt.indexOf(t1 + " " + t2), tt.getAgainst(t1, t2));
                    // compute the capacity of flow out from start
                    fullflow += tt.getAgainst(t1, t2);
                    fnw.addEdge(edge);
                    
                    // path to each team between pair vertex
                    edge = new FlowEdge(mt.indexOf(t1 + " " + t2), mt.indexOf(t1 + ""), Integer.MAX_VALUE);
                    fnw.addEdge(edge);
                    edge = new FlowEdge(mt.indexOf(t1 + " " + t2), mt.indexOf(t2 + ""), Integer.MAX_VALUE);
                    fnw.addEdge(edge);
                }
            }
            
            for (int idx = 0; idx < tt.teamNum; idx++) {
                if (idx == targetTeam) continue;
                
                // if max wins of target team less than the other team's existed wins
                // it's removed by this team
                if (wins(name) + remaining(name) - tt.getWin(idx) < 0) {
                    isEliminated = true;
                    // find out all the team can remove target team by this way
                    for (int temp = idx; temp < tt.teamNum; temp++) {
                        if (temp == targetTeam) continue;
                        else {
                            if (wins(name) + remaining(name) - tt.getWin(temp) < 0)
                                removedByTeams.add(tt.team.get(temp));
                        }
                    }
                    break;
                }
                // path to end with capacity equaling the wins resulting ties between the target team to each other team left
                FlowEdge edge = new FlowEdge(mt.indexOf(idx + ""), end, wins(name) + remaining(name) - tt.getWin(idx));
                fnw.addEdge(edge);
            } 
            
            return fnw;
        }
        
        boolean isEliminated() {
            if (isEliminated) return isEliminated; 
            int maxFlow = computeMaxFlow(0, graph.V() - 1);
            
            if (fullflow == maxFlow) isEliminated = false;
            else isEliminated = true;
            return isEliminated;
        }
        
        int computeMaxFlow(int start, int end) {
            int value = 0;
            FlowEdge[] edgeTo = new FlowEdge[graph.V()];
            while (hasAugmentingPath(edgeTo, start, end)) {
                
                double min = Double.MAX_VALUE;
                for (int ver = end; ver != start; ver = edgeTo[ver].other(ver)) {
                    FlowEdge edge = edgeTo[ver];
                    if (edge.residualCapacityTo(ver) < min) min = edge.residualCapacityTo(ver);
                }

                for (int ver = end; ver != start; ver = edgeTo[ver].other(ver)) {
                    FlowEdge edge = edgeTo[ver];
                    edge.addResidualFlowTo(ver, min);
                }
                
                value += min;
            }
            return value;
        }
        private boolean hasAugmentingPath(FlowEdge[] edgeTo, int s, int e) {
            boolean[] marked = new boolean[graph.V()];
            return dfs(s, e, marked, edgeTo);
            
        }
        
        private boolean dfs(int ver, int target, boolean[] marked, FlowEdge[] edgeTo) {
            if (ver == target) {
                return true;
            }
            marked[ver] = true;
            for (FlowEdge edge: graph.adj(ver)) {
                int other = edge.other(ver);
                if (edge.residualCapacityTo(other) > 0 && !marked[other] && dfs(other, target, marked, edgeTo)) {
                    edgeTo[other] = edge;
                    return true;
                }
            }
            return false;
        }
        
        private Iterable<String> minCut() {
            if (removedByTeams.size() != 0) return removedByTeams;
            boolean[] visited = new boolean[graph.V()];
            dfsMincut(0, visited);
            
            for (int teamIdx = 1; teamIdx < tt.teamNum; teamIdx++) {
                if (visited[teamIdx]) {
                    removedByTeams.add(tt.team.get(Integer.parseInt(mt.get(teamIdx))));
                }
            }
            
            return removedByTeams;
        }
        
        private void dfsMincut(int ver, boolean[] visited) {
            visited[ver] = true;
            for (FlowEdge next: graph.adj(ver)) {
                int other = next.other(ver);
                if (next.residualCapacityTo(other) > 0 && !visited[other]) {
                    dfsMincut(other, visited);
                }
            }
        }
        
    }

    public Iterable<String> certificateOfElimination(String team) {
        // subset R of teams that eliminates given team; null if not eliminated
        Elimination elimitor = new Elimination(team);
        if (elimitor.isEliminated()) 
            return elimitor.minCut();
        return null;
     
    }
    
    public static void main(String[] args) {

    }
}
