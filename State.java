import java.util.*;

public class State implements Comparable<State>
{

	private ArrayList<Integer> left;
	private ArrayList<Integer> right;

	private int f, h, g;
	private State father;
	private int totalTime;
	private int depth;
	
	//constructor
	public State(int[] times) 
	{	
		depth = 0;
		this.f = 0;
		this.h = 0;
		this.g = 0;
		this.father = null;
		this.totalTime = 0;
		right = new ArrayList<>();
		left = new ArrayList<>();
		for (int i=0; i<times.length ; i+=1){
			right.add(times[i]);
		}
	}
	// copy constructor
	public State(State s)
	{
		this.f = s.f;
		this.h = s.h;
		this.g = s.g;
		this.depth = s.depth;
		this.father = s.father;
		this.totalTime = s.totalTime;

		this.right = new ArrayList<>();
		for (int i=0; i<s.right.size() ; i+=1){
			right.add(s.right.get(i));
		}

		this.left = new ArrayList<>();
		for (int i=0; i<s.left.size() ; i+=1){
			left.add(s.left.get(i));
		}
	}
	
	public int getF() 
	{
		return this.f;
	}
	
	public int getG() 
	{
		return this.g;
	}
	
	public int getH() 
	{
		return this.h;
	}
	
	public State getFather()
	{
		return this.father;
	}
	
	public void setF(int f)
	{
		this.f = f;
	}
	
	public void setG(int g)
	{
		this.g = g;
	}
	
	public void setH(int h)
	{
		this.h = h;
	}
	
	public void setFather(State f)
	{
		this.father = f;
	}
	
	public int getTotalTime() 
	{
		return this.totalTime;
	}
	
	public void setTotalTime(int time)
	{
		this.totalTime = time;
	}
	
	public void evaluate() 
	{
		setG(totalTime);
		if(!right.isEmpty()){
			setH(Collections.max(right) + Collections.min(left));
		}else{
			setH(0);
		}
		setF(getG() + getH());
	}
	
	public void print() {

		if (depth%2==0){
			System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n\n");
		
		}else{
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n\n");
		}
		//left side
		for (int i=0;i<this.left.size();i+=1){
			System.out.print(this.left.get(i)+" ");
		}

		//the bridge
		System.out.print("_______________________ ");

		//right side
		for (int i=0;i<this.right.size();i+=1){
			System.out.print(this.right.get(i)+" ");
		}
		if (depth%2==0){
			System.out.println("\n\n\n<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n\n\n");
		}else{
			System.out.println("\n\n\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n\n\n");
		}
	}
	
	public ArrayList<State> getChildren() {
		ArrayList<State> children = new ArrayList<>(); 
		State child;
        if(depth%2==0){

			for (int i=0; i<this.right.size()-1 ; i+=1){
				for (int j=i+1 ; j<this.right.size(); j+=1){
					
					child = new State(this);

					child.left.add(this.right.get(i));
					child.left.add(this.right.get(j));

					child.right.remove(Integer.valueOf(this.right.get(i)));
					child.right.remove(Integer.valueOf(this.right.get(j)));

					child.setFather(this);
					child.depth = this.depth + 1;

					int time = this.getTotalTime() + Math.max(this.right.get(i),this.right.get(j));
					child.setTotalTime(time);
					child.evaluate();

					children.add(child);

				}
			}
		}
		else{
			if (!isFinal()){
				child = new State(this);

				int min = Collections.min(this.left);
				int min_index = left.indexOf(min);

				child.right.add(this.left.get(min_index));
				child.left.remove(Integer.valueOf(this.left.get(min_index)));

				child.setFather(this);
				child.depth = this.depth + 1;

				int time = this.getTotalTime() + this.left.get(min_index);
				child.setTotalTime(time);

				children.add(child);
			}else{
				System.out.println("Finished");
			}
		}
        return children;
	}
	
	public boolean isFinal() {return right.isEmpty();}
	
	@Override
	public boolean equals(Object obj) {return true;}
	
	@Override
    public int hashCode() {return 0;}
	
	@Override
    public int compareTo(State s)
    {
        return Double.compare(this.f, s.getF()); // compare based on the heuristic score.
    }
}