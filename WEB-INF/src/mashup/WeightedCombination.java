package mashup;

public class WeightedCombination extends Combination implements Comparable {

	int weight;
	
	public WeightedCombination( Combination combination, int weight ) {
		super( combination.getCategory(), combination.getContent() );
		this.weight = weight; 
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compareTo(Object o) {
		WeightedCombination other = (WeightedCombination)o;
		
		return other.weight - weight;
	}
}
