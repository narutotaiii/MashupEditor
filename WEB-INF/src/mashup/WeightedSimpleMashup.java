package mashup;

public class WeightedSimpleMashup implements Comparable {

	private double weight;
	private SimpleMashup simpleMashup; 
	
	public WeightedSimpleMashup( double weight, SimpleMashup simpleMashup ) {
		this.weight = weight;
		this.simpleMashup = simpleMashup;
	}
	public double getWeight() {
		return weight;
	}
	
	SimpleMashup getMashup() {
		return simpleMashup;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public int compareTo(Object arg0) {
		WeightedSimpleMashup rhs = (WeightedSimpleMashup)arg0;
		// TODO Auto-generated method stub
		if( this.weight < rhs.weight ) 
			return 1;
		else if( rhs.weight < this.weight )
			return -1;
		
		return 0;
	}
}
