import java.lang.reflect.Array;


public class BinaryHeap<T extends Comparable<T>> {
	private Comparable[] a;
	private int Size,currentSize;
	private Class<T> type;
	
	public BinaryHeap(Class<T> type){
		this.Size=20;
		this.currentSize=0;
		this.type = type;
		this.a = (T[]) Array.newInstance(type,this.Size);
	}

	public T deleteMin() {
		T x = (T) this.a[0];
		return x;
		
	}

	public void insert(T i) {
		// TODO Auto-generated method stub
		if (this.currentSize+1>=this.a.length){
			this.a= doubleArray();
		}
	
		int hole = this.currentSize+1;
		
		for ( ; i.compareTo((T) this.a[hole])<0; hole=hole/2){
			this.a[hole] = this.a[hole/2];
		}
		this.a[hole]=i;	
		this.a[0] = null;
	}

	public void sort(String[] csLegends) {
		// TODO Auto-generated method stub
		
	}
	
	public T[] doubleArray(){
		T[] result = (T[]) Array.newInstance(this.type,this.Size*2);
		for (int i = 0; i<a.length; i++){
			result[i] = (T) this.a[i];
		}
		return result;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		for (int i = 0; i<this.a.length; i++){
			sb.append(this.a[i]);
			sb.append(", ");
		}
		
		sb.delete(sb.length()-2,sb.length()-1);
		sb.append(']');
		
		return sb.toString();
	}
	

}
