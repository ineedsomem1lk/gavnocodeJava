package lab6;

import java.util.ArrayList;
import java.util.Scanner;

class Edge 
{
    int id1,id2,dest;
    Edge(int id1, int id2, int dest) 
    {
            this.id1 = id1;
            this.id2 = id2;
            this.dest = dest;
    }
    public String toString()
    {
    	return id1 + " " + id2 + " " + dest; 
    }
}

public class Graph<T> 
{
	static ArrayList<Node> graph = new ArrayList<>();
	static Scanner scan = new Scanner(System.in);
	
	static ArrayList<Edge> edges = new ArrayList<>();
	
	public static void connectNodes()
	{
		System.out.print("������ id,��� �'������ ����: \n");
		int n = scan.nextInt(), m = scan.nextInt(),dist = scan.nextInt();
		edges.add(new Edge(n,m,dist));
	}
	/*
	public static void printEdges()
	{
		for(int i=0;i<edges.size();i++)
		{
			System.out.print("����� " + i + ": " + edges.get(i) + '\n');
		}
	}*/
	
	
	public static void printGraph()
	{
		for(int i=0;i<graph.size();i++)
		{
			System.out.print("��'�: " + graph.get(i).getData() + " ID: " + graph.get(i).getId()+ '\n');
		}
	}
	
	public static void findNodeById()
	{
		boolean finded = false;
		System.out.print("������ ��'� ���� ��� ������: \n");
		int id = scan.nextInt();
		for(int i=0;i<graph.size();i++)
		{
			if(id == graph.get(i).getId())
			{
				System.out.print("������ ����: " + graph.get(i).getData() 
						+ "������ id: " + graph.get(i).getId() + "\n");
				finded = true;
			}
			
		}
		if(!finded) System.out.print("������ ���� �� ����((((( \n");
	}
	
	public static Node findNodeById(int id)
	{
		for(int i=0;i<graph.size();i++)
		{
			if(id == graph.get(i).getId())
			{
				return graph.get(i);
			}
			
		}
		return null;
	}
	public static int findNodeByName(String name)
	{
		for(int i=0;i<graph.size();i++)
		{
			if(name.equals(graph.get(i).getData()))
			{
				return graph.get(i).getId();
			}
		}
		return -1;
	}
	
	public static void findNodeByName()
	{
		boolean finded = false;
		System.out.print("������ ��'� ���� ��� ������: \n");
		String name = scan.next();
		for(int i=0;i<graph.size();i++)
		{
			if(name.equals(graph.get(i).getData()))
			{
				System.out.print("������ ����: " + graph.get(i).getData() 
						+ " ������ id: " + graph.get(i).getId() + "\n");
				finded = true;
			}
			
		}
		if(!finded) System.out.print("���� �� ���� \n");
	}
	
	public static String findShortestWay(String name1,String name2)
    {
        int n = findNodeByName(name1),m = findNodeByName(name2);
        ArrayList<Node> algDijkstra = new ArrayList<>();
        algDijkstra.add(new Node(findNodeById(n).getData(),n));
        algDijkstra.get(0).score=0; 
        
        for(int i=0;i<algDijkstra.size();i++)
        {
            for(int j=0;j<edges.size();j++)
            {
                if((edges.get(j).id1 == algDijkstra.get(i).getId()) ||
                        edges.get(j).id2 == algDijkstra.get(i).getId())
                {
                    int loopId = algDijkstra.get(i).getId(); 
                    if(loopId == edges.get(j).id1) loopId = edges.get(j).id2;
                    else loopId = edges.get(j).id1; 
                    
                    Node loop = new Node(findNodeById(loopId).getData(),loopId);
                    loop.score = -1;
                    
                    for(int g=0;g<algDijkstra.size();g++) //�������� �� ���� ��� �� ����
                    {
                        if(loop.Id == algDijkstra.get(g).Id)
                        {
                            loop.score = algDijkstra.get(g).score;
                        }
                    }
                    
                    if(loop.score == -1){ loop.score = Integer.MAX_VALUE; } //���� ���� �� �� ����, �� ��� ��������� �������� ��������
                    
                    if(algDijkstra.get(i).score + edges.get(j).dest < loop.score) //�� ����� ���� ������ ������� 
                    {
                        loop.score = algDijkstra.get(i).score + edges.get(j).dest;
                        algDijkstra.add(loop);
                    }
                }
            }
        }
        
        String result = "" + findNodeById(m).getData();
        for(int i=0;i<algDijkstra.size();i++)
        {
            if(m == algDijkstra.get(i).Id)
            {
                System.out.println("³������: " + algDijkstra.get(i).score);
            }
        }
        
        while(m != n) //����� ����� �����
        {
            for(int j=0;j<edges.size();j++)
            {
                if((edges.get(j).id1 == m) ||
                        edges.get(j).id2 == m)
                {
                    int loopId = edges.get(j).id1 + edges.get(j).id2 - m;
                    //M � ������� ���� ID �����������
                    Node loop = null,loopM = null;
                    for(int i =0;i<algDijkstra.size();i++)
                    {
                        if(loopId == algDijkstra.get(i).getId())
                        {
                            loop = algDijkstra.get(i);
                            break;
                        }
                    }//������ ���� ���� �� �����
                
                    for(int i =0;i<algDijkstra.size();i++)
                    {
                        if(m == algDijkstra.get(i).getId())
                        {
                            loopM = algDijkstra.get(i);
                            break;
                        }
                    }//������ ���� ����� �� �����
                    if(loop.score < loopM.score)
                    {
                        if(loop.score == loopM.score - edges.get(j).dest)
                        {
                            m = loop.Id;
                            result = loop.getData() + " --> " + result;
                        }
                    }
                }
            }
        }
        System.out.print("�������� ����: " + result + "\n");
        return result;
    }
	
	public static String findChildNode(int id)
	{
		
		String result = "";
		for(int i=0;i < edges.size();i++)
		{
			if(id == edges.get(i).id1)
			{
				if(id < edges.get(i).id2)
				{
					result += edges.get(i).id2 + " ";
				}
			}
		}
		System.out.println("������� ����: " + result);
		return result;
	}
	
	public static void main(String args[])
	{
		
		Node<String> node = new Node<String>("A",1);
		Node<String> node2 = new Node<String>("B",2);
		Node<String> node3 = new Node<String>("C",3);
		Node<String> node4 = new Node<String>("D",4);
		Node<String> node5 = new Node<String>("E",5);
		Node<String> node6 = new Node<String>("F",6);
		Node<String> node7 = new Node<String>("G",7);
		
		edges.add(new Edge(1,2,1));
		edges.add(new Edge(2,3,1));
		edges.add(new Edge(2,4,1));
		edges.add(new Edge(2,5,1));
		edges.add(new Edge(2,6,1));
		edges.add(new Edge(3,7,1));
		edges.add(new Edge(5,6,1));
		edges.add(new Edge(6,7,1));
		
		graph.add(node);
		graph.add(node2);
		graph.add(node3);
		graph.add(node4);
		graph.add(node5);
		graph.add(node6);
		graph.add(node7);
		
		while(true)
		{
		System.out.print("������� �������� :\n1 = ���� ���� �� id Id\n"
				+"2 = ����� ���� �� ����\n"
				+ "3 = ������� ��������� ����\n"
				+ "4 = �'������ ���� \n"
				+ "5 = ������ �������� ���� \n"
				+ "6 = ������ ������� ���� \n"
				+ "7 = ������ ����� \n");
		int op = scan.nextInt();
			switch(op) 
			{
				case 1:
					findNodeById();break;
				case 2:
					findNodeByName();break;
				case 3:
					printGraph();break;
				case 4:
					connectNodes();break;
				case 5:
					System.out.print("������ ��'� ��� ��� ������ ��������� �����: \n");
					String name1 = scan.next(),name2 = scan.next();
					findShortestWay(name1,name2);break;
				case 6:
					System.out.println("������ id ����,����� ������� ������ ��������(��): ");
					int id = scan.nextInt();
					findChildNode(id);break;
				case 7:
					return;
			}
		}
	}
}
